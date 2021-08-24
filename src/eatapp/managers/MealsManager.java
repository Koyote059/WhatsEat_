package eatapp.managers;

import eatapp.meals.Meal;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MealsManager {

    private final Set<Meal> meals = new HashSet<>();


    public boolean addMeal(Meal meal){
        return meals.add(meal);
    }

    public Set<Meal> getMeals(){
        return Collections.unmodifiableSet(meals);
    }


}
