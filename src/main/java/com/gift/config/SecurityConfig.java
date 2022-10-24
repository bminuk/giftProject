package com.gift.config;

//import com.gift.service.PrincipalOauth2UserService;
import com.gift.service.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//임시로 시큐리티 변경("/newPost/**") 페이지 이동 확인하기 위해서~
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/members/login") //구글 로그인 완료가 되면 엑세스토큰+사용자프로필정보를 한꺼번에 받아온다.
                .userInfoEndpoint()
                .userService(principalOauth2UserService)
                ;
        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()

                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ;


        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
