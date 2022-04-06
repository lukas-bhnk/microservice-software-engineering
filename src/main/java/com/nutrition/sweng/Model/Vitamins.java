package com.nutrition.sweng.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private double b11;

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

    private double b12;
    private double d;
    private double e;
    private double k;
    private double betacarotin;
    private double niacin;
    private double retinol;
}