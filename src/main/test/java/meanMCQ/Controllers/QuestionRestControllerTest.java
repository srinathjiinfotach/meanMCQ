package meanMCQ.Controllers;

import meanMCQ.Application;
import meanMCQ.domain.McqChoice;
import meanMCQ.domain.McqQuestion;
import meanMCQ.service.QuestionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by red on 11/29/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class QuestionRestControllerTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private McqQuestion mcqQuestion;
    private List<McqQuestion> mcqQuestionList = new ArrayList<>();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.questionRepository.deleteAll();

        for (int i = 1; i <= 10; i++) {
            McqQuestion q = new McqQuestion("This is question " + i + "?");
            Set<McqChoice> mcqChoices = new HashSet<>(4);
            mcqChoices.add(new McqChoice("Choice 1", false));
            mcqChoices.add(new McqChoice("Choice 2", false));
            mcqChoices.add(new McqChoice("Choice 3", true));
            mcqChoices.add(new McqChoice("Choice 4", false));
            q.setMcqChoices(mcqChoices);
            mcqChoices.forEach(c -> c.setMcqQuestion(q));
            questionRepository.save(q);
            mcqQuestionList.add(q);
        }
    }

    @Test
    public void createQuestion() throws Exception {
        mcqQuestion = new McqQuestion("This is a test question?");
        Set<McqChoice> mcqChoices = new HashSet<>(4);
        mcqChoices.add(new McqChoice("Choice 1", false));
        mcqChoices.add(new McqChoice("Choice 2", false));
        mcqChoices.add(new McqChoice("Choice 3", true));
        mcqChoices.add(new McqChoice("Choice 4", false));

        mcqQuestion.setMcqChoices(mcqChoices);
        mcqChoices.forEach(c -> c.setMcqQuestion(mcqQuestion));

        String questionJson = json(mcqQuestion);
        this.mockMvc.perform(post("/questions")
                .contentType(contentType)
                .content(questionJson))
                .andExpect(status().isCreated());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
