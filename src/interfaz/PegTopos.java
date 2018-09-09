/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;


import acciontecla.Topo;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static java.lang.Thread.sleep;
import java.util.Random;
import javax.swing.*;
/**
 *
 * @author USER
 */
public class PegTopos extends JPanel implements MouseMotionListener, MouseListener{
    
    public Image fondo = getToolkit().getImage(getClass().getResource("/imagenes/fondoTop.gif"));
    public Image mesa = getToolkit().getImage(getClass().getResource("/imagenes/mesaTop.gif"));
    public Image mazo = getToolkit().getImage(getClass().getResource("/imagenes/mazo.png"));
    public Image latDer = getToolkit().getImage(getClass().getResource("/imagenes/latDer.png"));
    public Image latIzq = getToolkit().getImage(getClass().getResource("/imagenes/latIzq.png"));
    public Image arr = getToolkit().getImage(getClass().getResource("/imagenes/latArr.png"));
    public Image aba = getToolkit().getImage(getClass().getResource("/imagenes/latAba.png"));
    public static int pocMazox,pocMazoy;public int fila, colu;
    public static int Matriz[][]={{0,0,0},{0,0,0},{0,0,0}};
    public static boolean MatrizBool[][] = {{true,true,true},{true,true,true},{true,true,true},};
    Random al = new Random();
    public static Topo vecTop[][] ={{null,null,null},{null,null,null},{null,null,null}};

    
    public PegTopos (){
        setSize(800,725);
        addMouseMotionListener(this);
        addMouseListener(this);
        setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        comienzaJuego();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(fondo, 0, 0,getWidth(),getHeight(), this);
        g.drawImage(mesa, 150, 75, this);
        g.drawImage(latIzq, 100, 25, this);
        g.drawImage(latDer, 650, 25, this);
        g.drawImage(arr, 100, 25, this);
        g.drawImage(aba, 100,675, this);
        
        for(int i = 0;i<3;i++){
           for(int j = 0;j<3;j++){
                if(MatrizBool[i][j] == false){
                    boolean v = g.drawImage(vecTop[i][j].img,vecTop[i][j].pocX , vecTop[i][j].pocY,null, this);
                    //System.out.println(v);
                }
            }//fin for j
        }//fin for i
        g.drawImage(mazo, pocMazox,pocMazoy, this);
    }

        public void comienzaJuego(){
        try {sleep(2000);} catch (InterruptedException ex) {}
     // control = new ControlTop();
        inicia();
    }

