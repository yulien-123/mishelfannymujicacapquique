
package com.Emergentes.modelo;

/**
 *
 * @author Mujica
 */
public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private int estado;
    
    public Libro()
    {
        id=0;
        titulo="";
        autor="";
        estado=0;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
