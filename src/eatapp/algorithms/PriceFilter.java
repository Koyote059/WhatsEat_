package eatapp.algorithms;

import eatapp.managers.MealsManager;
import eatapp.meals.Meal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PriceFilter implements MealFilter {

    private final float priceRange;

    public PriceFilter(float priceRange){
        this.priceRange = priceRange;
    }

    @Override
    public List<Meal> filter(Collection<Meal> meals) {
        List<Meal> filteredMeals = new ArrayList<>();
        for(Meal meal: meals){
            float price = meal.getPrice();
            if(price<priceRange) filteredMeals.add(meal);
        }
        filteredMeals.sort((meal1,meal2) -> (int) (meal1.getPrice()-meal2.getPrice()));
        return filteredMeals;
    }
}
