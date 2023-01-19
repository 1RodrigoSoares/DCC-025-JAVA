/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.jogo;

import java.util.Scanner;

/**
 *
 * @author rodrigosoares
 */
public class Jogo {

    int[][] tabuleiro;
    private final int tamanho = 7;
    private int pecas = 32;
    private boolean vitoria = false;
    private boolean derrota = false;

    public Jogo() {
        this.tabuleiro = new int[tamanho][tamanho];
    }

    private void preencheMatriz() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = 1;
            }
        }
        tabuleiro[0][0] = 0;
        tabuleiro[0][1] = 0;
        tabuleiro[1][0] = 0;
        tabuleiro[1][1] = 0;
        tabuleiro[0][5] = 0;
        tabuleiro[0][6] = 0;
        tabuleiro[1][5] = 0;
        tabuleiro[1][6] = 0;
        tabuleiro[5][0] = 0;
        tabuleiro[5][1] = 0;
        tabuleiro[6][0] = 0;
        tabuleiro[6][1] = 0;
        tabuleiro[5][5] = 0;
        tabuleiro[5][6] = 0;
        tabuleiro[6][5] = 0;
        tabuleiro[6][6] = 0;
        tabuleiro[3][3] = 0;
    }

    private void imprimeMatriz() {
        for (int i = -1; i < tamanho; i++) {
            for (int j = -1; j < tamanho; j++) {
                if (i == -1 && j == -1) {
                    System.out.print(" ");
                } else if (j == -1) {
                    System.out.print((i) + " ");
                } else if (i == -1) {
                    System.out.print(" " + (j));
                } else if (i == 0 | i == 1 | i == 5 | i == 6) {
                    if (j == 0 | j == 1 | j == 5 | j == 6) {
                        System.out.print("  ");
                    } else {
                        System.out.print(tabuleiro[i][j] + " ");
                    }
                } else {
                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println("");

        }
    }

    private boolean verificaSelecionados(int linhaP, int colunaP, int linhaE, int colunaE) {
        boolean peça = false;
        boolean espaco = false;

        if (linhaP < 0 | linhaE < 0 | linhaP > tamanho - 1 | linhaE > tamanho - 1 | colunaP < 0 | colunaE < 0 | colunaP > tamanho - 1 | colunaE > tamanho - 1) {
            return false;
        }

        if (tabuleiro[linhaP][colunaP] == 1) {
            if (tabuleiro[linhaE][colunaE] == 0) {
                if ((linhaP != 2 & linhaP != 3 & linhaP != 4)) {
                    if (colunaP != 2 & colunaP != 3 & colunaP != 4) {
                        peça = false;
                    } else {
                        peça = true;
                    }
                } else {
                    peça = true;
                }
            }

            if ((linhaE != 2 & linhaE != 3 & linhaE != 4)) {
                if (colunaE != 2 & colunaE != 3 & colunaE != 4) {
                    espaco = false;
                } else {
                    espaco = true;
                }
            } else {
                espaco = true;
            }
        }

        return (peça & espaco);
    }

    private boolean existeJogada(int linhaP, int colunaP) {
        if (colunaP != 5 && colunaP != 6 && tabuleiro[linhaP][colunaP + 1] == 1 && tabuleiro[linhaP][colunaP + 2] == 0) {
            return true;
        }
        if (colunaP != 0 && colunaP != 1 && tabuleiro[linhaP][colunaP - 1] == 1 && tabuleiro[linhaP][colunaP - 2] == 0) {
            return true;
        }
        if (linhaP != 5 && linhaP != 6 && tabuleiro[linhaP + 1][colunaP] == 1 && tabuleiro[linhaP + 2][colunaP] == 0) {
            return true;
        }
        if (linhaP != 0 && linhaP != 1 && tabuleiro[linhaP - 1][colunaP] == 1 && tabuleiro[linhaP - 2][colunaP] == 0) {
            return true;
        }
        return false;
    }

    private void jogada(int linhaP, int colunaP, int linhaE, int colunaE) {
        if (linhaP == linhaE) {
            if (colunaP + 2 == colunaE) {
                tabuleiro[linhaP][colunaE - 1] = 0;
                tabuleiro[linhaP][colunaP] = 0;
                tabuleiro[linhaE][colunaE] = 1;
                pecas--;
            } else {
                tabuleiro[linhaP][colunaE + 1] = 0;
                tabuleiro[linhaP][colunaP] = 0;
                tabuleiro[linhaE][colunaE] = 1;
                pecas--;
            }
        } else if (colunaP == colunaE) {
            if (linhaP + 2 == linhaE) {
                tabuleiro[linhaP + 1][colunaE] = 0;
                tabuleiro[linhaP][colunaP] = 0;
                tabuleiro[linhaE][colunaE] = 1;
                pecas--;
            } else {
                tabuleiro[linhaP - 1][colunaE] = 0;
                tabuleiro[linhaP][colunaP] = 0;
                tabuleiro[linhaE][colunaE] = 1;
                pecas--;
            }
        } else {
            System.out.println("Valores invalidos");
        }
    }

    private boolean verificaEstadoJogo() {
        boolean x;
        if (pecas == 1) {
            vitoria = true;
            return false;
        }
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro[i][j] == 1) {
                    x = this.existeJogada(i, j);
                } else {
                    x = false;
                }
                if (x) {
                    return true;
                }
            }
        }
        derrota = true;
        return false;
    }

    private static int[] arrumaResposta(String respostaEscolhida) {
        String[] respostaSemVirgula;
        int[] numeroFinais = new int[4];

        respostaEscolhida = respostaEscolhida.trim();
        respostaEscolhida = respostaEscolhida.replace("(", "");
        respostaEscolhida = respostaEscolhida.replace(")", "");
        respostaEscolhida = respostaEscolhida.replaceAll("\\s+", "");

        respostaSemVirgula = respostaEscolhida.split(",");
        if (respostaSemVirgula.length == 4) {
            numeroFinais[0] = Integer.parseInt(respostaSemVirgula[0]);
            numeroFinais[1] = Integer.parseInt(respostaSemVirgula[1]);
            numeroFinais[2] = Integer.parseInt(respostaSemVirgula[2]);
            numeroFinais[3] = Integer.parseInt(respostaSemVirgula[3]);
        } else {
            numeroFinais[0] = -1;
        }
        return numeroFinais;
    }

    private void reiniciar() {
        this.preencheMatriz();
        this.pecas = 32;
        this.vitoria = false;
        this.derrota = false;
    }

    private static void pularLinha() {
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private void jogo(int linhaP, int colunaP, int linhaE, int colunaE) {
        if (this.verificaSelecionados(linhaP, colunaP, linhaE, colunaE)) {
            if (existeJogada(linhaP, colunaP)) {
                this.jogada(linhaP, colunaP, linhaE, colunaE);
            } else {
                pularLinha();
                System.out.println("Opção escolhida não há jogadas");
            }
        } else {
            System.out.println("Opções invalidas");
        }
    }

    public static void main(String[] args) {
        Jogo restaUm = new Jogo();
        Scanner teclado = new Scanner(System.in);

        String respostaEscolhida = "";
        String respostaContinuar = "";

        boolean validado = false;
        boolean programaUp = true;
        boolean continuar = true;

        int[] valoresEscolhidos = new int[4];

        restaUm.preencheMatriz();
        while (programaUp) {
            do {
                validado = false;
                restaUm.imprimeMatriz();
                System.out.println("Faça um movimento: ");
                respostaEscolhida = teclado.nextLine();
                valoresEscolhidos = arrumaResposta(respostaEscolhida);
                if (respostaEscolhida.startsWith("(") & respostaEscolhida.endsWith(")") & valoresEscolhidos[0] != -1) {
                    restaUm.jogo(valoresEscolhidos[0], valoresEscolhidos[1], valoresEscolhidos[2], valoresEscolhidos[3]);
                    continuar = restaUm.verificaEstadoJogo();
                    pularLinha();
                } else if (respostaEscolhida.equals("reiniciar")) {
                    restaUm.reiniciar();
                    pularLinha();
                } else if (respostaEscolhida.equals("sair")) {
                    break;
                } else {
                    pularLinha();
                    System.out.println("Erro: Por favor, digite novamente e no formato \"(Num,Num,Num,Num)\" ");
                }
            } while (continuar);

            if (restaUm.vitoria) {
                System.out.println("Parabéns você GANHOU");
            } else if (restaUm.derrota) {
                System.out.println("Infelizmente não foi dessa vez, você PERDEU");
            }

            while (!validado) {
                System.out.println("Deseja jogar novamente? (S/n)");
                respostaContinuar = teclado.nextLine();
                if (respostaContinuar.equalsIgnoreCase("S")) {
                    programaUp = true;
                    validado = true;
                    restaUm.reiniciar();
                    pularLinha();
                } else if (respostaContinuar.equalsIgnoreCase("N")) {
                    programaUp = false;
                    validado = true;
                } else {
                    System.out.println("Por favor digite (S/n)");
                    validado = false;
                }
            }
        }
        System.out.println("Fim de jogo! :)");
    }
}
