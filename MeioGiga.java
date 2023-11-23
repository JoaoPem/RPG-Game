package com.example.a20trabalhorpg;

public class MeioGiga extends Raca{
    @Override
    public int setConstituicao(int constituicao) {
        return super.setConstituicao(constituicao + 2);
    }

    @Override
    public int setCarisma(int carisma) {
        return super.setCarisma(carisma - 1);
    }

}
