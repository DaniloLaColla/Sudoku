package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class Juego {

	private Celda [][] tablero;
	private int tamanio, contador;
	private boolean habilitarGUI;

	public Juego(String archivo) throws IOException {  //constructor unico de Juego
		this.tamanio = 9;
		contador = 0;
		tablero = new Celda [tamanio][tamanio];
		File f = new File(archivo);
		if(esArchivoValido(f)) {  //si el archivo es valido se habilita la GUI
			setHabilitarGUI(true);
		}
		else {
			setHabilitarGUI(false); //de lo contrario obtenemos un mensaje de error
		}
		
	}
	

	private boolean esArchivoValido(File f) throws FileNotFoundException {    //verifica que el archivo sea valido, y de ser asi, genera la matriz de celdas
		boolean toReturn=true;
		Celda c;
		Scanner s;
        int numeroLeido, fila, col, cantLeidos;
       
        if(archivoCumpleFormato(f)) {  //si cumple con el formato de 9 lineas de enteros (del 0 al 8) separadas por espacios
        	
        	String rutaArchivo =f.getPath();
			InputStream in = Juego.class.getClassLoader().getResourceAsStream(rutaArchivo);
			
        	fila = 0;
            col = 0;
            cantLeidos = 0;
        	s = new Scanner(in);
        	
	        while (s.hasNextInt()) {    //mientras existan enteros por leer
	            numeroLeido = s.nextInt();     //se lee el siguiente entero del archivo
				

	            cantLeidos++;
	            c = new Celda();			//se crea la Celda y se setean atributos
	            tablero[fila][col] = c;
	        	c.setValor(numeroLeido);
	        	c.setFila(fila);
	        	c.setColumna(col);
	        	c.setCuadrante(fila, col);
	        	contador++;
	        	if (cantLeidos < 82)
	        		if (col == tamanio - 1) {
	        			fila++;
	        			col = 0;
	        		}
	        		else
	        			col++;   			
	        }	
	        
	        for(int i=0; i<tamanio && toReturn; i++) {   //si el archivo contiene la variante de resolucion correcta del juego
	        	for(int j=0; j<tamanio && toReturn; j++) {
	        		if(!cumpleReglas(tablero[i][j])) {
	        			toReturn=false;
	        		}
	        	}
	        }
        }
        else {
        	toReturn=false;
        }
		return toReturn;
	}

	private boolean archivoCumpleFormato(File f) {  //si cumple con el formato de 9 lineas de enteros (del 0 al 8) separadas por espacios
		boolean toReturn = true;
		String linea = "";
		String[] auxiliar;  
		int numero;
		try {
			String rutaArchivo =f.getPath();
			InputStream in = Juego.class.getClassLoader().getResourceAsStream(rutaArchivo);
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader bf = new BufferedReader(isr);

			while((linea = bf.readLine())!=null && toReturn) {
				auxiliar = linea.split(" ");
				if(auxiliar.length==tamanio) {
					for(int i=0; i<auxiliar.length && toReturn; i++) {
						numero = Integer.parseInt(auxiliar[i]);
						if(numero<0 || numero>8)
							toReturn = false;
					}
				}
				else
					toReturn = false;
			}
			bf.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
			toReturn = false;
		}
		return toReturn;
		
	}

	public int totalNumeros() {  //en total deben ser 81
		return contador;
	}

	public void accionar(Celda c) {  //conecta el accionar de un Boton con la Celda
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
	
	public boolean cumpleReglas(Celda c) { //si determinada celda esta en una posicion valida momentaneamente
		boolean toReturn=false;
		if(!noCumpleCuadrante(c)) {
			if(cumpleFila(c)) {
				if(cumpleColumna(c)) {
					toReturn=true;
				}
			}
		}
		return toReturn;
	}
	

	public boolean cumpleFila(Celda c) {  //si el valor de la celda solo se encuentra una sola vez en la fila
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
	
	public boolean cumpleColumna(Celda c) {  //si el valor de la celda solo se encuentra una sola vez en la columna
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
	
	public boolean noCumpleCuadrante(Celda c) { 
		Boolean toReturn=false;
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
		if(cuadrante==2) {                    //indicamos desde donde recorremos para encontrar coincidencias 
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

		while(fila<corteFila && toReturn==false) {
			while(columna<corteColumna && toReturn==false) {
				if (tablero[fila][columna] != null) {
					if(fila!=c.getFila() || columna!=c.getColumna()) {
						if(tablero[fila][columna].getValor()==c.getValor()) {
							toReturn=true;
						}
					}
				}
				columna++;
			}
			fila++;
		}
	
		return toReturn;
	}
	
	public void eliminarCeldas() {  //elimina celdas para comenzar
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

	public boolean ganar() {  //para revisar el tablero completo
		Boolean toReturn=true;
		for(int i=0; i<tamanio && toReturn==true; i++) {
			for(int j=0; j<tamanio ; j++) {
				if(!cumpleReglas(tablero[i][j])) {
					toReturn=false;
				}
			}
		}
		return toReturn;
	}


	public boolean getHabilitarGUI() {
		return habilitarGUI;
	}


	public void setHabilitarGUI(boolean habilitarGUI) {
		this.habilitarGUI = habilitarGUI;
	}

}
