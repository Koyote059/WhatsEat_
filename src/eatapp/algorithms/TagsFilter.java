package eatapp.algorithms;

import eatapp.meals.Ingredient;
import eatapp.meals.Meal;
import eatapp.meals.MealIngredient;

import java.util.*;

public class TagsFilter implements MealFilter {

    private final Set<String> tags;

    public TagsFilter(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public List<Meal> filter(Collection<Meal> meals) {
        List<Meal> filteredMeals = new ArrayList<>();
        for(Meal meal: meals){
            Set<String> tags = meal.getTags();
            if(this.tags.containsAll(tags)) filteredMeals.add(meal);
        }
        return filteredMeals;
    }

}
