package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {

    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardsDto) {
        return trelloBoardsDto.stream()
                .map(trelloBoardDto -> new TrelloBoard(trelloBoardDto.getId(), trelloBoardDto.getName(), mapToLists(trelloBoardDto.getLists())))
                .collect(Collectors.toList());

        // co zrobić kiedy lista jest pusta? null pointer exception wyskoczy po wykonaniu mapToLists(trelloBoardDto.getLists())
    }

    public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(trelloBoard -> new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(), mapToListsDto(trelloBoard.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloList> mapToLists(final List<TrelloListDto> trelloListsDto) {
        List<TrelloList> result = new ArrayList<>();

        if (trelloListsDto != null) {
            result = trelloListsDto.stream()
                    .map(trelloList -> new TrelloList(trelloList.getId(), trelloList.getName(), trelloList.getClosed()))
                    .collect(Collectors.toList());
        }
        return result;
    }

    public List<TrelloListDto> mapToListsDto(final List<TrelloList> trelloLists) {
        List<TrelloListDto> result = new ArrayList<>();

        if(trelloLists != null) {
            result = trelloLists.stream()
                    .map(trelloList -> new TrelloListDto(trelloList.getId(), trelloList.getName(), trelloList.getClosed()))
                    .collect(Collectors.toList());
        }
        return result;
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(),
                trelloCard.getPos(), trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto){
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(),
                trelloCardDto.getPos(), trelloCardDto.getListId());
    }
}
