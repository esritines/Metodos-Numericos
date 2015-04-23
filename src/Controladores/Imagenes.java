package Controladores;

import javax.swing.ImageIcon;

public class Imagenes {

    private ImageIcon imagen;

    public ImageIcon getImagen() {
        return imagen;
    }

    public Imagenes(int n) {
        switch (n) {
            case 1:
                imagen = new ImageIcon("C:/Users/Abraham/Documents/NetBeansProjects/Puzzle/src/Diseño/a1.png");
                break;
            case 2:
                imagen = new ImageIcon("C:/Users/Abraham/Documents/NetBeansProjects/puzzle/src/Diseño/a2.png");
                break;
            case 3:
                imagen = new ImageIcon("C:/Users/Abraham/Documents/NetBeansProjects/puzzle/src/Diseño/a3.png");
                break;
            case 4:
                imagen = new ImageIcon("C:/Users/Abraham/Documents/NetBeansProjects/puzzle/src/Diseño/a4.png");
                break;
        }
    }
}
