package br.com.gomide.hashing.model;

import java.util.Objects;

public class Produto implements Comparable<Produto> {
    private String tipo;
    private String descricao;

    public Produto(String tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public int compareTo(Produto other) {
        return this.descricao.compareTo(other.descricao);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Produto)) return false;
        Produto other = (Produto) obj;
        return Objects.equals(tipo, other.tipo) &&
               Objects.equals(descricao, other.descricao);
    }

    @Override
    public int hashCode() {
        // Usamos somente o tipo no hashCode, pois a chave da tabela é o tipo
        return tipo.hashCode();
    }

    @Override
    public String toString() {
        return descricao;
    }
}
