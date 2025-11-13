package ma.project.benchmarkR;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class ConfigTest {
    @Value("${spring.datasource.url:NOT_FOUND}")
    private String url;

    @PostConstruct
    public void init() {
        System.out.println(">>> Loaded spring.datasource.url = " + url);
    }
}
