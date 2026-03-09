package fi.haagahelia.course.StudentListSecureDB;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity( securedEnabled =  true)
public class WebSecurityConfig {
 
	
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests( authorize -> authorize
        	.requestMatchers("/css/**").permitAll() // Enable css when logged out
        	.anyRequest().authenticated()
        )
      .formLogin( formlogin -> formlogin
          .defaultSuccessUrl("/studentlist", true)
          .permitAll()
       )
      .logout(logout -> logout
          .permitAll()
      );
     return http.build();
    }
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
  
   
}