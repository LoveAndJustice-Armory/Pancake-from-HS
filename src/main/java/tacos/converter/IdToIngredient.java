package tacos.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.domain.Ingredient;
import tacos.repository.IngredientRepository;

import java.util.HashMap;

@Component
public class IdToIngredient implements Converter<String, Ingredient> {
    private IngredientRepository ingredientRepository;

    private HashMap<String, Ingredient> map = new HashMap<>();

    public IdToIngredient(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        /**/
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
