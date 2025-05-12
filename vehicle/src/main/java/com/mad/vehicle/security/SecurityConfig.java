package com.mad.vehicle.security;



import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private HttpBasicConfigurer<HttpSecurity> httpBasic;

	@SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        setHttpBasic(http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/workitems/**").hasAnyRole("ADMIN", "SERVICE_PROVIDER")
                .requestMatchers("/api/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	public HttpBasicConfigurer<HttpSecurity> getHttpBasic() {
		return httpBasic;
	}

	public void setHttpBasic(HttpBasicConfigurer<HttpSecurity> httpBasic) {
		this.httpBasic = httpBasic;
	}
}

