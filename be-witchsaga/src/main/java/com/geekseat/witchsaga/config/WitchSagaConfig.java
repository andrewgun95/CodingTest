package com.geekseat.witchsaga.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({WebChannelApiInfo.class})
public class WitchSagaConfig {

    private final WebChannelApiInfo webChannelApiInfo;

    public WitchSagaConfig(WebChannelApiInfo webChannelApiInfo) {
        this.webChannelApiInfo = webChannelApiInfo;
    }

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .addServersItem(host())
                .info(metadata());
    }

    private Info metadata() {
        return new Info()
                .title(webChannelApiInfo.getTitle())
                .description(webChannelApiInfo.getDescription())
                .version(webChannelApiInfo.getApiVersion())
                .contact(new Contact().name(webChannelApiInfo.getContact()));
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            prefix = "api-infos.web-channel",
            value = {"domain"}
    )
    public Server host() {
        return new Server()
                .url(webChannelApiInfo.getDomain())
                .description(webChannelApiInfo.getDescription());
    }


}
