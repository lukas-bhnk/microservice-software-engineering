package com.nutrition.sweng.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vitamins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Version
    private long version;

    @JoinColumn(name="FOOD_ID")
    @OneToOne
    @MapsId
    private Food food;

    public Vitamins(){}

    public Vitamins(long id, double c, double fol, double a, double b1, double b2, double b6, double b12, double d, double e, double k, double betacarotin, double niacin, double retinol, Food food) {
        this.id = id;
        this.c = c;
        this.fol = fol;
        this.a = a;
        this.b1 = b1;
        this.b2 = b2;
        this.b6 = b6;
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Vitamins{" +
                "id=" + id +
                ", c='" + c + '\'' +
                ", fol='" + fol + '\'' +
                ", a='" + a + '\'' +
                ", b1='" + b1 + '\'' +
                ", b2='" + b2 + '\'' +
                ", b11='" + b6 + '\'' +
                ", b12='" + b12 + '\'' +
                ", d='" + d + '\'' +
                ", e='" + e + '\'' +
                ", k='" + k + '\'' +
                ", betacarotin='" + betacarotin + '\'' +
                ", niacin='" + niacin + '\'' +
                ", retinol='" + retinol + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vitamins vitamins = (Vitamins) o;
        return id == vitamins.id && Double.compare(vitamins.c, c) == 0 && Double.compare(vitamins.fol, fol) == 0 && Double.compare(vitamins.a, a) == 0 && Double.compare(vitamins.b1, b1) == 0 && Double.compare(vitamins.b2, b2) == 0 && Double.compare(vitamins.b6, b6) == 0 && Double.compare(vitamins.b12, b12) == 0 && Double.compare(vitamins.d, d) == 0 && Double.compare(vitamins.e, e) == 0 && Double.compare(vitamins.k, k) == 0 && Double.compare(vitamins.betacarotin, betacarotin) == 0 && Double.compare(vitamins.niacin, niacin) == 0 && Double.compare(vitamins.retinol, retinol) == 0 && version == vitamins.version && Objects.equals(food, vitamins.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, c, fol, a, b1, b2, b6, b12, d, e, k, betacarotin, niacin, retinol, version, food);
    }
}
