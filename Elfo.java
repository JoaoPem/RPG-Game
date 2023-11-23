package com.example.a20trabalhorpg;

public class Elfo extends Raca{
    @Override
    public int setDestreza(int destreza) {
        return super.setDestreza(destreza + 1);
    }
}
