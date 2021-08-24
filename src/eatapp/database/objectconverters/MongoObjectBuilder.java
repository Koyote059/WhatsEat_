package eatapp.database.objectconverters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import eatapp.meals.CookingTool;
import eatapp.meals.Instructions;
import eatapp.meals.MealIngredient;

import java.util.*;

public class MongoObjectBuilder {

    private final BasicDBObject mongoMealObject = new BasicDBObject();

    public MongoObjectBuilder setName(String name){
        mongoMealObject.append("_id",name);
        return this;
    }

    public MongoObjectBuilder setIngredients(Collection<MealIngredient> ingredients){
        Set<DBObject> mongoIngredientObjects = new HashSet<>();
        for(MealIngredient mealIngredient: ingredients) {
            BasicDBObject mongoIngredientsObject = new BasicDBObject();
            String ingredientName = mealIngredient.getIngredient().getName();
            float quantity = mealIngredient.getQuantity();
            mongoIngredientsObject.append("ingredient",ingredientName);
            mongoIngredientsObject.append("quantity",quantity);
            Set<DBObject> mongoReplacementObjects = new HashSet<>();
            mealIngredient.getReplacements().forEach( replacement -> {
                BasicDBObject mongoReplacementObject = new BasicDBObject();
                String replacementIngredientName = mealIngredient.getIngredient().getName();
                float replacementQuantity = mealIngredient.getQuantity();
                mongoReplacementObject.append("ingredient",replacementIngredientName);
                mongoReplacementObject.append("quantity",replacementQuantity);
                mongoReplacementObjects.add(mongoMealObject);
            });
            if(!mongoReplacementObjects.isEmpty())
                mongoIngredientsObject.append("replacements",mongoReplacementObjects);
            mongoIngredientObjects.add(mongoIngredientsObject);
        }
        mongoMealObject.append("ingredients",mongoIngredientObjects);
        return this;
    }

    public MongoObjectBuilder setTools(Set<CookingTool> tools) {
        mongoMealObject.append("tools",tools);
        return this;
    }

    public MongoObjectBuilder setTags(Set<String> tags) {
        mongoMealObject.append("tags",tags);
        return this;
    }

    public MongoObjectBuilder setInstructions(Instructions instructions) {
        BasicDBObject mongoInstructionsObject = new BasicDBObject();
        mongoInstructionsObject.append("time",instructions.getMinutesToCook());
        mongoInstructionsObject.append("instructions",instructions.getInstructions());
        mongoMealObject.append("mealInstructions",mongoInstructionsObject);
        return this;
    }

    public DBObject build() {
        return mongoMealObject;
    }

}
