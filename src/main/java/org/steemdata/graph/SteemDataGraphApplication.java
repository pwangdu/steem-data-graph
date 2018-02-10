package org.steemdata.graph;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@Slf4j
@EnableNeo4jRepositories
@EnableSpringDataWebSupport
public class SteemDataGraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(SteemDataGraphApplication.class, args);
    }
}
