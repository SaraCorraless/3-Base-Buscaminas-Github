
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javazoom.jl.decoder.JavaLayerException;

/**
 * Ventana principal del Buscaminas
 * 
 * @author Sara Corrales Santos
 */
public class VentanaPrincipal {

	// La ventana principal, en este caso, guarda todos los componentes:
	JFrame ventana;
	JPanel panelImagen;
	JPanel panelEmpezar;
	JPanel panelPuntuacion;
	JPanel panelJuego;

	// Todos los botones se meten en un panel independiente.
	// Hacemos esto para que podamos cambiar después los componentes por otros
	JPanel[][] panelesJuego;
	JButton[][] botonesJuego;

	// Correspondencia de colores para las minas:
	Color correspondenciaColores[] = { Color.BLACK, Color.CYAN, Color.GREEN, Color.ORANGE, Color.RED, Color.RED,
			Color.RED, Color.RED, Color.RED, Color.RED };

	JButton botonEmpezar;
	JTextField pantallaPuntuacion;

	// LA VENTANA GUARDA UN CONTROL DE JUEGO:
	ControlJuego juego;

	// Constructor, marca el tamaño y el cierre del frame
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 700, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new ControlJuego();
	}

	// Inicializa todos los componentes del frame
	public void inicializarComponentes() {

		// Definimos el layout:
		ventana.setLayout(new GridBagLayout());

		// Inicializamos componentes
		panelImagen = new JPanel();
		panelEmpezar = new JPanel();
		panelEmpezar.setBackground(new Color(155, 89, 182));
		panelEmpezar.setLayout(new GridLayout(1, 1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1, 1));
		panelJuego = new JPanel();
		panelJuego.setBackground(new Color(155, 89, 182));
		panelJuego.setLayout(new GridLayout(10, 10));

		botonEmpezar = new JButton("Go!");
		pantallaPuntuacion = new JTextField("0");
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);

		// Bordes y colores:
		panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));

		// Colocamos los componentes:
		// AZUL
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelImagen, settings);
		// VERDE
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);
		// AMARILLO
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);
		// ROJO
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);

		// Paneles
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1, 1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}

		// Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}

		// BotónEmpezar:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);

	}

	/**
	 * Método que inicializa todos los lísteners que necesita inicialmente el
	 * programa
	 */
	public void inicializarListeners() {
		// Se recorren todos los botones del tablero
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				ActionBoton actionBoton = new ActionBoton(this, i, j);
				// Se inician los listener de los botones del tablero
				botonesJuego[i][j].addActionListener((e) -> {
					actionBoton.actionPerformed(e);
				});

			}
		}
		// Se inicia el listener del botón "GO"
		botonEmpezar.addActionListener((e) -> {
			panelJuego.removeAll();
			panelJuego.revalidate();
			panelJuego.repaint();
			// Se recorren los botones para iniciar una nueva partida
			for (int i = 0; i < botonesJuego.length; i++) {
				for (int j = 0; j < botonesJuego[i].length; j++) {
					panelesJuego[i][j] = new JPanel();
					panelesJuego[i][j].setLayout(new GridLayout(1, 1));
					panelJuego.add(panelesJuego[i][j]);

					botonesJuego[i][j] = new JButton("-");
					panelesJuego[i][j].add(botonesJuego[i][j]);
					ActionBoton actionBoton = new ActionBoton(this, i, j);
					botonesJuego[i][j].addActionListener((l) -> {
						actionBoton.actionPerformed(l);
					});
				}
			}
			getJuego().inicializarPartida();
			actualizarPuntuacion();
			refrescarPantalla();
			getJuego().depurarTablero();

		});

	}

	/**
	 * Pinta en la pantalla el número de minas que hay alrededor de la celda Saca el
	 * botón que haya en la celda determinada y añade un JLabel centrado y no
	 * editable con el número de minas alrededor. Se pinta el color del texto según
	 * la siguiente correspondecia (consultar la variable correspondeciaColor): - 0
	 * : negro - 1 : cyan - 2 : verde - 3 : naranja - 4 ó más : rojo
	 * 
	 * @param i: posición vertical de la celda.
	 * @param j: posición horizontal de la celda.
	 */
	public void mostrarNumMinasAlrededor(int i, int j) {
		// seleccionar el Jpanel[i][j] correspondiente
		// elminar todos los componentes
		panelesJuego[i][j].remove(botonesJuego[i][j]);

		// Añadimos un Jlabel centrado y no editable con el numero de minas alrdedor

		JLabel JLnMinas = new JLabel();
		JLnMinas.setText("" + juego.getMinasAlrededor(i, j));
		JLnMinas.setHorizontalAlignment(SwingConstants.CENTER);
		// el numero de minas se saca de controJuego (getMinasAlrededor)

		for (int k = 0; k < correspondenciaColores.length; k++) {
			if (k == juego.getMinasAlrededor(i, j)) {
				JLnMinas.setForeground(correspondenciaColores[k]);
			}
		}
		panelesJuego[i][j].add(JLnMinas);
		refrescarPantalla();
	}

	/**
	 * Muestra una ventana que indica el fin del juego
	 * 
	 * @param porExplosion : Un booleano que indica si es final del juego porque ha
	 *                     explotado una mina (true) o bien porque hemos desactivado
	 *                     todas (false)
	 * @post : Todos los botones se desactivan excepto el de volver a iniciar el
	 *       juego.
	 */
	public void mostrarFinJuego(boolean porExplosion) {
		if (porExplosion) {
			try {
				// Inicia un hilo con el solino de la explosión de la bomba
				sonido();
			} catch (FileNotFoundException | JavaLayerException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(ventana, "Ha explotado una mina, fin del juego.");
		} else {
			JOptionPane.showMessageDialog(ventana, "Has conseguido evitar las minas, fin del juego.");
		}

		// se recorren los botones del tablero y se desactivan
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j].setEnabled(false);
				refrescarPantalla();
			}
		}
	}

	/**
	 * Método que muestra la puntuación por pantalla.
	 */
	public void actualizarPuntuacion() {
		pantallaPuntuacion.setText(Integer.toString(getJuego().getPuntuacion()));

	}

	/**
	 * Método para refrescar la pantalla
	 */
	public void refrescarPantalla() {
		ventana.revalidate();
		ventana.repaint();
	}

	/**
	 * Método que devuelve el control del juego de una ventana
	 * 
	 * @return un ControlJuego con el control del juego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * Método que cambia el botón que estuviera el una posición por un JLabel de la
	 * mina con la imagen de una mina, un borde negro y el fondo rojo y que lo añade
	 * al panel donde estuviera el botón que se ha borrado.
	 * 
	 * @param i
	 * @param j
	 */
	public void esMina(int i, int j) {
		panelesJuego[i][j].remove(botonesJuego[i][j]);
		ImageIcon mina = new ImageIcon(getClass().getResource("mine.png"));
		JLabel iconoMina = new JLabel();
		iconoMina.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panelesJuego[i][j].setBackground(Color.RED);
		iconoMina.setHorizontalAlignment(SwingConstants.CENTER);
		iconoMina.setIcon(mina);
		panelesJuego[i][j].add(iconoMina);
		refrescarPantalla();
	}

	/**
	 * Método que inicia el hilo de la clase Explosion que reproduce el solido de la explosión
	 * @throws FileNotFoundException
	 * @throws JavaLayerException
	 */
	public void sonido() throws FileNotFoundException, JavaLayerException {
		try {
			Explosion.reproducirExplosion();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para inicializar el programa
	 */
	public void inicializar() {
		// IMPORTANTE, PRIMERO HACEMOS LA VENTANA VISIBLE Y LUEGO INICIALIZAMOS LOS
		// COMPONENTES.
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();
	}
}
