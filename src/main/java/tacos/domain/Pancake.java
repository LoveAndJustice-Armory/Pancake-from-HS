package tacos.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Pancake {
    @NotNull
    @Size(min = 2, message = "名字最短为2")
    private String name;

    @NotNull
    @Size(min = 1, message = "最少选择一项配料")
    private List<Ingredient> ingredients;
}
