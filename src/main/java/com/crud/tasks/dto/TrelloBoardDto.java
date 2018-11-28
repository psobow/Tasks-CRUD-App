package com.crud.tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrelloBoardDto {
    private String name;
    private String id;

    @Override
    public String toString(){
        return "Board name: " + name + "\nID: " + id;
    }
}
