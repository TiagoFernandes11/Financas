package br.financeiro.Financas.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebAppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((request) ->
                    request.requestMatchers( "/", "home").permitAll()
                           .requestMatchers("/usuario/cadastrar").permitAll()
                           .requestMatchers("/usuario/update").authenticated()
                           .requestMatchers("/usuario/delete").authenticated()
                           .requestMatchers("/login/**").permitAll()
                           .requestMatchers("/dashboard/**").hasRole("USUARIO")
            )
            .formLogin(formLogin ->
                    formLogin.loginPage("/login")
                            .defaultSuccessUrl("/dashboard")
                            .failureUrl("/login?error=true")
                            .permitAll())
            .logout(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());

        http.headers(httpSecurityHeadersConfigurer ->
                httpSecurityHeadersConfigurer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
