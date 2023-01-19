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
public class Data {

    private int dia;
    private int mes;
    private int ano;

    public Data() {
    }

    
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }
    
    

    public static Data parser(String data) throws DataException {
        Data dataModificada = null;

        String[] dataQuebrada = data.split("/");
        if (dataQuebrada.length == 3) {
            if (((dataQuebrada[0].length() == 1) || (dataQuebrada[0].length() == 2)) && ((dataQuebrada[1].length() == 2) || (dataQuebrada[1].length() == 1) ) && (dataQuebrada[2].length() == 4)) {
                int dia = Integer.parseInt(dataQuebrada[0]);
                int mes = Integer.parseInt(dataQuebrada[1]);
                int ano = Integer.parseInt(dataQuebrada[2]);

                if (1 <= dia && dia <= 30) {
                    if (1 <= mes && mes <= 12) {
                        dataModificada = new Data(dia, mes, ano);
                    } else {
                        throw new DataException();
                    }
                } else {
                    throw new DataException();
                }

            } else {
                throw new DataException();
            }
        }

        return dataModificada;
    }

    public int diferenca(Data data) {
        int diferença = 0;
        
        diferença = (data.getAno() - this.ano)* 365;
        diferença = diferença + ((data.getMes() - this.mes)* 30);
        diferença = diferença + (data.getDia() - this.dia);
        
        return diferença;
    }

    @Override
    public String toString() {
        return (dia +"/"+ mes +"/" +ano); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
}
