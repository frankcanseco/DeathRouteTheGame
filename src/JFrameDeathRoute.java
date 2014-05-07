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
import java.awt.Graphics2D;
import java.applet.AudioClip;
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
import java.util.Random;

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
    private LinkedList<Mutante> crosshair;
    private LinkedList<Mutante> bullets;
    private LinkedList<Mutante> mElectro;
    private LinkedList<Mutante> mRojo;
    private LinkedList<Mutante> mensajes;
    private LinkedList<Mutante> toolbox;
    private LinkedList<Mutante> acid;     //estos estaran en la calle para ser agarrados
    private LinkedList<Mutante> acidItem; // estos seran los utilizados y accionador por el jugador
    private Mutante toolboxObj; // objetos items
    private Mutante acidObj;
    private Mutante cactusObj;
    private Mutante acidItemObj;
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
    private Image vanE;
    private Image hidrante;
    private Image roca;
    private Image cam; //sprites utilizadas para los objetos
    private Image imToolbox;
    private Image imBubbles;
    private Image imInventoryAcid;
    private Image imCactus;
    private Image im1;
    private Image imZR;
    private Image imZE;
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
    private Image crossIm;
    private Image bulletIm;
    private Image bigZombie;
    private int ventana;//variable para cambio de ventana
    private int cambio;//variable para cambiar fondo
    private float norm;
    private int scoreJugador; // score juego
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
    private int mutanteCenterX;
    private int mutanteCenterY;
    private int camionCenterX;
    private int camionCenterY;
    private double angle;
    private int counterToolbox;
    private int counterAcid;
    private int numInventory;
    private int movimiento;
    private long tiempoActual;
    private long tiempoZombie;
    private long tiempoZombieR;
    private long tiempoZombieE;
    private boolean guardar;
    private boolean movU;
    private boolean movR;
    private boolean movL;
    private boolean movD;
    private boolean pausa;
    private String nombreArchivoHighscores;    //Nombre del archivo.
    private boolean lanzaAcido;
    private boolean electro;
    private String nombreArchivo;    //Nombre del archivo.
    private String nombreArchivoJugador;
    private String nombreJugador;
    private String [] arr;
    private Puntaje puntActual;
    private Vector puntajes;    // Objeto vector para agregar el puntaje.
    private int numCactusNivel; // numero de cactus por nivel
    private int numToolboxNivel; // numero de toolbox por nivel
    private int numAcidNivel; // numero de acid por nivel
    private int velocidadCactus;
    private int comboScore;
    private int matrixDamage;
    private long tiempoElectro;
    private long tiempoTecla;
    private int duracionMensaje;
    private boolean teclear;
    private boolean matrix;
    private int[] cambioElectro;
    private Random rnd; 
    private String teclaIntento;
    private int intento;
    private String abc;
    private String[] password;
    private SoundClip dying;    // Objetos de sonido Audioclip
    private SoundClip engine;    // Objeto SoundClip
    private SoundClip heartbeat;    // Objeto SoundClip
    private SoundClip honk;    // Objeto SoundClip
    private SoundClip splash;    // Objeto SoundClip
    private SoundClip splash2;
    private SoundClip music1;    // Objeto SoundClip
    private SoundClip music2;
    private int segundero;
    private int xCross;
    private int yCross;
    private boolean goodX;
    private long segunderoTiempo;
    StringBuilder sb = new StringBuilder();

    public JFrameDeathRoute(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Death Route"); setSize(800, 820);
        init();
        start();
    }

    public void init(){
        movU = false;
        duracionMensaje = 50;
        matrix = false;
        abc = "YUIOHJKL";
        password = new String[4];
        movR = false;
        movD = false;
        movL = false;
        matrixDamage = 0;
        teclear =true;
        rnd = new Random();
        crosshair = new LinkedList();
        bullets = new LinkedList();
        mensajes = new LinkedList();
        mElectro = new LinkedList();
        mRojo = new LinkedList();
        electro = true;
        ventana = 1;//se inicializa con menu
        cambio = 19;
        camionVx =0;
        camionVy = 0;
        velocidadCalle = 9;
        vidaJugador = 100;
        damageTempo = 50;
        counterCactus = 80;
        counterToolbox = 40;
        counterAcid = 0;
        numInventory = 0;
        damageZombie = 5;
        numCactusNivel = 50;
        numToolboxNivel = 40;
        numAcidNivel = 30;
        scoreJugador   = 0;
        tiempoTecla=0;
        lanzaAcido = false;
        pausa = false;
        electro = false;
        vanE = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/vanElec.gif"));
        Selva = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/selva.png"));
        Ciudad = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/ciudad.png"));
        Desierto = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/desierto.png"));
        Calle = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/carretera.png"));
        im1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/zombieUp.gif"));
        im2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/zombieDown.gif"));
        im3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/zombieLeft.gif"));
        im4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/zombieRight.gif"));
        imZR = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/zombieUpRojo.gif"));
        imZE = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/zombieUpThunder.gif"));
        Iplay = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/botonPlay.png"));
        Ioptions = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/botonOptions.png"));
        Icredits = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/botonCredits.png"));
        Ihowtoplay = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/botonHow.png"));
        bar = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/bar2.png"));
        cam = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/van.gif"));
        credits = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/credits.png"));
        sangre = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/sangre.png"));
        carHit = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/vanHit.gif"));
        imToolbox = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/toolbox.png"));
        imBubbles = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/burbujas.gif"));
        imInventoryAcid = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/acidInventory.png"));
        imCactus = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/cactus.png"));
        //URL eaURL = this.getClass().getResource("sounds/elephant.wav");
        dying = new SoundClip("/sounds/dying.wav");
        engine = new SoundClip("/sounds/engine.wav");
        heartbeat = new SoundClip("/sounds/heartbeat.wav");
        honk = new SoundClip("/sounds/honk.wav");
        splash = new SoundClip("/sounds/splash.wav");
        splash2 = new SoundClip("/sounds/splash2.wav");
        music1 = new SoundClip("/sounds/music1.wav");
        music2 = new SoundClip("/sounds/music2.wav");
        crossIm = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/crosshairB.png"));
        bulletIm = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/bullet.png"));
        bigZombie = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/zombiegrande.gif"));
        hidrante = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/hidrante.png"));
        roca = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/stone.png"));
        camion = new Jugador((int) (this.getWidth()/2),(int)((this.getHeight()/2)),cam);//se inicializan los objetos
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
        cambioElectro = new int[2];
        teclaIntento = " ";
        for (int i = 0 ; i < 2; i ++){
            cambioElectro[i] = (rnd.nextInt(2));
            cambioElectro[i] += cambioElectro[i]-1;
        }
        for (int i = 0; i < 4; i++) {
            sb.append(abc.charAt(rnd.nextInt(abc.length())));
            password[i]=sb.toString();
            sb.deleteCharAt(0);
        }
        toolbox = new LinkedList();
        acid = new LinkedList();
        acidItem = new LinkedList();
        this.setBackground(Color.BLACK);
        nombreArchivoHighscores = "Puntaje.txt";
        nombreArchivoJugador = "UltimoJugador.txt";
        puntajes = new Vector();
        addKeyListener(this);   
        addMouseListener(this);
        velocidadCactus = 3;
        tiempoElectro = 0;
        intento = 0;
        teclaIntento = " ";
        //Esto ira en el zombie
        
        //Esto ira en el otro zombie
        //matrix=true;
        
        cactus.push(new Mutante(206-30, 410, imCactus, velocidadCalle, 1));
        cactus.push(new Mutante(594, 410, imCactus, velocidadCalle, 1));
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
            if (vidaJugador > 0 && !pausa&&!matrix) {
                actualiza();
                checaColision();
                damageTempo += 1;
                counterCactus += 1;
                counterToolbox += 1;
                counterAcid += 1;
                if (electro){
                    tiempoElectro +=1;
                    tiempoTecla+=1;
                }
            } 
            if(matrix){
                tiempoActual += 20;
                matrixDamage += 20;
            }
            if (vidaJugador <= 0){
                
                try {
                    cargarArchivoHighscores();
                } catch (IOException ex) {
                }
                puntActual = new Puntaje(nombreJugador, scoreJugador);
                for(int i = 0; i < puntajes.size(); i++){
                    Puntaje puntUltimo = (Puntaje) puntajes.get(i);
                    if (puntActual.getPuntos() > puntUltimo.getPuntos()){
                        puntajes.insertElementAt(puntActual, i);
                        break;
                    }
                }
                JOptionPane.showMessageDialog(null,
                        "1. " + ((Puntaje)puntajes.get(0)).getNombre() + " " + ((Puntaje)puntajes.get(0)).getPuntos() + "\n" +
                        "2. " + ((Puntaje)puntajes.get(1)).getNombre() + " " + ((Puntaje)puntajes.get(1)).getPuntos() + "\n" +
                        "3. " + ((Puntaje)puntajes.get(2)).getNombre() + " " + ((Puntaje)puntajes.get(2)).getPuntos() + "\n" +
                        "4. " + ((Puntaje)puntajes.get(3)).getNombre() + " " + ((Puntaje)puntajes.get(3)).getPuntos() + "\n" +
                        "5. " + ((Puntaje)puntajes.get(4)).getNombre() + " " + ((Puntaje)puntajes.get(4)).getPuntos() + "\n" +
                        "6. " + ((Puntaje)puntajes.get(5)).getNombre() + " " + ((Puntaje)puntajes.get(5)).getPuntos() + "\n" +
                        "7. " + ((Puntaje)puntajes.get(6)).getNombre() + " " + ((Puntaje)puntajes.get(6)).getPuntos() + "\n" +
                        "8. " + ((Puntaje)puntajes.get(7)).getNombre() + " " + ((Puntaje)puntajes.get(7)).getPuntos() + "\n" +
                        "9. " + ((Puntaje)puntajes.get(8)).getNombre() + " " + ((Puntaje)puntajes.get(8)).getPuntos() + "\n" +
                        "10. " + ((Puntaje)puntajes.get(9)).getNombre() + " " + ((Puntaje)puntajes.get(9)).getPuntos() + "\n"
                                ,"Highscores",
                        JOptionPane.PLAIN_MESSAGE);
                reiniciarJuego();
                try {
                    grabaArchivoHighscores();
                } catch (IOException ex) {
                }
            }
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
            camionVy = 0;
            camionVx = 0;
            if (movU && !movD){
                camionVy = -4;
            }
            if (movD && !movU){
                camionVy = 4;
            }
            if (movR && !movL){
                camionVx = 4;
            }
            if (movL && !movR){
                camionVx = -4;
            }
            for (Mutante men:mensajes){
                men.setDamage(men.getDamage()-1);
                if (men.getDamage()<0){
                    mensajes.remove(men);
                }
            }
            if ((Math.abs(camionVx)+Math.abs(camionVy)) == 8){
                camionVy -= camionVy/4;
                camionVx -= camionVx/4;
            }
             if (electro == true){
                 
                 camionVy*=cambioElectro[0];
                 camionVx*=cambioElectro[1];
                 if(tiempoTecla >= 20){
                     tiempoTecla = 0;
                     teclear = true;
                 }
                 if (password[intento].equals(teclaIntento)){
                     teclaIntento = " ";
                     intento++;
                     if (intento ==4){
                     electro =false;
                    }
                 }
                 if (tiempoElectro >= 250){
                     for (int i = 0 ; i < 2; i ++){
                        cambioElectro[i] = (rnd.nextInt(2));
                         cambioElectro[i] += cambioElectro[i]-1;
                     }
                     tiempoElectro = 0;
                 }
            }
            camion.setPosX(camion.getPosX()+camionVx);
            camion.setPosY(camion.getPosY()+camionVy);

            for (Mutante cac:cactus){
              if (camionVy == 0){
              if((cac.getPosY()+cac.getAlto()/2)>(camion.getPosY()+camion.getAlto()/2)){
                  velocidadCactus = -3;
              }
              else{
                  if((cac.getPosY()+cac.getAlto()/2)<(camion.getPosY()+camion.getAlto()/2)){
                     velocidadCactus = 3;
                }
              }
              }
              else{
                  cac.setPosY(cac.getPosY()+velocidadCactus);
              }
              if (camionVy > 0 || camionVy < 0){
                  if ((cac.getPosY()+cac.getAlto()/2)>(camion.getPosY()+camion.getAlto()/2)+150){
                      velocidadCactus = -4;
                  }
                  if ((cac.getPosY()+cac.getAlto()/2)<(camion.getPosY()+camion.getAlto()/2)-150){
                      velocidadCactus = 4;
                  }
                  if (velocidadCactus > 0){
                      if ((cac.getPosY()+cac.getAlto()/2)>(camion.getPosY()+camion.getAlto()/2)-50){
                      velocidadCactus = 2;
                    }
                  }
                  if (velocidadCactus < 0){
                      if ((cac.getPosY()+cac.getAlto()/2)<(camion.getPosY()+camion.getAlto()/2)+50){
                      velocidadCactus = -2;
                    }
                  }
                }
              cac.setPosY(cac.getPosY()+velocidadCactus);
            }          

            for(Mutante tool:toolbox){
                tool.setPosY(tool.getPosY()+velocidadCalle);
            }

            for(Mutante ac:acid){
                ac.setPosY(ac.getPosY()+velocidadCalle);
            }

            for(Mutante ai:acidItem){ //acido cuando ya se lanza el acido
                ai.setPosY(ai.getPosY()+velocidadCalle);
            }

            if(counterToolbox > 250 && numToolboxNivel>0){ 
                int posrX = 206 + (int) (Math.random() * this.getWidth()/2);    //toolbox aparecen en lugares random en la orilla de arriba
                int posrY = -2;
                toolboxObj = new Mutante(posrX, posrY, imToolbox, velocidadCalle, 10);
                toolbox.add(toolboxObj);
                counterToolbox = 0;
                numToolboxNivel--;
            }

            if(counterAcid > 300 && numAcidNivel>0){ 
                int posrX = 206 + (int) (Math.random() * ((this.getWidth()/2))-30);    //acidos aparecen en lugares random en la orilla de arriba
                int posrY = -2;
                acidObj = new Mutante(posrX, posrY, imInventoryAcid, velocidadCalle, 0);
                acid.add(acidObj);
                counterAcid = 0;
                numAcidNivel--;
            }

            if(lanzaAcido && numInventory>0){
                lanzaAcido = false;
                numInventory--;
                int posaX;
                int posaY;
                if (camionVx>0){ // si va a la derecha, lo lanza a la izq
                    posaX = camion.getPosX()-60;
                    posaY = camion.getPosY();
                }
                else if(camionVx<0){ // si va a la izq, lo lanza a la der
                    posaX = camion.getPosX()+60;
                    posaY = camion.getPosY();
                }
                else{ //si va para enfrente, o no se mueve, lo lanza por detras
                    posaX = camion.getPosX();
                    posaY = camion.getPosY()+60;
                }
                acidItemObj = new Mutante(posaX, posaY, imBubbles, velocidadCalle, 0);
                acidItem.add(acidItemObj);
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
            for (Mutante mut:mElectro){
                dx = camion.getPosX() - mut.getPosX();
                dy = camion.getPosY() - mut.getPosY();
                norm = (float) Math.sqrt(dx * dx + dy * dy);
                dx = (int) (dx * (mut.getVelocidad() / norm));
                dy = (int) (dy * (mut.getVelocidad()  / norm));
                mut.setPosX(mut.getPosX()+dx);
                mut.setPosY(mut.getPosY()+dy);
            }
            for (Mutante mut:mRojo){
                dx = camion.getPosX() - mut.getPosX();
                dy = camion.getPosY() - mut.getPosY();
                norm = (float) Math.sqrt(dx * dx + dy * dy);
                dx = (int) (dx * (mut.getVelocidad() / norm));
                dy = (int) (dy * (mut.getVelocidad()  / norm));
                mut.setPosX(mut.getPosX()+dx);
                mut.setPosY(mut.getPosY()+dy);
            }
            if (System.currentTimeMillis()-tiempoActual >= 30000){
                cambio++;
                mensajes.push(new Mutante(camion.getPosX(),camion.getPosY(),sangre,0,duracionMensaje));
                if (cambio <11){
                    scoreJugador+=1000;
                }
                else{
                    if (cambio < 21){
                        scoreJugador+=2000;
                    }
                    else{
                        scoreJugador+=3000;
                    }
                }
                tiempoActual = System.currentTimeMillis();
                tiempoZombie = System.currentTimeMillis();
                tiempoZombieR = System.currentTimeMillis();
                tiempoZombieE = System.currentTimeMillis();
            }
            if (cambio%10<4){
                if (System.currentTimeMillis()-tiempoZombie >= 30000/12){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mutantes.push(new Mutante(entradaMut,entradaMutY,im1,3, damageZombie));

                    tiempoZombie = System.currentTimeMillis();
                }
            }
            else{
                if(cambio%10<8){
                   if (System.currentTimeMillis()-tiempoZombie >= 30000/16){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mutantes.push(new Mutante(entradaMut,entradaMutY,im1,3, damageZombie));
                    tiempoZombie = System.currentTimeMillis();
                    }
                }
                else{
                    if (System.currentTimeMillis()-tiempoZombie >= 30000/20){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mutantes.push(new Mutante(entradaMut,entradaMutY,im1,3, damageZombie));
                    tiempoZombie = System.currentTimeMillis();
                }
                }
            }
            if(cambio > 10){
                if (cambio%10<4){
                if (System.currentTimeMillis()-tiempoZombieR >= 30000/4){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mRojo.push(new Mutante(entradaMut,entradaMutY,imZR,4, damageZombie));

                    tiempoZombieR = System.currentTimeMillis();
                }
            }
            else{
                if(cambio%10<8){
                   if (System.currentTimeMillis()-tiempoZombieR >= 30000/6){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mRojo.push(new Mutante(entradaMut,entradaMutY,imZR,4, damageZombie));
                    tiempoZombieR = System.currentTimeMillis();
                    }
                }
                else{
                    if (System.currentTimeMillis()-tiempoZombieR >= 30000/8){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mRojo.push(new Mutante(entradaMut,entradaMutY,imZR,4, damageZombie));
                    tiempoZombieR = System.currentTimeMillis();
                }
                }
            }   
            }
            if(cambio > 20){
                if (cambio%10<4){
                if (System.currentTimeMillis()-tiempoZombieE >= 30000/3){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mElectro.push(new Mutante(entradaMut,entradaMutY,imZE,4, 0));

                    tiempoZombieE = System.currentTimeMillis();
                }
            }
            else{
                if(cambio%10<8){
                   if (System.currentTimeMillis()-tiempoZombieE >= 30000/4){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mElectro.push(new Mutante(entradaMut,entradaMutY,imZE,4, 0));
                    tiempoZombieE = System.currentTimeMillis();
                    }
                }
                else{
                    if (System.currentTimeMillis()-tiempoZombieE >= 30000/5){
                    if (Math.random()>= .5){
                        entradaMut = -30;
                    }
                    else{
                        entradaMut = this.getWidth();
                    }
                    entradaMutY = (int) (Math.random()*(this.getHeight() - 150) + 150);
                    mElectro.push(new Mutante(entradaMut,entradaMutY,imZE,4, 0));
                    tiempoZombieE = System.currentTimeMillis();
                }
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
        //engine.play();
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
        for (Mutante cac:cactus){
            if (cac.getPosY()+cac.getAlto()>this.getHeight()){
                cac.setPosY(this.getHeight()-cac.getAlto());
            }
            else{
                if (cac.getPosY()<140){
                cac.setPosY(140);
            }
            }
        }
        for (Mutante cac:cactus) {
            for (Mutante mut:mutantes) {
                if(mut.intersecta(camion)){
                    splash.play();
                    damageTempo = 0;
                    vidaJugador-=mut.getDamage();
                    mut.setDamage(0);
                    mutantes.remove(mut);
                    restos.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,0,0));
                    mensajes.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,-damageZombie,duracionMensaje));
                    break;
                }
                
                if(cac.intersecta(mut)){
                    splash2.play();
                    mutantes.remove(mut);
                    restos.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,0,0));
                    scoreJugador+=50;
                    mensajes.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,50,duracionMensaje));
                    break;
                }
            }
            for (Mutante mut:mRojo) {
                if(mut.intersecta(camion)){
                    dying.play();
                    heartbeat.play();
                    damageTempo = 0;
                    matrix = true;
                    matrixDamage = 0;
                    vidaJugador-=mut.getDamage();
                    mut.setDamage(0);
                    mRojo.remove(mut);
                    restos.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,0,0));
                    mensajes.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,-damageZombie,duracionMensaje));
                    for (int i = 0 ; i < rnd.nextInt(3)+3 ; i++){
                        goodX = false;
                        xCross =(int) (rnd.nextInt(600)+75);
                        yCross =(int) (rnd.nextInt(480)+45);
                        crosshair.push(new Mutante(xCross,yCross,crossIm,0,1));
                        while (!goodX){
                            if (xCross >= 215 && xCross <= 475 && yCross >= 45 && yCross <= 165){
                                goodX = true;
                            }
                            if (xCross >= 75 && xCross <= 670 && yCross >= 145 && yCross <= 425){
                                goodX = true;
                            }
                            if (xCross >= 200 && xCross <= 660 && yCross >= 430 && yCross <= 525){
                                goodX = true;
                            }

                            for (int j = 0 ; j < i ; j++){
                                if (crosshair.get(j).intersecta(crosshair.get(i))){
                                    goodX = false;
                                }
                            }

                            xCross =(int) (rnd.nextInt(600)+75);
                            yCross =(int) (rnd.nextInt(480)+45);
                            if(!goodX){
                                crosshair.remove(i);
                                crosshair.push(new Mutante(xCross,yCross,crossIm,0,1));
                            }
                        }

                    }
                    break;
                }
                
                if(cac.intersecta(mut)){
                    splash2.play();
                    dying.play();
                    mRojo.remove(mut);
                    restos.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,0,0));
                    scoreJugador+=100;
                    mensajes.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,100,duracionMensaje));
                    break;
                }
            }
            for (Mutante mut:mElectro) {
                if(mut.intersecta(camion)){
                    dying.play();
                    electro=true;
                    tiempoElectro = 0;
                    intento = 0;
                    teclaIntento = " ";
                    for (int i = 0 ; i < 2; i ++){
                        cambioElectro[i] = (rnd.nextInt(2));
                        cambioElectro[i] += cambioElectro[i]-1;
                    }
                    for (int i = 0; i < 4; i++) {
                        sb.append(abc.charAt(rnd.nextInt(abc.length())));
                        password[i]=sb.toString();
                        sb.deleteCharAt(0);
                    }
                    damageTempo = 0;
                    mut.setDamage(0);
                    mElectro.remove(mut);
                    restos.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,0,0));
                    mensajes.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,-damageZombie,duracionMensaje));
                    break;
                }
                
                if(cac.intersecta(mut)){
                    mElectro.remove(mut);
                    restos.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,0,0));
                    scoreJugador+=200;
                    mensajes.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,200,duracionMensaje));
                    break;
                }
            }
            
        }

        for(Mutante tool:toolbox){
            if(tool.intersecta(camion)){
                heartbeat.play();
                vidaJugador+=tool.getDamage();
                tool.setDamage(0);
                toolbox.remove(tool);
                break;
            }
        }

        for(Mutante ac:acid){
            if(ac.intersecta(camion)){
                acid.remove(ac);
                numInventory++;
                break;
            }
        }

        for(Mutante ai:acidItem){
            for(Mutante mut:mutantes){
                if(ai.intersecta(mut)){
                    splash2.play();
                    mutantes.remove(mut);
                    restos.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,0,0));
                    scoreJugador+=30;
                    mensajes.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,30,duracionMensaje));
                    break;
                }
            }
            for(Mutante mut:mRojo){
                if(ai.intersecta(mut)){
                    splash2.play();
                    mRojo.remove(mut);
                    restos.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,0,0));
                    scoreJugador+=60;
                    mensajes.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,60,duracionMensaje));
                    break;
                }
            }
            for(Mutante mut:mElectro){
                if(ai.intersecta(mut)){
                    splash2.play();
                    mElectro.remove(mut);
                    restos.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,0,0));
                    scoreJugador+=90;
                    mensajes.push(new Mutante(mut.getPosX(),mut.getPosY(),sangre,90,duracionMensaje));
                    break;
                }
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
    
    /**
     * Metodo que responde al ser presionadas las teclas.
     * @param e 
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
             movU = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
             movR = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
             movD = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
             movL = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
             honk.play();
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            lanzaAcido = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_Y){
             if(teclear){
                 teclaIntento = "Y";
                 teclear = false;
             }
        }
        if(e.getKeyCode() == KeyEvent.VK_U){
             if(teclear){
                 teclaIntento = "U";
                 teclear = false;
             }
        }
        if(e.getKeyCode() == KeyEvent.VK_I){
             if(teclear){
                 teclaIntento = "I";
                 teclear = false;
             }
        }
        if(e.getKeyCode() == KeyEvent.VK_O){
             if(teclear){
                 teclaIntento = "O";
                 teclear = false;
             }
        }
        if(e.getKeyCode() == KeyEvent.VK_H){
             if(teclear){
                 teclaIntento = "H";
                 teclear = false;
             }
        }
        if(e.getKeyCode() == KeyEvent.VK_J){
             if(teclear){
                 teclaIntento = "J";
                 teclear = false;
             }
        }
        if(e.getKeyCode() == KeyEvent.VK_K){
             if(teclear){
                 teclaIntento = "K";
                 teclear = false;
             }
        }
        if(e.getKeyCode() == KeyEvent.VK_L){
             if(teclear){
                 teclaIntento = "L";
                 teclear = false;
             }
        }
    } 

    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodo donde se responde al teclado
     * @param e 
     */
    public void keyReleased(KeyEvent e) {
        /**
         * Metodos para el movimiento del carro
         */
       if(e.getKeyCode() == KeyEvent.VK_W){
             movU = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
             movR = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
             movD = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
             movL = false;
        }
        //Se reinicia el juego si el jugador presiona "Q"
        if (e.getKeyCode() == KeyEvent.VK_Q && pausa){
            reiniciarJuego();
        }
        //Se cambia el valor de pausa.
        if (e.getKeyCode() == KeyEvent.VK_P) {
            pausa = !pausa;
        }
    }

    /**
     * En este metodo es donde se le da funcionalidad a los botones
     * @param e es el <code>click</code> del boton.
     */
    public void mouseClicked(MouseEvent e) {
        if (ventana == 1){
            //music1.play();
            if(e.getPoint().getX()>= 270 && e.getPoint().getX() <= 560){

                //boton para entrar al juego
                if (e.getPoint().getY()>= 150 && e.getPoint().getY() <= 250){
                    //music1.stop();
                    engine.setLooping(true);
                    engine.play();
                    music2.setLooping(true);
                    music2.play();
                    ventana = 2;
                    tiempoActual = System.currentTimeMillis();
                    tiempoZombie = System.currentTimeMillis();
                    tiempoZombieR = System.currentTimeMillis();
                    tiempoZombieE = System.currentTimeMillis();
                }
                
                //boton para cambiar de juego
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
                //ventana de instrucciones
                if (e.getPoint().getY() >= 300 && e.getPoint().getY() <= 400) {
                    ventana = 3;
                }
                //ventana de opciones
                if (e.getPoint().getY() >= 455 && e.getPoint().getY() <= 550) {
                    ventana = 4;
                }
                //ventana de creditos
                if (e.getPoint().getY()>= 600 && e.getPoint().getY() <= 700){
                    ventana = 5;
                }
            }
        }
        if(matrix){
            for (Mutante xh:crosshair) {
                if (e.getPoint().getX() <= xh.getPosX()+xh.getAncho() && e.getPoint().getX() >= xh.getPosX()){
                    if (e.getPoint().getY() <= xh.getPosY()+xh.getAncho() && e.getPoint().getY() >= xh.getPosY()){
                        bullets.push(new Mutante(xh.getPosX(),xh.getPosY(),bulletIm,0,0));
                        crosshair.remove(xh);
                        if(crosshair.size()<1){
                            bullets.clear();
                            vidaJugador -=(int) (matrixDamage/1000);
                            matrixDamage = 0;
                            matrix=false;
                        }
                    }
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
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint1(Graphics g) {
        if (camion != null) {
            //Dibuja la imagen en la posicion actualizada            
            switch(ventana){
                /**
                 * Este caso representa la ventana de inicio donde se despliega el menu principal
                 * Aqui se puede mover a diferentes ventanas.
                 */
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
                   
                /**
                 * En este caso es donde se lleva acabo el juego.
                 */
                case 2:
                    if (cambio < 11){
                        g.drawImage(desierto.getImagenI(), desierto.getPosX(), desierto.getPosY(), this);
                        g.drawImage(desierto2.getImagenI(), desierto2.getPosX(), desierto2.getPosY(), this);
                    }
                    else{
                        if (cambio < 21){
                            g.drawImage(selva.getImagenI(), ciudad.getPosX(), ciudad.getPosY(), this);
                            g.drawImage(selva2.getImagenI(), ciudad2.getPosX(), ciudad2.getPosY(), this);
                        }
                        else{
                            g.drawImage(ciudad.getImagenI(), selva.getPosX(), selva.getPosY(), this);
                            g.drawImage(ciudad2.getImagenI(), selva2.getPosX(), selva2.getPosY(), this);
                        }
                    }
                    
                    g.drawImage(carretera.getImagenI(), carretera.getPosX(), carretera.getPosY(), this);
                    g.drawImage(carretera2.getImagenI(), carretera2.getPosX(), carretera2.getPosY(), this);
                    
                    for (Mutante cac:cactus){
                        if(cambio < 11){
                        g.drawImage(cac.getImagenI(), cac.getPosX(), cac.getPosY(), this);
                        }
                        else{
                            if(cambio < 21){
                                g.drawImage(roca, cac.getPosX(), cac.getPosY(), this);
                            }
                            else{
                                g.drawImage(hidrante, cac.getPosX(), cac.getPosY(), this);
                            }
                        }
                        
                    }
                    for (Mutante tool:toolbox){
                        g.drawImage(tool.getImagenI(), tool.getPosX(), tool.getPosY(), this);
                    }
                    for(Mutante ac:acid){
                        g.drawImage(ac.getImagenI(), ac.getPosX(), ac.getPosY(), this);
                    }
                    for(Mutante ai:acidItem){
                        g.drawImage(ai.getImagenI(), ai.getPosX(), ai.getPosY(), this);
                    }

                    
                    //g.setColor(Color.white);
                    //g.drawString(nombreJugador , 2, 70);                    
                    //g.drawString( "" + vidaJugador, 2, 120);
                    //g.setFont(new Font("default", Font.BOLD, 50));
                    //g.drawString( "" + scoreJugador, 710, 110);

                    if(numInventory>0){
                        g.drawImage(imInventoryAcid, 2, 170, this);
                        g.setColor(Color.white);
                        g.setFont(new Font("default", Font.BOLD, 19));
                        g.drawString("" + numInventory , 50, 198);           
                    }

                    if(damageTempo<50){
                        g.drawImage(carHit, camion.getPosX(), camion.getPosY(), this);
                    }
                    else{
                        if(electro){
                            g.drawImage(vanE, camion.getPosX(), camion.getPosY(), this); 
                        }else{
                        g.drawImage(camion.getImagenI(), camion.getPosX(), camion.getPosY(), this);    
                        }
                    }
//                  for (Mutante cac:cactus){
//                     g.drawImage(cac.getImagenI(), cac.getPosX(), cac.getPosY(), this);
//                  }
                    g.drawImage(bar, 0, 20, this);
                    camionCenterX = (int) (camion.getPosX()+camion.getAncho()/2);
                    camionCenterY = (int) (camion.getPosY()+camion.getAlto()/2);
                    for (Mutante mut:mutantes){
                        mutanteCenterX = (int) (mut.getPosX()+mut.getAncho()/2);
                        mutanteCenterY = (int) (mut.getPosY()+mut.getAlto()/2);
                        angle = Math.atan2(mutanteCenterY - camionCenterY, mutanteCenterX - camionCenterX) - Math.PI / 2;
                        ((Graphics2D)g).rotate(angle, mutanteCenterX, mutanteCenterY);
                        g.drawImage(mut.getImagenI(), mut.getPosX(), mut.getPosY(), this);
                        ((Graphics2D)g).rotate(-angle, mutanteCenterX, mutanteCenterY);
                    }
                    for (Mutante mut:mRojo){
                        mutanteCenterX = (int) (mut.getPosX()+mut.getAncho()/2);
                        mutanteCenterY = (int) (mut.getPosY()+mut.getAlto()/2);
                        angle = Math.atan2(mutanteCenterY - camionCenterY, mutanteCenterX - camionCenterX) - Math.PI / 2;
                        ((Graphics2D)g).rotate(angle, mutanteCenterX, mutanteCenterY);
                        g.drawImage(mut.getImagenI(), mut.getPosX(), mut.getPosY(), this);
                        ((Graphics2D)g).rotate(-angle, mutanteCenterX, mutanteCenterY);
                    }
                    for (Mutante mut:mElectro){
                        mutanteCenterX = (int) (mut.getPosX()+mut.getAncho()/2);
                        mutanteCenterY = (int) (mut.getPosY()+mut.getAlto()/2);
                        angle = Math.atan2(mutanteCenterY - camionCenterY, mutanteCenterX - camionCenterX) - Math.PI / 2;
                        ((Graphics2D)g).rotate(angle, mutanteCenterX, mutanteCenterY);
                        g.drawImage(mut.getImagenI(), mut.getPosX(), mut.getPosY(), this);
                        ((Graphics2D)g).rotate(-angle, mutanteCenterX, mutanteCenterY);
                    }
                    for (Mutante res:mensajes) {
                        if(res.getVelocidad()> 0){
                            g.setColor(Color.blue);
                        }else{
                            g.setColor(Color.red);
                        }
                        if(res.getVelocidad()==0){
                            g.setColor(Color.white);
                            g.setFont(new Font("default", Font.BOLD, 40));
                            g.drawString("CAMBIO DE MILLA",res.getPosX()-50, res.getPosY());
                        }
                        else{
                        g.setFont(new Font("default", Font.BOLD, 30));
                        g.drawString(""+res.getVelocidad(),res.getPosX(), res.getPosY());
                        }
                        
                    }
                    for (Mutante res:restos) {
                        g.drawImage(res.getImagenI(), res.getPosX(), res.getPosY(), this);
                    }
                    
                    g.setColor(Color.white);
                    int tamaño;
                    if(scoreJugador > 10000){
                        tamaño = 25;
                    }
                    else{
                        tamaño=40;
                    }
                    g.setFont(new Font("default", Font.BOLD, tamaño));
                    g.setColor(Color.blue);
                    g.drawString(""+scoreJugador,690, 110);
                    g.setFont(new Font("default", Font.BOLD, 20));
                    g.setColor(Color.green);
                    g.drawString(""+nombreJugador,40, 70);
                    if (electro){
                        g.setColor(Color.CYAN);
                        g.setFont(new Font("default", Font.BOLD, 80));
                        for(int i = 0 ; i < 4 ; i++){
                            g.drawString( "_",320+50 *i, 60);
                        }
                        g.setFont(new Font("default", Font.BOLD, 40));
                        for(int i = 0 ; i < intento ; i++){
                            g.drawString( password[i],325+52 *i, 60);
                        }
                        g.setColor(Color.red);
                        g.drawString( teclaIntento ,325+52 *intento, 60);
                    }
                    if(pausa){
                        g.setColor(Color.red);
                        g.setFont(new Font("default", Font.BOLD, 40));
                        g.drawString("Pause",340, 70);
                        g.drawString("Press 'Q' to Quit",235, 120);
                    } else {
                        g.setFont(new Font("default", Font.BOLD, 40));
                        g.setColor(Color.white);
                        g.drawString("Mile " + cambio, 340, 120);
                    }
                    g.setColor(Color.red);
                    g.setFont(new Font("default", Font.BOLD, 30));
                    if(vidaJugador>0)
                        g.drawString(""+vidaJugador+"%", 30, 127);
                    else
                        g.drawString("0%", 30, 127);
                    if(matrix){
                        g.drawImage(bigZombie, 10, 20, this);
                        g.setColor(Color.red);
                        g.setFont(new Font("default", Font.BOLD, 60));
                        g.drawString("-"+(int) (matrixDamage/1000), 50, 127);
                        for (Mutante xh:crosshair) {
                            g.drawImage(xh.getImagenI(), xh.getPosX(), xh.getPosY(), this);
                        }
                        for (Mutante bul:bullets) {
                            g.drawImage(bul.getImagenI(), bul.getPosX(), bul.getPosY(), this);
                        }
                    }
                    break;

                /**
                 * En esta ventana tienen las instrucciones del juego
                 */
                case 3:
                    g.setColor(Color.white);
                    g.setFont(new Font("default", Font.BOLD, 20));
                    g.drawString("Agregar imagen con instrucciones",100,100);
                    g.drawString("Agregar boton para regresar a menu",100,150);
                    break;
                    
                /**
                 * En esta ventana estan las opciones de juego
                 */    
                case 4:
                    g.setColor(Color.white);
                    g.setFont(new Font("default", Font.BOLD, 20));
                    g.drawString("Agregar imagen con instrucciones",100,100);
                    g.drawString("Agregar boton para regresar a menu",100,150);
                    break;
                
                /**
                 * Aqui se tienen los creditos del juego
                 */
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

    /**
     * Este metodo itera por el arreglo de puntajes y los imprime en el
     * archivo nombreArchivoHighscores
     * @throws IOException 
     */
    public void grabaArchivoHighscores() throws IOException {
        PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivoHighscores));
        for (int i = 0; i < puntajes.size(); i++) {
            Puntaje x;
            x = (Puntaje) puntajes.get(i);
            fileOut.println(x.toString());
        }
        fileOut.close();
    }
    
    /**
     * Metodo utilizado para tomar los mejores 10 puntajes del juego.
     * @throws IOException 
     */
    public void cargarArchivoHighscores() throws IOException {
        BufferedReader fileIn;
        try {
            fileIn = new BufferedReader(new FileReader(nombreArchivoHighscores));
        } catch (FileNotFoundException e) {
            File nom = new File(nombreArchivoHighscores);
            PrintWriter fileOut = new PrintWriter(nom);
            //Crea 10 juegos ficticios para rellenar la lista
            fileOut.println("alguien,0\nalguien,0\nalguien,0\nalguien,0\nalguien,"
                    + "0\nalguien,0\nalguien,0\nalguien,0\nalguien,0\nalguien,0");
            fileOut.close();
            fileIn = new BufferedReader(new FileReader(nombreArchivoHighscores));
        }
        
        // Itera por los renglones del archivo para agregarlos al arreglo puntajes
        String dato = fileIn.readLine();
        while (dato != null) {
            arr = dato.split(",");
            String nombre = arr[0];
            int sc = (Integer.parseInt(arr[1]));
            puntajes.add(new Puntaje(nombre, sc));
            dato = fileIn.readLine();

        }
        fileIn.close();
    }
    
    /**
     * Metodo que escribe en el archivo nombreArchivoJugador el nombre del jugador actual.
     * @throws IOException 
     */
    public void grabaArchivoNombre() throws IOException {
        PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivoJugador));
        fileOut.println(nombreJugador);
        fileOut.close();
    }

    /**
     * Metodo que extrae del archivo nombreArchivoJugador el nombre del jugador actual
     * y lo asigna a la variable nombreJugador.
     * @throws IOException 
     */
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

    /**
     * Metodo que reinicia todas las variables del juego para que se pueda volver a jugar
     */
    private void reiniciarJuego() {
        // Reinicia los valores por default
        numInventory = 0;
        ventana = 1;
        vidaJugador = 100;
        cambio = 1;
        camionVx =0;
        camionVy = 0;
        velocidadCalle = 9;
        damageTempo = 50;
        counterCactus = 80;
        counterToolbox = 40;
        counterAcid = 0;
        damageZombie = 11;
        numCactusNivel = 50;
        numToolboxNivel = 40;
        numAcidNivel = 30;
        scoreJugador = 0;
        //Quita la pausa del juego
        pausa = false;
        // Limpia las listas de objetos del juego anterior
        mutantes.clear();
        mRojo.clear();
        mElectro.clear();
        restos.clear();
        cactus.clear(); 
        toolbox.clear();
        acid.clear();
        acidItem.clear();
        //Coloca de nuevo al camion en su posicion inicial
        camion.setPosX(getWidth()/2);
        camion.setPosY(getHeight()/2);
        //Crea las armas del jugador
        cactus.push(new Mutante(206-30, 410, imCactus, velocidadCalle, 1));
        cactus.push(new Mutante(594, 410, imCactus, velocidadCalle, 1));
    }
    

    
}
