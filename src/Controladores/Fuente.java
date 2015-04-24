package Controladores;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Fuente {

    private Font fuente;

    public Font getFont() {
        return fuente;
    }

    public Fuente() {
        try {
            fuente = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/Abraham/Documents/NetBeansProjects/Puzzle/src/Diseño/Games.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/Abraham/Documents/NetBeansProjects/Puzzle/src/Diseño/Games.ttf")));
        } catch (IOException | FontFormatException e) {
        }

    }
}
