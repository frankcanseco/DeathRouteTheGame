/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Clase Puntaje
 *
 * @author Antonio Mejorado
 * @version 1.00 2008/6/13
 */
public class Puntaje {

    private String nombre; //Guarda el nombre del jugador
    private int puntos; //Guarda el valor de score

    /**
     * Constructor vacio con darle valores iniciales al momento de crear el
     * objeto Puntaje
     */
    public Puntaje() {
        nombre = "";
        puntos = 0;
    }


    /**
     * Metodo constructor del objeto Puntaje
     * @param nom es el <code>nombre</code> del jugador;
     * @param puntaje es el <code>puntaje</code> del jugador;
     */
    public Puntaje(String nom, int puntaje) {
        this.nombre = nom;
        this.puntos = puntaje;
    }


    /**
     * Metodo setter para la variable nombre
     * @param nom es el <code>nombre</code> del juador.
     */
    public void setNombre(String nom) {
        this.nombre = nom;
    }

    /**
     * Metodo de acceso que retorna el nombre del jugador.
     * @return nombre es el <code>nombre</code> del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo modificador usado para cambiar el puntaje del objeto
     *
     * @param puntaje es el <code>puntaje</code> del objeto.
     */
    public void setPuntos(int puntaje) {
        this.puntos = puntaje;
    }

    /**
     * Metodo de acceso que regresa el puntaje del objeto
     *
     * @return puntos es el <code>puntaje</code> del objeto.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Metodo que regresa el objeto en formato String
     *
     * @return un objeto de la clase <code>String</code>.
     */
    public String toString() {
        return "" +getNombre() + "," + getPuntos();
    }
}
