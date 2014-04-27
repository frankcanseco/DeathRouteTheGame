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
    private int velocidad;
    /**
     * Metodo constructor usado para crear el objeto Malo
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param vel es la <code>velocidad</code> del objeto.
     * @param conteo es un <code>contador</code> del objeto.
     */
    public Mutante(int posX, int posY, Image imagen,int v) {
        super(posX,posY,imagen);
        velocidad = v;
    }
    public int getVelocidad (){
        return velocidad;
    }
}
