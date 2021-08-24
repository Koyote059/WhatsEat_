package eatapp.algorithms;

import eatapp.meals.Ingredient;
import eatapp.meals.Meal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCalculator {

    private final Map<Ingredient,Float> ingredientQuantityMap = new HashMap<>();

    public Map<Ingredient,Float> getShoppingList(Collection<Meal> meals){
        for(Meal meal: meals){
            meal.getIngredients().forEach( mealIngredient -> ingredientQuantityMap.
                    put(mealIngredient.getIngredient(),mealIngredient.getQuantity()));
        }
        return ingredientQuantityMap;
    }

}
