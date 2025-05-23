package com.bwardweb.spring6_webclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public ReactiveOAuth2AuthorizedClientManager auth2AuthorizedClientManager(
            ReactiveClientRegistrationRepository reactiveClientRegistrationRepository,
            ReactiveOAuth2AuthorizedClientService reactiveOAuth2AuthorizedClientService
    ){
        ReactiveOAuth2AuthorizedClientProvider auth2AuthorizedClientProvider =
                ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager
                = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
                        reactiveClientRegistrationRepository, reactiveOAuth2AuthorizedClientService);

        authorizedClientManager.setAuthorizedClientProvider(auth2AuthorizedClientProvider);
        return authorizedClientManager;
    }
}
