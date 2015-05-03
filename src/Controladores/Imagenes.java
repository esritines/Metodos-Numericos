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
                imagen = new ImageIcon("./src/Diseño/a1.png");
                break;
            case 2:
                imagen = new ImageIcon("./src/Diseño/a2.png");
                break;
            case 3:
                imagen = new ImageIcon("./src/Diseño/a3.png");
                break;
            case 4:
                imagen = new ImageIcon("./src/Diseño/a4.png");
                break;
            case 5:
                imagen = new ImageIcon("./src/Diseño/fondo.png");
                break;
        }
    }
}
