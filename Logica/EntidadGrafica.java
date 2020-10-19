package Logica;

import javax.swing.ImageIcon;

public class EntidadGrafica {

	private ImageIcon grafico;
	private String[] imagenes;
	
	public EntidadGrafica() {
		this.grafico = new ImageIcon();
		this.imagenes = new String[]{"/Imagenes/amarillo.png", "/Imagenes/azul.png",
				 "/Imagenes/celeste.png", "/Imagenes/marron.png","/Imagenes/naranja.png",
				 "/Imagenes/rojo.png", "/Imagenes/rosa.png", "/Imagenes/verde.png",
				"/Imagenes/violeta.png"};
	}
	
	public void actualizar(int indice) {
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
	
	/*
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
	*/
}
