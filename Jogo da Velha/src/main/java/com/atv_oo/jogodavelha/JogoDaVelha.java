/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.atv_oo.jogodavelha;

import java.util.*;

/**
 *
 * @author rodrigosoares
 */
public class JogoDaVelha {

    private static Scanner teclado;
    private String modoDoJogo;
    private char simboloPlayer1;
    private char simboloPlayer2;
    private String nomePlayer1;
    private String nomePlayer2;
    private char[][] dimensao1;
    private char[][] dimensao2;
    private char[][] dimensao3;

    public JogoDaVelha() {
        this.teclado = new Scanner(System.in);
        this.dimensao1 = new char[3][3];
        this.dimensao2 = new char[3][3];
        this.dimensao3 = new char[3][3];
    }

    private void escolheModoJogo() {
        boolean modoDoJogoFoiEscolhido = false;

        System.out.println("Digite o modo de jogo(JxJ - para dois jogadores, ou JxB - para jogar contra o bot):");
        do {
            modoDoJogo = teclado.nextLine().toUpperCase();
            if (modoDoJogo.equals("JXJ")) {
                modoDoJogoFoiEscolhido = true;
            } else if (modoDoJogo.equals("JXB")) {
                modoDoJogoFoiEscolhido = true;
            } else {
                System.out.println("Entrada inválida, digite novamente: ");
            }
        } while (!modoDoJogoFoiEscolhido);
    }

    private void iniciaOjogo() {
        if (modoDoJogo.equals("JXJ")) {
            jogoContraJogador();
        } else {
            jogoContraBot();
        }
    }

    private void determinaNomeESimbolos() {
        System.out.println("Digite o nome do jogador: ");
        this.nomePlayer1 = teclado.nextLine();

        System.out.println("Digite o simbolo que deseja utilizar durante o jogo: ");
        this.simboloPlayer1 = teclado.next().toUpperCase().charAt(0);

        teclado.nextLine(); //arrumar bug de pular a linha seguinte

        System.out.println("Digite o nome do jogador: ");
        this.nomePlayer2 = teclado.nextLine();

        do {
            System.out.println("Digite o simbolo que deseja utilizar durante o jogo: ");
            this.simboloPlayer2 = teclado.next().toUpperCase().charAt(0);
            teclado.nextLine();
            if (simboloPlayer1 == simboloPlayer2) {
                System.out.println("Esse simbolo já foi escolhido!");
            }
        } while (simboloPlayer1 == simboloPlayer2);
    }

    private void determinaNomeCBot(Bot bot) {
        System.out.println("Digite o nome do jogador: ");
        this.nomePlayer1 = teclado.nextLine();

        System.out.println("Digite o simbolo que deseja utilizar durante o jogo: ");
        this.simboloPlayer1 = teclado.next().toUpperCase().charAt(0);

        if (simboloPlayer1 == 'X') {
            bot.setSimbolo('O');
            simboloPlayer2 = '0';
        }
    }

