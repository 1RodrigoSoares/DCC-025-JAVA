/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc.dcc025.provaeventos;
//RODRIGO SOARES DE ASSIS

import java.util.List;

/**
 *
 * @author ice
 */
public class Natal extends Evento{

    public Natal(int valor, Data data, String nome, int capacidade) {
        super(valor, data, nome, capacidade);
    }
    
    @Override
    public boolean pessoaPodeParticipar(Pessoa pessoa) {
        if(pessoa.getDataNascimento().diferenca(this.getData()) >= 1825){
            if(this.getCapacidade() > this.getParticipantes().size()){
                return true;
            }
        }
        return false;
    }
    
}
