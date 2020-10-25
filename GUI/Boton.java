package GUI;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Boton extends JButton{

	private int fila, columna;
	
	public Boton(int f, int c) {  //Constructor de Boton con fiilas y columnas
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
