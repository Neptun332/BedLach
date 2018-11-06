package com.example.sklab.bedlach.utils;

import java.util.Random;

public class RandomNumberGenerator {

    public int randomInteger(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min)+ 1) + min;
    }

    public double randomDouble(double min, double max) {
        return min + new Random().nextDouble() * (max - min);
    }


}
