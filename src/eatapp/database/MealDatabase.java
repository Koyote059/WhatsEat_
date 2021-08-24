package eatapp.database;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import eatapp.database.objectconverters.MongoObjectBuilder;
import eatapp.meals.CookingTool;
import eatapp.meals.Ingredient;
import eatapp.meals.Meal;
import eatapp.meals.builders.IngredientBuilder;
import eatapp.meals.builders.MealBuilder;
import eatapp.meals.builders.MealIngredientBuilder;
import org.bson.Document;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MealDatabase {

    public void saveMeal(Meal meal){
        MongoClient mongoClient = new MongoClient();
        MongoDatabase appDatabase = mongoClient.getDatabase("MealApp");
        MongoCollection<DBObject> collection = appDatabase.getCollection("Meals",DBObject.class);
        MongoObjectBuilder mealMongoBuilder = new MongoObjectBuilder();
        mealMongoBuilder.setName(meal.getName());
        mealMongoBuilder.setIngredients(meal.getIngredients());
        mealMongoBuilder.setTools(meal.getTools());
        mealMongoBuilder.setTags(meal.getMealTags());
        mealMongoBuilder.setInstructions(meal.getCookingInstructions());
        DBObject mongoMealObject = mealMongoBuilder.build();
        collection.insertOne(mongoMealObject);
    }

    public void removeMeal(Meal meal){
        MongoClient mongoClient = new MongoClient();
        MongoDatabase appDatabase = mongoClient.getDatabase("MealApp");
        MongoCollection<DBObject> collection = appDatabase.getCollection("Meals",DBObject.class);
        collection.deleteOne(new Document("_id",meal.getName()));
    }

    public Set<Meal> getMeals(Collection<Ingredient> ingredients){
        Set<Meal> meals = new HashSet<>();
        MongoClient mongoClient = new MongoClient();
        MongoDatabase appDatabase = mongoClient.getDatabase("MealApp");
        MongoCollection<DBObject> collection = appDatabase.getCollection("Meals",DBObject.class);
        FindIterable<DBObject> results = collection.find();
        for(DBObject mongoMealObject: results){
            MealBuilder mealBuilder = MealBuilder.getInstance();
            mealBuilder.setName((String) mongoMealObject.get("_id"));
            Collection<String> tags = (Collection<String>) mongoMealObject.get("tags");
            tags.forEach(mealBuilder::addTag);
            Collection<String> tools = (Collection<String>) mongoMealObject.get("tools");
            tools.forEach(tool -> mealBuilder.addCookingTool(CookingTool.valueOf(tool)));
            DBObject mongoInstructionsObject = (DBObject) mongoMealObject.get("mealInstructions");
            int minutesToCook = (int) mongoInstructionsObject.get("time");
            mealBuilder.setMinutesToCook(minutesToCook);
            Collection<String> instructions = (Collection<String>) mongoInstructionsObject.get("instructions");
            mealBuilder.addInstructions(instructions.toArray(String[]::new));
            Collection<DBObject> mongoInstructionObjects = (Collection<DBObject>) mongoMealObject.get("ingredients");
            mongoInstructionObjects.forEach(mongoIngredientObject ->{
                MealIngredientBuilder mealIngredientBuilder = MealIngredientBuilder.getInstance();
                String ingredientName = (String) mongoIngredientObject.get("ingredient");
                int quantity = (int) mongoIngredientObject.get("quantity");
                Optional<Ingredient> ingredientOptional = ingredients.stream().filter(ingredient -> ingredient.getName().equals(ingredientName)).findAny();
                if(ingredientOptional.isPresent()){
                    mealIngredientBuilder.setMainIngredient(ingredientOptional.get(),quantity);
                } else {
                    mealIngredientBuilder.setMainIngredient(IngredientBuilder.getInstance().setName(ingredientName).build(),quantity);
                }
                Collection<String> replacementNames = (Collection<String>) mongoIngredientObject.get("replacements");
                replacementNames.forEach( replacementName -> {
                    Optional<Ingredient> replacementOptional = ingredients.stream().filter(ingredient -> ingredient.getName().equals(replacementName)).findAny();
                    if(replacementOptional.isPresent()){
                        mealIngredientBuilder.addReplacement(replacementOptional.get(), quantity);
                    } else {
                        mealIngredientBuilder.setMainIngredient(IngredientBuilder.getInstance().setName(replacementName).build(),quantity);
                    }
                });
            });
            Meal meal = mealBuilder.build();
            meals.add(meal);
        }
        return meals;
    }

}
