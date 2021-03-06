package com.xa.demo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登录功能   效果: 如果没有登录,没有权限就会来到登录页面
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userLogin");
        //1./login来到登录页
        //2.重定向到/login?error表示登录失败
        //3.更多详细规则
        //4.默认post形式的/login代表处理登录
        //5.一旦定制loginPage;那么 loginPage的post请求就是登录比如现在loginPage里是/userLogin,那么提交表单也就写/userLogin 只不过方式改为post


        //开启自动配置的注销功能.
        http.logout().logoutSuccessUrl("/");//设置注销成功后去的页面
        //1.访问 /logout  表示用户注销,清空session
        //2.注销成功会返回  /login?logout  页面

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
        //登录成功以后,将cookie发给浏览器保存,以后访问页面带上这个cookie,只要通过检查就可以免登录
        //点击主线会删除cookie

    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //在内存中查找
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1","VIP2")
                .and()
                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2","VIP3")
                .and()
                .withUser("wangwu").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1","VIP3");
    }
}
