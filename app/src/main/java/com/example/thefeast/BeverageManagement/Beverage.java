package com.example.thefeast.BeverageManagement;

public class Beverage {

    String beverageId;
    String beverageName;
    String beverageIngredient;
    String beverageInstructions;
    String beverageGenre;

    public Beverage(){


    }

    public Beverage(String beverageId, String beverageName, String beverageIngredient, String beverageInstructions, String beverageGenre) {
        this.beverageId = beverageId;
        this.beverageName = beverageName;
        this.beverageIngredient = beverageIngredient;
        this.beverageInstructions = beverageInstructions;
        this.beverageGenre = beverageGenre;
    }

    public String getBeverageId() {
        return beverageId;
    }

    public String getBeverageName() {
        return beverageName;
    }

    public String getBeverageIngredient() {
        return beverageIngredient;
    }

    public String getBeverageInstructions() {
        return beverageInstructions;
    }

    public String getBeverageGenre() {
        return beverageGenre;
    }
}