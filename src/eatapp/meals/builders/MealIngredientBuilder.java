package eatapp.meals.builders;

import eatapp.meals.Ingredient;
import eatapp.meals.Meal;
import eatapp.meals.MealIngredient;
import eatapp.meals.QuantityType;

import java.util.HashSet;
import java.util.Set;

public class MealIngredientBuilder {

    private Ingredient mainIngredient = null;
    private float quantity = 0;
    private boolean optional = false;
    private final Set<MealIngredient> replacements = new HashSet<>();

    private MealIngredientBuilder(){}

    public static MealIngredientBuilder getInstance() {
        return new MealIngredientBuilder();
    }

    public MealIngredientBuilder setMainIngredient(Ingredient ingredient, float quantity){
        this.mainIngredient = ingredient;
        this.quantity = quantity;
        return this;
    }

    public MealIngredientBuilder addReplacement(Ingredient ingredient, float quantity){
        this.replacements.add(new MealIngredient(ingredient,quantity));
        return this;
    }

    public MealIngredientBuilder setOptional(boolean optional){
        this.optional = optional;
        return this;
    }

    public MealIngredient build(){
        return new MealIngredient(mainIngredient,quantity,replacements,optional);
    }


}
