package com.github.iudini.polling.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Integer displayOrder;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Poll poll;
}
