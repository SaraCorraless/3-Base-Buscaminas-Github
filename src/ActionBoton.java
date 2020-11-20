import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;

import javazoom.jl.decoder.JavaLayerException;

/**
 * Clase que implementa el listener de los botones del Buscaminas. De alguna
 * manera tendrá que poder acceder a la ventana principal. Se puede lograr
 * pasando en el constructor la referencia a la ventana. Recuerda que desde la
 * ventana, se puede acceder a la variable de tipo ControlJuego
 * 
 * @author jesusredondogarcia
 **
 */
public class ActionBoton implements ActionListener {

	private VentanaPrincipal ventanaPrincipal;
	private int i;
	private int j;

	public ActionBoton(VentanaPrincipal ventanaPrincipal, int i, int j) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.i = i;
		this.j = j;
	}

	/**
	 * Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: seguir con el comportamiento de los botones.
		if (ventanaPrincipal.getJuego().abrirCasilla(i, j)) {
			/*
			 * if (ventanaPrincipal.getJuego().esFinJuego()) {
			 * ventanaPrincipal.mostrarFinJuego(ventanaPrincipal.getJuego().esFinJuego()); }
			 * else {
			 * ventanaPrincipal.mostrarFinJuego(ventanaPrincipal.getJuego().esFinJuego()); }
			 */
			ventanaPrincipal.mostrarNumMinasAlrededor(i, j);
			ventanaPrincipal.actualizarPuntuacion();
			if (ventanaPrincipal.getJuego().esFinJuego()) {
				ventanaPrincipal.mostrarFinJuego(!ventanaPrincipal.getJuego().esFinJuego());
			}
		} else {
			ventanaPrincipal.esMina(i, j);
			ventanaPrincipal.mostrarFinJuego(!ventanaPrincipal.getJuego().esFinJuego());
			
				ventanaPrincipal.sonido();
			
		}

	}

}
