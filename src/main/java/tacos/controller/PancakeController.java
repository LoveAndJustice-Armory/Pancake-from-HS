package tacos.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.domain.Ingredient;
import tacos.domain.Ingredient.Type;
import tacos.domain.Pancake;
import tacos.domain.PancakeOrder;
import tacos.repository.IngredientRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pancakeOrder")    // 使得对象可以在会话中保存，跨多个请求
public class PancakeController {
    private IngredientRepository ingredientRepository;

    @Autowired
    public PancakeController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute // 将对象添加到模型，表明这个方法会在控制器方法之前调用
    public void addIngredientsToModel(Model model) { // model负责在控制器和视图之间传递数据，其中的数据会被复制到Servlet Request属性中
        Iterable<Ingredient> result = ingredientRepository.findAll();
        List<Ingredient> ingredients =
                StreamSupport
                        .stream(result.spliterator(), false)    // key：Iterable类型转换为List
                        .collect(Collectors.toList());

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type)
            );  // 枚举常量的toString方法返回相应的名称，也可以自定义字符串表示
        }
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) { // 根据配料类型过滤配料列表
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "pancakeOrder")
    public PancakeOrder order() {
        return new PancakeOrder();
    }

    @ModelAttribute
    public Pancake pancake() {
        return new Pancake();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processPancake(@Valid Pancake pancake, Errors errors, @ModelAttribute PancakeOrder pancakeOrder) {
        if (errors.hasErrors())
            return "design";
        pancakeOrder.addPancake(pancake);
        log.info("Processing pancake: {}", pancake);
        return "redirect:/orders/current";
    }
}
