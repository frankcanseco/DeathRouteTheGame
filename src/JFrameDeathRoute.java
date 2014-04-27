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
    private LinkedList<Mutante> mutantes; //objetos mutantes enemigos
    private Carretera carretera;//objeo carretera
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
    private int entradaMut;
    private int entradaMutY;
    private int velocidadCalle; //velocidad a la que se mueve la calle
    private long tiempoActual;
    private long tiempoZombie;
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
        tiempoActual = 0;
        velocidadCalle = 2;
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
        mutantes = new LinkedList();
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
            camion.setPosX(camion.getPosX()+camionVx);
            camion.setPosY(camion.getPosY()+camionVy);
            for (Mutante mut:mutantes){
                dx = camion.getPosX() - mut.getPosX();
                dy = camion.getPosY() - mut.getPosY();
                norm = (float) Math.sqrt(dx * dx + dy * dy);
                dx = (int) (dx * (mut.getVelocidad() / norm));
                dy = (int) (dy * (mut.getVelocidad()  / norm));
                mut.setPosX(mut.getPosX()+dx);
                mut.setPosY(mut.getPosY()+dy);
            }
            if (System.currentTimeMillis()-tiempoActual >= 60000){
                cambio++;
                tiempoActual = System.currentTimeMillis();
                tiempoZombie = System.currentTimeMillis();
            }
            if (cambio%10<4){
                if (System.currentTimeMillis()-tiempoZombie >= 60000/6){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mutantes.push(new Mutante(entradaMut,entradaMutY,im2,2));
                    tiempoZombie = System.currentTimeMillis();
                }
            }
            else{
                if(cambio%10<8){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mutantes.push(new Mutante(entradaMut,entradaMutY,im2,2));
                    tiempoZombie = System.currentTimeMillis();
                }
                else{
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mutantes.push(new Mutante(entradaMut,entradaMutY,im2,2));
                    tiempoZombie = System.currentTimeMillis();
                }
            }
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
                    tiempoActual = System.currentTimeMillis();
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
                for (Mutante mut:mutantes){
                    g.drawImage(mut.getImagenI(), mut.getPosX(), mut.getPosY(), this);
                }
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
