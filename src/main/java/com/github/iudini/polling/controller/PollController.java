package com.github.iudini.polling.controller;

import com.github.iudini.polling.domain.Poll;
import com.github.iudini.polling.service.PollService;
import com.github.iudini.polling.validation.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/polls")
@RequiredArgsConstructor
@Validated
public class PollController {
    private final PollService service;
    @Value("${poll.page-size}")
    private Integer size;

    @GetMapping("/")
    public Page<Poll> findAll(@RequestParam(required = false) Optional<Integer> page,
                              @RequestParam(required = false) Optional<String> sortBy) {
        return service.findAll(PageRequest.of(
                page.orElse(0),
                size,
                Sort.Direction.ASC,
                sortBy.orElse("id")
        ));
    }

    @PostMapping("/")
    public ResponseEntity<Poll> create(@Valid @RequestBody Poll poll) {
        return new ResponseEntity<>(service.saveOrUpdate(poll), HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Poll> update(@Valid @RequestBody Poll poll) {
        return new ResponseEntity<>(service.saveOrUpdate(poll), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
