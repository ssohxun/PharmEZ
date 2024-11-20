package com.PharmEZ.PharmEZback.config.property;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "db")
public class DataSourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int initialSize;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;
    private long maxWaitMillis;
    private String validationQuery;
    private boolean testOnBorrow;

}