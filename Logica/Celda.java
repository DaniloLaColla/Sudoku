package Logica;

public class Celda {

	private Integer valor;
	private EntidadGrafica entidadGrafica;
	private int fila, columna, cuadrante;
	

	public Celda() {  //constructor Celda
		
		this.valor = null;
		this.entidadGrafica = new EntidadGrafica();
	}
	
	public void actualizar() {    //actualiza entidad grafica de la Celda
		if (this.valor != null && this.valor + 1 < this.getCantElementos()) {
			this.valor++;
		}else {
			this.valor = 0;
		}
		entidadGrafica.actualizar(this.valor);
	}
	
	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getCantElementos() {
		return this.entidadGrafica.getImagenes().length;
	}
	
	
	public Integer getValor() {
		return this.valor;
	}
	
	public int getCuadrante() {
		return cuadrante;
	}
	
	public void setCuadrante(int fila, int columna) {  // Segun fila y columna reconoce en que cuadrante esta
		if(fila<3) {
			if(columna<3) {
				cuadrante=0;
			}
			else { 
				if(columna<6) {
					cuadrante=1;
				}
				else{
					cuadrante=2;
				}
			}
		}else {
			if(fila<6) {
				if(columna<3) {
					cuadrante=3;
				}
				else { 
					if(columna<6) {
						cuadrante=4;
					}
					else{
						cuadrante=5;
					}
				}
			}
			
			else {
				if(fila<9) {
					if(columna<3) {
						cuadrante=6;
					}
					else { 
						if(columna<6) {
							cuadrante=7;
						}
						else{
							cuadrante=8;
						}
					}
				}
			}
		}
		
	}

	public void setValor(Integer valor) {    // seteamos valor de entidad
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizar(this.valor);
		}else {
			this.valor = null;
		}
	}
	
	public EntidadGrafica getEntidadGrafica() {
		return this.entidadGrafica;
	}
	
	public void setGrafica(EntidadGrafica g) {
		this.entidadGrafica = g;
	}
	
	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}
}
