
package Controladores;

import sonidos.AePlayWave;

public class ReproducirSonido {
    private AePlayWave sonido;

    public AePlayWave getSonido() {
        return sonido;
    }

    public ReproducirSonido(int n){
        switch(n){
            case 1:
                sonido = new AePlayWave("./src/Diseño/clic1.wav");
                break;
            case 2:
                sonido = new AePlayWave("./src/Diseño/clic2.wav");
                break;       
        }
    }
}
