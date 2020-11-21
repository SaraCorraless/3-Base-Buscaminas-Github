import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Explosion {

    static boolean pausa = false;

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
