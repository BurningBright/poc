package cn.burningbright.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;

/**
 * @author chenguang.lin
 * @date 2022-08-08
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public SimpleServletHandlerAdapter simpleServletHandlerAdapter() {
        return new SimpleServletHandlerAdapter();
    }

    /**
     * 视图解析器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //默认所有的页面都从 /WEB-INF/ xxx .jsp
        //registry.jsp();
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("test");
    }

    // 开启静态资源的请求转发到默认servlet上,不配置页面报错404,(默认servlet不是DispatcherServlet!理解的)
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //自定义添加拦截器=========这个比较常用
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
    }

}