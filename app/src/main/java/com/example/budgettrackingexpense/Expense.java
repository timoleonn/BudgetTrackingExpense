package com.example.budgettrackingexpense;

public class Expense {
    private String date;
    private String note;
    private String category;
    private Double amount;

    public Expense(String date, String note, String category, Double amount) {
        this.date = date;
        this.note = note;
        this.category = category;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