    private void preencheDimensoes() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dimensao1[i][j] = '-';
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dimensao2[i][j] = '-';
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dimensao3[i][j] = '-';
            }
        }
    }

    private void imprimeDimensões() {
        imprimeDimensoesAux(0, 1);
        imprimeDimensoesAux(1, 2);
        imprimeDimensoesAux(2, 3);
    }

    private void imprimeDimensoesAux(int numInicial, int numFinal) {
        for (int i = numInicial; i < numFinal; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(dimensao1[i][j]);
            }
            System.out.print("   ");
            for (int j = 0; j < 3; j++) {
                System.out.print(dimensao2[i][j]);
            }
            System.out.print("   ");
            for (int j = 0; j < 3; j++) {
                System.out.print(dimensao3[i][j]);
            }
        }
        System.out.println();
    }

    private void rodada(int jogador) {
        String jogadaEscolhida = "";
        int[] numerosArrumados;
        boolean jogadaValida = false;

        if (jogador == 1) {
            System.out.println("=====>Turno da(o) " + this.nomePlayer1);
        } else {
            System.out.println("=====>Turno da(o) " + this.nomePlayer2);
        }

        do {
            System.out.println("Digite a posicao que deseja jogar no formato (linha, coluna, camada):");
            //arrumar bug de pular a linha seguinte
            jogadaEscolhida = teclado.nextLine().trim();
            numerosArrumados = arrumaResposta(jogadaEscolhida);
            if (jogadaEscolhida.startsWith("(") && jogadaEscolhida.endsWith(")") && numerosArrumados[0] != -1) {
                if (verificaPosicaoEscolhida(numerosArrumados)) {
                    jogadaValida = true;
                } else {
                    System.out.println("Posição escolhida não disponivel");
                }
            } else {
                System.out.println("JOGADA INVÁLIDA!");
            }
        } while (!jogadaValida);

        jogada(numerosArrumados, jogador);
        imprimeDimensões();
    }

    private void rodadaBot(Bot bot) {
        System.out.println("=====>Turno da(o) " + this.nomePlayer2);
        int[] numerosEscolhidos = bot.realizaJogada();
        while (!verificaPosicaoEscolhida(numerosEscolhidos)) {
            numerosEscolhidos = bot.realizaJogada();
        }
        jogada(numerosEscolhidos, 2);
        imprimeDimensões();
    }

    private boolean verificaPosicaoEscolhida(int[] numerosEscolhidos) {
        if (numerosEscolhidos[0] < 0 || numerosEscolhidos[0] > 2) {
            return false;
        }
        if (numerosEscolhidos[1] < 0 || numerosEscolhidos[1] > 2) {
            return false;
        }
        if (numerosEscolhidos[2] < 0 || numerosEscolhidos[2] > 2) {
            return false;
        }

        if (numerosEscolhidos[2] == 0) {
            if (dimensao1[numerosEscolhidos[0]][numerosEscolhidos[1]] == '-') {
                return true;
            }
        } else if (numerosEscolhidos[2] == 1) {
            if (dimensao2[numerosEscolhidos[0]][numerosEscolhidos[1]] == '-') {
                return true;
            }
        } else if (numerosEscolhidos[2] == 2) {
            if (dimensao3[numerosEscolhidos[0]][numerosEscolhidos[1]] == '-') {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    private static int[] arrumaResposta(String respostaEscolhida) {
        String[] respostaSemVirgula;
        int[] numeroFinais = new int[3];

        respostaEscolhida = respostaEscolhida.trim();
        respostaEscolhida = respostaEscolhida.replace("(", "");
        respostaEscolhida = respostaEscolhida.replace(")", "");
        respostaEscolhida = respostaEscolhida.replaceAll("\\s+", "");

        respostaSemVirgula = respostaEscolhida.split(",");
        if (respostaSemVirgula.length == 3) {
            numeroFinais[0] = Integer.parseInt(respostaSemVirgula[0]);
            numeroFinais[1] = Integer.parseInt(respostaSemVirgula[1]);
            numeroFinais[2] = Integer.parseInt(respostaSemVirgula[2]);
        } else {
            numeroFinais[0] = -1;
        }
        return numeroFinais;
    }

    private void jogada(int[] valoresEscolhidos, int jogador) {
        if (jogador == 1) {
            if (valoresEscolhidos[2] == 0) {
                dimensao1[valoresEscolhidos[0]][valoresEscolhidos[1]] = this.simboloPlayer1;
            } else if (valoresEscolhidos[2] == 1) {
                dimensao2[valoresEscolhidos[0]][valoresEscolhidos[1]] = this.simboloPlayer1;
            } else {
                dimensao3[valoresEscolhidos[0]][valoresEscolhidos[1]] = this.simboloPlayer1;
            }
        } else {
            if (valoresEscolhidos[2] == 0) {
                dimensao1[valoresEscolhidos[0]][valoresEscolhidos[1]] = this.simboloPlayer2;
            } else if (valoresEscolhidos[2] == 1) {
                dimensao2[valoresEscolhidos[0]][valoresEscolhidos[1]] = this.simboloPlayer2;
            } else {
                dimensao3[valoresEscolhidos[0]][valoresEscolhidos[1]] = this.simboloPlayer2;
            }
        }
    }

    private boolean verificaResultado(int jogador) {
        return verificaHorizontal(jogador) | verificaVertical(jogador) | verificaDiagonal(jogador) | verificaDiagonalSecundaria(jogador);
    }

    private boolean verificaHorizontal(int jogador) {
        int posicaox = 0;
        int posicaoy = 0;
        int posicaoz = 0;
        int i = 0;

        if (jogador == 1) {
            while (i < 3 && (posicaox != 1 && posicaoy != 1 && posicaoz != 1)) {
                posicaox = 0;
                posicaoy = 0;
                posicaoz = 0;

                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        if (dimensao1[i][j] == this.simboloPlayer1) {
                            posicaox = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer1) {
                            posicaox = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer1) {
                            posicaox = +1;
                        }
                    } else if (j == 1) {
                        if (dimensao1[i][j] == this.simboloPlayer1) {
                            posicaoy = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer1) {
                            posicaoy = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer1) {
                            posicaoy = +1;
                        }
                    } else {
                        if (dimensao1[i][j] == this.simboloPlayer1) {
                            posicaoz = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer1) {
                            posicaoz = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer1) {
                            posicaoz = +1;
                        }
                    }
                }
                i++;
            }
        } else {
            while (i < 3 && (posicaox != 1 && posicaoy != 1 && posicaoz != 1)) {
                posicaox = 0;
                posicaoy = 0;
                posicaoz = 0;

                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        if (dimensao1[i][j] == this.simboloPlayer2) {
                            posicaox = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer2) {
                            posicaox = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer2) {
                            posicaox = +1;
                        }
                    } else if (j == 1) {
                        if (dimensao1[i][j] == this.simboloPlayer2) {
                            posicaoy = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer2) {
                            posicaoy = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer2) {
                            posicaoy = +1;
                        }
                    } else {
                        if (dimensao1[i][j] == this.simboloPlayer2) {
                            posicaoz = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer2) {
                            posicaoz = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer2) {
                            posicaoz = +1;
                        }
                    }
                }
                i++;
            }
        }

        if (posicaox == 1 && posicaoy == 1 && posicaoz == 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verificaVertical(int jogador) {
        int posicaox = 0;
        int posicaoy = 0;
        int posicaoz = 0;
        int j = 0;

        if (jogador == 1) {
            while (j < 3 && !(posicaox == 1 && posicaoy == 1 && posicaoz == 1)) {
                posicaox = 0;
                posicaoy = 0;
                posicaoz = 0;

                for (int i = 0; i < 3; i++) {
                    if (i == 0) {
                        if (dimensao1[i][j] == this.simboloPlayer1) {
                            posicaox = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer1) {
                            posicaox = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer1) {
                            posicaox = +1;
                        }
                    } else if (i == 1) {
                        if (dimensao1[i][j] == this.simboloPlayer1) {
                            posicaoy = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer1) {
                            posicaoy = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer1) {
                            posicaoy = +1;
                        }
                    } else {
                        if (dimensao1[i][j] == this.simboloPlayer1) {
                            posicaoz = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer1) {
                            posicaoz = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer1) {
                            posicaoz = +1;
                        }
                    }
                }
                j++;
            }
        } else {
            while (j < 3 && !(posicaox == 1 && posicaoy == 1 && posicaoz == 1)) {
                posicaox = 0;
                posicaoy = 0;
                posicaoz = 0;

                for (int i = 0; i < 3; i++) {
                    if (i == 0) {
                        if (dimensao1[i][j] == this.simboloPlayer2) {
                            posicaox = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer2) {
                            posicaox = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer2) {
                            posicaox = +1;
                        }
                    } else if (i == 1) {
                        if (dimensao1[i][j] == this.simboloPlayer2) {
                            posicaoy = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer2) {
                            posicaoy = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer2) {
                            posicaoy = +1;
                        }
                    } else {
                        if (dimensao1[i][j] == this.simboloPlayer2) {
                            posicaoz = +1;
                        } else if (dimensao2[i][j] == this.simboloPlayer2) {
                            posicaoz = +1;
                        } else if (dimensao3[i][j] == this.simboloPlayer2) {
                            posicaoz = +1;
                        }
                    }
                }
                j++;
            }
        }

        if (posicaox == 1 && posicaoy == 1 && posicaoz == 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verificaDiagonal(int jogador) {
        int posicaox = 0;
        int posicaoy = 0;
        int posicaoz = 0;
        int j = 0;

        if (jogador == 1) {
            while (j < 3 && !(posicaox == 1 && posicaoy == 1 && posicaoz == 1)) {
                for (int i = 0; i < 3; i++) {
                    if (i == 0 && j == 0) {
                        if (simboloPlayer1 == dimensao1[i][j]) {
                            posicaox = 1;
                        } else if (simboloPlayer1 == dimensao2[i][j]) {
                            posicaox = 1;
                        } else if (simboloPlayer1 == dimensao3[i][j]) {
                            posicaox = 1;
                        }
                    }

                    if (i == 1 && j == 1) {
                        if (simboloPlayer1 == dimensao1[i][j]) {
                            posicaoy = 1;
                        } else if (simboloPlayer1 == dimensao2[i][j]) {
                            posicaoy = 1;
                        } else if (simboloPlayer1 == dimensao3[i][j]) {
                            posicaoy = 1;
                        }
                    }

                    if (i == 2 && j == 2) {
                        if (simboloPlayer1 == dimensao1[i][j]) {
                            posicaoz = 1;
                        } else if (simboloPlayer1 == dimensao2[i][j]) {
                            posicaoz = 1;
                        } else if (simboloPlayer1 == dimensao3[i][j]) {
                            posicaoz = 1;
                        }
                    }
                }
                j++;
            }

        } else {
            while (j < 3 && !(posicaox == 1 && posicaoy == 1 && posicaoz == 1)) {
                for (int i = 0; i < 3; i++) {
                    if (i == 0 && j == 0) {
                        if (simboloPlayer2 == dimensao1[i][j]) {
                            posicaox = 1;
                        } else if (simboloPlayer2 == dimensao2[i][j]) {
                            posicaox = 1;
                        } else if (simboloPlayer2 == dimensao3[i][j]) {
                            posicaox = 1;
                        }
                    }

                    if (i == 1 && j == 1) {
                        if (simboloPlayer2 == dimensao1[i][j]) {
                            posicaoy = 1;
                        } else if (simboloPlayer2 == dimensao2[i][j]) {
                            posicaoy = 1;
                        } else if (simboloPlayer2 == dimensao3[i][j]) {
                            posicaoy = 1;
                        }
                    }

                    if (i == 2 && j == 2) {
                        if (simboloPlayer2 == dimensao1[i][j]) {
                            posicaoz = 1;
                        } else if (simboloPlayer2 == dimensao2[i][j]) {
                            posicaoz = 1;
                        } else if (simboloPlayer2 == dimensao3[i][j]) {
                            posicaoz = 1;
                        }
                    }
                }
                j++;
            }
        }

        if (posicaox == 1 && posicaoy == 1 && posicaoz == 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verificaDiagonalSecundaria(int jogador) {
        int posicaox = 0;
        int posicaoy = 0;
        int posicaoz = 0;
        int j = 0;

        if (jogador == 1) {
            while (j < 3 && !(posicaox == 1 && posicaoy == 1 && posicaoz == 1)) {
                for (int i = 0; i < 3; i++) {
                    if (i == 0 && j == 2) {
                        if (simboloPlayer1 == dimensao1[i][j]) {
                            posicaox = 1;
                        } else if (simboloPlayer1 == dimensao2[i][j]) {
                            posicaox = 1;
                        } else if (simboloPlayer1 == dimensao3[i][j]) {
                            posicaox = 1;
                        }
                    }

                    if (i == 1 && j == 1) {
                        if (simboloPlayer1 == dimensao1[i][j]) {
                            posicaoy = 1;
                        } else if (simboloPlayer1 == dimensao2[i][j]) {
                            posicaoy = 1;
                        } else if (simboloPlayer1 == dimensao3[i][j]) {
                            posicaoy = 1;
                        }
                    }

                    if (i == 2 && j == 0) {
                        if (simboloPlayer1 == dimensao1[i][j]) {
                            posicaoz = 1;
                        } else if (simboloPlayer1 == dimensao2[i][j]) {
                            posicaoz = 1;
                        } else if (simboloPlayer1 == dimensao3[i][j]) {
                            posicaoz = 1;
                        }
                    }
                }
                j++;
            }

        } else {
            while (j < 3 && !(posicaox == 1 && posicaoy == 1 && posicaoz == 1)) {
                for (int i = 0; i < 3; i++) {
                    if (i == 0 && j == 2) {
                        if (simboloPlayer2 == dimensao1[i][j]) {
                            posicaox = 1;
                        } else if (simboloPlayer2 == dimensao2[i][j]) {
                            posicaox = 1;
                        } else if (simboloPlayer2 == dimensao3[i][j]) {
                            posicaox = 1;
                        }
                    }

                    if (i == 1 && j == 1) {
                        if (simboloPlayer2 == dimensao1[i][j]) {
                            posicaoy = 1;
                        } else if (simboloPlayer2 == dimensao2[i][j]) {
                            posicaoy = 1;
                        } else if (simboloPlayer2 == dimensao3[i][j]) {
                            posicaoy = 1;
                        }
                    }

                    if (i == 2 && j == 0) {
                        if (simboloPlayer2 == dimensao1[i][j]) {
                            posicaoz = 1;
                        } else if (simboloPlayer2 == dimensao2[i][j]) {
                            posicaoz = 1;
                        } else if (simboloPlayer2 == dimensao3[i][j]) {
                            posicaoz = 1;
                        }
                    }
                }
                j++;
            }
        }

        if (posicaox == 1 && posicaoy == 1 && posicaoz == 1) {
            return true;
        } else {
            return false;
        }
    }

    private void jogoContraJogador() {
        boolean fimdejogo = false;
        int jogadorVitorioso = 0;
        char continuar = 'S';

        determinaNomeESimbolos();
        preencheDimensoes();

        while (continuar == 'S') {
            while (!fimdejogo) {
                rodada(1);
                fimdejogo = verificaResultado(1);
                if (fimdejogo) {
                    jogadorVitorioso = 1;
                }

                if (!fimdejogo) {
                    rodada(2);
                    fimdejogo = verificaResultado(2);
                    if (fimdejogo) {
                        jogadorVitorioso = 2;
                    }
                }
            }

            if (jogadorVitorioso == 1) {
                System.out.println("Parabés " + nomePlayer1 + " VOCÊ GANHOU!");
            } else if (jogadorVitorioso == 2) {
                System.out.println("Parabés " + nomePlayer1 + " VOCÊ GANHOU!");
            }

            System.out.println("Deseja jogar novamente?(S/n)");
            continuar = teclado.nextLine().toUpperCase().charAt(0);

            if (continuar == 'S') {
                reiniciar();
                fimdejogo = false;
                jogadorVitorioso = 0;
            }
        }
    }

    private void reiniciar() {
        determinaNomeESimbolos();
        preencheDimensoes();
    }

    private void jogoContraBot() {
        boolean fimdejogo = false;
        int jogadorVitorioso = 0;
        char continuar = 'S';
        Bot bot = new Bot('X');
        simboloPlayer2 = bot.getSimbolo();
        nomePlayer2 = "Bot";

        determinaNomeCBot(bot);
        teclado.nextLine();
        preencheDimensoes();

        while (continuar == 'S') {
            while (!fimdejogo) {
                rodada(1);
                fimdejogo = verificaResultado(1);
                if (fimdejogo) {
                    jogadorVitorioso = 1;
                }
                if (!fimdejogo) {
                    rodadaBot(bot);
                    fimdejogo = verificaResultado(2);
                    if (fimdejogo) {
                        jogadorVitorioso = 2;
                    }
                }
            }
            if (jogadorVitorioso == 1) {
                System.out.println("Parabés " + nomePlayer1 + " VOCÊ GANHOU!");
            } else if (jogadorVitorioso == 2) {
                System.out.println("IXI O BOT GANHOU!");
            }

            System.out.println("Deseja jogar novamente?(S/n)");
            continuar = teclado.nextLine().toUpperCase().charAt(0);

            if (continuar == 'S') {
                determinaNomeCBot(bot);
                teclado.nextLine();
                preencheDimensoes();
                fimdejogo = false;
                jogadorVitorioso = 0;
            }
        }
    }

    public static void main(String[] args) {
        JogoDaVelha jogo = new JogoDaVelha();

        jogo.escolheModoJogo();
        jogo.iniciaOjogo();
    }
}
