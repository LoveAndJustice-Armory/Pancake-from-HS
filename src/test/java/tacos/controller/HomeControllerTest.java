package tacos.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@WebMvcTest(HomeController.class)   // 将相应的控制器注册到 mvc容器中
public class HomeControllerTest {
    //@Autowired
    private MockMvc mockMvc;

    @Test
    public void testHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))    // get请求
                .andExpect(MockMvcResultMatchers.status().isOk())   // 期望 200
                .andExpect(MockMvcResultMatchers.view().name("home"))   // 期望视图名
                .andExpect(MockMvcResultMatchers.content().string(
                        Matchers.containsString("Welcome to...")    // 期望内容包含
                ));
    }
}
