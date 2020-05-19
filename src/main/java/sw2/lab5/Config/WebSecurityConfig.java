package sw2.lab5.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception  {
        http.formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/processLogin")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/redirectByRole", true);



        http.authorizeRequests()
                .antMatchers("/user/list").hasAuthority("admin")
                .antMatchers("/user","/user/edit").hasAnyAuthority("user","admin")
                .antMatchers("/post/delete").hasAuthority("admin")
                .antMatchers("/post/create","/post/edit").hasAnyAuthority("user","admin")
                .anyRequest().permitAll();

        http.logout()
                .logoutSuccessUrl("/posts/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT email, password, active FROM lab5.user WHERE email=? ")
                .authoritiesByUsernameQuery("SELECT u.email , r.role_name  FROM lab5.user u, lab5.role r WHERE u.id_role=r.id_role AND u.active=1 AND u.email=?");
    }




}