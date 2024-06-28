
package com.mycompany.radix;

public class Item {
    private int codigo;
    private String descricao;
    private String unidade;
    private double valor;

    // Construtores, getters e setters omitidos para brevidade

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public double getValor() {
        return valor;
    }

    public Item(int codigo, String descricao, String unidade, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.unidade = unidade;
        this.valor = valor;
    }
}
