package com.TCP.messaging.dto;

import java.sql.Timestamp;

public class MessageResponseDTO {

    private String fromUserName;
    private Long channelName;
    private String content;
    private String timestamp;


    public MessageResponseDTO() {
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getChannelName() {
        return channelName;
    }

    public void setChannelName(Long channelName) {
        this.channelName = channelName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
