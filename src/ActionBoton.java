import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private int iV;
	private int jV;

	public ActionBoton(VentanaPrincipal ventanaPrincipal, int i, int j) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.iV = i;
		this.jV = j;
	}

	/**
	 * Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (ventanaPrincipal.getJuego().abrirCasilla(iV, jV)) {
			ventanaPrincipal.mostrarNumMinasAlrededor(iV, jV);
			ventanaPrincipal.actualizarPuntuacion();
			if (ventanaPrincipal.getJuego().esFinJuego()) {
				ventanaPrincipal.mostrarFinJuego(!ventanaPrincipal.getJuego().esFinJuego());
			}
		} else {
			ventanaPrincipal.mostrarFinJuego(!ventanaPrincipal.getJuego().esFinJuego());

			for (int i = 0; i < ventanaPrincipal.panelesJuego.length; i++) {
				for (int j = 0; j < ventanaPrincipal.panelesJuego[i].length; j++) {
					if (!ventanaPrincipal.getJuego().abrirCasilla(i, j)) {
						ventanaPrincipal.esMina(i, j);
					}
				}
			}
		}

	}

}