    public void inicia(){
        System.out.println("Inicioooooooooooooooooooooooooooooooo");
        new Controladora();
    }//fin met inicia

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX()-150,y = e.getY()-75;
        if((x>=0)&& (x<=166) && (y>=0)&&(y<=200)){
            System.out.println("Se presiono el primer cuadro");
        }
        if((x>=167)&& (x<=333) && (y>=0)&&(y<=200)){
            System.out.println("Se presiono el segundo cuadro");
        }
        if((x>=333)&& (x<=500) && (y>=0)&&(y<=200)){
            System.out.println("Se presiono el tercer cuadro");
        }
        if((x>=0)&& (x<=166) && (y>=201)&&(y<=400)){
            System.out.println("Se presiono el cuarto cuadro");
        }
        if((x>=166)&& (x<=333) && (y>=201)&&(y<=400)){
            System.out.println("Se presiono el quinto cuadro");
        }
        if((x>=333)&& (x<=500) && (y>=201)&&(y<=400)){
            System.out.println("Se presiono el sexto cuadro");
        }
        if((x>=0)&& (x<=166) && (y>=401)&&(y<=600)){
            System.out.println("Se presiono el septimo cuadro");
        }
        if((x>=166)&& (x<=333) && (y>=401)&&(y<=600)){
            System.out.println("Se presiono el octavo cuadro");
        }
        if((x>=333)&& (x<=500) && (y>=401)&&(y<=600)){
            System.out.println("Se presiono el noveno cuadro");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
        private class Controladora extends Thread{

        public Controladora() {
            start();
        }
        @Override
        public void run(){
            
        
            for(int i = 0 ; i < 30 ; i++){
                try{sleep(1200); }catch(InterruptedException e){}
                fila = pocAleatoria();colu = pocAleatoria();System.out.println(fila+" "+colu);
                if(MatrizBool[fila][colu] == true){//para saber si la poc esta disponible
                    int nuImg =tipImg();//obtenemos el tipo de topo
                    //añadimos un nuevo topo al vector de topos
                    vecTop[fila][colu] = new Topo(coordX(colu), coordY(fila), true, daImgSub(nuImg),daImgBaj(nuImg), fila, colu);
                    vecTop[fila][colu].setImg(vecTop[fila][colu].imgSub);
                    MatrizBool[fila][colu] = false;//cambiamos el estado en la matriz bool de topos
                    new auxilio(fila, colu);
                }//fin del if Disponible
            }//fin for
            
        }
        private class auxilio extends Thread{
            private int f, c;
            public auxilio(int fi,int co){
                this.c =co;this.f =fi;
                this.start();
            }
            
            public void run(){
                try{sleep(3000); }catch(InterruptedException e){}//esperamos 3 segundos para bajarlo
                    vecTop[f][c].setImg(vecTop[f][c].imgBaj);
                    vecTop[f][c].estado =false;
                    try{sleep(1250); }catch(InterruptedException e){}//esperamos 3 segundos para bajarlo                    
                    MatrizBool[f][c] = true;
            
            }
        }
    }
    


    public int pocAleatoria(){
        return al.nextInt(3);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        pocMazox = e.getX()-80;pocMazoy = e.getY()-90;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        pocMazox = e.getX()-80;pocMazoy = e.getY()-90;
        repaint();
    }
    
    public int coordX( int col){
        int ret=0;
        if(col == 0){ret = 150;}
        if(col == 1){ret = 317;}
        if(col == 2){ret = 484;}
        return ret;
    }
    public int coordY( int fil){
        int ret=0;
        if(fil == 0){ret = 75;}
        if(fil == 1){ret = 275;}
        if(fil == 2){ret = 475;}
        return ret;
    }
    public int tipImg(){
        return al.nextInt(5);
    }
    public Image daImgSub(int tip){
        Image ret = null;
        if(tip == 0){ret = getToolkit().getImage(getClass().getResource("/imagenes/tops/AZUL/TopAxulSubida.gif"));}
        if(tip == 1){ret = getToolkit().getImage(getClass().getResource("/imagenes/tops/CAFE/TopCafeSubida.gif"));}
        if(tip == 2){ret = getToolkit().getImage(getClass().getResource("/imagenes/tops/NEGRO/TopNegroSubida.gif"));}
        if(tip == 3){ret = getToolkit().getImage(getClass().getResource("/imagenes/tops/ROJO/TopRojoSubida.gif"));}
        if(tip == 4){ret = getToolkit().getImage(getClass().getResource("/imagenes/tops/VERDE/TopVerdeSubida.gif"));}
        
        return ret;
    }
    public Image daImgBaj(int tip){
        Image ret = null;
        if(tip == 0){ret = getToolkit().getImage(getClass().getResource("/imagenes/tops/AZUL/TopAxulBajada.gif"));}
        if(tip == 1){ret = getToolkit().getImage(getClass().getResource("/imagenes/tops/CAFE/TopCafeBajada.gif"));}
        if(tip == 2){ret = getToolkit().getImage(getClass().getResource("/imagenes/tops/NEGRO/TopNegroBajada.gif"));}
        if(tip == 3){ret = getToolkit().getImage(getClass().getResource("/imagenes/tops/ROJO/TopRojoBajada.gif"));}
        if(tip == 4){ret = getToolkit().getImage(getClass().getResource("/imagenes/tops/VERDE/TopVerdeBajada.gif"));}
        
        return ret;
    }
    
    
}//fin clase
