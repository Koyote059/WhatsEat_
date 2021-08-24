package eatapp.algorithms;

import eatapp.meals.Ingredient;
import eatapp.meals.Meal;
import eatapp.meals.MealIngredient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class IngredientsFilter implements MealFilter {


    private final Set<Ingredient> ingredients;

    public IngredientsFilter(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public List<Meal> filter(Collection<Meal> meals) {
        List<Meal> filteredMeals = new ArrayList<>();
        for(Meal meal: meals){
            boolean containsIngredients;
            for(MealIngredient mealIngredient: meal.getIngredients()){
                if(mealIngredient.isOptional()) continue;
                if(!ingredients.contains(mealIngredient.getIngredient())){
                    containsIngredients = false;
                    for(MealIngredient replacement : mealIngredient.getReplacements()){
                        if(ingredients.contains(replacement.getIngredient())){
                            containsIngredients = true;
                            break;
                        }
                    }
                    if(!containsIngredients) break;
                }
            }
        }
        return filteredMeals;
    }
}
