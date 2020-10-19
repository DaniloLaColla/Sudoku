package GUI;

import javax.swing.JButton;

public class Boton extends JButton{

	private int fila, columna;
	
	public Boton(int f, int c) {
		fila=f;
		columna=c;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}
}
