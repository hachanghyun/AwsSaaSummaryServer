package com.hotong.awsSaaSummaryServer.chatGpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Topic과 Subtopic 간의 일대다 관계 정의
    //@OneToMany(mappedBy = "topic")
   // private List<Subtopic> subtopics;

    // Getter, Setter, Constructors
}

