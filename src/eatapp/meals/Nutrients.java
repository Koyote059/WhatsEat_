package eatapp.meals;

public class Nutrients {

    private final int CALORIES_PER_PROTEIN = 4;
    private final int CALORIES_PER_FAT = 9;
    private final int CALORIES_PER_CARB = 4;

    private final float proteinGrams;
    private final float carbGrams;
    private final float fatGrams;


    public Nutrients(float proteinGrams, float carbGrams, float fatGrams) {
        this.proteinGrams = proteinGrams;
        this.carbGrams = carbGrams;
        this.fatGrams = fatGrams;
    }

    public float getProteinGrams() {
        return proteinGrams;
    }

    public float getCarbGrams() {
        return carbGrams;
    }

    public float getFatGrams() {
        return fatGrams;
    }

    public float getCalories(){
        return carbGrams*CALORIES_PER_CARB +
                fatGrams*CALORIES_PER_FAT +
                proteinGrams*CALORIES_PER_PROTEIN;
    }
}
