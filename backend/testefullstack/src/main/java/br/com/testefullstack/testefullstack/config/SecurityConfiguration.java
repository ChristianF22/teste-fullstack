package br.com.testefullstack.testefullstack.config;

import br.com.testefullstack.testefullstack.security.CustomUserDatailsService;
import br.com.testefullstack.testefullstack.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(configure ->{
                    configure.requestMatchers(HttpMethod.POST, "/plano/**").hasRole("ADMIN");
                    configure.requestMatchers(HttpMethod.GET, "/plano/**").hasRole("ADMIN");
                    configure.requestMatchers(HttpMethod.PUT, "/plano/**").hasRole("ADMIN");
                    configure.requestMatchers(HttpMethod.DELETE, "/plano/**").hasRole("ADMIN");

                    configure.requestMatchers(HttpMethod.POST, "/beneficiario/**").hasAnyRole("ADMIN","ATENDENTE");
                    configure.requestMatchers(HttpMethod.GET, "/beneficiario/**").hasAnyRole("ADMIN","ATENDENTE");
                    configure.requestMatchers(HttpMethod.PUT, "/beneficiario/**").hasAnyRole("ADMIN","ATENDENTE");
                    configure.requestMatchers(HttpMethod.DELETE, "/beneficiario/**").hasRole("ADMIN");

                    configure.requestMatchers(HttpMethod.POST,"/usuario/**").hasRole("ADMIN");
                    configure.requestMatchers(HttpMethod.GET,"/usuario/**").hasRole("ADMIN");
                    configure.requestMatchers(HttpMethod.PUT,"/usuario/**").hasRole("ADMIN");
                    configure.requestMatchers(HttpMethod.DELETE,"/usuario/**").hasRole("ADMIN");

                    configure.anyRequest().authenticated();
                })
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioService usuarioService){
        return  new CustomUserDatailsService(usuarioService);
    }
}
