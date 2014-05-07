/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Image;
import java.util.ArrayList;

/**
 * La clase Animacion maneja una serie de imágenes (cuadros) y la cantidad de
 * tiempo que se muestra cada cuadro.
 */
public class Animacion {

    private ArrayList cuadros; // Arreglo de imagenes de la animacion
    private int indiceCuadroActual; // Cuadro de la animacion que esta activo
    private long tiempoDeAnimacion; // Tiempo de Animacion
    private long duracionTotal; // Tiempo total de animacion

    /**
     * Crea una nueva Animacion vacía
     */
    public Animacion() {
        cuadros = new ArrayList();
        duracionTotal = 0;
        iniciar();
    }

    /**
     * Añade una cuadro a la animación con la duración indicada (tiempo que se
     * muestra la imagen).
     */
    public synchronized void sumaCuadro(Image imagen, long duracion) {
        duracionTotal += duracion;
        cuadros.add(new cuadroDeAnimacion(imagen, duracionTotal));
    }

    // Inicializa la animación desde el principio. 
    public synchronized void iniciar() {
        tiempoDeAnimacion = 0;
        indiceCuadroActual = 0;
    }

    /**
     * Actualiza la imagen (cuadro) actual de la animación, si es necesario.
     * @param tiempoTranscurrido es el <code>tiempo de animacion</code> .
     */
    public synchronized void actualiza(long tiempoTranscurrido) {
        if (cuadros.size() > 1) {
            tiempoDeAnimacion += tiempoTranscurrido;

            if (tiempoDeAnimacion >= duracionTotal) {
                tiempoDeAnimacion = tiempoDeAnimacion % duracionTotal;
                indiceCuadroActual = 0;
            }

            while (tiempoDeAnimacion > getCuadro(indiceCuadroActual).tiempoFinal) {
                indiceCuadroActual++;
            }
        }
    }

    /**
     * Captura la imagen actual de la animación. Regeresa null si la animación
     * no tiene imágenes.
     */
    public synchronized Image getImagen() {
        if (cuadros.size() == 0) {
            return null;
        } else {
            return getCuadro(indiceCuadroActual).imagen;
        }
    }

    /**
     * Metodo de Animacion que regresa el cuadro especificado
     * @param i es el <code>numero de cuadro<code> de la animacion
     * @return cuadroDeAnimacion es el <code>cuadro de animacion<code> especificado
     */
    private cuadroDeAnimacion getCuadro(int i) {
        return (cuadroDeAnimacion) cuadros.get(i);
    }

    
    public class cuadroDeAnimacion {

        Image imagen; //Imagen del cuadro
        long tiempoFinal; //Tiempo de animacion

        /**
         * Inicializa un cuadro vacio
         */
        public cuadroDeAnimacion() {
            this.imagen = null;
            this.tiempoFinal = 0;
        }

        /**
         * Inicializa un cuadro de animacion con la imagen y el tiempoFinal
         * @param imagen la <code>imagen<code> de la animacion
         * @param tiempoFinal es el <code>tiempo final<code> de la animacion
         */
        public cuadroDeAnimacion(Image imagen, long tiempoFinal) {
            this.imagen = imagen;
            this.tiempoFinal = tiempoFinal;
        }

        /**
         * Regresa la imagen del cuadro
         * @return imagen es la <code>imagen<code> de la animacion
         */
        public Image getImagen() {
            return imagen;
        }

        /**
         * Regresa el tiempo final del objecto
         * @return tiempoFinal <code> tiempo final del cuadro<code>
         */
        public long getTiempoFinal() {
            return tiempoFinal;
        }

        /**
         * Usado para asignar imagen
         * @param imagen  asigna la <code>imagen<code> del cuadro
         */
        public void setImagen(Image imagen) {
            this.imagen = imagen;
        }

        /**
         * Usado para asignar tiempo final
         * @param tiempoFinal asigna el <code>tiempo final<code> del cuadro
         */
        public void setTiempoFinal(long tiempoFinal) {
            this.tiempoFinal = tiempoFinal;
        }
    }

}
