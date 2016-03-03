package Back_end;


import Back_end.dao.ContactMapper;
import Back_end.entity.Contact;
import Back_end.service.ContactService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Олег on 27.02.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration

public class RestTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ContactService contactService;

    private static MockMvc mockMvc;

    @Before
    public  void init() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        contactService.addContact(new Contact("aaa"));
    }
    /*
    проверка на ожидаемый результат
    */
    @Test
    public void testEquals() throws Exception{

        Contact contact=new Contact("222");

        contactService.addContact(contact);
        ResultActions resultActions=mockMvc.perform(get("/hello/contacts?nameFilter=^[a-z].*$&limit=20&page=1"));
         resultActions.andExpect(content().json("[{\"id\":5,\"name\":\"222\"}]")).andReturn();
    }

    /*
    * проверка верных запросов
    */
    @Test
    public void testOk() throws Exception {
        mockMvc.perform(get("/hello/contacts?nameFilter=1&limit=10")).andExpect(status().isOk());
        mockMvc.perform(get("/hello/contacts?nameFilter=^[a-z].*$")).andExpect(status().isOk());
        mockMvc.perform(get("/hello/contacts?nameFilter=^[b-z].*$&limit=2&page=2")).andExpect(status().isOk());
    }


    /*
    *проверка типа ответа
    */
    @Test
    public void testPage() throws Exception{
        mockMvc.perform(get("/hello/contacts?nameFilter=^[b-z].*$&limit=2")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    /*
    * ожидание ошибки с кодом 400, если фильтр не задан
    */
    @Test
    public void testNotFound() throws Exception{
        mockMvc.perform(get("/hello/contacts")).andExpect(status().is(400));
    }
}
