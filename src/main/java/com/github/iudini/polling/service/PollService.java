package com.github.iudini.polling.service;

import com.github.iudini.polling.domain.Poll;
import com.github.iudini.polling.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PollService {
    private final PollRepository repository;

    public Page<Poll> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Poll saveOrUpdate(Poll poll) {
        return repository.save(poll);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Poll> findById(Long pollId) {
        return repository.findById(pollId);
    }
}
