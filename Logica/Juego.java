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
        int i = 0;
        while (s.hasNextInt()) { //mientras queden enteros por leer
            numeroLeido = s.nextInt(); //se lee un entero del archivo
            cantLeidos++;
            c = new Celda();
            tablero[fila][col] = c;
        	c.setValor(numeroLeido);
        	System.out.print(numeroLeido+" ");
        	i++;
        	if (i == 9)
        	{
        		System.out.println();
        		i = 0;
        	}
        	c.setFila(fila);
        	c.setColumna(col);
        	contador++;
        	if (cantLeidos < 82)
        		if (cantLeidos % 9 == 0) {
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
	
	public boolean cumpleConLasReglas(Celda c) {
		boolean toReturn=false;
//		
//		if(cumplenFilas() && cumplenColumnas() && cumplenCuadrantes()) {
//			toReturn=true;
//		}
		return toReturn;
	}
	
	public boolean cumplenFilas() {
		boolean toReturn= true;
		for(int i=0; i<10; i++) { //cantidad de iconos
			for(int j=0; j<tamanio ; j++) { //cantidad de filas
				//if(!revisaFila(i, j)) {
					toReturn=false;
					return toReturn;
				}
			}
		//}
		return toReturn;
	}
	

	/*
	public Juego() {
		this.cantFilas = 9;
		tablero = new Celda[this.cantFilas][this.cantFilas];
		
		for (int i =0; i<cantFilas; i++) {
			for (int j =0; j<cantFilas; j++) {
				Random rand = new Random();
				int value = rand.nextInt(2);
				tablero[i][j] = new Celda();
				//De acuerdo a value decidir si asignar un valor o no
				if (value == 0){
					// elijo aleatoriamente un valor entre 0 (incluido) y cantElementos (excluido)
					int valor = rand.nextInt(tablero[i][j].getCantElementos());
					tablero[i][j].setValor(valor);	
				}
			}
		}
		
	}
	*/}
