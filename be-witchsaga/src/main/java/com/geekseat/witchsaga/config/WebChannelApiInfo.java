package com.geekseat.witchsaga.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "api-infos.web-channel")
public class WebChannelApiInfo {

    private String apiVersion;
    private String title;
    private String domain;
    private String description;
    private String group;
    private String contact;

}