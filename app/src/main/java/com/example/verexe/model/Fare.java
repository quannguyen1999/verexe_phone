package com.example.verexe.model;

public class Fare {
    private float original;
    private int operator_discount;
    private int verexe_discount;

    public Fare(float original, int operator_discount, int verexe_discount) {
        this.original = original;
        this.operator_discount = operator_discount;
        this.verexe_discount = verexe_discount;
    }

    public Fare() {
    }

    public float getOriginal() {
        return original;
    }

    public void setOriginal(float original) {
        this.original = original;
    }

    public int getOperator_discount() {
        return operator_discount;
    }

    public void setOperator_discount(int operator_discount) {
        this.operator_discount = operator_discount;
    }

    public int getVerexe_discount() {
        return verexe_discount;
    }

    public void setVerexe_discount(int verexe_discount) {
        this.verexe_discount = verexe_discount;
    }

    @Override
    public String toString() {
        return "Fare{" +
                "original=" + original +
                ", operator_discount=" + operator_discount +
                ", verexe_discount=" + verexe_discount +
                '}';
    }
}
