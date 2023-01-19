/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.exercicios.bingo;

import java.util.*;

/**
 *
 * @author rodrigosoares
 */
public class Bingo {

    private int qtdCartelas;
    private List<Cartela> tdsCartelas = new ArrayList<>();
    private int modoDeJogo;
    private boolean jogoFinalizado = false;

    public Bingo(int qtdCartelas, int modoDeJogo) {
        this.qtdCartelas = qtdCartelas;
        this.modoDeJogo = modoDeJogo;
        geraTodasAsCartelas();
    }

    public void setJogoFinalizado(boolean jogoFinalizado) {
        this.jogoFinalizado = jogoFinalizado;
    }

    public boolean isJogoFinalizado() {
        return jogoFinalizado;
    }

    public int getQtdCartelas() {
        return qtdCartelas;
    }

    public List<Cartela> getTdsCartelas() {
        return tdsCartelas;
    }

    public int getModoDeJogo() {
        return modoDeJogo;
    }

    private void geraTodasAsCartelas() {
        for (int i = 0; i < this.qtdCartelas; i++) {
            Cartela cartela = new Cartela();
            tdsCartelas.add(cartela);
        }
    }

    private void imprimeTodasAsCartelas() {
        for (Cartela cartela : tdsCartelas) {
            cartela.imprimeCartela();
        }
    }

    private void verificaNSorteadoEmTdsCartelas(int num) {
        int ganhadoresRodada = 0;
        for (Cartela cartela : tdsCartelas) {
            if (cartela.verificaValorNaTabela(num)) {
                System.out.println("O jogador " + cartela.getNumeroCartela() + " marcou essa rodada");
                ganhadoresRodada++;
            }
        }
        if (ganhadoresRodada == 0) {
            System.out.println("Ninguem marcou nesta rodada!");
        }
    }

    private static void pulaLinhas() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    private static Bingo introducao() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bem vindo ao Bingo!");
        System.out.println("Digite o número de jogadores:");
        int numeroJogadores = teclado.nextInt();
        System.out.println("Qual modo de jogo?");
        System.out.println("1 - Linha/Coluna");
        System.out.println("2 - Cruz");
        int modoJogo = teclado.nextInt();
        Bingo jogo = new Bingo(numeroJogadores, modoJogo);
        return jogo;
    }

    private boolean veficaFimDoJogo() {
        int contadorVitoriosos = 0;
        for (Cartela cartela : tdsCartelas) {
            if (cartela.verificaVitoria(modoDeJogo)) {
                System.out.println("O jogador " + cartela.getNumeroCartela() + " venceu! Parabéns");
                contadorVitoriosos++;
            }
        }
        if (contadorVitoriosos > 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean continuarBingo = true;
        boolean continuarRodada = true;
        boolean numeroRodadaEscolhido = false;
        int numeroSorteado = 0;
        char resposta = ' ';
        Set<Integer> numerosJaSorteados = new HashSet<>();
        Scanner teclado = new Scanner(System.in);

        do {
            Bingo bingo = introducao();
            bingo.imprimeTodasAsCartelas();
            pulaLinhas();
            while (continuarRodada) {
                numeroRodadaEscolhido = false;
                while (!numeroRodadaEscolhido) {
                    numeroSorteado = Cartela.sorteiaNumero(-1);
                    if (numerosJaSorteados.add(numeroSorteado)) {
                        numeroRodadaEscolhido = true;
                    }
                }
                System.out.println("O numero sorteado foi: " + numeroSorteado);
                bingo.verificaNSorteadoEmTdsCartelas(numeroSorteado);
                bingo.imprimeTodasAsCartelas();
                bingo.setJogoFinalizado(bingo.veficaFimDoJogo());
                if (!bingo.isJogoFinalizado()) {
                    while (!(resposta == 'S' || resposta == 'N')) {
                        System.out.println("Deseja continuar? (S/n)");
                        resposta = teclado.nextLine().toUpperCase().charAt(0);
                        if (resposta == 'N') {
                            continuarRodada = false;
                            continuarBingo = false;
                        }
                    }
                    pulaLinhas();
                } else {
                    continuarRodada = false;
                    continuarBingo = false;
                }
                resposta = ' ';
            }
        } while (continuarBingo);
    }
}
