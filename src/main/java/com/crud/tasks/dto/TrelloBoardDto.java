package com.crud.tasks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBoardDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("lists")
    private List<TrelloListDto> lists;
    
    
    @Override
    public String toString(){
        return "Board name: " + name + " ID: " + id +"\n"
                +"      lists inside:\n" + getEveryListToString();
    }

    private String getEveryListToString(){
        String everyList = "";
        for (TrelloListDto list : this.lists) {
            everyList += "           " + list + "\n";
        }
        return everyList;
    }
}
