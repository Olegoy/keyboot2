package com.example.keyboot2.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.representations.adapters.config.AdapterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

/**
 * Separate this to avoid cyclic dependency
 */
@Component
public class KeycloakResolverConfig {


    @Bean
    public KeycloakConfigResolver fileKeycloakConfigResolver() {
        return new KeycloakConfigResolver() {
            @SneakyThrows
            @Override
            public KeycloakDeployment resolve(HttpFacade.Request request) {
                ClassPathResource classPathResource = new ClassPathResource("./keycloak.json");
                AdapterConfig adapterConfig = new ObjectMapper().readValue(classPathResource.getFile(),
                    AdapterConfig.class);

                return KeycloakDeploymentBuilder.build(adapterConfig);
            }
        };
    }
}