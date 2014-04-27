/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francisco Canseco A01034948
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.awt.Point;
import java.util.Vector;
import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JFrameDeathRoute extends JFrame implements Runnable, KeyListener, MouseListener{
    
    private static final long serialVersionUID = 1L;
    private Image dbImage;	// Imagen
    private Graphics dbg;	// Objeto grafico
    private Jugador camion;//objeto jugador
    private Botones play;//objetos botones
    private Botones options;
    private Botones bcredits;
    private Botones howtoplay;
    private Mutante M1;//objetos mutantes enemigos
    private Mutante M2;
    private Mutante M3;
    private Mutante M4;
    private Image Selva;//Se declaran las variables de imagenes
    private Image Ciudad;
    private Image Desierto;
    private Image Calle;
    private Image cam;
    private Image im1;
    private Image im2;
    private Image im3;
    private Image im4;
    private Image Iplay;
    private Image Ioptions;
    private Image Icredits;
    private Image Ihowtoplay;
    private Image bar;
    private Image credits;
    private int ventana;//variable para cambio de ventana
    private int cambio;//variable para cambiar fondo
    private float norm;
    private int dx;
    private int dy;
    private int camionVx;
    private int camionVy;
    public JFrameDeathRoute(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Death Route"); setSize(800, 820);
        init();
        start();
    }
    
    public void init(){
        ventana = 1;//se inicializa con menu
        cambio = 1;
        camionVx =0;
        camionVy = 0;
        Selva = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/selva.png"));
        Ciudad = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/ciudad.png"));
        Desierto = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/desierto.png"));
        Calle = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/carretera.png"));
        im1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/zombieUp.gif"));
        im2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/zombieDown.gif"));
        im3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/zombieLeft.gif"));
        im4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/zombieRight.gif"));
        Iplay = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/botonPlay.png"));
        Ioptions = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/botonOptions.png"));
        Icredits = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/botonCredits.png"));
        Ihowtoplay = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/botonHow.png"));
        bar = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/bar.png"));
        cam = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/van.gif"));
        credits = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/credits.png"));
        camion = new Jugador((int) (this.getWidth()/2),(int)((this.getHeight()/2)),cam);//se inicializan los objetos
        play = new Botones(270,150,Iplay);
        options = new Botones (270 , 450 , Ioptions);
        bcredits = new Botones (270,600,Icredits);
        howtoplay = new Botones (270,300,Ihowtoplay);
        M1 = new Mutante (300,700,im1,2);
        M2 = new Mutante (500,200,im2,2);
        M3 = new Mutante (600,600,im3,2);
        M4 = new Mutante (200,200,im4,2);
        this.setBackground(Color.BLACK);
        addKeyListener(this);   
        addMouseListener(this);
    }
    
     /**
     * Metodo <I>start</I> sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se crea e inicializa el hilo para la animacion este metodo
     * es llamado despues del init o cuando el usuario visita otra pagina y
     * luego regresa a la pagina en donde esta este <code>Applet</code>
     *
     */
    public void start() {
        // Declaras un hilo
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();
    }
    
    /**
     * Metodo <I>run</I> sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, es un ciclo indefinido donde se
     * incrementa la posicion en x o y dependiendo de la direccion, finalmente
     * se repinta el <code>Applet</code> y luego manda a dormir el hilo.
     *
     */
    public void run() {
        
        while (true) {
           
            actualiza();
            checaColision();
            // Se actualiza el <code>Applet</code> repintando el contenido.
            repaint();
            try {
                // El thread se duerme.
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }
        
    }
    
     /**
     * Metodo usado para actualizar la posicion de objetos bueno y malo.
     *
     */
    public void actualiza() {
        if (ventana == 2){
            dx = camion.getPosX() - M1.getPosX();
            dy = camion.getPosY() - M1.getPosY();
            norm = (float) Math.sqrt(dx * dx + dy * dy);
            dx = (int) (dx * (M1.getVelocidad() / norm));
            dy = (int) (dy * (M1.getVelocidad()  / norm));
            M1.setPosX(M1.getPosX()+dx);
            M1.setPosY(M1.getPosY()+dy);
            dx = camion.getPosX() - M2.getPosX();
            dy = camion.getPosY() - M2.getPosY();
            norm = (float) Math.sqrt(dx * dx + dy * dy);
            dx = (int) (dx * (M2.getVelocidad() / norm));
            dy = (int) (dy * (M2.getVelocidad()  / norm));
            M2.setPosX(M2.getPosX()+dx);
            M2.setPosY(M2.getPosY()+dy);
            dx = camion.getPosX() - M3.getPosX();
            dy = camion.getPosY() - M3.getPosY();
            norm = (float) Math.sqrt(dx * dx + dy * dy);
            dx = (int) (dx * (M3.getVelocidad() / norm));
            dy = (int) (dy * (M3.getVelocidad()  / norm));
            M3.setPosX(M3.getPosX()+dx);
            M3.setPosY(M3.getPosY()+dy);
            dx = camion.getPosX() - M4.getPosX();
            dy = camion.getPosY() - M4.getPosY();
            norm = (float) Math.sqrt(dx * dx + dy * dy);
            dx = (int) (dx * (M4.getVelocidad() / norm));
            dy = (int) (dy * (M4.getVelocidad()  / norm));
            M4.setPosX(M4.getPosX()+dx);
            M4.setPosY(M4.getPosY()+dy);
            camion.setPosX(camion.getPosX()+camionVx);
            camion.setPosY(camion.getPosY()+camionVy);
        }
    }
    
    /**
     * Metodo usado para checar las colisiones del objeto elefante y raton con
     * las orillas del <code>Applet</code>.
     */
    public void checaColision() {
    }
    
    /**
     * Metodo <I>update</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint(Graphics g) {
                // Inicializan el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A){
            camionVx = -2;
        }
        else{
            if (e.getKeyCode() == KeyEvent.VK_D){
                camionVx = 2;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W){
            camionVy = -2;
        }
        else{
            if (e.getKeyCode() == KeyEvent.VK_S){
                camionVy = 2;
            }
        }
    } 

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D){
             camionVx = 0;
         }
         if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S){
             camionVy = 0;
         }
    }

    public void mouseClicked(MouseEvent e) {
        if (ventana == 1){//Funcion para el cambio de ventanas en el menu
            if(e.getPoint().getX()>= 270 && e.getPoint().getX() <= 560){
                if (e.getPoint().getY()>= 150 && e.getPoint().getY() <= 250){
                    ventana = 2;
                }
                if (e.getPoint().getY()>= 600 && e.getPoint().getY() <= 700){
                    ventana = 5;
                }
            }
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Metodo <I>paint</I> sobrescrito de la clase <code>Applet</code>, heredado
     * de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada, ademas
     * que cuando la imagen es cargada te despliega una advertencia.
     *
     * @paramg es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint1(Graphics g) {
        if (camion != null) {
            //Dibuja la imagen en la posicion actualizada
            if (ventana == 1){//dibuja el menu
                g.drawImage(play.getImagenI(), play.getPosX(), play.getPosY(), this);
                g.drawImage(options.getImagenI(), options.getPosX(), options.getPosY(), this);
                g.drawImage(bcredits.getImagenI(), bcredits.getPosX(), bcredits.getPosY(), this);
                g.drawImage(howtoplay.getImagenI(), howtoplay.getPosX(), howtoplay.getPosY(), this);
                g.setColor(Color.white);
                g.setFont(new Font("default", Font.BOLD, 20));
                g.drawString("Solo use clicks. Funcionan play y credits",20, 60);
            }
            if (ventana==2){//dibuja el juego
                if (cambio % 3==1){
                    g.drawImage(Desierto, 0, 0, this);
                }
                else{
                    if (cambio % 3 ==2){
                        g.drawImage(Ciudad, 0, 0, this);
                    }
                    else{
                        g.drawImage(Selva, 0, 0, this);
                    }
                }
                g.drawImage(Calle, 206, 0, this);
                g.drawImage(bar, 0, 20, this);
                g.drawImage(camion.getImagenI(), camion.getPosX(), camion.getPosY(), this);
                g.drawImage(M1.getImagenI(), M1.getPosX(), M1.getPosY(), this);
                g.drawImage(M2.getImagenI(), M2.getPosX(), M2.getPosY(), this);
                g.drawImage(M3.getImagenI(), M3.getPosX(), M3.getPosY(), this);
                g.drawImage(M4.getImagenI(), M4.getPosX(), M4.getPosY(), this);
            }
            if(ventana == 5){//dibuja los creditos
                g.drawImage(credits, 0, 0, this);
            }
        }
        else{
            //Da un mensaje mientras se carga el dibujo	
            g.drawString("No se cargo la imagen..", 20, 20);
        }
    }
   
}
