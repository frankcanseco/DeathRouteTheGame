import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author enriquemarroquin
 */
public class Fondo extends Base {

    protected Animacion animCarretera; //animacion del carro
    private int posX;    //posicion en x.       
    private int posY;    //posicion en y.

    public Fondo(int posX, int posY, Image m) {

        super(posX, posY, m);	//constructor
        //Se cargan las imágenes(cuadros) para la animación del malo
        Image van = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/carretera.png"));

        //Se crea una nueva animacion con la inizialicacion dada
        animCarretera = new Animacion();
        animCarretera.sumaCuadro(van, 100);
    }

    /**
     * Metodo de acceso que regresa el ancho del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del
     * icono.
     */
    public int getAncho() {
        return (new ImageIcon(animCarretera.getImagen())).getIconWidth();
    }

    /**
     * Metodo de acceso que regresa el alto del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el alto del
     * icono.
     */
    public int getAlto() {
        return (new ImageIcon(animCarretera.getImagen())).getIconHeight();
    }

    /**
     * Metodo de acceso que regresa la imagen del icono
     *
     * @return un objeto de la clase <code>Image</code> que es la imagen del
     * icono.
     */
    public Image getImagen() {
        return (new ImageIcon(animCarretera.getImagen())).getImage();
    }
}
