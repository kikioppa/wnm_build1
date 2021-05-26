package com.example.dodgema.config.security;



import com.example.dodgema.repository.UserRepository;
import com.example.dodgema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfigration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                 jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .userDetailsService(principalDetailService).passwordEncoder(bCryptPasswordEncoder);
    }
//.antMatchers("/add-spirit","/add-price").hasAnyAuthority("ADMIN").anyRequest()
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/",
                                        "/index",
                                        "/kakao/**",
                                        "/spirit_view/**",
                                        "/kakao_register",
                                        "/kakao_signup",
                                        "/kakao_completed",
                                        "/kakao/logout",
                                        "/zzz/**",
                                        "/price_data/**",
                                        "/taste_data/**",
                                        "/spirit/**",
                                        "/.well-known/**",
                                        "/ads.txt"
                        ).permitAll()
                .antMatchers("/add_price").hasAuthority("ADMIN")
                .antMatchers("/add_spirit").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                 .loginPage("/kakao_signup")
                .loginProcessingUrl("/kakao/login")
                .defaultSuccessUrl("/")
        .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/");
    }

    @Bean
    public SpringDataDialect springSecurityDialect() {
        return new SpringDataDialect();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                 //.antMatchers("/**")
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/assets/**");
    }

}