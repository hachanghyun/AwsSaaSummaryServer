package com.hotong.awsSaaSummaryServer.chatGpt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotRequest implements Serializable {
    private String message;
}



