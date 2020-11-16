package com.example.budgettrackingexpense;

public class Categories {
    String name;
    Double budget;

    public Categories(String name, Double budget) {
        this.name = name;
        this.budget = budget;
    }

    public Categories() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
