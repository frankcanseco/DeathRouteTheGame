/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author CSG
 */

    import java.awt.Image;
    import java.awt.Toolkit;

public class Jugador extends Base{
    
    /**
     * Metodo constructor usado para crear el objeto Malo
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     * @param imagen es el <code>icono</code> del jugador.
     */
    public Jugador(int posX, int posY, Image imagen) {
        super(posX,posY,imagen);
    }
 
    
    
}
