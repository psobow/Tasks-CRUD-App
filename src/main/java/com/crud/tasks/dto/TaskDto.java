package com.crud.tasks.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @Id
    private Long id;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String content;
}
