package com.github.iudini.polling.service;

import com.github.iudini.polling.domain.Poll;
import com.github.iudini.polling.domain.Question;
import com.github.iudini.polling.repository.PollRepository;
import com.github.iudini.polling.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final PollRepository pollRepository;

    public Optional<Question> saveOrUpdate(Question question, Long pollId) {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if (poll.isPresent()) {
            question.setPoll(poll.get());
            return Optional.of(questionRepository.save(question));
        }
        return Optional.empty();
    }

    public Collection<Question> findAllByPollId(Long id) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isPresent()) {
            return questionRepository.findAllByPollOrderByDisplayOrder(poll.get());
        }
        return null;
    }

    public void delete(Long id) {
        questionRepository.deleteById(id);
    }
}
