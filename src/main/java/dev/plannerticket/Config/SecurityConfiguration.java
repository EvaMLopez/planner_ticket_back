package dev.plannerticket.Config;



// import java.util.Arrays;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import dev.plannerticket.Services.JpaUserDetailsService;


// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration {

//     @Value("${api-endpoint}")
//     String endpoint;

//     JpaUserDetailsService jpaUserDetailsService;

//     public SecurityConfiguration(JpaUserDetailsService jpaUserDetailsService) {
//         this.jpaUserDetailsService = jpaUserDetailsService;
//     }

//     @Bean
//     SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//                 .cors(Customizer.withDefaults())
//                 .csrf(csrf -> csrf.disable())
//                 .formLogin(form -> form.disable())
//                 .logout(out -> out
//                         .logoutUrl(endpoint + "/logout")
//                         .deleteCookies("JSESSIONID"))
//                 .authorizeHttpRequests(auth -> auth
//                 .requestMatchers(HttpMethod.GET, endpoint + "/login").hasAnyRole("ADMIN","USER")
//                 .requestMatchers(HttpMethod.GET, endpoint + "/list_us").hasAnyRole("ADMIN ", "USER")
//                 .requestMatchers(HttpMethod.POST, endpoint + "/list_us").hasAnyRole("ADMIN","USER")
//                 .requestMatchers(HttpMethod.GET, endpoint + "/events").permitAll()                        
//                 .requestMatchers(HttpMethod.GET, endpoint + "/images").permitAll()
//                 .requestMatchers(HttpMethod.POST, endpoint + "/images").permitAll()
//                 .requestMatchers(HttpMethod.POST, endpoint + "/events").permitAll()
//                 .anyRequest().authenticated())
//                 .userDetailsService(jpaUserDetailsService)
//                 .httpBasic(Customizer.withDefaults())
//                 .sessionManagement(session -> session
//                         .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

//         http.headers(header -> header.frameOptions(frame -> frame.sameOrigin()));

//         return http.build();
//     } 

//     @Bean
//     CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.setAllowCredentials(true);

//         configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173","http://localhost:5174", "http://localhost:8080"));
   
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//         configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration(("/**"), configuration);
//         return source;
//     }

//     @Bean
//     PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//}
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import dev.plannerticket.Services.JpaUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${api-endpoint}")
    String endpoint;

    JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfiguration(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .logout(out -> out
                        .logoutUrl(endpoint + "/logout")
                        .deleteCookies("JSESSIONID"))
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, endpoint + "/login").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET, endpoint + "/list_us").hasAnyRole("ADMIN ", "USER")
                .requestMatchers(HttpMethod.POST, endpoint + "/list_us").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET, endpoint + "/events").permitAll()                        
                .requestMatchers(HttpMethod.GET, endpoint + "/images/**").permitAll() 
                .requestMatchers(HttpMethod.POST, endpoint + "/events").hasRole("ADMIN")                
                .requestMatchers(HttpMethod.POST, endpoint + "/images").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, endpoint + "/events/{id}").hasRole("ADMIN") 
                .requestMatchers(HttpMethod.DELETE, endpoint + "/events/{id}").hasRole("ADMIN") 
                .requestMatchers(HttpMethod.POST, endpoint + "/logout/").permitAll()
                .requestMatchers(HttpMethod.POST, endpoint + "/users/").permitAll()

                .anyRequest().authenticated())
                .userDetailsService(jpaUserDetailsService)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        http.headers(header -> header.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    } 

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
    
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173","http://localhost:5174", "http://localhost:8080"));
    
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    
        // Configuración general para todas las rutas
        source.registerCorsConfiguration("/**", configuration);
    
        // Configuración específica para /images
        CorsConfiguration imageConfiguration = new CorsConfiguration();
        imageConfiguration.setAllowCredentials(true);
        imageConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173","http://localhost:5174", "http://localhost:8080"));
        imageConfiguration.setAllowedMethods(Arrays.asList("GET", "POST"));
        imageConfiguration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        source.registerCorsConfiguration("/images/**", imageConfiguration);
    
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
