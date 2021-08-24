package eatapp.database;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import eatapp.meals.Ingredient;
import eatapp.meals.Nutrients;
import eatapp.meals.QuantityType;
import eatapp.meals.builders.IngredientBuilder;
import org.bson.Document;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class IngredientsDatabase {

    public void saveIngredient(Ingredient ingredient){
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        mongoClient.startSession();
        MongoDatabase appDatabase = mongoClient.getDatabase("MealApp");

        MongoCollection<DBObject> collection = appDatabase.getCollection("Ingredients",DBObject.class);

        String ingredientName = ingredient.getName();
        float price = ingredient.getEurosPricePer100();
        Nutrients nutrients  = ingredient.getNutrientsPer100();
        QuantityType quantityType = ingredient.getQuantityType();
        Set<String> tags = ingredient.getTags();

        BasicDBObjectBuilder ingredientObjectBuilder = new BasicDBObjectBuilder();
        ingredientObjectBuilder.append("_id",ingredientName);
        ingredientObjectBuilder.append("price",price);
        ingredientObjectBuilder.append("quantityType",quantityType.name());
        ingredientObjectBuilder.append("tags",tags);

        BasicDBObjectBuilder nutrientsObjectBuilder = new BasicDBObjectBuilder();
        nutrientsObjectBuilder.append("prots",nutrients.getProteinGrams());
        nutrientsObjectBuilder.append("carbs",nutrients.getCarbGrams());
        nutrientsObjectBuilder.append("fats",nutrients.getFatGrams());

        ingredientObjectBuilder.append("nutrients",nutrientsObjectBuilder.get());
        collection.insertOne(ingredientObjectBuilder.get());
    }

    public void removeIngredient(Ingredient ingredient){
        MongoClient mongoClient = new MongoClient();
        MongoDatabase appDatabase = mongoClient.getDatabase("MealApp");
        MongoCollection<DBObject> collection = appDatabase.getCollection("Ingredients",DBObject.class);
        collection.deleteOne(new Document("_id",ingredient.getName()));
    }

    public Set<Ingredient> getIngredients(){
        Set<Ingredient> ingredients = new HashSet<>();
        MongoClient mongoClient = new MongoClient();
        MongoDatabase appDatabase = mongoClient.getDatabase("MealApp");
        MongoCollection<DBObject> collection = appDatabase.getCollection("Ingredients",DBObject.class);
        FindIterable<DBObject> results = collection.find();

        for(DBObject result: results){
            String ingredientName = (String) result.get("_id");
            double price = (Double) result.get("price");
            QuantityType quantityType = QuantityType.valueOf((String) result.get("quantityType"));
            Collection<String> tags = (Collection<String>) result.get("tags");
            DBObject nutrientsObject = (DBObject) result.get("nutrients");

            float prots = (float) ((double)nutrientsObject.get("prots"));
            float carbs = (float) ((double) nutrientsObject.get("carbs"));
            float fats = (float) ((double)nutrientsObject.get("fats"));

            Nutrients nutrients = new Nutrients(prots,carbs,fats);

            IngredientBuilder ingredientBuilder = IngredientBuilder.getInstance();
            ingredientBuilder.setName(ingredientName);
            ingredientBuilder.setPrice((float) price);
            ingredientBuilder.setQuantityType(quantityType);
            ingredientBuilder.setNutrients(prots,carbs,fats);

            Ingredient ingredient = ingredientBuilder.build();
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    public void showAll() {
        MongoClient mongoClient = new MongoClient();
        mongoClient.startSession();
        MongoDatabase appDatabase = mongoClient.getDatabase("MealApp");
        MongoCollection<DBObject> collection = appDatabase.getCollection("Ingredients",DBObject.class);
        FindIterable<DBObject> results = collection.find();
        for(DBObject result: results){
            System.out.println(result);
        }
        mongoClient.close();
    }
}
