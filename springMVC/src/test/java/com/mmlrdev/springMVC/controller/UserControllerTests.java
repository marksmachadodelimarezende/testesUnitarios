package com.mmlrdev.springMVC.controller;

import com.mmlrdev.springMVC.service.UserService;
import com.mmlrdev.springMVC.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static com.mmlrdev.springMVC.mocks.MocksObjects.getUserMock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({UserController.class, UserService.class})
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    private UserController controller;

    private UserService service;

    @BeforeEach
    public void prepare() {
        service = Mockito.mock(UserServiceImpl.class);
        controller = new UserController(service);
        mvc = MockMvcBuilders.standaloneSetup(controller).build(); //Necessário para setar
    }

    @Test
    public void deveRetornar200_QuandoBuscarFindAll() throws Exception {
        Mockito.when(service.findAll()).thenReturn(Collections.singletonList(getUserMock()));
        mvc.perform(MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$[*].id").isNotEmpty())
        ;
    }

    @Test
    public void deveRetornar204_QuandoBuscarFindAll() throws Exception {
        Mockito.when(service.findAll()).thenReturn(null);
        mvc.perform(MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
        ;
    }

}
