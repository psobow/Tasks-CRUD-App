package com.crud.tasks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBoardDto {
    private String id;
    private String name;
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

    private String getEveryListToStringVersion2(){
        return lists.stream()
                .map(TrelloListDto::toString)
                .collect(Collectors.joining("\n"));
     }
}
