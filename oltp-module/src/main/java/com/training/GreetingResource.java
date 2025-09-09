package com.training;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;

import org.jboss.logging.Logger;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class);
    private final LongCounter counter;

    public GreetingResource(Meter meter) {
        counter = meter.counterBuilder("greeting-metrics")
            .setDescription("Some description")
            .setUnit("invocations")
            .build();
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOG.info("hello: OLTP");
        counter.add(1);
        return "Hello from Quarkus REST";
    }
}
