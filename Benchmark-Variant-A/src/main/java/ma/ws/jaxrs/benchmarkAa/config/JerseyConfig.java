package ma.ws.jaxrs.benchmarkAa.config;

import ma.ws.jaxrs.benchmarkAa.controllers.CategoryController;
import ma.ws.jaxrs.benchmarkAa.controllers.ItemController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        // Register your JAX-RS (Jersey) controllers here
        register(CategoryController.class);
        register(ItemController.class);
        
        // You could also have Jersey scan the whole package:
        // packages("ma.ws.jaxrs.benchmarkAa.controllers");
    }
}