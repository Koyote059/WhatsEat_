package eatapp.algorithms;

import eatapp.meals.Meal;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface MealFilter {

    List<Meal> filter(Collection<Meal> meals);

}
