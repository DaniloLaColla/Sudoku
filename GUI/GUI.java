package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Logica.Celda;

import Logica.EntidadGraficaReloj;
import Logica.Juego;
import Logica.NumerosReloj;

import java.awt.GridLayout;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.awt.Color;

import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	@SuppressWarnings("unused")
	private JPanel panelPrincipal, panelTablero, panelSecundario, panelTiempo, panelTiempoGeneral;
	private Juego juego;
	private JButton tableroBotones[][];
	private JPanel cuadrantes[];
	private JPanel panelVerificacion;
	private JButton botonGanar;
	
	private EntidadGraficaReloj izqSegundos, derSegundos, izqMinutos, derMinutos;
	private Timer timer;
	private NumerosReloj minutos, segundos;
	private JLabel [] reloj;

	
	/**
	 * Lanza la aplicacion
	 */
	 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GUI() {  //inicializacion de GUI
		
		inicializarJuego();
		
		
		
		if(juego.getHabilitarGUI()) { //si el archivo es correcto
			
			inicializarFrame(); 
			
			inicializarPaneles();	
			
			inicializarCuadrantes(); 
		
			juego.eliminarCeldas(); //Se eliminan celdas al azar
			
			inicializarBotones();
			
			iniciarTiempo();
		}
		else {		//si el archivo no es valido mostramos mensaje de error y cortamos ejecucion
			
			JOptionPane.showMessageDialog(null,"Archivo no valido","Estamos en problemas",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
			
		
	}
	
	/**
	 * inicializa el reloj que lleva el tiempo transcurrido de juego.
	 */
	private void iniciarTiempo() {		
		izqMinutos = new EntidadGraficaReloj();
		izqSegundos = new EntidadGraficaReloj();
		derMinutos = new EntidadGraficaReloj();
		derSegundos = new EntidadGraficaReloj();
		
		minutos = new NumerosReloj(izqMinutos, derMinutos);
		segundos = new NumerosReloj(izqSegundos, derSegundos);
		
		reloj = new JLabel[5];
		
		minutos.getIzq().actualizar(0);
		minutos.getDer().actualizar(0);	
		segundos.getIzq().actualizar(0);
		segundos.getDer().actualizar(0);
		
		
		reloj[0] = new JLabel(minutos.getIzq().getGrafico());
		reloj[1] = new JLabel(minutos.getDer().getGrafico());
		reloj[2] = new JLabel(new ImageIcon(getClass().getResource("/ImagenesCronometro/dosPuntos.png")));
		reloj[3] = new JLabel(segundos.getIzq().getGrafico());
		reloj[4] = new JLabel(segundos.getDer().getGrafico());
		
		for (int i = 0; i < reloj.length; i++)
			panelTiempo.add(reloj[i]);
		
		timer = new Timer();
		
		TimerTask tarea = new TimerTask() {		
				public void run() {
					
					if (segundos.getDer().getValor() < 9) {
						segundos.getDer().setValor(segundos.getDer().getValor() + 1);
						segundos.getDer().actualizar(segundos.getDer().getValor());		
					}
					else {
						segundos.getDer().setValor(0);
						segundos.getDer().actualizar(segundos.getDer().getValor());
						segundos.getIzq().setValor(segundos.getIzq().getValor() + 1);
						segundos.getIzq().actualizar(segundos.getIzq().getValor());
					}

					if (segundos.getIzq().getValor() == 6) {
						minutos.getDer().setValor(minutos.getDer().getValor() + 1);
						minutos.getDer().actualizar(minutos.getDer().getValor());
						segundos.getIzq().setValor(0);
						segundos.getIzq().actualizar(segundos.getIzq().getValor());
					}
					
					if (minutos.getDer().getValor() == 9) {
						if (minutos.getIzq().getValor() != 9) {
							minutos.getDer().setValor(0);
							minutos.getDer().actualizar(minutos.getDer().getValor());
							minutos.getIzq().setValor(minutos.getIzq().getValor() + 1);
							minutos.getIzq().actualizar(minutos.getIzq().getValor());
						}
					}
					
					if (minutos.getIzq().getValor() == 9 && minutos.getDer().getValor() == 9) {
						JOptionPane.showMessageDialog(null, "Ha excedido el tiempo neto de juego ","¡HAS PERDIDO!",2);
						System.exit(0);
						
					}
					repaint();
				}		
		};
		timer.schedule(tarea, 0 , 1000);
	}


		private void inicializarJuego() {
		// TODO Auto-generated method stub
			String archivo = "/Archivo/generadorSudoku.txt"; //seleccion de archivo
			try {
				juego = new Juego(archivo);
			} catch (IOException e) {
				
				e.printStackTrace();
			}

	}

		private void inicializarFrame() {//Inicializacion JFrame
			setTitle("Sudok-us");
			setIconImage(new ImageIcon(getClass().getResource("/ImagenesJuego/rojo.png")).getImage() );
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1280, 720);
		
	}

		private void inicializarPaneles() { //Inicializacion Paneles
			panelPrincipal = new JPanel();
			panelPrincipal.setBorder(new LineBorder(Color.BLACK));
			setContentPane(panelPrincipal);
			 
			
			panelPrincipal.setLayout(new BorderLayout(0, 0));
			
			panelTablero = new JPanel();
			panelPrincipal.add(panelTablero);
			panelTablero.setLayout(new GridLayout(3, 3, 0, 0));
			panelTablero.setSize(565, 565);
			
			JPanel panelSecundario = new JPanel();
			panelPrincipal.add(panelSecundario, BorderLayout.EAST);
			panelSecundario.setLayout(new BorderLayout(0, 0));

			panelTiempoGeneral = new JPanel();
			panelTiempoGeneral.setLayout(new BorderLayout(0, 0));
			
			panelSecundario.add(panelTiempoGeneral, BorderLayout.NORTH);
			
			panelTiempo = new JPanel();
			panelTiempo.setLayout(new FlowLayout());
			
			panelTiempoGeneral.add(panelTiempo, BorderLayout.SOUTH);
			
			JLabel etiquetaTiempo = new JLabel("Tiempo de juego:");
			panelTiempoGeneral.add(etiquetaTiempo, BorderLayout.NORTH);
			etiquetaTiempo.setBounds(110, 110, 110, 110);
			etiquetaTiempo.setFont(new Font("Font.PLAIN", 5, 26));
			
			
			panelVerificacion = new JPanel();
			panelSecundario.add(panelVerificacion, BorderLayout.SOUTH);
			
			botonGanar = new JButton("GANE");   //boton que sera utilizado para copnsultar al juego si se gana o no
			panelVerificacion.add(botonGanar);
			botonGanar.setEnabled(false);
			botonGanar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(juego.ganar()) {
						JOptionPane.showMessageDialog(GUI.this, "Haz Ganado, tu tiempo: "+minutos.getIzq().getValor()+""+minutos.getDer().getValor()+":"+segundos.getIzq().getValor()+""+segundos.getDer().getValor());
						System.exit(0);
						
					}else {
						JOptionPane.showMessageDialog(GUI.this, "AUN HAY ERRORES EN EL TABLERO");
					}
				}
			});
			
			
			tableroBotones = new JButton[juego.getCantFilas()][juego.getCantFilas()];  //inicializacion de tableros y cuadrantes
			cuadrantes = new JPanel[9];
		
	}

		private void inicializarCuadrantes() {  //Inicializacion Cuadrantes
			for(int i=0; i<9; i++) {
				cuadrantes[i]= new JPanel();
				cuadrantes[i].setBorder(new LineBorder(new Color(0, 0, 0), 3));
				panelTablero.add(cuadrantes[i]);
				cuadrantes[i].setLayout(new GridLayout(3, 3, 0, 0));
			}
			
		}
		
			private void crearBotones(int fila, int col, JPanel panel) {
				int auxFila = (fila+2);
				int auxColMax = (col+2);
				int auxColMin = col;
				while (fila <= auxFila) {
					
						
					tableroBotones[fila][col] = new Boton(fila, col);
					
					tableroBotones[fila][col].setBackground(Color.white);
					panel.add(tableroBotones[fila][col]);
						
						if (col == auxColMax) {
							fila ++;
							col = auxColMin;
						}
						else 
							col++;
					}
					
				}
			
				
			private void inicializarBotones() { //Inicializacion de Botones
				tableroBotones = new Boton[juego.getCantFilas()][juego.getCantColumnas()];
				
					crearBotones(0,0,cuadrantes[0]);
					crearBotones(0,3,cuadrantes[1]);
					crearBotones(0,6,cuadrantes[2]);
					crearBotones(3,0,cuadrantes[3]);
					crearBotones(3,3,cuadrantes[4]);  //Se crean los botones en su respectiva posicion y cuadrante
					crearBotones(3,6,cuadrantes[5]);
					crearBotones(6,0,cuadrantes[6]);
					crearBotones(6,3,cuadrantes[7]);
					crearBotones(6,6,cuadrantes[8]);
					
				
				for (int i = 0; i < juego.getCantFilas(); i++) {
					for (int j = 0; j < juego.getCantFilas(); j++) {
						if (juego.getCelda(i, j) != null) {  //las celdas que no fueron eliminadas seran botones "deshabilitados"
							ImageIcon imagen = juego.getCelda(i, j).getEntidadGrafica().getGrafico();
							Boton boton= (Boton) tableroBotones[i][j];
							boton.setIcon(imagen);
							boton.setBackground(Color.GRAY);    //se "deshabilitan" los botones utilizando fondo gris
							boton.setEnabled(true);
							
							
						}
					}	
				}
				
			oyenteTablero();
						
			}
			
			private void oyenteTablero() {   //se crea un oyente para cada boton del tablero
				
				for (int i = 0; i < juego.getCantFilas(); i++)
					for (int j = 0; j < juego.getCantFilas(); j++) {
						if (tableroBotones[i][j] != null) 
							tableroBotones[i][j].addActionListener(new ActionListener() {
						
							
					public void actionPerformed(ActionEvent e) {  //al pulsar un boton
						Boton evento = (Boton) e.getSource();
						int columna = evento.getColumna();
						int fila = evento.getFila();
						Celda celda = juego.getCelda(fila, columna); //reconocemos la celda relacionada a ese boton
						
						if (celda == null) {    //si estaba en nulo, le seteamos sus atributos
							celda = new Celda();			
							celda.setColumna(columna);
							celda.setFila(fila);
							celda.setCuadrante(fila, columna);
							celda.setValor(0);							
							juego.setCelda(fila, columna, celda);  
							actualizarBoton(tableroBotones[celda.getFila()][celda.getColumna()], celda.getFila(), celda.getColumna(), celda);  //actualizamos botones
						}
						else {
							if(evento.getBackground()!=(Color.GRAY)) { // si el boton es distinto de nulo y no esta "deshabilitado"
								juego.accionar(celda);
								actualizarBoton(tableroBotones[fila][columna],fila,columna,celda);
							}
							if(evento.getBackground()==(Color.GRAY)) { // si el boton es distinto de nulo y esta "deshabilitado"
								
								celda.setValor(celda.getValor()); //vuelve a su mismo valor ya que esta "deshabilitado"
							}
						}
						
					}	
				});
			}
				
				
			}

			private void actualizarBoton (JButton boton, int fila, int col, Celda c) {  //actualizamos un boton con su nuevo icono y dejamos ver si cumple las reglas
		
				c = juego.getCelda(fila, col);
				ImageIcon img = c.getEntidadGrafica().getGrafico();
				boton.setIcon(img);
				
				repintadoBackground(fila, col, c); //repintamos background
				
				actualizarTablero(); //se actualiza el tablero

			}
			
			private void actualizarTablero() {  //recorremos y actualizamos el tablero
				Celda c;
		
				for (int i = 0; i < tableroBotones.length; i++) {
					for (int j = 0; j < tableroBotones.length; j++) {    //por cada celda del tablero
						c = juego.getCelda(i, j);							
						
						repintadoBackground(i, j, c);//repintamos background
					}
				}
				
				activarBotonGanar();
			}
			
			private void repintadoBackground(int fila, int col, Celda c) { 
				if (c != null) {   //si la celda no es nula
					if (tableroBotones[fila][col].getBackground()!=(Color.GRAY)) {
						if (juego.cumpleReglas(c)) {									//si el boton esta habilitado y cumple las reglas le seteamos fondo verde
							tableroBotones[fila][col].setBackground(Color.green);
						}
						else {
							tableroBotones[fila][col].setBackground(Color.red);			//si el boton esta habilitado y no cumple las reglas le seteamos fondo rojo
						}
					}
				}
			}

			private void activarBotonGanar() {    //cuando todas las celdas sean distintas a nulo se habilita el boton de ganar
				boolean activarBotonGanar=true;
				for (int i = 0; i < tableroBotones.length && activarBotonGanar; i++) {
					for (int j = 0; j < tableroBotones.length && activarBotonGanar; j++) {
						if(!(tableroBotones[i][j].getBackground()==Color.green || tableroBotones[i][j].getBackground()==Color.red || tableroBotones[i][j].getBackground()==Color.gray)){
							activarBotonGanar=false;
						}
					}
				}
				
				if(activarBotonGanar) {
					botonGanar.setEnabled(true);
				}
				else {
					botonGanar.setEnabled(false);
				}
			}
			
}