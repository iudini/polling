package com.github.iudini.polling.repository;

import com.github.iudini.polling.domain.Poll;
import com.github.iudini.polling.domain.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    Collection<Question> findAllByPollOrderByDisplayOrder(Poll poll);
}
