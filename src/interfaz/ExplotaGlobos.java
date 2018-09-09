/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import acciontecla.Bomba;
import acciontecla.Control;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import static java.lang.Thread.sleep;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author USER
 */
public class ExplotaGlobos extends JPanel{
    Image fondo = getToolkit().getImage(getClass().getResource("/imagenes/fondoGlobos.png"));
    Image listo = getToolkit().getImage(getClass().getResource("/imagenes/LISTO.png"));
    Image ya =null;
    JLabel puntuacion,vida1,vida2,vida3;
    JTextField Letra;
    JButton menu,pause;
    ImageIcon siVida = new javax.swing.ImageIcon(getClass().getResource("/imagenes/vida.png"));
    ImageIcon noVida = new javax.swing.ImageIcon(getClass().getResource("/imagenes/vidaNo.png"));
    public static int clx=600,cly=250,cyx =300,cyy=325;
    public static int ayx=30,ayy=30;
    static boolean banderaInicio =true,banderaTirar=false,Pause = false;
    public static char ing;
    private static String nombre;
    eventos manejador = new eventos();
    Control bombas;
    public static Vector<Bomba> vector = new Vector(50,20);
    static int esp=5000,contPunt =0,contVidas=3;
    
    public ExplotaGlobos() {
        setLayout(null);
        setSize(600,550);
        colocarComp();
        Letra.addKeyListener(manejador);        
    }
    public void colocarComp(){
        Letra = new JTextField("-",20);Letra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        puntuacion = new JLabel(""+contPunt);puntuacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vida1= new JLabel("", siVida ,javax.swing.SwingConstants.CENTER );
        vida2= new JLabel("", siVida ,javax.swing.SwingConstants.CENTER );
        vida3= new JLabel("", siVida ,javax.swing.SwingConstants.CENTER );
        menu = new JButton("Volver");
        pause = new JButton("Pausar");
        //añadimos los componentes
        this.add(Letra);Letra.setBounds(290,432,40,30);Letra.setFont(new java.awt.Font("Tekton Pro", 2, 18));
        add(puntuacion);puntuacion.setBounds(500,430,50,30);puntuacion.setFont(new java.awt.Font("Tekton Pro", 2, 18));
        add(vida1);vida1.setBounds(50,420,40,40);
        add(vida2);vida2.setBounds(90,420,40,40);
        add(vida3);vida3.setBounds(130,420,40,40);
        add(menu);menu.setBounds(100,470,100,50);
        
        add(pause);pause.setBounds(400,470,100,50);
        //eventossssssssssssss
        pause.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //int res = JOptionPane.showConfirmDialog(, "Continuar" , "P-A-U-S-E", JOptionPane.INFORMATION_MESSAGE);
                bombas.pause();
                try {sleep(esp);} catch (InterruptedException ex) {}
                
            }
        });
        
    }//fin coloca componentes
    static int help=0,help2 =0;
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle cuadro = new Rectangle(0, 0, 600, 400);
        Point2D p1 = new Point2D.Double(0, 0);
        Point2D p2 = new Point2D.Double(600, 400);
        g2.setPaint(new GradientPaint(p1, Color.white, p2, Color.blue));
        g2.fill(cuadro);g2.draw(cuadro);        
        
        g.drawRect(280, 425, 60, 40);
        g.drawImage(fondo, 100, 0,400,400, this);
        
        
        if(contVidas==0){//game over
            vida2.setIcon(noVida);vida3.setIcon(noVida);vida1.setIcon(noVida);
            if(help2==0){
                bombas.gameOver();help2++;
            }
        }
        if(contVidas==1){vida2.setIcon(noVida);vida3.setIcon(noVida);}
        if(contVidas==2){vida3.setIcon(noVida);}
        
        if(banderaInicio==true){
        if(help==0){new comienzo();help++;}
        g.drawImage(listo, clx, cly, this);
        g.drawImage(ya, cyx, cyy,ayx,ayy, this);       
        }
        boolean v;
        for(int i=0;i<vector.size();i++){
            if((vector.get(i).estado==true)||(vector.get(i).exploto == true)){
                v=g.drawImage(vector.get(i).img,vector.get(i).pocX ,vector.get(i).pocY, this);
              //  System.out.println(" se pinto el "+i+" "+ v);
                //System.out.println("coordenada x "+vector.get(i).pocX);
            }//fin if
        }//for pintabombas
        
    }//paint

    private class bajarGlobos extends Thread{
        private int ind;
        private static final int vel=70;
        public bajarGlobos(int ind) {
            this.ind = ind;
            this.start();
        }
        @Override
        public void run(){
            for(int i = 0;i<80;i++){
                if((vector.get(ind).estado==true) && Pause == false){
                try {sleep(vel);} catch (InterruptedException ex) {}
                vector.get(ind).pocY+=5;
                if((i==79) && (vector.get(ind).estado =true)){
                    contVidas-=1;
                    vector.get(ind).estado =false;vector.get(ind).exploto =false;
                }
                }else{
                    break;
                }
            }//fin for
        }//fin run 
        public void quita_ind(){
            this.ind-=1;
        }
    }
    
    
    public  bajarGlobos obj;
    public void comienzaJuego(){
        char atrap;
        bombas = new Control();
        int cont;
        bombas.music();
        while(banderaTirar==false){
            try {Thread.sleep(1500);} catch (InterruptedException ex) {}
            atrap=bombas.obtenerLetra();
            vector.add(new Bomba(true,bombas.pocXaleatoria(),0,atrap));
            repaint();cont = vector.size();
            obj = new bajarGlobos(cont-1);
            //System.out.println("bomba "+atrap+ " talla : "+vector.size());
        }
        
    }
    private class eventos implements KeyListener{

    
    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String let =""+e.getKeyChar();
        ing =e.getKeyChar();
        Letra.setText(let+" ");
        
        for(int i =0;i<vector.size();i++){
            if((vector.get(i).letra ==ing)&&(vector.get(i).estado == true)){
                //vector.get(i).estado =false;
                contPunt+=5;puntuacion.setText(""+contPunt);
                bombas.explotar(i);
            //AudioClip sonido;
            //sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Sonidos/bomba1.wav"));
            //sonido.play();
                //obj.quita_ind();
                //vector.remove(vector.get(i));
                repaint();
            }//fin iffffff
        }//fin for 
    }//fin evento

    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    }//fin clase evetos
    private class comienzo extends Thread{

        public comienzo() {
            this.start();
        }
        @Override
        public void run(){
           banderaInicio =true;
try{ 
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();sleep(35); clx=clx-9; repaint();
    
    
    
}catch(InterruptedException e){}
           
           //yaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
           ya = getToolkit().getImage(getClass().getResource("/imagenes/YA.png"));
           
try{

    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();sleep(35); cyx=cyx-6;cyy-=13; ayx+=10;ayy+=10; repaint();
    
}catch(InterruptedException e){}
           
           banderaInicio =false;
           comienzaJuego();
        }//fin run
    }//fin class comienzo tread

}//fin de la class