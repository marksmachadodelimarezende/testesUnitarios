package com.mmlrdev.springMVC.service;

import com.mmlrdev.springMVC.models.User;
import com.mmlrdev.springMVC.service.configs.UserApiConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static com.mmlrdev.springMVC.mocks.MocksObjects.getUserMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTests {

    private UserService service;
    private RestTemplate template;
    private UserApiConfig config;

    @BeforeEach
    public void prepare() {
        config = mock(UserApiConfig.class);
        template = mock(RestTemplate.class);
        when(config.getSchema()).thenReturn("https");
        when(config.getHostname()).thenReturn("hostname.mock");
        service = new UserServiceImpl(template, config);
    }

    @Test
    public void deveRetornarLista_QuandoBuscarFindAll() {
        ResponseEntity<List<User>> expected = ResponseEntity.ok(Collections.singletonList(getUserMock()));
        when(template.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(expected);

        List<User> list = service.findAll();
        Assertions.assertNotNull(list);
        Assertions.assertNotEquals(0, list.size());
    }

    @Test
    public void deveRetornarNoContent_QuandoBuscarFindAll() {
        ResponseEntity<List<User>> expected = ResponseEntity.noContent().build();
        when(template.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(expected);

        List<User> list = service.findAll();
        Assertions.assertNull(list);
    }

    @Test
    public void deveRetornarRestClientException_QuandoBuscarFindAll() {
        when(template.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenThrow(new RestClientException(""));
        Assertions.assertThrows(RestClientException.class, () -> service.findAll());
    }

    @Test
    public void deveRetornarException_QuandoBuscarFindAll() {
        when(template.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenThrow(new RuntimeException(""));
        Assertions.assertThrows(Exception.class, () -> service.findAll());
    }
}
