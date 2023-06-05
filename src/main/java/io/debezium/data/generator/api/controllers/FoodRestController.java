package io.debezium.data.generator.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.debezium.performance.load.data.builder.FoodDataBuilder;
import io.debezium.performance.load.data.builder.RequestBuilder;
import io.debezium.performance.load.graph.GraphVisualisation;
import io.debezium.performance.load.scenarios.ScenarioRequestExecutor;
import io.debezium.performance.load.scenarios.builder.ConstantScenarioBuilder;
import io.debezium.performance.load.scenarios.builder.LinearScenarioBuilder;
import io.debezium.performance.load.scenarios.builder.PeakScenarioBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;


// Food controller generates tables with different column count
@RestController
@RequestMapping("/food")
public class FoodRestController extends GeneratorRestController {
    @RequestMapping(
            value = "/constant",
            params = {"requestCount", "maxRowCount", "rate", "rounds"},
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    @ResponseBody
    public ResponseEntity<byte[]> prepareConstantLoad(
            @RequestParam("requestCount") int requestCount,
            @RequestParam("maxRowCount") int maxRowCount,
            @RequestParam("rate") int rate,
            @RequestParam("rounds") int rounds) throws MalformedURLException, JsonProcessingException {
        RequestBuilder<ConstantScenarioBuilder> requestBuilder
                = new RequestBuilder<>(new FoodDataBuilder(), new ConstantScenarioBuilder(rounds, rate));

        ScenarioRequestExecutor ex = requestBuilder
                .setEndpoint(super.endpoint)
                .setMaxRows(maxRowCount)
                .setRequestCount(requestCount)
                .buildScenario(rate);
        String graph = GraphVisualisation.drawGraph(ex);

        CompletableFuture.runAsync(ex);

        return ResponseEntity.ok(graph.getBytes(StandardCharsets.UTF_8));
    }

    @RequestMapping(
            value = "/linear",
            params = {"requestCount", "maxRowCount", "rate", "delta"},
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    @ResponseBody
    public ResponseEntity<byte[]> prepareLinearLoad(
            @RequestParam("requestCount") int requestCount,
            @RequestParam("maxRowCount") int maxRowCount,
            @RequestParam("rate") int rate,
            @RequestParam("delta") int delta) throws MalformedURLException, JsonProcessingException {

        RequestBuilder<LinearScenarioBuilder> requestBuilder
                = new RequestBuilder<>(new FoodDataBuilder(), new LinearScenarioBuilder(delta, rate));

        ScenarioRequestExecutor ex = requestBuilder
                .setEndpoint(super.endpoint)
                .setMaxRows(maxRowCount)
                .setRequestCount(requestCount)
                .buildScenario(rate);
        String graph = GraphVisualisation.drawGraph(ex);

        CompletableFuture.runAsync(ex);

        return ResponseEntity.ok(graph.getBytes(StandardCharsets.UTF_8));
    }


    @RequestMapping(
            value = "/peak",
            params = {"requestCount", "maxRowCount", "rate", "peakLevel", "peakRounds", "quietRounds"},
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    @ResponseBody
    public ResponseEntity<byte[]> preparePeakLoad(
            @RequestParam("requestCount") int requestCount,
            @RequestParam("maxRowCount") int maxRowCount,
            @RequestParam("rate") int rate,
            @RequestParam("peakLevel") int peakLevel,
            @RequestParam("peakRounds") int peakRounds,
            @RequestParam("quietRounds") int quietRounds) throws MalformedURLException, JsonProcessingException {

        RequestBuilder<PeakScenarioBuilder> requestBuilder
                = new RequestBuilder<>(new FoodDataBuilder(), new PeakScenarioBuilder(peakLevel, peakRounds, rate, quietRounds));

        ScenarioRequestExecutor ex = requestBuilder
                .setEndpoint(super.endpoint)
                .setMaxRows(maxRowCount)
                .setRequestCount(requestCount)
                .buildScenario(rate);
        String graph = GraphVisualisation.drawGraph(ex);

        CompletableFuture.runAsync(ex);
        return ResponseEntity.ok(graph.getBytes(StandardCharsets.UTF_8));
    }
}
