package tacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tacos.domain.User;
import tacos.repository.UserRepository;

@Configuration
public class SecurityConfig {   // 安全配置

    @Bean
    public PasswordEncoder passwordEncoder() {   // 密码转码器，创建新用户和登录时使用
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {  // 将 用户查找功能委托给 UserRepository
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null)
                return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {    // 使用安全过滤器链保护web请求
        return http
                .authorizeRequests()
                    .requestMatchers("/design","/orders")
                        .access("hasRole('USER')")
                    .requestMatchers("/","/**")
                        .access("permitAll()")
                .and()
                    .formLogin(
                            formLogin -> formLogin.loginPage("/login").permitAll()
                    )
                .build();
    }
}
