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
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new LineBorder(Color.BLACK));
		setContentPane(panelPrincipal);
		 
		//juego = new Juego();
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		panelTablero = new JPanel();
		panelPrincipal.add(panelTablero);
		panelTablero.setLayout(new GridLayout(3, 3, 0, 0));
		//panelTablero.setBounds(0, 0, 720, 720);
		panelTablero.setSize(600, 565);
		
		JPanel panelSecundario = new JPanel();
		panelPrincipal.add(panelSecundario);
		//panelSecundario.setSize(560, 720);
		
		String archivo = "C:\\Users\\UsuarioCAV\\OneDrive\\Universidad\\6to cuatrimestre\\Tecnologia de Programacion\\Proyecto 2\\generadorSudoku.txt";
		try {
			juego = new Juego(archivo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tableroBotones = new JButton[juego.getCantFilas()][juego.getCantFilas()];
		cuadrantes = new JPanel[9];
		
		
		
		inicializarCuadrantes();
	
		//inicializarBotones();
		
		crearBotones();
	}
		public void inicializarCuadrantes() {
			
			
			JPanel panel_0 = new JPanel();
			panel_0.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTablero.add(panel_0);
			panel_0.setLayout(new GridLayout(3, 3, 0, 0));
			cuadrantes[0]=panel_0;
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTablero.add(panel_1);
			panel_1.setLayout(new GridLayout(3, 3, 0, 0));
			cuadrantes[1]=panel_1;
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTablero.add(panel_2);
			panel_2.setLayout(new GridLayout(3, 3, 0, 0));
			cuadrantes[2]=panel_2;
			
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTablero.add(panel_3);
			panel_3.setLayout(new GridLayout(3, 3, 0, 0));
			cuadrantes[3]=panel_3;
			
			JPanel panel_4 = new JPanel();
			panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTablero.add(panel_4);
			panel_4.setLayout(new GridLayout(3, 3, 0, 0));
			cuadrantes[4]=panel_4;
			
			JPanel panel_5 = new JPanel();
			panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTablero.add(panel_5);
			panel_5.setLayout(new GridLayout(3, 3, 0, 0));
			cuadrantes[5]=panel_5;
			
			JPanel panel_6 = new JPanel();
			panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTablero.add(panel_6);
			panel_6.setLayout(new GridLayout(3, 3, 0, 0));
			cuadrantes[6]=panel_6;
			
			JPanel panel_7 = new JPanel();
			panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTablero.add(panel_7);
			panel_7.setLayout(new GridLayout(3, 3, 0, 0));
			cuadrantes[7]=panel_7;
			
			JPanel panel_8 = new JPanel();
			panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTablero.add(panel_8);
			panel_8.setLayout(new GridLayout(3, 3, 0, 0));
			cuadrantes[8]=panel_8;
		}
		
			public void crearBotones(int fila, int col, JPanel panel) {
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
			
				
			void crearBotones() {
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
				
				System.out.println("creo botones");
				
				for (int i = 0; i < juego.getCantFilas(); i++) {
					for (int j = 0; j < juego.getCantFilas(); j++) {
						if (juego.getCelda(i, j) != null) {
							ImageIcon img = juego.getCelda(i, j).getEntidadGrafica().getGrafico();
							Boton boton= (Boton) tableroBotones[i][j];
							System.out.println("columna de celda: "+juego.getCelda(i, j).getColumna());
							boton.setIcon(img);
							boton.setBackground(Color.white);
							boton.setEnabled(true);
							//tableroBotones[i][j].resize(width, height);
							//reDimensionar(boton,img);
							
						}
					}	
				}
				for (int i = 0; i < juego.getCantFilas(); i++)
					for (int j = 0; j < juego.getCantFilas(); j++) {
						tableroBotones[i][j].addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {
								Boton botonEvento = (Boton) e.getSource();
								int col = botonEvento.getColumna();
								int fila = botonEvento.getFila();
								Celda c = juego.getCelda(fila, col);
								if (c == null) {
									Celda nueva = new Celda();			
									nueva.setValor(1);							
									nueva.setColumna(col);
									nueva.setFila(fila);
									juego.setCelda(fila, col, nueva);
									actualizarBoton(tableroBotones[nueva.getFila()][nueva.getColumna()], nueva.getFila(), nueva.getColumna(), nueva);
								}
								else {
									juego.accionar(c);
									actualizarBoton(tableroBotones[fila][col],fila,col,c);
								}
								//reDimensionar(botonEvento,c.getEntidadGrafica().getGrafico());
							}	
						});
					}
			}
			
			private void actualizarBoton (JButton boton, int fila, int col, Celda c) {
				boton.setBackground(Color.green);
				c = juego.getCelda(fila, col);
				ImageIcon img = c.getEntidadGrafica().getGrafico();
				boton.setIcon(img);
				//actualizarBotones();
				//reDimensionar(boton,img);
			}
			
			private void actualizarBotones() {
				Celda c;
				for (int i = 0; i < tableroBotones.length; i++) {
					for (int j = 0; j < tableroBotones.length; j++) {
						c = juego.getCelda(i, j);
						if (c != null) {
							if (juego.cumpleConLasReglas(c)) {
								if (tableroBotones[i][j].isEnabled() == true)
									tableroBotones[i][j].setBackground(Color.green);
							}
							else
								if (tableroBotones[i][j].isEnabled() == true)
									tableroBotones[i][j].setBackground(Color.red);
						}
					}
				}
			}
			
	
			private void reDimensionar(JButton boton, ImageIcon grafico) {
				Image image = grafico.getImage();
				if (image != null) {  
					Image newimg = image.getScaledInstance(boton.getWidth(), boton.getHeight(),  java.awt.Image.SCALE_SMOOTH);
					grafico.setImage(newimg);
					boton.repaint();
					System.out.println("altura "+ newimg.getHeight(boton)+ " ancho "+newimg.getWidth(boton));
				}
			}

			/*
			public void inicializarBotones(){
			for (int i = 0; i <juego.getCantFilas(); i++) {
				for(int j =0; j<juego.getCantFilas(); j++) {
					Celda c = juego.getCelda(i,j);
					ImageIcon grafico = c.getEntidadGrafica().getGrafico();
					JLabel label = new JLabel();
					panelTablero.add(label);
				
//				for (int i = 0; i <juego.getCantFilas(); i++) {
//					for(int j =0; j<juego.getCantFilas(); j++) {
				
					label.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							reDimensionar(label, grafico);
							label.setIcon(grafico);
						}
					});
					
					label.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							juego.accionar(c);
							reDimensionar(label,grafico);
							//juego.verificar(c);
						}
					});
				}
			}
		}
				
				private void reDimensionar(JLabel label, ImageIcon grafico) {
					Image image = grafico.getImage();
					if (image != null) {  
						Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
						grafico.setImage(newimg);
						label.repaint();
					}
				}
		
	*/		
}

