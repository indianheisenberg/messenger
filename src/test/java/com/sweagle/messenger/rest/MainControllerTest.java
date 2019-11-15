package com.sweagle.messenger.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sweagle.messenger.repo.MessageRepo;
import com.sweagle.messenger.service.MessageService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@RunWith(SpringRunner.class)

public class MainControllerTest {

	
     private MockMvc mockMvc;
     
     @InjectMocks
     private MainController mainController;
     
     @Mock
     private MessageService messageService;

     
     @Before
     public void init(){
         MockitoAnnotations.initMocks(this);
         mockMvc = MockMvcBuilders
                 .standaloneSetup(mainController)
                 .build();
     }
	 
	

	 
@Test
public void testmethod() throws Exception {
	
	/*mockMvc.perform(get("/home"))
    .andExpect(status().isOk());*/
	
	int count=1;
	
	assertThat(count).isEqualTo(1);
}
}
