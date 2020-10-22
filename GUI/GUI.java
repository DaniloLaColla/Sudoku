package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.Celda;
import Logica.Juego;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;

public class GUI extends JFrame {

	private JPanel panelPrincipal, panelTablero, panelSecundario;
	private Juego juego;
	private JButton tableroBotones[][];
	private JPanel cuadrantes[];
	
	/**
	 * Launch the application.
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

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		inicializarPaneles();
		
		tableroBotones = new JButton[juego.getCantFilas()][juego.getCantFilas()];
		cuadrantes = new JPanel[9];
		
		
		inicializarCuadrantes();
	
		juego.eliminarCeldasParaComenzar();
		
		inicializarBotones();
		
		
	}
		private void inicializarPaneles() {
			panelPrincipal = new JPanel();
			panelPrincipal.setBorder(new LineBorder(Color.BLACK));
			setContentPane(panelPrincipal);
			 
			
			panelPrincipal.setLayout(new BorderLayout(0, 0));
			
			panelTablero = new JPanel();
			panelPrincipal.add(panelTablero);
			panelTablero.setLayout(new GridLayout(3, 3, 0, 0));
			panelTablero.setSize(600, 565);
			
			JPanel panelSecundario = new JPanel();
			panelPrincipal.add(panelSecundario);
			
			String archivo = "C:\\Users\\UsuarioCAV\\OneDrive\\Universidad\\6to cuatrimestre\\Tecnologia de Programacion\\Proyecto 2\\generadorSudoku.txt";
			try {
				juego = new Juego(archivo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

		private void inicializarCuadrantes() {
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
			
				
			private void inicializarBotones() {
				tableroBotones = new Boton[juego.getCantFilas()][juego.getCantColumnas()];
				
					crearBotones(0,0,cuadrantes[0]);
					crearBotones(0,3,cuadrantes[1]);
					crearBotones(0,6,cuadrantes[2]);
					crearBotones(3,0,cuadrantes[3]);
					crearBotones(3,3,cuadrantes[4]);
					crearBotones(3,6,cuadrantes[5]);
					crearBotones(6,0,cuadrantes[6]);
					crearBotones(6,3,cuadrantes[7]);
					crearBotones(6,6,cuadrantes[8]);
					
				
				for (int i = 0; i < juego.getCantFilas(); i++) {
					for (int j = 0; j < juego.getCantFilas(); j++) {
						if (juego.getCelda(i, j) != null) {
							ImageIcon imagen = juego.getCelda(i, j).getEntidadGrafica().getGrafico();
							Boton boton= (Boton) tableroBotones[i][j];
							System.out.println("columna de celda: "+juego.getCelda(i, j).getColumna());
							boton.setIcon(imagen);
							boton.setBackground(Color.GRAY);
							boton.setEnabled(true);
							
							
						}
					}	
				}
				
			oyenteTablero();
						
			}
			
			private void oyenteTablero() {
				
				for (int i = 0; i < juego.getCantFilas(); i++)
					for (int j = 0; j < juego.getCantFilas(); j++) {
						if (tableroBotones[i][j] != null) 
							tableroBotones[i][j].addActionListener(new ActionListener() {
						
							
					public void actionPerformed(ActionEvent e) {
						Boton evento = (Boton) e.getSource();
						int columna = evento.getColumna();
						int fila = evento.getFila();
						Celda celda = juego.getCelda(fila, columna);
						
						if (celda == null) {
							celda = new Celda();			
							celda.setColumna(columna);
							celda.setFila(fila);
							celda.setCuadrante(fila, columna);
							celda.setValor(1);							
							juego.setCelda(fila, columna, celda);
							actualizarBoton(tableroBotones[celda.getFila()][celda.getColumna()], celda.getFila(), celda.getColumna(), celda);
						}
						else {
							if(evento.getBackground()!=(Color.GRAY)) {
								juego.accionar(celda);
								actualizarBoton(tableroBotones[fila][columna],fila,columna,celda);
							}
							if(evento.getBackground()==(Color.GRAY)) {
								celda.setValor(celda.getValor());
							}
						}
						//reDimensionar(botonEvento,c.getEntidadGrafica().getGrafico());
						System.out.println("cuadrante "+ celda.getCuadrante()+ " fila: "+ fila+ " columna: "+ columna) ;
						System.out.println("valor "+celda.getValor());
					}	
				});
			}
				
				
			}

			private void actualizarBoton (JButton boton, int fila, int col, Celda c) {
				//boton.setBackground(Color.green);
				c = juego.getCelda(fila, col);
				ImageIcon img = c.getEntidadGrafica().getGrafico();
				boton.setIcon(img);
				actualizarTablero();
				//reDimensionar(boton,img);
			}
			
			private void actualizarTablero() {
				Celda c;
				for (int i = 0; i < tableroBotones.length; i++) {
					for (int j = 0; j < tableroBotones.length; j++) {
						c = juego.getCelda(i, j);
						if (c != null) {
							if (juego.cumpleReglas(c) && tableroBotones[i][j].getBackground()!=(Color.GRAY) ) {
				
									tableroBotones[i][j].setBackground(Color.green);
							}
							else if(! juego.cumpleReglas(c) && tableroBotones[i][j].getBackground()!=(Color.GRAY))
								
									tableroBotones[i][j].setBackground(Color.red);
						}
					}
				}
			}
			
	/*
			private void reDimensionar(JButton boton, ImageIcon grafico) {
				Image image = grafico.getImage();
				if (image != null) {  
					Image newimg = image.getScaledInstance(boton.getWidth(), boton.getHeight(),  java.awt.Image.SCALE_SMOOTH);
					grafico.setImage(newimg);
					boton.repaint();
					//System.out.println("altura "+ newimg.getHeight(boton)+ " ancho "+newimg.getWidth(boton));
				}
			}
			*/
			
}