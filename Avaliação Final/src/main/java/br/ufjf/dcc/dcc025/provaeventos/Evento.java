/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc.dcc025.provaeventos;

import java.util.*;


/**
 *
 * @author ice
 * //RODRIGO SOARES DE ASSIS - 202176027
 */
public abstract class Evento {

    private int valor;
    private Data data;
    private String nome;
    private int capacidade;
    private List<Pessoa> participantes = new ArrayList<>();

    public Evento(int valor, Data data, String nome, int capacidade) {
        this.valor = valor;
        this.data = data;
        this.nome = nome;
        this.capacidade = capacidade;
    }

    public int getValor() {
        return valor;
    }

    public Data getData() {
        return data;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public List<Pessoa> getParticipantes() {
        return participantes;
    }

    private boolean temVaga() {
        if (this.capacidade > this.participantes.size()) {
            return true;
        }
        return false;
    }

    public abstract boolean pessoaPodeParticipar(Pessoa pessoa);

    public void adicionaPessoa(Pessoa pessoa) {
        if (pessoaPodeParticipar(pessoa)) {
            participantes.add(pessoa);
        }
    }
}
