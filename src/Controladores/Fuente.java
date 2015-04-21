package Controladores;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class Fuente {

    private InputStream input;
    private Font font;
    private Font sizedFont;

    public Font getFont() {
        return font;
    }
    
    public Fuente(){
        try {
            input = Fuente.class.getResourceAsStream
            ("C:/Users/Abraham/Documents/NetBeansProjects/Puzzle/src/Diseño/Games.TTF");
            font = Font.createFont(Font.TRUETYPE_FONT, input);
            
        } catch (FontFormatException ex) {
            System.out.println("No se cargo la Fuente");
        } catch (IOException ex){           
        }
    }
}
