
package Reloj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

public class Functions extends JFrame implements Runnable, Interface{
    private Image temporal = null;
    private BufferedImage buffer;
    private Graphics gr = null;
    private int hora, minutos, segundos;
    Calendar calendario = new GregorianCalendar();
    Thread hilo;
    
    public Functions(){
        super("Reloj DFGG 17310120");
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        if(hora>=12){
            hora-=12;
        }
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);        
        hora *= 30;
        minutos *= 6;
        segundos *= 6;        
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        this.setLayout(null);
        this.setBounds(1430, 100, 500, 500);
        this.setBackground(Color.RED);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        start();
    }
        
    @Override
    public void start(){
        if(hilo==null){
           hilo=new Thread(this);
           hilo.start();
        }
     }
        
    @Override
    public void Fondo (Graphics g) {
        Color c;
        c = Color.BLACK;
         Circulo(250, 250, 145, c, g);
        c = Color.RED;
        Circulo(250, 250, 143, c, g);
        c = Color.BLACK;
        Circulo(200, 110, 51, c, g);
        Circulo(300, 110, 51, c, g);        
        c = Color.WHITE;
        Circulo(200, 110, 50, c, g);
        Circulo(300, 110, 50, c, g);
        c = Color.BLACK;
        Circulo(200, 130, 30, c, g);
        Circulo(300, 130, 30, c, g);
        Linea(150, 300, 350, 300, c, g);
        c = Color.ORANGE;
        Circulo(250, 250, 40, c, g);        
        c = Color.BLACK;
        //3
        Linea(400,230, 410, 230, c, g);
        Linea(410, 230, 410, 240, c, g);
        Linea(410, 240, 405, 240, c, g);
        Linea(410, 240, 410, 250, c, g);
        Linea(400, 250, 410, 250, c, g);
        //6
        Circulo(250, 415, 10, c, g);
        Linea(240, 415, 247, 397, c, g);
        c = Color.WHITE;
        Circulo(250, 415, 9, c, g);
        //9
        c = Color.BLACK;
        Circulo(90, 240, 10, c, g);
        Linea(99, 240, 99, 260, c, g);
        c = Color.WHITE;
        Circulo(90, 240, 9, c, g);        
        Hora(temporal.getGraphics());
        
    }   
    
    @Override
    public void Rectangle(int x0, int y0, int x1, int y1, Color c, Graphics g){
        x1 = x1 + x0;
        y1 = y1 + y0;
        for(int i = x0; i < x1; i++){
            for(int j = y0; j < y1; j++){
                putPixel(i,j,c,g);
            }
        }
    }
    
    @Override
    public void Circulo(int h, int k, int r, Color c, Graphics g){
        
        for(int i = 0; i < r; i++){
            int x=0;
            int y=i;
            int p=(1-i);
            do{
                putPixel((h+x),(k+y),c, g);
                putPixel((h+y),(k+x),c, g);
                putPixel((h+y),(k-x),c, g);
                putPixel((h+x),(k-y),c, g);
                putPixel((h-x),(k-y),c, g);
                putPixel((h-y),(k-x),c, g);
                putPixel((h-y),(k+x),c, g);
                putPixel((h-x),(k+y),c, g);
                x++;
                if(p<0)
                    p+=((2*x)+1);
                else{
                    y--;
                    p+=((2*(x-y))+1);
                }
            }
            while(x<=y);
        }
    }
    
    @Override
    public void Linea (int x0, int y0, int x1, int y1, Color c, Graphics g){        
        int dx = x1 - x0;
        int dy = y1 - y0;
        
        putPixel(x0, y0, c, gr);
        
        if(Math.abs(dx) > Math.abs(dy)){ 
            float m = (float) dy / (float) dx;            
            float b = y0 - m*x0;
            if(dx < 0)
                dx = -1;
            else
                dx = 1;
            while(x0 != x1){
                x0 += dx;
                y0 = Math.round(m*x0 + b);
                putPixel(x0, y0, c, gr);
            }
        }
        else{
            if(dy != 0){
                float m = (float) dx / (float) dy;                
                float b = x0 - m*y0;
                if(dy < 0)
                    dy = -1;
                else
                    dy = 1;
                while(y0 != y1){
                    y0 += dy;
                    x0 = Math.round(m*y0 + b);
                    putPixel(x0, y0, c,gr);
                }
            }
        }
    }
    
    @Override
    public void Hora (Graphics g){        
        // Radio de las Lineas
        int hrs = 50, min = 80, seg = 100; 
        int x = 250, y = 250;
        double anguloH = 90 - this.hora;
        double anguloM = 90 - this.minutos;
        double anguloS = 90 - this.segundos;        
        int coH=0, caH=0, coM=0, caM=0, coS=0, caS=0;
        coH =(int) -(hrs*Math.sin(anguloH * Math.PI/180));
        caH =(int) (hrs*Math.cos(anguloH * Math.PI/180));
        coM =(int) -(min*Math.sin(anguloM * Math.PI/180));
        caM =(int) (min*Math.cos(anguloM * Math.PI/180));
        coS =(int) -(seg*Math.sin(anguloS * Math.PI/180));
        caS =(int) (seg*Math.cos(anguloS * Math.PI/180));        
        Linea (x, y, x+caH, y+coH, Color.BLACK, g);//Horas
        Linea (x, y, x+caM, y+coM, Color.BLACK, g);//Minutos
        Linea (x, y, x+caS, y+coS, Color.BLACK, g);//Segundos
        
    }
    
    @Override
    public void Conteo(){
        if(segundos==360){
            segundos = 0;
            minutos += 6;
            if(minutos == 360){
                minutos = 0;
                hora += 30;
                if(hora == 360){
                    hora = 0;
                }
            }
        }
        update(this.getGraphics());
    }

    @Override
    public void putPixel(int x, int y, Color c, Graphics g){
        buffer.setRGB(0,0,c.getRGB());
        g.drawImage(buffer, x, y, this);
    }
    
    @Override
    public void paint(Graphics g){
        
        final Dimension d = getSize();
        
        if (temporal == null) {                          
            temporal = createImage(d.width, d.height);   
        } 
        
        gr = temporal.getGraphics();      
        gr.setColor(Color.white);
        gr.fillRect(0, 0, d.width, d.height) ;
        
        Fondo(temporal.getGraphics());
        g.drawImage(temporal, 0, 0, null);                
    }

    @Override
    public void update (Graphics g) {
        paint(g);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                this.segundos+=6;
                Conteo();                
            } catch (Exception e) {
                System.out.println("Valió verga la pata del mameitor");
            }
        }
    }

    @Override
    public void dibujo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void puntos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imagen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void time() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
