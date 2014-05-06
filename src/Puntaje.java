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
    private int posXCarro; //Guarda la posX del carro
    private int posXPopo; //Guarda la posX del proyectil
    private int posYPopo; //Guarda la posY del proyectil
    private int velXPopo; //Guarda la velocidad en X del proyectil
    private int velYPopo; //Guarda la velocidad en Y del proyectil
    private int vidas; //Guarda las vidas del jugador
    private int perdidas; // Guarda num intentos
    private int sonActiv; // Guarda sonido activado o no

    /**
     * Constructor vacio con darle valores iniciales al momento de crear el
     * objeto Puntaje
     */
    public Puntaje() {
        nombre = "";
        puntos = 0;
  /*      posXCarro = 0;
        posXPopo = 0;
        posYPopo = 0;
        velXPopo = 0;
        velYPopo = 0;
        vidas = 0;
        perdidas = 0;
        int sonActiv = 1; */
    }

    /**
     * Metodo constructor usado para crear el objeto
     *
     * @param puntaje es el <code>puntaje</code> del jugador.
     * @param posXCarro es la <code>posicion en X</code> del carro.
     * @param posXPopo es la <code>posicion en X</code> del proyectil.
     * @param posYPopo es la <code>posicion en Y</code> del proyectil.
     * @param velXPopo es la <code>velocidad en X</code> del proyectil.
     * @param velYPopo es la <code>velocidad en Y</code> del proyectil.
     * @param vidas son las <code>vidas</code> del jugador.
     * 
     */
    public Puntaje(String nom, int puntaje/*, int posXCarro, int posXPopo, int posYPopo, int velXPopo, int velYPopo, int vidas, 
                                            int perdidas, int sonActiv*/) {
        //Asigna los valores de los parametros al objeto Puntaje
        this.nombre = nom;
        this.puntos = puntaje;
    /*    this.posXCarro = posXCarro;
        this.posXPopo = posXPopo;
        this.posYPopo = posYPopo;
        this.velXPopo = velXPopo;
        this.velYPopo = velYPopo;
        this.vidas = vidas;
        this.perdidas = perdidas;
        this.sonActiv = sonActiv;
        */
    }
                                                   
//    /**
//     * Metodo modificador usado para modificar la activacion del sonido
//     *
//     * @param sonActiv es binario para modificar activcion del<code>sonActiv</code> 
//     */
//    public void setSonActiv(int sonActiv) {
//        this.sonActiv = sonActiv;
//    }
//
//    /**
//     * Metodo de acceso que regresa si el sonido esta activado o no
//     *
//     * @return valor si esta activo o no el sonido <code>sonActiv</code>.
//     */
//    public int getSonActiv() {
//        return sonActiv;
//    }                                                
//                                               
//                                                    
//     /**
//     * Metodo modificador usado para cambiar num intentos del juego
//     *
//     * @param perdidas son los intentos <code>perdidas</code> del juego.
//     */
//    public void setPerdidas(int perdidas) {
//        this.perdidas = perdidas;
//    }
//
//    /**
//     * Metodo de acceso que regresa las num perdidas del juego
//     *
//     * @return perdidas son los num <code>perdidas</code> del juego.
//     */
//    public int getPerdidas() {
//        return perdidas;
//    }                                                
//
//    /**
//     * Metodo modificador usado para cambiar las vidas del objeto
//     *
//     * @param vidas son las <code>vidas</code> del objeto.
//     */
//    public void setVidas(int vidas) {
//        this.vidas = vidas;
//    }
//
//    /**
//     * Metodo de acceso que regresa las vidas del objeto
//     *
//     * @return vidas son las <code>vidas</code> del objeto.
//     */
//    public int getVidas() {
//        return vidas;
//    }
//
//    /**
//     * Metodo modificador usado para cambiar la velocidad en Y del proyectil
//     *
//     * @param velYPopo es la <code>velocidad en Y</code> del objeto.
//     */
//    public void setvelYPopo(int velYPopo) {
//        this.velYPopo = velYPopo;
//    }
//
//    /**
//     * Metodo de acceso que regresa la velocidad en Y del proyectil
//     *
//     * @return velYPopo es la <code>velocidad en Y</code> del proyectil.
//     */
//    public int getvelYPopo() {
//        return velYPopo;
//    }
//
//    /**
//     * Metodo modificador usado para cambiar la velocidad en X del proyectil
//     *
//     * @param velXPopo es la <code>velocidad en X</code> del objeto.
//     */
//    public void setvelXPopo(int velXPopo) {
//        this.velXPopo = velXPopo;
//    }
//
//    /**
//     * Metodo de acceso que regresa la velocidad en X del proyectil
//     *
//     * @return velXPopo es la <code>velocidad en X</code> del proyectil.
//     */
//    public int getvelXPopo() {
//        return velXPopo;
//    }
//
//    /**
//     * Metodo modificador usado para cambiar la posicion en Y del proyectil
//     *
//     * @param posYPopo es la <code>posicion en Y</code> del proyectil.
//     */
//    public void setposYPopo(int posYPopo) {
//        this.posYPopo = posYPopo;
//    }
//
//    /**
//     * Metodo de acceso que regresa la posicion en Y del proyectil
//     *
//     * @return posYPopo es la <code>posicion en Y</code> del proyectil.
//     */
//    public int getposYPopo() {
//        return posYPopo;
//    }
//
//    /**
//     * Metodo modificador usado para cambiar la posicion en X del proyectil
//     *
//     * @param posXPopo es la <code>posicion en X</code> del proyectil.
//     */
//    public void setposXPopo(int posXPopo) {
//        this.posXPopo = posXPopo;
//    }
//
//    /**
//     * Metodo de acceso que regresa la posicion en X del proyectil
//     *
//     * @return posXPopo es la <code>posicion en X</code> del proyectil.
//     */
//    public int getposXPopo() {
//        return posXPopo;
//    }
//
//    /**
//     * Metodo modificador usado para cambiar la posicion en X del proyectil
//     *
//     * @param posXCarro es la <code>posicion en X</code> del carro.
//     */
//    public void setposXCarro(int posXCarro) {
//        this.posXCarro = posXCarro;
//    }
//
//    /**
//     * Metodo de acceso que regresa la posicion en X del carro
//     *
//     * @return posXCarro es la <code>posicion en x</code> del carro.
//     */
//    public int getposXCarro() {
//        return posXCarro;
//    }

    /**
     * Metodo modificador usado para cambiar el puntaje del objeto
     *
     * @param puntaje es el <code>puntaje</code> del objeto.
     */
    public void setNombre(String nom) {
        this.nombre = nom;
    }

    /**
     * Metodo de acceso que regresa el puntaje del objeto
     *
     * @return puntaje es el <code>puntaje</code> del objeto.
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
     * @return puntaje es el <code>puntaje</code> del objeto.
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
        return "" +getNombre() + "," + getPuntos();/* + "," + getposXCarro() + ","
                + getposXPopo() + "," + getposYPopo() + ","
                + getvelXPopo() + "," + getvelYPopo() + ","
                + getVidas() + "," +  getPerdidas() + ","
                + getSonActiv();*/
    }
}
