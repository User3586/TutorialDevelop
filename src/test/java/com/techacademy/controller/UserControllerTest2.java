package com.techacademy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.techacademy.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest2 {
    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    UserControllerTest2(WebApplicationContext context) {
        this.webApplicationContext = context;
    }

    @BeforeEach
    void beforeEach() {
        // Spring Securityを有効にする
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();
    }
    @DisplayName("User一覧")
    @WithMockUser
    @Test
    void testGetList() throws Exception {

        MvcResult result = mockMvc.perform(get("/user/list"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("userlist"))
        .andExpect(model().hasNoErrors())
        .andExpect(view().name("user/list"))
        .andReturn();

        List<User> userlist = (List<User>)result.getModelAndView().getModel().get("userlist");
        User user=userlist.get(0);

        assertEquals(userlist.size(),3);
        assertEquals(user.getId(),1);
        assertEquals(user.getName(),"キラメキ太郎");
        user=userlist.get(1);
        assertEquals(user.getId(),2);
        assertEquals(user.getName(),"キラメキ次郎");
        user=userlist.get(2);
        assertEquals(user.getId(),3);
        assertEquals(user.getName(),"キラメキ花子");








    }


}
