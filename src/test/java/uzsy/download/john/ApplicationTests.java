package uzsy.download.john;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CrimeRepository crimeRepository;

    @Before
    public void deleteAllBeforeTests() throws Exception {
        crimeRepository.deleteAll();
    }

    @Test
    public void shouldReturnRepositoryIndex() throws Exception {

        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
                jsonPath("$._links.crime").exists());
    }

    @Test
    public void shouldCreateEntity() throws Exception {

        mockMvc.perform(post("/crime").content(
                "{\"title\": \"Test\", \"date\": \"2017-01-12T05:37:00.708+0000\"}")).andExpect(
                status().isCreated()).andExpect(
                header().string("Location", containsString("crime/")));
    }

    @Test
    public void shouldRetrieveEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/crime").content(
                "{\"title\": \"Test\", \"date\": \"2017-01-12T05:37:00.708+0000\"}")).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.title").value("Test")).andExpect(
                jsonPath("$.date").value("2017-01-12T05:37:00.708+0000"));
    }

    @Test
    public void shouldQueryEntity() throws Exception {

        mockMvc.perform(post("/crime").content(
                "{\"title\": \"Test\", \"date\": \"2017-01-12T05:37:00.708+0000\"}")).andExpect(
                status().isCreated());

        mockMvc.perform(
                get("/crime/search/findByTitle?title={title}", "Test")).andExpect(
                status().isOk()).andExpect(
                jsonPath("$._embedded.crime[0].title").value(
                        "Test"));
    }

    @Test
    public void shouldUpdateEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/crime").content(
                "{\"title\": \"Test\", \"date\": \"2017-01-12T05:37:00.708+0000\"}")).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(put(location).content(
                "{\"title\": \"Test2\", \"date\": \"2017-01-12T05:37:00.718+0000\"}")).andExpect(
                status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.title").value("Test2")).andExpect(
                jsonPath("$.date").value("2017-01-12T05:37:00.718+0000"));
    }

    @Test
    public void shouldPartiallyUpdateEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/crime").content(
                "{\"title\": \"Test\", \"date\": \"2017-01-12T05:37:00.708+0000\"}")).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(
                patch(location).content("{\"title\": \"test this is a test\"}")).andExpect(
                status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.title").value("test this is a test")).andExpect(
                jsonPath("$.date").value("2017-01-12T05:37:00.708+0000"));
    }

    @Test
    public void shouldDeleteEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/crime").content(
                "{\"title\": \"Test2\", \"date\": \"2017-01-12T05:37:00.718+0000\"}")).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(delete(location)).andExpect(status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }
}