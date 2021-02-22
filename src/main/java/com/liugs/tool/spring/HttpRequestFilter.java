package com.liugs.tool.spring;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @ClassName HttpRequestFilter
 * @Description GBK编码请求过滤
 * @Author liugs
 * @Date 2021/1/19 18:10:53
 */
@Configuration
public class HttpRequestFilter {
    @Bean
    public FilterRegistrationBean httpRequestCharsetFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new Filter() {
            @Override
            public void init(FilterConfig filterConfig) {
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

                Enumeration parameterNames = request.getParameterNames();
                while (parameterNames.hasMoreElements()) {
                    Object param = parameterNames.nextElement();
                    String val = request.getParameter(String.valueOf(param));
                    System.out.println(param + " : " + val);
                }
                //放行
                chain.doFilter(request, response);
            }

            @Override
            public void destroy() {
            }
        });

        // 需要过滤的URL
        registration.addUrlPatterns("/pay/rest/dealCnncIssNotify");

        registration.setName("httpRequestCharsetFilter");
        registration.setOrder(Integer.MIN_VALUE);
        return registration;
    }


}
