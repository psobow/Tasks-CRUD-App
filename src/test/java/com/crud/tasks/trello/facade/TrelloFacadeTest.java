package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {
    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("id", "name", false));

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("id", "name", trelloListsDto));

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("id", "name", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("id", "name", trelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardsDto);
        when(trelloMapper.mapToBoards(trelloBoardsDto)).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(trelloBoards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("id", "name", false));

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("id", "name", trelloListsDto));

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("id", "name", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("id", "name", trelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardsDto);
        when(trelloMapper.mapToBoards(trelloBoardsDto)).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoardsDto);
        when(trelloValidator.validateTrelloBoards(trelloBoards)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("id", trelloBoardDto.getId());
            assertEquals("name", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("id", trelloListDto.getId());
                assertEquals("name", trelloListDto.getName());
                assertEquals(false, trelloListDto.getClosed());
            });
        });
    }
}