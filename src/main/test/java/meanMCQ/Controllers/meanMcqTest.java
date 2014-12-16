package meanMCQ.Controllers;

import meanMCQ.App;
import meanMCQ.domain.Choice;
import meanMCQ.domain.McqTest;
import meanMCQ.domain.Question;
import meanMCQ.service.McqTestRepository;
import meanMCQ.service.QuestionRepository;
import meanMCQ.service.UserRepository;
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
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by red on 11/29/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class meanMcqTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private Question question;
    private Set<Question> questions;
    private McqTest mcqTest;

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private McqTestRepository mcqTestRepository;
    @Autowired
    private UserRepository userRepository;

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

//        userRepository.deleteAll();
//        questionRepository.deleteAll();
//        mcqTestRepository.deleteAll();

//        userRepository.save(new Account("tester1", "pass", UserRole.EXAMINER));

//        for (int i = 1; i <= 10; i++) {
//            userRepository.save(new Account("pupil"+i, "pass", UserRole.STUDENT));
//            Question q = new Question("This is question " + i + "?");
//            Set<Choice> choices = new HashSet<>(4);
//            choices.add(new Choice("Choice 1", false));
//            choices.add(new Choice("Choice 2", false));
//            choices.add(new Choice("Choice 3", true));
//            choices.add(new Choice("Choice 4", false));
//            q.setChoices(choices);
//            choices.forEach(c -> c.setQuestion(q));
//            questionRepository.save(q);
//            questions.add(q);
//        }

//        McqTest mcqTest = new McqTest(Date.from(Instant.now()), 30, questions);
//        mcqTestRepository.save(mcqTest);
    }

    @Test
    public void createQuestion() throws Exception {
        question = new Question("This is a test question?");
        Set<Choice> choices = new HashSet<>(4);
        choices.add(new Choice("Choice 1", false));
        choices.add(new Choice("Choice 2", false));
        choices.add(new Choice("Choice 3", true));
        choices.add(new Choice("Choice 4", false));

        question.setChoices(choices);
        choices.forEach(c -> c.setQuestion(question));

        String questionJson = json(question);
        this.mockMvc.perform(post("/questions")
                .contentType(contentType)
                .content(questionJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void createTest() throws Exception {
        questions = new HashSet<>(questionRepository.findAll());
        mcqTest = new McqTest("UnitTest McqTest", Date.from(Instant.now()), 30);
        mcqTest.setQuestions(questions);
        String mcqTestJson = json(mcqTest);
        this.mockMvc.perform(post("/mcqtests/")
                .contentType(contentType)
                .content(mcqTestJson))
                .andExpect(status().isCreated());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
