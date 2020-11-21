import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Clase que se encarga de generar el sonido al finalizar el juego por la explosión de una mina.
 * 
 * @author Sara Corrales Santos
 * @version 1.0
 * @since 1.0
 */

public class Explosion {

    /** 
     * Método que crea e inicia un hilo que reproduce el soluso de una explosión
     * @throws JavaLayerException
     * @throws InterruptedException
     * @throws FileNotFoundException
     */
    public static void reproducirExplosion() throws JavaLayerException, InterruptedException, FileNotFoundException {
        final Player explosion = new Player(new FileInputStream("src\\explosion.mp3"));

        new Thread() {
            public void run() {
                try {
                    while (true) {
                        if (!explosion.play(1)) {
                            break;
                        }
                    }
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
