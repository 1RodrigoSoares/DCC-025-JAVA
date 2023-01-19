/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exercicios.bingo;

import java.util.*;

/**
 *
 * @author rodrigosoares
 */
public class Cartela {

    private Posicao[][] tabela;
    private static int cartelasAlocadas = 0;
    private int numeroCartela;

    public Cartela() {
        this.tabela = new Posicao[5][5];
        cartelasAlocadas++;
        this.numeroCartela = cartelasAlocadas;
        this.preencheValores();
    }

    public Posicao[][] getTabela() {
        return tabela;
    }

    public int getNumeroCartela() {
        return numeroCartela;
    }

    public void setTabela(Posicao[][] tabela) {
        this.tabela = tabela;
    }

    private void preencheValores() {
        Set<Integer> valoresEscolhidos = new HashSet<>();
        int numeroSorteado;
        boolean flag;
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                flag = false;
                while (!flag) {
                    numeroSorteado = sorteiaNumero(j);
                    if (valoresEscolhidos.add(numeroSorteado)) {
                        Posicao posicao = new Posicao(numeroSorteado, false, " ");
                        this.tabela[i][j] = posicao;
                        flag = true;
                    }
                }
            }
        }
        ordenaValores();
    }

    protected static int sorteiaNumero(int i) {
        Random random = new Random();
        int numeroSorteado;
        switch (i) {
            case 0:
                numeroSorteado = random.nextInt(15) + 1;
                break;
            case 1:
                numeroSorteado = random.nextInt((30 - 16) + 1) + 16;
                break;
            case 2:
                numeroSorteado = random.nextInt((45 - 31) + 1) + 31;
                break;
            case 3:
                numeroSorteado = random.nextInt((60 - 46) + 1) + 46;
                break;
            case 4:
                numeroSorteado = random.nextInt((75 - 61) + 1) + 61;
                break;
            case -1:
                numeroSorteado = random.nextInt(75) + 1;
                break;
            default:
                numeroSorteado = 0;
                break;
        }
        return numeroSorteado;
    }

    protected void imprimeCartela() {
        System.out.println("Cartela Nº" + this.numeroCartela);
        System.out.println("   B      I      N      G      O");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 2) {
                    System.out.print("       ");
                } else {
                    System.out.print("[" + this.tabela[i][j].getSimbolo() + "]" + String.format("%02d", this.tabela[i][j].getValor()) + "  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void ordenaValores() {
        ordenaValoresPelaColuna(0);
        ordenaValoresPelaColuna(1);
        ordenaValoresPelaColuna(2);
        ordenaValoresPelaColuna(3);
        ordenaValoresPelaColuna(4);
        this.tabela[2][2].setValor(0);       //torna a posição do meio nao relevante
        this.tabela[2][2].setMarcado(true);//torna a posição do meio nao relevante
    }

    private void ordenaValoresPelaColuna(int coluna) {
        int[] numeros = new int[5];
        for (int i = 0; i < 5; i++) {
            numeros[i] = this.tabela[i][coluna].getValor();
        }
        Arrays.sort(numeros);
        for (int i = 0; i < 5; i++) {
            this.tabela[i][coluna].setValor(numeros[i]);
        }
    }

    protected boolean verificaValorNaTabela(int numeroSorteado) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!tabela[i][j].isMarcado() && tabela[i][j].getValor() == numeroSorteado) {
                    tabela[i][j].setMarcado(true);
                    tabela[i][j].setSimbolo("x");
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean verificaVitoria(int modoDeJogo) {
        if (modoDeJogo == 1) {
            for (int j = 0; j < 5; j++) {
                if (verificaVitoriaColuna(j)) {
                    return true;
                }
                if (verificaVitoriaLinha(j)) {
                    return true;
                }
            }
        } else {
            return verificaVItoriaCruz();
        }
        return false;
    }

    private boolean verificaVitoriaColuna(int coluna) {
        for (int i = 0; i < 5; i++) {
            if (!this.tabela[i][coluna].isMarcado()) {
                return false;
            }
        }
        return true;
    }

    private boolean verificaVitoriaLinha(int linha) {
        for (int j = 0; j < 5; j++) {
            if (!this.tabela[linha][j].isMarcado()) {
                return false;
            }
        }
        return true;
    }

    private boolean verificaVItoriaCruz() {
        for (int i = 0; i < 5; i++) {
            if (!this.tabela[i][2].isMarcado()) {
                return false;
            }
            if (!this.tabela[2][i].isMarcado()) {
                return false;
            }
        }
        return true;
    }
}
