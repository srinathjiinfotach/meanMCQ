package meanMCQ;

import meanMCQ.domain.Account;
import meanMCQ.domain.AccountRole;
import meanMCQ.domain.McqChoice;
import meanMCQ.domain.McqQuestion;
import meanMCQ.service.AccountRepository;
import meanMCQ.service.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/28/14.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    CommandLineRunner init(QuestionRepository questionRepository, AccountRepository accountRepository) {

        return (evt) -> {
            accountRepository.save(new Account("Musa", "pass1", AccountRole.TESTER));
            accountRepository.save(new Account("Isa", "pass2", AccountRole.PUPIL));

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
            }

        };
    }
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return (username) -> accountRepository
                .findByUsername(username)
                .map(a -> {
                    return new User(a.username, a.password, true, true, true, true,
                            AuthorityUtils.createAuthorityList("USER", "write"));
                })
                .orElseThrow(
                        () -> new UsernameNotFoundException("could not find the user '"
                                + username + "'"));
    }
}

//@Configuration
//@EnableResourceServer
//@EnableAuthorizationServer
//class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {
//
//    String applicationName = "meanMCQ";
//
//    // This is required for password grants, which we specify below as one of the
//    // {@literal authorizedGrantTypes()}.
//    @Autowired
//    AuthenticationManagerBuilder authenticationManager;
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
//            throws Exception {
//        // Workaround for https://github.com/spring-projects/spring-boot/issues/1801
//        endpoints.authenticationManager(new AuthenticationManager() {
//            @Override
//            public Authentication authenticate(Authentication authentication)
//                    throws AuthenticationException {
//                return authenticationManager.getOrBuild().authenticate(authentication);
//            }
//        });
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//        clients.inMemory().withClient("android-" + applicationName)
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .authorities("ROLE_USER").scopes("write").resourceIds(applicationName)
//                .secret("123456");
//    }
//}

@SuppressWarnings("serial")
class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super("could not find user '" + userId + "'.");
    }
}
