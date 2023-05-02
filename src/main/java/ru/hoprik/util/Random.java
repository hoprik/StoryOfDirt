package ru.hoprik.util;

public class Random {
    public static int random(int a, int b){
        return a + (int) (Math.random() * ((b - a) + 1));
    }
}
