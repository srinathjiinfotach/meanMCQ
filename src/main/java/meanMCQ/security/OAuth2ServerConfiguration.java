package meanMCQ.security;

import meanMCQ.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
public class OAuth2ServerConfiguration {

    private static final String RESOURCE_ID = "meanmcq";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            // @formatter:off
            resources
                    .resourceId(RESOURCE_ID);
            // @formatter:on
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .authorizeRequests()
                    .antMatchers("/questions/*")
                    .hasAnyAuthority(UserRole.EXAMINER.toString())
                    .and()
                    .authorizeRequests()
                    .antMatchers("/mcqtests/*")
                    .hasAuthority(UserRole.EXAMINER.toString())
                    .and()
                    .authorizeRequests()
                    .antMatchers("/exam/mcqtests/**")
                    .hasAnyAuthority(UserRole.EXAMINER.toString(), UserRole.STUDENT.toString())
                    .and()
                    .authorizeRequests()
                    .antMatchers("/exam/mcqtests/**/results")
                    .hasAuthority(UserRole.EXAMINER.toString())
                    .and()
                    .authorizeRequests()
                    .antMatchers("/users/profile")
                    .hasAnyAuthority(UserRole.EXAMINER.toString(), UserRole.STUDENT.toString())
                    .and()
                    .authorizeRequests()
                    .antMatchers("/users/*")
                    .hasAuthority(UserRole.EXAMINER.toString());
            // @formatter:on
        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {

        private TokenStore tokenStore = new InMemoryTokenStore();

        @Autowired
        //@Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            // @formatter:off
            endpoints
                    .tokenStore(this.tokenStore)
                    .authenticationManager(this.authenticationManager);
            // @formatter:on
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // @formatter:off
            clients
                    .inMemory()
                    .withClient("meanmcq")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                    .authorities(UserRole.EXAMINER.toString(), UserRole.STUDENT.toString())
                    .scopes("read", "write")
                    .resourceIds(RESOURCE_ID)
                    .secret("123456")
                    .redirectUris("http://localhost:9000/");
            // @formatter:on
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(this.tokenStore);
            return tokenServices;
        }

    }

}