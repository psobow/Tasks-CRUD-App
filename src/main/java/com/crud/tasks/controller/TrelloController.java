package com.crud.tasks.controller;


import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.dto.CreatedTrelloCard;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @GetMapping
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    @PostMapping
    public CreatedTrelloCard createdTrelloCard(@RequestBody final TrelloCardDto trelloCardDto){
        return trelloClient.createNewCard(trelloCardDto);
    }
}