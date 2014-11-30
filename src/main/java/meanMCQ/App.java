package meanMCQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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

//    // CORS
//    @Bean
//    FilterRegistrationBean corsFilter(
//            @Value("${tagit.origin:http://localhost:9000}") String origin) {
//        return new FilterRegistrationBean(new Filter() {
//            public void doFilter(ServletRequest req, ServletResponse res,
//                                 FilterChain chain) throws IOException, ServletException {
//                HttpServletRequest request = (HttpServletRequest) req;
//                HttpServletResponse response = (HttpServletResponse) res;
//                String method = request.getMethod();
//                // this origin value could just as easily have come from a database
//                response.setHeader("Access-Control-Allow-Origin", origin);
//                response.setHeader("Access-Control-Allow-Methods",
//                        "POST,GET,OPTIONS,DELETE");
//                response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
//                response.setHeader("Access-Control-Allow-Credentials", "true");
//                response.setHeader(
//                        "Access-Control-Allow-Headers",
//                        "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
//                if ("OPTIONS".equals(method)) {
//                    response.setStatus(HttpStatus.OK.value());
//                }
//                else {
//                    chain.doFilter(req, res);
//                }
//            }
//
//            public void init(FilterConfig filterConfig) {
//            }
//
//            public void destroy() {
//            }
//        });
//    }

//    @Bean
//    CommandLineRunner init(QuestionRepository questionRepository, AccountRepository accountRepository) {
//
//        return (evt) -> {
//            accountRepository.save(new Account("Musa", "pass1"));
//            accountRepository.save(new Account("Isa", "pass2"));
//
//            for (int i = 1; i <= 10; i++) {
//                McqQuestion q = new McqQuestion("This is question " + i + "?");
//                Set<McqChoice> mcqChoices = new HashSet<>(4);
//                mcqChoices.add(new McqChoice("Choice 1", false));
//                mcqChoices.add(new McqChoice("Choice 2", false));
//                mcqChoices.add(new McqChoice("Choice 3", true));
//                mcqChoices.add(new McqChoice("Choice 4", false));
//                q.setMcqChoices(mcqChoices);
//                mcqChoices.forEach(c -> c.setMcqQuestion(q));
//                questionRepository.save(q);
//            }
//
//        };
//    }
}