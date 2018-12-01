package com.crud.tasks.controller;


import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.dto.CreatedTrelloCard;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @GetMapping
    public List<TrelloBoardDto> getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        //trelloBoards.forEach(System.out::println);
        trelloBoards.stream()
                .filter(trelloBoardDto -> Objects.nonNull(trelloBoardDto.getId()))
                .filter(trelloBoardDto -> trelloBoardDto.getName() != null)
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .forEach(System.out::println);

        return trelloBoards;
    }

    @PostMapping
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloClient.createNewCard(trelloCardDto);
    }
}