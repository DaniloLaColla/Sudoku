package Logica;

import javax.swing.ImageIcon;

public class EntidadGraficaReloj {

	private ImageIcon grafico;
	private String[] imagenes;
	
	private int valor;
	
	public EntidadGraficaReloj() {      //arreglo que contiene las direcciones de las imagenes utilizadas en reloj del juego
		this.grafico = new ImageIcon();
		this.imagenes = new String[]{"/ImagenesCronometro/0.png", "/ImagenesCronometro/1.png",
				"/ImagenesCronometro/2.png", "/ImagenesCronometro/3.png","/ImagenesCronometro/4.png",
				"/ImagenesCronometro/5.png", "/ImagenesCronometro/6.png", "/ImagenesCronometro/7.png",
				"/ImagenesCronometro/8.png", "/ImagenesCronometro/9.png",};
	}
	
	public void actualizar(int indice) {    //actualizamos numeros
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
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
}
