/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.atv_oo.jogodavelha;

import java.util.*;

/**
 *
 * @author rodrigosoares
 */
public class Bot {

    private char simbolo;
    private String nome;

    public Bot(char simbolo) {
        this.nome = "Bot";
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public String getNome() {
        return nome;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public int[] realizaJogada() {
        int[] numerosEscolhidos = new int[3];
        Random random = new Random();
        numerosEscolhidos[0] = random.nextInt(3);
        numerosEscolhidos[1] = random.nextInt(3);
        numerosEscolhidos[2] = random.nextInt(3);
        return numerosEscolhidos;
    }
}
