package vista;

import java.util.Calendar;

import javax.swing.JTextField;

public class Reloj extends JTextField implements Runnable {
	private int hora;
	private int minutos;
	private int segundos;
	private String horaActualizable;
	private Thread h1;

	public Reloj() {
		this.setEditable(false);
		this.inicializarHora();
	}

	private void inicializarHora() {
		h1 = new Thread(this);
		Calendar horaA = Calendar.getInstance();
		hora = horaA.get(Calendar.HOUR_OF_DAY);
		minutos = horaA.get(Calendar.MINUTE);
		segundos = horaA.get(Calendar.SECOND);
		horaActualizable = hora + ":" + minutos + ":" + segundos;
		h1.start();
	}

	@Override
	public void run() {
		Thread cThread = Thread.currentThread();
		while (cThread == h1) {
			this.setAlignmentX(20);
			this.setText(horaActualizable);
			calcula();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void calcula() {
		Calendar horaA = Calendar.getInstance();
		hora = horaA.get(Calendar.HOUR_OF_DAY);
		minutos = horaA.get(Calendar.MINUTE);
		segundos = horaA.get(Calendar.SECOND);
		horaActualizable = hora + ":" + minutos + ":" + segundos;
	}

}
