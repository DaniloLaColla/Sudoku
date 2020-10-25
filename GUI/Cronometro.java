package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Cronometro extends JPanel implements Runnable, ActionListener{
public static int onoff = 0;

JLabel tiempo;
Thread hilo;
boolean cronometroActivo;
boolean pausar;

boolean iniciado = true;

public Cronometro()    {  //constructor cronometro
	setSize( 500, 200 );
	setLayout( new BorderLayout() );

	//Etiqueta donde se colocara el tiempo 
	tiempo = new JLabel( "00:00:000" );
	tiempo.setFont( new Font( Font.SERIF, Font.BOLD, 50 ) );
	tiempo.setHorizontalAlignment( JLabel.CENTER );
	tiempo.setForeground( Color.WHITE );
	tiempo.setBackground( Color.red );
	tiempo.setOpaque( true );

	add( tiempo, BorderLayout.CENTER );

	//Boton iniciar
	JButton btn = new JButton( "Iniciar" );
	btn.addActionListener( this );
	add( btn, BorderLayout.NORTH );

	//Boton reiniciar inicia nuevamente desde 0
	JButton btnP = new JButton( "Reiniciar" );
	btnP.addActionListener( this );
	add( btnP, BorderLayout.EAST );

	JButton btnD = new JButton( "Detener" );
	
	btnD.addActionListener( this );
	add( btnD, BorderLayout.SOUTH );

	
	setVisible( true );
}

public void run(){
	Integer minutos = 0 , segundos = 0, milesimas = 0;
	//min es minutos, seg es segundos y mil es milesimas de segundo
	String min="", seg="", mil="";
	try {

		//Mientras cronometroActivo sea verdadero entonces seguira aumentando el tiempo
		while( cronometroActivo ) {
				
			if (!pausar) {
				
				Thread.sleep( 4 );
				//Incrementamos 4 milesimas de segundo
				milesimas += 4;
				//Cuando llega a 1000 osea 1 segundo aumenta 1 segundo y las milesimas de segundo de nuevo a 0
				if( milesimas == 1000 ) {
					milesimas = 0;
					segundos += 1;
					//Si los segundos llegan a 60 entonces aumenta 1 los minutos y los segundos vuelven a 0
					if( segundos == 60 )
					{
						segundos = 0;
						minutos++;
					}
				}

				//Esto solamente es estetica para que siempre este en formato 00:00:000
				if( minutos < 10 ) min = "0" + minutos;
				else min = minutos.toString();
				if( segundos < 10 ) seg = "0" + segundos;
				else seg = segundos.toString();

				if( milesimas < 10 ) mil = "00" + milesimas;
				else if( milesimas < 100 ) mil = "0" + milesimas;
				else mil = milesimas.toString();

				//Colocamos en la etiqueta la informacion
				tiempo.setText( min + ":" + seg + ":" + mil );
			}
		}
		tiempo.setText( min + ":" + seg + ":" + mil );

	}catch(Exception e){System.out.println("Error al correr metodo run");}
	//Cuando se reincie se coloca nuevamente en 00:00:000
	tiempo.setText( "00:00:000" );
}

//Oyentes de botones
public void actionPerformed( ActionEvent evt ) {
	Object o = evt.getSource();
	if( o instanceof JButton ) {
		JButton btn = (JButton)o;
		if( btn.getText().equals("Iniciar") ){
			iniciarCronometro();
		}
		if( btn.getText().equals("Reiniciar") ) {
			reiniciarCronometro();
		}
		if( btn.getText().equals("Detener") ) {
			pararCronometro();
		}
	}
}

//Iniciar el cronometro poniendo cronometroActivo en verdadero para que entre en el while
public void iniciarCronometro() {
	//este if es para que cuando se vuelva oprimir iniciar no llame a otro hilo e impida que se vea como si estuviera en error.
	if (iniciado) {
		hilo = new Thread( this ); 	
		cronometroActivo = true;
		pausar = false;       
		hilo.start();
		iniciado = false;
	}else {
	}        
}

//Detener el cronometro
public void pararCronometro(){
	pausar = true ;
	
	iniciado = true;
}

public void reiniciarCronometro(){
	cronometroActivo = false;
	iniciado = true;
	tiempo.setText( "00:00:000" );
	}

public String getTime() {
	pararCronometro();
	return tiempo.getText();
}

}