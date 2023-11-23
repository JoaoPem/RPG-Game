package com.example.a20trabalhorpg;

import java.util.Random;

public class Dado {
    private Random random = new Random();

    public int sortear() {
        int d = random.nextInt(16) + 3; //exclusivo o 19
        return d;
    }
}
