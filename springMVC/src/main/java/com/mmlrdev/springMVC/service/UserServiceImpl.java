package com.mmlrdev.springMVC.service;

import com.mmlrdev.springMVC.models.User;
import com.mmlrdev.springMVC.service.configs.UserApiConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private RestTemplate restTemplate;
    private UserApiConfig config;

    @Autowired
    public UserServiceImpl(@Qualifier("configRestTemplateUserApiConfig") RestTemplate restTemplate, UserApiConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @Override
    public List<User> findAll() {

        try {
            UriComponents uri = UriComponentsBuilder.newInstance()
                    .scheme(config.getSchema())
                    .host(config.getHostname())
                    .path("users")
                    .build();

            HttpEntity httpEntity = HttpEntity.EMPTY;

            ResponseEntity<List<User>> response = restTemplate.exchange(
                    uri.toUriString(),
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<User>>() {
                    });

            if (response.getStatusCode().equals(HttpStatus.NO_CONTENT))
                return null;

            return response.getBody();
        } catch (RestClientException e) {
            log.error("Erro ao tentar obter dados em " + config.getHostname());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
