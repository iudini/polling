package com.github.iudini.polling.controller;

import com.github.iudini.polling.domain.Question;
import com.github.iudini.polling.service.QuestionService;
import com.github.iudini.polling.validation.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@Validated
public class QuestionController {
    private final QuestionService service;

    @GetMapping("/{pollId}")
    public Collection<Question> findAllByPollId(@PathVariable Long pollId) {
        return service.findAllByPollId(pollId);
    }

    @PostMapping("/{pollId}")
    public ResponseEntity<Question> create(@RequestBody Question question,
                                           @PathVariable Long pollId) {
        return new ResponseEntity<>(
                service.saveOrUpdate(question, pollId).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                ),
                HttpStatus.CREATED);
    }

    @PutMapping("/{pollId}")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Question> update(@Valid @RequestBody Question question,
                                           @PathVariable Long pollId) {
        return new ResponseEntity<>(
                service.saveOrUpdate(question, pollId).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                ),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
