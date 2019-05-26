package com.example.thefeast;

public class Recipe {

    String foodId;
    String foodDescription;
    String foodIngredient;
    String foodInstruction;
    String foodFeast;

    public Recipe(){


    }

    public Recipe(String foodId, String foodDescription, String foodIngredient, String foodInstruction, String foodFeast) {
        this.foodId = foodId;
        this.foodDescription = foodDescription;
        this.foodIngredient = foodIngredient;
        this.foodInstruction = foodInstruction;
        this.foodFeast = foodFeast;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public String getFoodIngredient() {
        return foodIngredient;
    }

    public String getFoodInstruction() {
        return foodInstruction;
    }

    public String getFoodFeast() {
        return foodFeast;
    }
}
