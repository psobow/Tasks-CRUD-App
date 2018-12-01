package com.crud.tasks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Badges {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private AttachmentsByType attachmentsByType;
}
