package com.liugs.tool.spring;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName AccessRequestFilter
 * @Description http请求过滤器（主要是针对财企通的回调）
 * @Author liugs
 * @Date 2021/1/19 15:56:39
 */
@Configuration
public class AccessRequestFilter implements WebMvcConfigurer {

    /**
     * 描述 注册一个字符编码过滤器（只有当spring.http.encoding.enabled=false配置成false后，过滤器才起作用）
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
     * @author liugs
     * @date 2021/1/19 16:04:29
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        //创建一个spring提供的过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();

        //强制编码
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("GBK");

        //filter注册bean
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(characterEncodingFilter);
        //要过滤的路径
        registrationBean.addUrlPatterns("/pay/rest/dealCnncIssNotify");
        return registrationBean;
    }
}
