package com.nutrition.sweng.DTO;

import com.nutrition.sweng.Model.Vitamins;

public class VitaminsDto {
    private long id;
    private double c;
    private double fol;
    private double a;
    private double b1;
    private double b2;
    private double b6;
    private double b12;
    private double d;
    private double e;
    private double k;
    private double betacarotin;
    private double niacin;
    private double retinol;

    public VitaminsDto(){
    }

    public VitaminsDto(Vitamins v){
        this.id = v.getId();
        this.a = v.getA();
        this.b1 = v.getB1();
        this.b2 = v.getB2();
        this.b6 = v.getB6();
        this.b12 = v.getB12();
        this.c = v.getC();
        this.d = v.getD();
        this.e = v.getE();
        this.k = v.getK();
        this.fol = v.getFol();
        this.niacin = v.getNiacin();
        this.betacarotin = v.getBetacarotin();
        this.retinol = v.getRetinol();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getFol() {
        return fol;
    }

    public void setFol(double fol) {
        this.fol = fol;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB1() {
        return b1;
    }

    public void setB1(double b1) {
        this.b1 = b1;
    }

    public double getB2() {
        return b2;
    }

    public void setB2(double b2) {
        this.b2 = b2;
    }

    public double getB6() {
        return b6;
    }

    public void setB6(double b6) {
        this.b6 = b6;
    }

    public double getB12() {
        return b12;
    }

    public void setB12(double b12) {
        this.b12 = b12;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getBetacarotin() {
        return betacarotin;
    }

    public void setBetacarotin(double betacarotin) {
        this.betacarotin = betacarotin;
    }

    public double getNiacin() {
        return niacin;
    }

    public void setNiacin(double niacin) {
        this.niacin = niacin;
    }

    public double getRetinol() {
        return retinol;
    }

    public void setRetinol(double retinol) {
        this.retinol = retinol;
    }
}
