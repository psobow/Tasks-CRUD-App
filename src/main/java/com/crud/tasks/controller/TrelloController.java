package com.crud.tasks.controller;


import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.dto.CreatedTrelloCard;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {
    private final TrelloService trelloService;

    @GetMapping
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }

    @PostMapping
    public CreatedTrelloCard createTrelloCard(@RequestBody final TrelloCardDto trelloCardDto){
        return trelloService.createdTrelloCard(trelloCardDto);
    }
}