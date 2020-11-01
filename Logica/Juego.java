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

	private boolean archivoCumpleFormato(File f) {  //si cumple con el formato de 9 lineas de 9 enteros (del 0 al 8) separadas por espacios
		boolean toReturn = true;
		String linea = "";
		String[] auxiliar;  
		int numero, contadorFilas;
		contadorFilas=0;
		try {
			String rutaArchivo =f.getPath();
			InputStream in = Juego.class.getClassLoader().getResourceAsStream(rutaArchivo);
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader bf = new BufferedReader(isr);

			while((linea = bf.readLine())!=null && toReturn) {
				contadorFilas++;
				if(contadorFilas==10) {
					toReturn=false;
				}
				else {
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
		if(!estaEnCuadrante(c)) {
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
	
	private boolean seRepite(int fila, int col, Celda c) {
		boolean seRepite = false;
		if (tablero[fila][col] != null)
			if (fila != c.getFila() || col != c.getColumna())
				if (tablero[fila][col].getValor() == c.getValor()) 
					seRepite = true;
		return seRepite;
	}
	
	// En base al numero de fila y columna que la celda pasada como parametro contenga,
	// realiza el recorrido del cuadrante al que la celda pertenezca para ver si el valor 
	// de la celda ya estaba.
	public boolean estaEnCuadrante(Celda c) {
		boolean toReturn = false;
		int fila = c.getFila();
		int col = c.getColumna();
		
		if ((fila == 0) | (fila == 3) | (fila == 6)) {
			if ((col == 0) | (col == 3) | (col == 6)) {
				for (int i = fila; i <= (fila+2) && toReturn == false; i++) 
					for (int j = col; j <= (col+2) && toReturn == false; j++) 
						toReturn = seRepite(i, j, c);
			}
			if ((col == 1) || (col == 4) || (col == 7)) {
				for (int i = fila; i <= (fila+2) && toReturn == false; i++) 
					for (int j = col-1; j <= (col+1) && toReturn == false; j++) 
						toReturn = seRepite(i, j, c);		
			}
			if ((col == 2) || (col == 5) || (col == 8)) {
				for (int i = fila; i <= fila+2 && toReturn == false; i++) 
					for (int j = col-2; j <= col && toReturn == false; j++) 
						toReturn = seRepite(i, j, c);			
				}
		}
		else
			if ((fila == 1) || (fila == 4) || (fila == 7)) {
				if ((col == 0) || (col == 3) || (col == 6)) {
					for (int i = fila-1; i <= fila+1 && toReturn == false; i++)
						for (int j = col; j <= col+2 && toReturn == false; j++)
							toReturn = seRepite(i, j, c);			
				}				
				if ((col == 1) || (col == 4) || (col == 7)) {
					for (int i = fila-1; i <= fila+1 && toReturn == false; i++) 
						for (int j = col-1; j <= col+1 && toReturn == false; j++) 
							toReturn = seRepite(i, j, c);
				}
				if ((col == 2) || (col == 5) || (col == 8)) {
					for (int i = fila-1; i <= fila+1 && toReturn == false; i++)
						for (int j = col-2; j <= col && toReturn == false; j++)
							toReturn = seRepite(i, j, c);
				}			
			}
			else
				if ((fila == 2) || (fila == 5) || (fila == 8)) {
					if ((col == 0) || (col == 3) || (col == 6)) {
						for (int i = fila-2; i <= fila && toReturn == false; i++) 
							for (int j = col; j <= col+2 && toReturn == false; j++) 
								toReturn = seRepite(i, j, c);		
					}
					if ((col == 1) || (col == 4) || (col == 7)) {
						for (int i = fila-2; i <= fila && toReturn == false; i++) 
							for (int j = col-1; j <= col+1 && toReturn == false; j++) 
								toReturn = seRepite(i, j, c);
					}				
					if ((col == 2) || (col == 5) || (col == 8)) {
						for (int i = fila-2; i <= fila && toReturn == false; i++) 
							for (int j = col-2; j <= col && toReturn == false; j++) 
								toReturn = seRepite(i, j, c);
					}
				}
			return toReturn;	
	}
	
	public void eliminarCeldas() {  //elimina celdas para comenzar
		int columna = 0;
		Random rndFila = new Random();
		int rnd1,rnd2,rnd3,rnd4;
		while (columna < tamanio) {
			rnd1 = rndFila.nextInt(9);
			rnd2 = rndFila.nextInt(9);
			rnd3 = rndFila.nextInt(9);
			rnd4 = rndFila.nextInt(9);
			
			while ((rnd1 == rnd2) || (rnd1 == rnd3) || (rnd1 == rnd4) || (rnd2 == rnd3) || (rnd2 == rnd4) || (rnd3 == rnd4)) {
				rnd1 = rndFila.nextInt(9);
				rnd2 = rndFila.nextInt(9);
				rnd3 = rndFila.nextInt(9);
				rnd4 = rndFila.nextInt(9);
			}
				
			contador -= 4;
			tablero[rnd1][columna] = null;
			tablero[rnd2][columna] = null;
			tablero[rnd3][columna] = null;
			tablero[rnd4][columna] = null;
			
			columna++;
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
