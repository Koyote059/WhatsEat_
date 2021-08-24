package eatapp;

import eatapp.database.IngredientsDatabase;
import eatapp.meals.Ingredient;
import eatapp.meals.QuantityType;
import eatapp.meals.builders.IngredientBuilder;
import eatapp.meals.builders.MealBuilder;
/*
import java.util.Scanner;

public class Console {

    public static void main(String... args){
        Ingredient egg = IngredientBuilder.getInstance().
                setName("Carrot").setNutrients(5,10,3).setPrice(0.5f).addTag("Vegetariano").build();




    }

    private void registerIngredients(){
        IngredientsDatabase ingredientsDatabase = new IngredientsDatabase();
        Scanner scanner = new Scanner(System.in);
        while(true){
            IngredientBuilder ingredientBuilder = IngredientBuilder.getInstance();
            System.out.println("Insert ingredient name:");
            ingredientBuilder.setName(scanner.nextLine());
            System.out.println("Insert ingredient price:");
            ingredientBuilder.setPrice(Float.parseFloat(scanner.nextLine()));
            System.out.println("Insert proteins: ");
            float proteins = Float.parseFloat(scanner.nextLine());
            System.out.println("Insert carbs: ");
            float carbs = Float.parseFloat(scanner.nextLine());
            System.out.println("Insert fats: ");
            float fats = Float.parseFloat(scanner.nextLine());
            ingredientBuilder.setNutrients(proteins,carbs,fats);
            System.out.println("Choose quantityType: \n" +
                    "1) Grams\n" +
                    "2) Liters\n" +
                    "3) Milliliters\n" +
                    "4) Pieces");

            QuantityType quantityType = null;
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1 -> quantityType = QuantityType.GRAMS;
                case 2 -> quantityType = QuantityType.LITERS;
                case 3 -> quantityType = QuantityType.MILLILITERS;
                case 4 -> quantityType = QuantityType.PIECES;
            }
            ingredientBuilder.setQuantityType(quantityType);

            Ingredient ingredient = ingredientBuilder.build();
            ingredientsDatabase.saveIngredient(ingredient);
            ingredientsDatabase.showAll();
        }
    }

    private void registerMeals(){
        IngredientsDatabase ingredientsDatabase = new IngredientsDatabase();
        Scanner scanner = new Scanner(System.in);
        while(true){
            IngredientBuilder ingredient = IngredientBuilder.getInstance();
            System.out.println("Insert meal name:");
            ingredient.setName(scanner.nextLine());
            System.out.println("Insert minutes to cook:");
            ingredient.setMinutesToCook(Integer.parseInt(scanner.nextLine()));
            System.out.println("Insert proteins: ");

            ingredient.setNutrients(proteins,carbs,fats);
            System.out.println("Choose quantityType: \n" +
                    "1) Grams\n" +
                    "2) Liters\n" +
                    "3) Milliliters\n" +
                    "4) Pieces");

            QuantityType quantityType = null;
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1 -> quantityType = QuantityType.GRAMS;
                case 2 -> quantityType = QuantityType.LITERS;
                case 3 -> quantityType = QuantityType.MILLILITERS;
                case 4 -> quantityType = QuantityType.PIECES;
            }
            ingredient.setQuantityType(quantityType);

            Ingredient ingredient = ingredient.build();
            ingredientsDatabase.saveIngredient(ingredient);
            ingredientsDatabase.showAll();
        }
    }
}
*/