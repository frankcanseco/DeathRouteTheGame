/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pako_knc13
 */
    import java.awt.Image;
    import java.awt.Toolkit;

public class Mutante extends Base{
    private int velocidad; //Velocidad del mutante
    private int damage; //Daño hecho por el mutante
    /**
     * Metodo constructor usado para crear el objeto Malo
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param imagen es la <code>imagen</code> del objeto.
     * @param v es la <code>velocidad</code> del objeto.
     * @param d es un <code>contador</code> del objeto.
     */
    public Mutante(int posX, int posY, Image imagen,int v, int d) {
        super(posX,posY,imagen);
        velocidad = v;
        damage = d;
    }
    
    /**
     * Metodo que regresa velodiad del Mutante
     * @return velocidad es la <code> velocidad </code> del objeto
     */
    public int getVelocidad (){
        return velocidad;
    }
    
    /**
     * Metodo utilizado para regresar el valor de damage o daño que causa
     * el zombie al colisionar con el jugador
     * @return damage es el <code> damage </code> causado por el zombie
     */
    public int getDamage (){
        return damage;
    }
    
    /**
     * Metodo setter para declarar el daño que causa el zombie
     * @param d es el <code> damage o daño < /code>  que causa el zombie
     */
    public void setDamage (int d){
        damage = d;
    }
}
