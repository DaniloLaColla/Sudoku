package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;

public class GUI extends JFrame {
	
	private JPanel MainPane;
	private JPanel BoardPane;
	private JPanel InputPane;

	private JButton board[][];
	private JButton input[][];
	

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
		setBounds(100, 100, 450, 300);
		MainPane = new JPanel();
		MainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPane);
		MainPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		BoardPane = new JPanel();
		MainPane.add(BoardPane);
		initBoard();
		
		InputPane = new JPanel();
		MainPane.add(InputPane);
		initInput();
	}
	
	private void initBoard() {
		board= new JButton[9][9];
		BoardPane.add(Board);
	}
	
	private void initInput() {
		input= new JButton[3][3];
	}

}
