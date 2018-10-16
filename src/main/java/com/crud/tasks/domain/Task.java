package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // powinno być final??

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String content;

    public Task(String name, String content) {
        this.title = name;
        this.content = content;
    }
}
