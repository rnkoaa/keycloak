package com.richard.spring.cloud.backend.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by 7/17/17.
 */
/*@Configuration
@EnableWebSecurity*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AccessDeniedHandler accessDeniedHandler;

  @Autowired
  private AuthenticationSuccessHandler authenticationSuccessHandler;

  // roles admin allow to access /admin/**
  // roles user allow to access /user/**
  // custom 403 access denied handler
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
      .csrf()
        .disable()
      .exceptionHandling()
        //.authenticationEntryPoint(new ContinueEntryPoint("/login"))
      .and()
        .authorizeRequests()
          .antMatchers("/", "/home", "/about").permitAll()
          //.antMatchers("/admin/**").hasAnyRole("ADMIN")
          //.antMatchers("/user/**").hasAnyRole("USER")
          .anyRequest()
          .authenticated()
      .and()
        .formLogin()
        .loginPage("/login")
       // .loginProcessingUrl("/login")
        .successHandler(authenticationSuccessHandler)
        .permitAll()
      .and()
        .logout()
        .permitAll()
      .and()
      .exceptionHandling()
          .accessDeniedHandler(accessDeniedHandler);

    /*http
			.exceptionHandling()
				.authenticationEntryPoint(new ApplicationEntryPoint("/login"))
				.and()
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
        .successHandler(authenticationSuccessHandler);*/
    // @formatter:on
  }

  /*@Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // @formatter:off
        auth
            .inMemoryAuthentication()
                .withUser("reader")
                .password("reader")
                .authorities("FOO_READ")
            .and()
                .withUser("writer")
                .password("writer")
                .authorities("FOO_READ", "FOO_WRITE");

        // @formatter:on
  }
*/
  // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
    }
}
