package eatapp.meals;

import java.util.HashSet;
import java.util.Set;

public class Meal {

    private final Set<MealIngredient> ingredients;
    private final Instructions cookingInstructions;
    private final Set<CookingTool> tools;
    private final String name;
    private final Set<String> tags;

    public Meal(Set<MealIngredient> ingredients, Instructions cookingInstructions, Set<CookingTool> tools, String name,Set<String> tags) {
        this.ingredients = ingredients;
        this.cookingInstructions = cookingInstructions;
        this.tools = tools;
        this.name = name;
        this.tags = tags;
    }

    public Set<MealIngredient> getIngredients() {
        return ingredients;
    }

    public Instructions getCookingInstructions() {
        return cookingInstructions;
    }

    public Set<CookingTool> getTools() {
        return tools;
    }

    public float getPrice(){
        float price = 0;
        for(MealIngredient ingredient: ingredients) price += ingredient.getPrice();
        return price;
    }

    public Set<String> getMealTags(){
        return tags;
    }

    public Set<String> getTags() {
        Set<String> tags = new HashSet<>();
        for(MealIngredient mealIngredient: ingredients){
            Ingredient ingredient = mealIngredient.getIngredient();
            tags.addAll(ingredient.getTags()); //TODO -> Questo codice può essere messo nel costruttore
        }
        return tags;
    }

    public String getName(){
        return name;
    }
}
