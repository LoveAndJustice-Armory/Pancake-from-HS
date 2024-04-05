package tacos.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.domain.Ingredient;

import java.util.HashMap;

@Component
public class IdToIngredient implements Converter<String, Ingredient> {

    private HashMap<String, Ingredient> map = new HashMap<>();

    public IdToIngredient() {
        map.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        map.put("COTO", new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        map.put("GRBF", new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        map.put("CARN", new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
        map.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        map.put("LETC", new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
        map.put("CHED", new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        map.put("JACK", new Ingredient("JACK", "Monterrey", Ingredient.Type.CHEESE));
        map.put("SLSA", new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
        map.put("SRCR", new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
    }

    @Override
    public Ingredient convert(String id) {
        return map.get(id);
    }
}
