package Logica;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Juego {

	private Celda [][] tablero;
	private int tamanio, contador;

	public Juego(String archivo) throws IOException {
		this.tamanio = 9;
		contador = 0;
		tablero = new Celda [tamanio][tamanio];
		Celda c;
		File f = new File(archivo);
        Scanner s = new Scanner(f);
        int numeroLeido, fila, col, cantLeidos;
        fila = 0;
        col = 0;
        cantLeidos = 0;
        while (s.hasNextInt()) { //mientras queden enteros por leer
            numeroLeido = s.nextInt(); //se lee un entero del archivo
            cantLeidos++;
            c = new Celda();
            tablero[fila][col] = c;
        	c.setValor(numeroLeido);
        	c.setFila(fila);
        	c.setColumna(col);
        	c.setCuadrante(fila, col);
        	System.out.println("cuadrante "+ c.getCuadrante());
        	contador++;
        	if (cantLeidos < 82)
        		if (col == tamanio - 1) {
        			fila++;
        			col = 0;
        		}
        		else
        			col++;   			
        }
	}
	
	public int totalNumeros() {
		return contador;
	}

	public void accionar(Celda c) {
		c.actualizar();
	}
	
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}
	
	public void setCelda(int fila, int col, Celda c) {
		tablero[fila][col] = c;
	}
	
	public int getCantFilas() {
		return this.tamanio;
	}
	
	public int getCantColumnas() {
		return this.tamanio;
	}
	
	public void verificar(Celda c) {
		int valor = c.getValor();
		
	//	for(int i=0; i<cantFilas ; i++) {
		//	if()
	//	}
	}
	
	public boolean cumpleReglas(Celda c) {
		
		return (cumpleFila(c) && cumpleColumna(c) && cumpleCuadrante(c));
	}
	
	public boolean cumpleFila(Celda c) {
		boolean toReturn= true;
		int fila= c.getFila();
		int columna= c.getColumna();
		
		for(int i=0; i<tamanio && toReturn==true; i++) { 
			if(i!=columna) {
				if (this.tablero[c.getFila()][i] != null) {
				
					if(tablero[fila][i].getValor()== c.getValor()) {
						toReturn=false;
					}
				}
			}
			
		}
		return toReturn;
	}
	
	public boolean cumpleColumna(Celda c) {
		boolean toReturn= true;
		int fila= c.getFila();
		int columna= c.getColumna();
		
		for(int i=0; i<tamanio ; i++) { 
			if(i!=fila) {
				if (this.tablero[i][c.getColumna()] != null) {
					
					if(tablero[i][columna].getValor()== c.getValor()) {
						toReturn=false;
					}
				}
				
			}
			
		}
		return toReturn;
	}
	
	public boolean cumpleCuadrante(Celda c) {
		Boolean toReturn=true;
		int cuadrante= c.getCuadrante();
		int fila, columna, corteFila, corteColumna; //variables que se usaran para recorrer el tablero
		fila=0;
		columna=0;
		corteFila=0;
		corteColumna=0;
		
		if(cuadrante==0) {
			fila=0; columna=0;
		}
		if(cuadrante==1) {
			fila=0; columna=3;
		}
		if(cuadrante==2) {
			fila=0; columna=6;
		}
		if(cuadrante==3) {
			fila=3; columna=0;
		}
		if(cuadrante==4) {
			fila=3; columna=3;
		}
		if(cuadrante==5) {
			fila=3; columna=6;
		}
		if(cuadrante==6) {
			fila=6; columna=0;
		}
		if(cuadrante==7) {
			fila=6; columna=3;
		}
		if(cuadrante==8) {
			fila=6; columna=6;
		}
		
		corteFila=fila+3;
		corteColumna=columna+3;

		
		while(fila<corteFila && toReturn!=false) {
			while(columna<corteColumna && toReturn!=false) {
				if (tablero[fila][columna] != null) {
					if(tablero[fila][columna].getFila()!=c.getFila() && tablero[fila][columna].getColumna()!=c.getColumna()) {
						if(tablero[fila][columna].getValor()==c.getValor()) {
							toReturn=false;
						}
					}
				}
				columna++;
			}
			fila++;
		}
		
		return toReturn;
	}
	
	public void eliminarCeldasParaComenzar() {
		int fila = 0;
		Random rndCol = new Random();
		int rnd1,rnd2,rnd3,rnd4;
		while (fila < tamanio) {
			rnd1 = rndCol.nextInt(9);
			rnd2 = rndCol.nextInt(9);
			rnd3 = rndCol.nextInt(9);
			rnd4 = rndCol.nextInt(9);
			
			while ((rnd1 == rnd2) || (rnd1 == rnd3) || (rnd1 == rnd4) || (rnd2 == rnd3) || (rnd2 == rnd4) || (rnd3 == rnd4)) {
				rnd1 = rndCol.nextInt(9);
				rnd2 = rndCol.nextInt(9);
				rnd3 = rndCol.nextInt(9);
				rnd4 = rndCol.nextInt(9);
			}
				
			contador -= 4;
			tablero[fila][rnd1] = null;
			tablero[fila][rnd2] = null;
			tablero[fila][rnd3] = null;
			tablero[fila][rnd4] = null;
			
			fila++;
		}
	}

}
