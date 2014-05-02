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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.JFrame;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    private LinkedList<Mutante> restos; //objetos restos
    private LinkedList<Mutante> cactus;
    private Mutante toolbox; // objetos items
    private Mutante bubbles;
    private Mutante cactusObj;
    private Fondo carretera;//objeto carretera
    private Fondo carretera2;//objeto carretera2 para simular continuidad
    private Fondo desierto;//objeto desierto
    private Fondo desierto2;//objeto desierto2 para simular continuidad en movimiento
    private Fondo selva;//objeto selva
    private Fondo selva2;//objeto selva2 para simular continuidad
    private Fondo ciudad;//objeto ciudad
    private Fondo ciudad2;//objeto ciudad2 para simular continuidad
    private Image Selva;//Se declaran las variables de imagenes
    private Image Ciudad;
    private Image Desierto;
    private Image Calle;
    private Image cam; //sprites utilizadas para los objetos
    private Image imToolbox;
    private Image imBubbles;
    private Image imCactus;
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
    private Image sangre;
    private Image carHit;
    private int ventana;//variable para cambio de ventana
    private int cambio;//variable para cambiar fondo
    private float norm;
    private int dx;
    private int damageTempo; // counter utilizado para la duracion del golpe para la animacion de la van
    private int dy;
    private int camionVx;
    private int camionVy;
    private int entradaMut;
    private int entradaMutY;
    private int velocidadCalle; //velocidad a la que se mueve la calle
    private int vidaJugador;    //vida del jugador, empieza en 100 y va reduciendo por el damageZombie
    private int damageZombie;   //valor que representa el damage que quita el zombie
    private int counterCactus;
    private long tiempoActual;
    private long tiempoZombie;
    private boolean guardar;
    private String nombreArchivo;    //Nombre del archivo.
    private String nombreArchivoJugador;
    private String nombreJugador;
    private Vector vec;    // Objeto vector para agregar el puntaje.
    private int numCactusNivel; // numero de cactus por nivel

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
        velocidadCalle = 15;
        vidaJugador = 100;
        damageTempo = 50;
        counterCactus = 80;
        damageZombie = 11;
        numCactusNivel = 15;
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
        sangre = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/sangre.png"));
        carHit = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/vanHit.gif"));
        imToolbox = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/toolbox.png"));
        imBubbles = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/burbujas.gif"));
        imCactus = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/cactus.png"));
        camion = new Jugador((int) (this.getWidth()/2),(int)((this.getHeight()/2)),cam);//se inicializan los objetos
        toolbox = new Mutante((int) (this.getWidth()/2),(int)((this.getHeight()/2)),imToolbox, velocidadCalle, 0);
        bubbles = new Mutante((int) (this.getWidth()/2),(int)((this.getHeight()/2)),imBubbles, velocidadCalle, 0);
        carretera = new Fondo(206, 0, Calle);
        carretera2 = new Fondo(206, -820, Calle);
        desierto = new Fondo(0, 0, Desierto);
        desierto2 = new Fondo(0, -820, Desierto);
        ciudad = new Fondo(0, 0, Ciudad);
        ciudad2 = new Fondo(0, -820, Ciudad);
        selva = new Fondo(0, 0, Selva);
        selva2 = new Fondo(0, -820, Selva);
        play = new Botones(270,150,Iplay);
        options = new Botones (270 , 450 , Ioptions);
        bcredits = new Botones (270,600,Icredits);
        howtoplay = new Botones (270,300,Ihowtoplay);
        mutantes = new LinkedList();
        restos = new LinkedList();
        cactus = new LinkedList(); 
        this.setBackground(Color.BLACK);
        nombreArchivo = "Puntaje.txt";
        nombreArchivoJugador = "UltimoJugador.txt";
        vec = new Vector();
        addKeyListener(this);   
        addMouseListener(this);

        try {
            cargarNombreJugador();
        } catch (IOException ex) {
            Logger.getLogger(JFrameDeathRoute.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            damageTempo += 1;
            counterCactus += 1;
            // Se actualiza el <code>Applet</code> repintando el contenido.
            repaint();
            if (guardar) {
                guardar = false;
                try {
                    //leeArchivo();    
                    //lee el contenido del archivo
                    //Agrega el contenido del nuevo puntaje al vector.
                    //guarda posX del carrodel carro, posX y posY y velX y velY popo
                    vec.removeAllElements();
                    //vec.add(new Puntaje());
                    //Graba el vector en el archivo.
                    grabaArchivo();
                } catch (IOException ex) {

                    System.out.println("Error en " + ex.toString());
                }
            }
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

            for (Mutante cac:cactus){
              cac.setPosY(cac.getPosY()+5);
            }          

            if(counterCactus > 300 && numCactusNivel>0){ 
                int posrX = 206 + (int) (Math.random() * this.getWidth()/2);    //cactus aparecen en lugares random en la orilla de arriba
                int posrY = -2;
                cactusObj = new Mutante(posrX, posrY, imCactus, velocidadCalle, 1);
                cactus.add(cactusObj);
                counterCactus = 0;
                numCactusNivel--;
            }
            
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
                    mutantes.push(new Mutante(entradaMut,entradaMutY,im2, 3, damageZombie));
                    tiempoZombie = System.currentTimeMillis();
                }
            }
            else{
                if(cambio%10<8){
                   if (System.currentTimeMillis()-tiempoZombie >= 60000/9){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mutantes.push(new Mutante(entradaMut,entradaMutY,im2,2, damageZombie));
                    tiempoZombie = System.currentTimeMillis();
                    }
                }
                else{
                    if (System.currentTimeMillis()-tiempoZombie >= 60000/12){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mutantes.push(new Mutante(entradaMut,entradaMutY,im2,2, damageZombie));
                    tiempoZombie = System.currentTimeMillis();
                }
                }
            }
            //movimiento infinito de la carretera, selva, ciudad, desierto
            carretera.setPosY(carretera.getPosY()+velocidadCalle);
            carretera2.setPosY(carretera2.getPosY()+velocidadCalle);
            desierto.setPosY(desierto.getPosY()+velocidadCalle);
            desierto2.setPosY(desierto2.getPosY()+velocidadCalle);
            selva.setPosY(selva.getPosY()+velocidadCalle);
            selva2.setPosY(selva2.getPosY()+velocidadCalle);
            ciudad.setPosY(ciudad.getPosY()+velocidadCalle);
            ciudad2.setPosY(ciudad2.getPosY()+velocidadCalle);
            if (carretera.getPosY() > this.getHeight()){
                carretera.setPosY(carretera2.getPosY()-800);
                desierto.setPosY(desierto2.getPosY()-800);
                selva.setPosY(selva2.getPosY()-800);
                ciudad.setPosY(ciudad2.getPosY()-800);
            }
            if (carretera2.getPosY() > this.getHeight()){
                carretera2.setPosY(carretera.getPosY()-800);
                desierto2.setPosY(desierto.getPosY()-800);
                selva2.setPosY(selva.getPosY()-800);
                ciudad2.setPosY(ciudad.getPosY()-800);
            }
        }
        for (Mutante res:restos) {
            res.setPosY(res.getPosY()+velocidadCalle);
        }
    }
    
    /**
     * Metodo usado para checar las colisiones del objeto elefante y raton con
     * las orillas del <code>Applet</code>.
     */
    public void checaColision() {

        if (camion.getPosX()<206){//Checa que el camion  no se salga de la carretera
            camion.setPosX(206);
        }
        else{
            if (camion.getPosX()+camion.getAncho()>594){ // lo mismo
                camion.setPosX(594-camion.getAncho());
            }
        }
        if (camion.getPosY()<140){//Checa que el camion  no se salga de la carretera
            camion.setPosY(140);
        }
        else{
            if (camion.getPosY()+camion.getAlto()>getHeight()){
                camion.setPosY(getHeight()-camion.getAlto());
            }
        }

        for (Mutante cac:cactus) {
            for (Mutante mut:mutantes) {
                if(mut.intersecta(camion)){
                    damageTempo = 0;
                    vidaJugador-=mut.getDamage();
                    mut.setDamage(0);
                    mutantes.remove(mut);
                    restos.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,0,0));
                    break;
                }
                
                if(cac.intersecta(mut)){
                    mutantes.remove(mut);
                    restos.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,0,0));
                    break;
                }
            }
            if (cac.intersecta(camion)){

                vidaJugador-=cac.getDamage();
                damageTempo = 0;
                cac.setDamage(0);
                camion.setPosY(camion.getPosY()+5);
                break;
            }
        }


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
            camionVx = -4;
        }
        else{
            if (e.getKeyCode() == KeyEvent.VK_D){
                camionVx = 4;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W){
            if (camionVx != 0){
            camionVy = -2;
            camionVx -= camionVx;
            }
            else{
                camionVy = -4;
            }
        }
        else{
            if (e.getKeyCode() == KeyEvent.VK_S){
                velocidadCalle = 13;
                if (camionVx != 0){
                    camionVy = 2;
                    camionVx -= camionVx;
                }
                else{
                    camionVy = 4;
                }
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
             velocidadCalle = 15;
         }
    }

    public void mouseClicked(MouseEvent e) {
        if (ventana == 1){//Funcion para el cambio de ventanas en el menu
            if(e.getPoint().getX()>= 270 && e.getPoint().getX() <= 560){
                if (e.getPoint().getY()>= 150 && e.getPoint().getY() <= 250){
                    ventana = 2;
                    tiempoActual = System.currentTimeMillis();
                }
                if (e.getPoint().getY() >= 255 && e.getPoint().getY() <= 300) {
                    String nombre = JOptionPane.showInputDialog("Cual es tu nombre?");
                    try {

                        nombreJugador = nombre;
                        //Graba el vector en el archivo.
                        grabaArchivoNombre();
                    } catch (IOException ex) {
                        System.out.println("Error en " + ex.toString());
                    }

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
            switch(ventana){
                case 1:
                    g.drawImage(play.getImagenI(), play.getPosX(), play.getPosY(), this);
                    g.drawImage(options.getImagenI(), options.getPosX(), options.getPosY(), this);
                    g.drawImage(bcredits.getImagenI(), bcredits.getPosX(), bcredits.getPosY(), this);
                    g.drawImage(howtoplay.getImagenI(), howtoplay.getPosX(), howtoplay.getPosY(), this);
                    g.setColor(Color.white);
                    //g.setFont(new Font("default", Font.BOLD, 20));
                    //g.drawString("Solo use clicks. Funcionan play y credits",20, 60);
                    g.setFont(new Font("default", Font.BOLD, 20));
                    g.drawString("Playing as: " + nombreJugador,270,280);
                    break;
                    
                case 2:
                    if (cambio < 11){
                        g.drawImage(desierto.getImagenI(), desierto.getPosX(), desierto.getPosY(), this);
                        g.drawImage(desierto2.getImagenI(), desierto2.getPosX(), desierto2.getPosY(), this);
                    }
                    else{
                        if (cambio < 21){
                            g.drawImage(ciudad.getImagenI(), ciudad.getPosX(), ciudad.getPosY(), this);
                            g.drawImage(ciudad2.getImagenI(), ciudad2.getPosX(), ciudad2.getPosY(), this);
                        }
                        else{
                            g.drawImage(selva.getImagenI(), selva.getPosX(), selva.getPosY(), this);
                            g.drawImage(selva2.getImagenI(), selva2.getPosX(), selva2.getPosY(), this);
                        }
                    }
                    g.drawImage(carretera.getImagenI(), carretera.getPosX(), carretera.getPosY(), this);
                    g.drawImage(carretera2.getImagenI(), carretera2.getPosX(), carretera2.getPosY(), this);
                    for (Mutante cac:cactus){
                        g.drawImage(cac.getImagenI(), cac.getPosX(), cac.getPosY(), this);
                    }
                    g.drawImage(bar, 0, 20, this);
                    g.setColor(Color.white);
                    g.setFont(new Font("default", Font.BOLD, 20));
                    g.drawString(nombreJugador , 2, 70);                    
                    g.setFont(new Font("default", Font.BOLD, 20));
                    g.drawString(""+ vidaJugador, 2, 120);

                    if(damageTempo<50){
                        g.drawImage(carHit, camion.getPosX(), camion.getPosY(), this);
                    }
                    else{
                        g.drawImage(camion.getImagenI(), camion.getPosX(), camion.getPosY(), this);    
                    }

                    for (Mutante mut:mutantes){
                        g.drawImage(mut.getImagenI(), mut.getPosX(), mut.getPosY(), this);
                    }
                    for (Mutante res:restos) {
                        g.drawImage(res.getImagenI(), res.getPosX(), res.getPosY(), this);
                    }
                    break;
                    
                case 5:
                    g.drawImage(credits, 0, 0, this);
                    break;
            }
        }
        else{
            //Da un mensaje mientras se carga el dibujo	
            g.drawString("No se cargo la imagen..", 20, 20);
        }
    }
   
    public void grabaArchivo() throws IOException {
        PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivo));
        for (int i = 0; i < vec.size(); i++) {
            Puntaje x;
            x = (Puntaje) vec.get(i);
            fileOut.println(x.toString());
        }
        fileOut.close();
    }
    
    public void grabaArchivoNombre() throws IOException {
        PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivoJugador));
        fileOut.println(nombreJugador);
        fileOut.close();
    }

    public void cargarNombreJugador() throws IOException {
        BufferedReader fileIn;
        try {
            fileIn = new BufferedReader(new FileReader(nombreArchivoJugador));
        } catch (FileNotFoundException e) {
            File nombreJug = new File(nombreArchivoJugador);
            PrintWriter fileOut = new PrintWriter(nombreJug);
            fileOut.println("N/A");
            fileOut.close();
            fileIn = new BufferedReader(new FileReader(nombreArchivoJugador));
        }
        nombreJugador = fileIn.readLine();

        fileIn.close();
    }

    
}
