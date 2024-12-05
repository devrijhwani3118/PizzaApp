package com.example.project5;

public class ToppingView {
    private String toppingName;

    public ToppingView(String topName) {
        toppingName = topName;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }
}