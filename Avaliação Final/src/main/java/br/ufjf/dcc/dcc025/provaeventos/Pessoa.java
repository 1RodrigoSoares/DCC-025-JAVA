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
public class Pessoa {
    private Data dataNascimento;
    private String nome;
    private List<Evento> listaDeEventos = new ArrayList<>();

    public Pessoa(Data dataNascimento, String nome) {
        this.dataNascimento = dataNascimento;
        this.nome = nome;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public List<Evento> getListaDeEventos() {
        return listaDeEventos;
    }

    public boolean podeParticiparEvento(Evento evento){
        if(!listaDeEventos.isEmpty()){
            for(Evento eventoAgendado: listaDeEventos){
                if(eventoAgendado.getData().getAno() == evento.getData().getAno()){
                    if(eventoAgendado.getData().getMes() == evento.getData().getMes()){
                        if(eventoAgendado.getData().getDia() == evento.getData().getDia()){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public void agendarEvento(Evento evento){
        if(this.podeParticiparEvento(evento)){
            listaDeEventos.add(evento);
        }
    }
}
