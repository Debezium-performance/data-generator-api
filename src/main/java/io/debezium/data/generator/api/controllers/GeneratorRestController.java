package io.debezium.data.generator.api.controllers;

import io.debezium.data.generator.api.SupportedGenerators;
import io.debezium.performance.load.data.builder.AddressDataBuilder;
import io.debezium.performance.load.data.builder.AviationDataBuilder;
import io.debezium.performance.load.data.builder.BeerDataBuilder;
import io.debezium.performance.load.data.builder.DataBuilder;
import io.debezium.performance.load.data.builder.FoodDataBuilder;
import io.debezium.performance.load.data.builder.PersonDataBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

public abstract class GeneratorRestController {
    @Value("${dmt.endpoint}")
    protected String endpoint;

    Random random = new Random();

    protected DataBuilder randomGenerator() {
        int x = random.nextInt(SupportedGenerators.class.getEnumConstants().length);
        SupportedGenerators selectedGenerator = SupportedGenerators.class.getEnumConstants()[x];
        DataBuilder selectedDataBuilder = null;
        switch (selectedGenerator) {
            case ADDRESS -> selectedDataBuilder = new AddressDataBuilder();
            case AVIATION -> selectedDataBuilder = new AviationDataBuilder();
            case BEER -> selectedDataBuilder = new BeerDataBuilder();
            case FOOD -> selectedDataBuilder = new FoodDataBuilder();
            case PERSON -> selectedDataBuilder = new PersonDataBuilder();
        }
        return selectedDataBuilder;
    }
}
