package ru.otus.homework25.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.homework25.controller.error.DeniedHandler;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DeniedHandler deniedHandler;

    @Override
    public void configure( WebSecurity web ) {
        web.ignoring().antMatchers( "/" );
    }

    @Override
    public void configure( HttpSecurity http ) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .and()
                .authorizeRequests()
                    .antMatchers( "/book" )
                    .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_TERMINATOR")
                    .and()
                .authorizeRequests()
                    .antMatchers("/book/edit")
                    .hasAnyAuthority("ROLE_ADMIN", "ROLE_TERMINATOR")
                    .and()
//                .authorizeRequests().antMatchers("/**").denyAll()
//                .and()
                // Включает Form-based аутентификацию
                .formLogin()
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedHandler(deniedHandler);

    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
