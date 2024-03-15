package com.example.desapp008.api;

public class Geometry {
    private double[] coordinates;

    public double getLingitude(){
        return coordinates[0];
    }

    public double getLatitude(){
        return coordinates[1];
    }
}
