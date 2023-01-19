/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exercicios.bingo;

/**
 *
 * @author rodrigosoares
 */
public class Posicao {

    private int valor;
    private boolean marcado;
    private String simbolo;

    public Posicao(int valor, boolean marcado, String simbolo) {
        this.valor = valor;
        this.marcado = marcado;
        this.simbolo = simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public int getValor() {
        return valor;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
