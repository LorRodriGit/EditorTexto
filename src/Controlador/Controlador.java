package Controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.PixelGrabber;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.spi.AbstractInterruptibleChannel;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultEditorKit;

import vista.Vista;

public class Controlador {
	ActionMap acciones;
	Vista miVentana;

	Action accionCortar;

	public Controlador() {
		crearvista();

	}

	private void crearvista() {
		miVentana = new Vista();
		acciones = miVentana.getAreaDetexto().getActionMap();
		aniadirAccionesVista();
	}

	private void aniadirAccionesVista() {

		aniadirFuncionesBarraMenu();
	}

	private void aniadirFuncionesBarraMenu() {
		aniadirFuncionesMenuArchivo();
		aniadirFuncionesMenuEditar();
		aniadirFuncionesFormato();
	}

	private void aniadirFuncionesFormato() {
		fuente();
		colorFondo();
		colorFuente();
	}

	private void aniadirFuncionesMenuEditar() {

		//cortar();
		//copiar();
		//pegar();
		buscar();
		deshacer();
		reHacer();

	}

	private void aniadirFuncionesMenuArchivo() {
		nuevo();
		abrir();
		guardar();
		salir();

	}

	private void reHacer() {
		Action accionRehacer=new Acciones(this, "", 'Y', new ImageIcon("src/iconos/redo.png"));
		miVentana.getBtRedo().setAction(accionRehacer);
	}

	private void deshacer() {
		Action accionDeshacer=new Acciones(this, "", 'Z', new ImageIcon("src/iconos/undo.png"));
		miVentana.getBtUndo().setAction(accionDeshacer);
	}
	private void salir() {
		Action accionSalir = new Acciones(this, "Salir", 'E', new ImageIcon("src/iconos/salir.png"));
		miVentana.getItSalir().setAction(accionSalir);
	}

	private void colorFuente() {
		Action accionColorFuente = new Acciones(this, "Color Fuente", 'T', new ImageIcon("src/iconos/colorTexto.png"));
		miVentana.getBtColorFuente().setAction(accionColorFuente);
		miVentana.getItColorTexto().setAction(accionColorFuente);
	}

	private void colorFondo() {
		Action accionColorFondo = new Acciones(this, "Color Fondo", 'T', new ImageIcon("src/iconos/colorFondo.png"));
		miVentana.getItColorFondo().setAction(accionColorFondo);
		miVentana.getBtColorFondo().setAction(accionColorFondo);
	}

	private void fuente() {
		Action accionFuente = new Acciones(this, "Fuente", 'J', new ImageIcon("src/iconos/fuente.png"));
		miVentana.getBtfuente().setAction(accionFuente);
		miVentana.getItFuente().setAction(accionFuente);
	}

	private void nuevo() {
		Action accionNuevo = new Acciones(this, "Nuevo", 'N', new ImageIcon("src/iconos/nuevo.png"));
		miVentana.getNuevo().setAction(accionNuevo);
		miVentana.getBtnuevo().setAction(accionNuevo);
	}

	private void abrir() {
		Action accionAbrir = new Acciones(this, "Abrir", 'A', new ImageIcon("src/iconos/abrir.png"));
		miVentana.getItemabrir().setAction(accionAbrir);
		miVentana.getBtAbrir().setAction(accionAbrir);

	}

	private void guardar() {

		Action accionGuardar = new Acciones(this, "Guardar", 'G', new ImageIcon("src/iconos/guardar.png"));
		miVentana.getBtGuardar().setAction(accionGuardar);
		miVentana.getItemguardar().setAction(accionGuardar);
	}

	public void cortar() {
		accionCortar = acciones.get(DefaultEditorKit.cutAction);
		accionCortar = new Acciones(this, "Cortar", 'X', new ImageIcon("src/iconos/cortar.png"));
		miVentana.getItemcortar().setAction(accionCortar);
		miVentana.getBtCortar().setAction(accionCortar);
	}

	public void copiar() {

		Action accionCopiar = new Acciones(this, "Copiar", 'C', new ImageIcon("src/iconos/copiar.png"));
		miVentana.getItemcopiar().setAction(accionCopiar);
		miVentana.getBtCopiar().setAction(accionCopiar);
	}

	private void pegar() {
		Action accionpegar = new Acciones(this, "Pegar", 'V', new ImageIcon("src/iconos/pegar.png"));
		miVentana.getItempegar().setAction(accionpegar);
		miVentana.getBtPegar().setAction(accionpegar);
	}

	private void buscar() {
		Action accionBuscar = new Acciones(this, "Buscar", 'F', new ImageIcon("src/iconos/buscar.png"));
		miVentana.getItembuscar().setAction(accionBuscar);
		miVentana.getBtBuscar().setAction(accionBuscar);
	}

}
