package com.crud.tasks.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.dto.CreatedTrelloCard;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class TrelloClient {
    @Autowired
    private TrelloConfig trelloConfig;
    @Autowired
    private RestTemplate restTemplate;


    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = UriComponentsBuilder
                .fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloLogin() + "/boards")
                .queryParam("key",     trelloConfig.getTrelloAppKey())
                .queryParam("token",   trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists",  "all")
                .build().encode().toUri();

        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));

        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }


    public CreatedTrelloCard createNewCard(final TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key",    trelloConfig.getTrelloAppKey())
                .queryParam("token",  trelloConfig.getTrelloToken())
                .queryParam("name",   trelloCardDto.getName())
                .queryParam("desc",   trelloCardDto.getDescription())
                .queryParam("pos",    trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build().encode().toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }
}
