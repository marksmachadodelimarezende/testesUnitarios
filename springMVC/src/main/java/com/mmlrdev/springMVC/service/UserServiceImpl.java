package com.mmlrdev.springMVC.service;

import com.mmlrdev.springMVC.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
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
    public static final String HOSTNAME = "jsonplaceholder.typicode.com";

    @Override
    public List<User> findAll() {

        try {
            UriComponents uri = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host(HOSTNAME)
                    .path("users")
                    .build();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<User>> response = restTemplate.exchange(
                    uri.toUriString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<User>>() {
                    });

            if (response.getStatusCode().equals(HttpStatus.NO_CONTENT))
                return null;

            return response.getBody();
        } catch (RestClientException e) {
            log.error("Erro ao tentar obter dados em " + HOSTNAME);
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
