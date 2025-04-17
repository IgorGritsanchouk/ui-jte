package com.ui.config;

import com.ui.component.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final DataSource dataSource;

    public SecurityConfig(DataSource postgresDataSource) {
        this.dataSource = postgresDataSource;
    }

//    @Bean
//    EmbeddedDatabase datasource(){
//
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .setName("dashboard")
//                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//                .build();
//    }


    //   RUN IT ONLY ONCE TO POPULATE USERS AND AUTHORITIES TABLE
    @Bean
    JdbcUserDetailsManager users(DataSource dataSource, PasswordEncoder encoder){

//        UserDetails user= User.builder()
//                .username("user")
//                .password(encoder.encode("user_password"))
//                .roles("USER")
//                .build();
//        //  .password("{noop}password")  to use without encoder
//
//        UserDetails manager= User.builder()
//                .username("manager")
//                .password(encoder.encode("manager_password"))
//                .roles("MANAGER")
//                .build();
//
//        UserDetails admin= User.builder()
//                .username("admin")
//                .password(encoder.encode("admin_password"))
//                .roles("ADMIN")
//                //.roles("ADMIN", "USER", "MANAGER")
//                .build();
//
//        UserDetails qualityVerifier = User.builder()
//                .username("verifier")
//                .password(encoder.encode("verifier_password"))
//                .roles("QUALITY_VERIFIER") // Automatically becomes "ROLE_QUALITY_VERIFIER"
//                .build();
//
//        UserDetails multiuser = User.builder()
//                .username("multiuser")
//                .password(encoder.encode("multi_password"))
//                .roles("USER", "QUALITY_VERIFIER")
//                .build();


        JdbcUserDetailsManager jdbcUserDetailsManager= new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(manager);
//        jdbcUserDetailsManager.createUser(admin);
//        jdbcUserDetailsManager.createUser(qualityVerifier);
//        jdbcUserDetailsManager.createUser(multiuser);

        return jdbcUserDetailsManager;
    }

//    @Bean       // in memeory user detail manager
//    InMemoryUserDetailsManager users(PasswordEncoder encoder){
//        return new InMemoryUserDetailsManager(
//                User.withUsername("igor")
//                        .password(encoder.encode("password"))
//                        .roles("ADMIN")
//                        .build()
//        );
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                //.csrf(csrf -> csrf.ignoringRequestMatchers("/"))
                //.csrf(csrf -> csrf.ignoringRequestMatchers("/home-vm"))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/change-language"))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/permitted"))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .authorizeHttpRequests( auth -> auth
                        //.requestMatchers("/").permitAll()
                        //.requestMatchers("/home-vm").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/change-language").permitAll()
                        .requestMatchers("/permitted").permitAll()
                        .anyRequest().authenticated()
                )

                .headers(headers -> headers.frameOptions(Customizer.withDefaults()))
                .exceptionHandling(exception -> exception.accessDeniedHandler(new CustomAccessDeniedHandler()))
                .formLogin(Customizer.withDefaults())
                .build();

    }

    @Bean
    CustomAccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

