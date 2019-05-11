/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.ps2.filmes;

/**
 *
 * @author joaquim
 */
public class Filme {
    private int id;
    private String titulo;
    private String genero;
    private int minutos;

    public Filme(){
        titulo = "";
        genero = "";
    }
    
    public Filme(int id, String titulo, String genero, int minutos) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.minutos = minutos;
    }
    
    public Filme(String titulo, String genero, int minutos) {
        this.titulo = titulo;
        this.genero = genero;
        this.minutos = minutos;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the minutos
     */
    public int getMinutos() {
        return minutos;
    }

    /**
     * @param minutos the minutos to set
     */
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
    
}
