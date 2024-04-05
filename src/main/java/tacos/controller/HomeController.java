package tacos.controller;

import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class HomeController {   // 主页

    @GetMapping("/")
    public String home(){
        return "home";  // 返回视图名
    }

}
