package com.example.budgettrackingexpense;

public class Categories {
    String name;
    Long budget;

    public Categories(String name, Long budget) {
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

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "name: " + this.name +
                ", budget: " + this.budget;
    }
}
