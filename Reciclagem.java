package com.reciclagem.model;

public class Reciclagem {

    private Long id;
    private String tipo;
    private double peso;

    public Reciclagem() {}

    public Reciclagem(Long id, String tipo, double peso) {
        this.id = id;
        this.tipo = tipo;
        this.peso = peso;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
}