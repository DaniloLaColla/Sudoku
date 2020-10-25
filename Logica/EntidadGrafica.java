package Logica;

import javax.swing.ImageIcon;

public class EntidadGrafica {

	private ImageIcon grafico;
	private String[] imagenes;
	
	public EntidadGrafica() {      //arreglo que contiene las direcciones de las imagenes utilizadas en la Gui del juego
		this.grafico = new ImageIcon();
		this.imagenes = new String[]{"/ImagenesJuego/amarillo.png", "/ImagenesJuego/azul.png",
				 "/ImagenesJuego/celeste.png", "/ImagenesJuego/marron.png","/ImagenesJuego/naranja.png",
				 "/ImagenesJuego/rojo.png", "/ImagenesJuego/rosa.png", "/ImagenesJuego/verde.png",
				"/ImagenesJuego/violeta.png"};
	}
	
	public void actualizar(int indice) {    //actualizamos imagen
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	
	public String[] getImagenes() {
		return this.imagenes;
	}
	
}
