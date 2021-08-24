package eatapp.meals;

import java.util.Set;

public class Ingredient {

    private final String name;
    private final QuantityType quantityType;
    private final Set<String> tags;
    private final Nutrients nutrients;
    private final float eurosPrice;

    public Ingredient(Set<String> tags, String name, QuantityType quantityType, Nutrients nutrients, float eurosPrice) {
        this.name = name;
        this.tags = tags;
        this.quantityType = quantityType;
        this.nutrients = nutrients;
        this.eurosPrice = eurosPrice;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getName() {
        return name;
    }

    public Nutrients getNutrientsPer100() {
        return nutrients;
    }

    public float getEurosPricePer100() {
        return eurosPrice;
    }

    public QuantityType getQuantityType() {
        return quantityType;
    }
}
