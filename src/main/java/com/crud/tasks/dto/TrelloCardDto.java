package com.crud.tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@AllArgsConstructor // ten konstruktor jest nie potrzebny!
@Getter
public class TrelloCardDto {
    private String name;
    private String description;
    private String pos;
    private String listId;
}
