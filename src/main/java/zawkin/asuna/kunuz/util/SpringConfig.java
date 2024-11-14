package zawkin.asuna.kunuz.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SpringConfig {
    @Bean
    public AuthenticationProvider authenticationProvider() {
        String password = UUID.randomUUID().toString();
        System.out.println("User password: " + password);

        UserDetails user = User.builder()
                .username("asuna")
                .password("{noop}" + password)
                .roles("USER")
                .build();

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(user));
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                // Authorization endpoints
                .requestMatchers("/auth/registration").permitAll()
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/verification/**").permitAll()
                .requestMatchers("/auth/resend/**").permitAll()

                // Profile endpoints
                .requestMatchers(HttpMethod.POST, "/profile").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/profile/admin/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/profile/detail").authenticated()
                .requestMatchers(HttpMethod.GET, "/profile").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/profile/**").hasRole("ADMIN")
                .requestMatchers("/profile/photo").authenticated()

                // ArticleType endpoints
                .requestMatchers(HttpMethod.POST, "/article-type/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/article-type/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/article-type/**").hasRole("ADMIN")
                .requestMatchers("/article-type/lang").permitAll()

                // Region endpoints
                .requestMatchers(HttpMethod.POST, "/region/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/region/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/region/**").hasRole("ADMIN")
                .requestMatchers("/region/lang").permitAll()

                // Category endpoints
                .requestMatchers(HttpMethod.POST, "/category/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/category/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/category/**").hasRole("ADMIN")
                .requestMatchers("/category/lang").permitAll()

                // Article endpoints
                .requestMatchers(HttpMethod.POST, "/article").hasRole("MODERATOR")
                .requestMatchers(HttpMethod.PUT, "/article/**").hasRole("MODERATOR")
                .requestMatchers(HttpMethod.DELETE, "/article/**").hasRole("MODERATOR")
                .requestMatchers("/article/status/**").hasRole("PUBLISHER")
                .requestMatchers(HttpMethod.GET, "/article/**").permitAll()
                .requestMatchers("/article/view/**").permitAll()
                .requestMatchers("/article/share/**").permitAll()
                .requestMatchers("/article/filter").hasRole("PUBLISHER")

                // Like, Comment, and Save endpoints
                .requestMatchers("/article-like/**").authenticated()
                .requestMatchers("/comment/**").authenticated()
                .requestMatchers("/comment-like/**").authenticated()
                .requestMatchers("/saved-article/**").authenticated()

                // Attach endpoints
                .requestMatchers(HttpMethod.POST, "/attach").authenticated()
                .requestMatchers(HttpMethod.GET, "/attach/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/attach/**").hasRole("ADMIN")

                // Admin-only endpoints
                .requestMatchers("/sms-history/**").hasRole("ADMIN")
                .requestMatchers("/email-history/**").hasRole("ADMIN")

                // Any other request requires authentication
                .anyRequest().authenticated()
        );

        // Disable CSRF and CORS
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);


        return http.build();
    }
}
