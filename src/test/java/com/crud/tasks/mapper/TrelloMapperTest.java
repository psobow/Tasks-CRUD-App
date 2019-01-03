package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {
    @Autowired
    private TrelloMapper trelloMapper;



    @Test
    public void mapToBoardsWithEmptyObject() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        TrelloBoard result = trelloBoards.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException());

        Assert.assertEquals("TrelloBoard", result.getClass().getSimpleName());
    }

    @Test
    public void mapToBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("id", "name", true);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("id", "name", trelloListsDto);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        TrelloBoard result = trelloBoards.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException());

        Assert.assertEquals("TrelloBoard", result.getClass().getSimpleName());
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("id", "name", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("id", "name", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        TrelloBoardDto result = trelloBoardsDto.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException());

        Assert.assertEquals("TrelloBoardDto", result.getClass().getSimpleName());
    }

    @Test
    public void mapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("id", "name", true);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToLists(trelloListsDto);

        //Then
        TrelloList result = trelloLists.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException());

        Assert.assertEquals("TrelloList", result.getClass().getSimpleName());
    }

    @Test
    public void mapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("id", "name", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListsDto(trelloLists);

        //Then
        TrelloListDto result = trelloListsDto.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException());

        Assert.assertEquals("TrelloListDto", result.getClass().getSimpleName());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listId");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("TrelloCardDto", trelloCardDto.getClass().getSimpleName());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "listId");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals("TrelloCard", trelloCard.getClass().getSimpleName());
    }
}