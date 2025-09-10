package com.meditrack.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "secrets")
@Data
@Component
public class MeditrackAppParameters {

    private String accountsKey;

}
