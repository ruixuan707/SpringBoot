package com.atc.oa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * AtcOaApplication - System entry
 *
 * @author Lijin
 * @version 1.0.0
 */
@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
public class AtcOaApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AtcOaApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\tEnvironment '{}' is running! Access URLs:\n\tLocal: \t\thttp://localhost:{}\n\t\n----------------------------------------------------------", env.getProperty("spring.application.name"), env.getProperty("server.port"));
    }
}
