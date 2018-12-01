package com.crud.tasks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@AllArgsConstructor // ten konstruktor jest nie potrzebny!
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloListDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("closed")
    private boolean isClosed;

    @Override
    public String toString(){
        return "List name: " + name + " ID: " + id;
    }
}
