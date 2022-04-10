package com.nutrition.sweng.Model;

import javax.persistence.*;

@Entity
public class Vitamins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //have to set nullable true, because jpa sets it automatically to Not Null
    @Column(nullable = true)
    private double c;
    @Column(nullable = true)
    private double fol;
    @Column(nullable = true)
    private double a;
    @Column(nullable = true)
    private double b1;
    @Column(nullable = true)
    private double b2;
    @Column(nullable = true)
    private double b11;
    @Column(nullable = true)
    private double b12;
    @Column(nullable = true)
    private double d;
    @Column(nullable = true)
    private double e;
    @Column(nullable = true)
    private double k;
    @Column(nullable = true)
    private double betacarotin;
    @Column(nullable = true)
    private double niacin;
    @Column(nullable = true)
    private double retinol;

    @JoinColumn(name="FOOD_ID")
    @OneToOne
    @MapsId
    private Food food;

    public Vitamins(){}

    public Vitamins(long id, double c, double fol, double a, double b1, double b2, double b11, double b12, double d, double e, double k, double betacarotin, double niacin, double retinol, Food food) {
        this.id = id;
        this.c = c;
        this.fol = fol;
        this.a = a;
        this.b1 = b1;
        this.b2 = b2;
        this.b11 = b11;
        this.b12 = b12;
        this.d = d;
        this.e = e;
        this.k = k;
        this.betacarotin = betacarotin;
        this.niacin = niacin;
        this.retinol = retinol;
        this.food = food;
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

    public double getB11() {
        return b11;
    }

    public void setB11(double b11) {
        this.b11 = b11;
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
