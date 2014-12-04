package meanMCQ;

import meanMCQ.domain.*;
import meanMCQ.service.AccountRepository;
import meanMCQ.service.McqTestRepository;
import meanMCQ.service.QuestionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/28/14.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // CORS
    @Bean
    FilterRegistrationBean corsFilter(
            @Value("${tagit.origin:http://localhost:9000}") String origin) {
        return new FilterRegistrationBean(new Filter() {
            public void doFilter(ServletRequest req, ServletResponse res,
                                 FilterChain chain) throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                String method = request.getMethod();
                // this origin value could just as easily have come from a database
                response.setHeader("Access-Control-Allow-Origin", origin);
                response.setHeader("Access-Control-Allow-Methods",
                        "POST,GET,OPTIONS,DELETE");
                response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader(
                        "Access-Control-Allow-Headers",
                        "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
                if ("OPTIONS".equals(method)) {
                    response.setStatus(HttpStatus.OK.value());
                } else {
                    chain.doFilter(req, res);
                }
            }

            public void init(FilterConfig filterConfig) {
            }

            public void destroy() {
            }
        });
    }

    @Bean
    CommandLineRunner init(QuestionRepository questionRepository, AccountRepository accountRepository,
                           McqTestRepository mcqTestRepository) {

        return (evt) -> {
            accountRepository.save(new Account("Teacher", "pass", AccountRole.TESTER));

            Set<Question> questions = new HashSet<>();

            for (int i = 1; i <= 10; i++) {
                accountRepository.save(new Account("Pupil"+i, "pass", AccountRole.PUPIL));
                Question q = new Question("This is question " + i + "?");
                Set<Choice> choices = new HashSet<>(4);
                choices.add(new Choice("Choice 1", false));
                choices.add(new Choice("Choice 2", false));
                choices.add(new Choice("Choice 3", true));
                choices.add(new Choice("Choice 4", false));
                q.setChoices(choices);
                choices.forEach(c -> c.setQuestion(q));
                questionRepository.save(q);
                questions.add(q);
            }

            McqTest mcqTest = new McqTest(Date.from(Instant.now()), 30, questions);
            mcqTestRepository.save(mcqTest);

        };
    }
}