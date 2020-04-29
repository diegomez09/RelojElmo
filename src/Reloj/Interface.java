package Reloj;
import java.awt.Color;
import java.awt.Graphics;
//TODOS LOS METODOS PASADOS A UNA INTERFAZZZZ
public interface Interface {
    public void dibujo();
    public void Rectangle(int x0, int y0, int x1, int y1, Color c, Graphics g);    
    public void puntos();
    public void Fondo (Graphics g);     
    public void imagen();
    public void Linea (int x0, int y0, int x1, int y1, Color c, Graphics g);    
    public void time();
    public void start();                
    public void Circulo(int h, int k, int r, Color c, Graphics g);    
    public void Hora (Graphics g);    
    public void Conteo();
    public void putPixel(int x, int y, Color c, Graphics g);
}
