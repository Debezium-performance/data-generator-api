package io.debezium.datageneratorapi;

import org.springframework.beans.factory.annotation.Value;

public abstract class GeneratorRestController {
    @Value("${dmt.endpoint}")
    protected String endpoint;
}
