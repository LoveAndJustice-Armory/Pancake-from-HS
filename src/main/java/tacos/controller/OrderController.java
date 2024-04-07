package tacos.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.domain.PancakeOrder;
import tacos.domain.User;
import tacos.repository.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("pancakeOrder")
public class OrderController {
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid PancakeOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user){ // 传递用户信息（也可以自定义认证成功处理器）
        if (errors.hasErrors())
            return "orderForm";
        //log.info("提交的订单：{}", order);
        order.setUser(user);    // 用户信息
        orderRepository.save(order);
        sessionStatus.setComplete();    // 清理会话
        return "redirect:/";    // 返回主页
    }
}
