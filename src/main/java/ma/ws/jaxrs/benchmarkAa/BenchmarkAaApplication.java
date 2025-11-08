package ma.ws.jaxrs.benchmarkAa;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class BenchmarkAaApplication extends ResourceConfig {
    public BenchmarkAaApplication() {
        packages("ma.ws.jaxrs.benchmarkAa.controllers");
    }
}
