/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc.dcc025.provaeventos;


/**
 *
 * @author ice
 * //RODRIGO SOARES DE ASSIS - 202176027
 */
public class Carnaval extends Evento {

    public Carnaval(int valor, Data data, String nome, int capacidade) {
        super(valor, data, nome, capacidade);
    }

    @Override
    public boolean pessoaPodeParticipar(Pessoa pessoa) {
        if (pessoa.getDataNascimento().diferenca(this.getData()) >= 6570) {
            if (this.getCapacidade() >= 1) {
                return true;
            }
        }
        return false;
    }

}
