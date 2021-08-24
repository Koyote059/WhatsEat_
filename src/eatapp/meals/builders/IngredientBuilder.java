package eatapp.meals.builders;

import eatapp.meals.Ingredient;
import eatapp.meals.Nutrients;
import eatapp.meals.QuantityType;

import java.util.HashSet;
import java.util.Set;

public class IngredientBuilder {

    private IngredientBuilder(){}

    public static IngredientBuilder getInstance() {
        return new IngredientBuilder();
    }

    private QuantityType quantityType = QuantityType.GRAMS;
    private final Set<String> tags = new HashSet<>();
    private String name = null;
    private float prots = 0;
    private float carbs= 0;
    private float fats= 0;
    private float eurosPrice = 0;

    public IngredientBuilder setName(String name){
        this.name = name;
        return this;
    }

    public IngredientBuilder setPrice(float eurosPrice){
        this.eurosPrice = eurosPrice;
        return this;
    }

    public IngredientBuilder setNutrients(float prots, float carbs, float fats){
        this.prots = prots;
        this.carbs = carbs;
        this.fats = fats;
        return this;
    }

    public IngredientBuilder addTag(String tag){
        this.tags.add(tag);
        return this;
    }

    public IngredientBuilder setQuantityType(QuantityType quantityType){
        this.quantityType = quantityType;
        return this;
    }

    public Ingredient build(){
        Nutrients nutrients = new Nutrients(prots,carbs,fats);
        return new Ingredient(tags,name, quantityType, nutrients,eurosPrice);
    }
}
