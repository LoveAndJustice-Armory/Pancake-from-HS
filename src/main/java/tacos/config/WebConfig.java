package tacos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {    // 视图控制器：只转发请求到视图，不做其他操作（用于定义简单的控制器）

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {   // 注册视图控制器
        registry.addViewController("/").setViewName("home");    // 替代 HomeController
    }
}
