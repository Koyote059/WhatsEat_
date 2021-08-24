package eatapp.algorithms;

import eatapp.meals.Meal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class TimeFilter implements MealFilter {

    private final int maxMinutes;

    public TimeFilter(int maxMinutes){
        this.maxMinutes = maxMinutes;
    }

    @Override
    public List<Meal> filter(Collection<Meal> meals) {
        List<Meal> filteredMeals = new ArrayList<>();
        for(Meal meal: meals){
            float minutesToCook = meal.getCookingInstructions().getMinutesToCook();
            if(minutesToCook<maxMinutes) filteredMeals.add(meal);
        }
        filteredMeals.sort(Comparator.comparingInt(meal -> meal.getCookingInstructions().getMinutesToCook()));
        return filteredMeals;
    }
}
