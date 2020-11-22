package Buscaminas;

import java.awt.EventQueue;

/**
 * Clase principal del Buscaminas
 * 
 * @author Sara Corrales Santos
 * @version 1.11
 */
public class Principal {

	/**
	 * Método main
	 * 
	 * @param args : Cadenas de parámetros del main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal ventana = new VentanaPrincipal();
					ventana.inicializar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
