package Logica;

public class NumerosReloj {


private EntidadGraficaReloj izquierda, derecha;
	
	public NumerosReloj(EntidadGraficaReloj izquierda, EntidadGraficaReloj derecha) {
		this.izquierda = izquierda;
		this.derecha = derecha;
	}
	
	public EntidadGraficaReloj getIzq() {
		return izquierda;
	}
	
	public EntidadGraficaReloj getDer() {
		return derecha;
	}
	
}
