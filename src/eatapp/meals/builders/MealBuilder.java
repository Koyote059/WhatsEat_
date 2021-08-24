package eatapp.meals.builders;

import eatapp.meals.*;

import java.util.*;

public class MealBuilder {

    private MealBuilder(){}

    public static MealBuilder getInstance() {
        return new MealBuilder();
    }

    private final Set<MealIngredient> ingredients = new HashSet<>();
    private final Set<CookingTool> tools = new HashSet<>();
    private final List<String> instructions = new ArrayList<>();
    private final Set<String> tags = new HashSet<>();
    private int minutesToCook = 0;
    private String name = null;

    public MealBuilder addIngredient(MealIngredient mealIngredient){
        this.ingredients.add(mealIngredient);
        return this;
    }

    public MealBuilder addIngredient(Ingredient ingredient, float quantity){
        this.ingredients.add(new MealIngredient(ingredient,quantity));
        return this;
    }

    public MealBuilder addCookingTool(CookingTool tool){
        this.tools.add(tool);
        return this;
    }

    public MealBuilder setMinutesToCook(int minutesToCook){
        this.minutesToCook = minutesToCook;
        return this;
    }

    public MealBuilder addInstructions(String... instructions){
        this.instructions.addAll(Arrays.asList(instructions));
        return this;
    }

    public MealBuilder setName(String name){
        this.name = name;
        return this;
    }

    public MealBuilder addTag(String tag){
        this.tags.add(tag);
        return this;
    }

    public Meal build(){
        Instructions cookingInstructions = new Instructions(instructions,minutesToCook);
        return new Meal(ingredients,cookingInstructions,tools,name,tags);
    }
}
