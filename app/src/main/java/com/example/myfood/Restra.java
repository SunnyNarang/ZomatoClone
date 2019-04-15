package com.example.myfood;

/**
 * Created by Tanmay Ranjan on 19-Apr-18.
 */

public class Restra {
    private int id;
    private String name;

    private String add;
    private String tme;
    private String cat1;
    private String cat2;
    private String cat3;
    private String rate;
    private String image;

    public Restra(int id, String name, String add, String tme, String cat1, String cat2, String cat3, String rate, String image) {
        this.id = id;
        this.name = name;
        this.add = add;
        this.tme = tme;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
        this.rate = rate;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdd() {
        return add;
    }

    public String getTme() {
        return tme;
    }

    public String getCat1() {
        return cat1;
    }

    public String getCat2() {
        return cat2;
    }

    public String getCat3() {
        return cat3;
    }

    public String getRate() {
        return rate;
    }

    public String getImage() {
        return image;
    }
}