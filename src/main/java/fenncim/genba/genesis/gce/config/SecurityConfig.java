package fenncim.genba.genesis.gce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.security.user.password}")
    private String doorOpener;
    @Value("${spring.security.user.name}")
    private String technicalUser;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->  auth.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        httpSecurity.userDetailsService(userDetailsService());
        httpSecurity.cors(Customizer.withDefaults());

        return httpSecurity.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        var user1 = User.withUsername(technicalUser).password(doorOpener)
                .passwordEncoder(encodedPass -> passwordEncoder().encode(doorOpener))
                .roles("ADMIN").authorities("ADMIN")
                .build();
        inMemoryUserDetailsManager.createUser(user1);
        return inMemoryUserDetailsManager;
    }
}
