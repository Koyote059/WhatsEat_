package eatapp.meals;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MealIngredient {

    private final Ingredient ingredient;
    private final float quantity;
    private final boolean optional;
    private final Set<MealIngredient> replacements;

    public MealIngredient(Ingredient ingredient, float quantity, Set<MealIngredient> replacements, boolean optional) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.optional = optional;
        this.replacements = replacements;
    }

    public MealIngredient(Ingredient ingredient,float quantity){
        this(ingredient,quantity,new HashSet<>(),false);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getPrice(){
        return ingredient.getEurosPricePer100()*quantity;
    }

    public Set<MealIngredient> getReplacements() {
        return Collections.unmodifiableSet(replacements);
    }

    public boolean isOptional(){
        return optional;
    }
}
