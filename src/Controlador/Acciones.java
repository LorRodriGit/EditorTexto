package Controlador;

import java.awt.Color;
import java.awt.Event;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.StatementEventListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Caret;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.undo.UndoManager;

import ZoeloeSoft.projects.JFontChooser.JFontChooser;

public class Acciones extends AbstractAction {
	private Controlador controlador;
	private String nombre;
	private char c;
	private ImageIcon imagen;
	private HashMap acciones;
	private JFileChooser selecctorFicheros = new JFileChooser();
	private File archivo = null;
	private FileWriter mlFileWriter = null;
	private PrintWriter pr = null;
	private String texto;
	JColorChooser selectorColor=new JColorChooser();


	public Acciones(Controlador controlador, String nombre, char c, ImageIcon imagen) {
		super();
		this.controlador = controlador;
		this.nombre = nombre;
		this.c = c;
		this.imagen = imagen;
		iniciarAcciones();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (nombre) {
		case "Abrir":
			abrirArchivo();
			break;
		case "Guardar":
			guardarArchivo();
			break;
		case "Nuevo":
			archivoNuevo();
			break;
		case "Fuente":
			seleccionarFuente();
			break;
		case "Color Fuente":
			seleccionarColorFuente();
			break;
		case "Color Fondo":
			seleccionaColorFondo();
			break;
		case "Buscar":
			realizarBusqueda();
			break;
		case "Salir":
			salir();
		break;
		
		case "Deshacer":
			desHacerReahcer();
			break;
		case "Rehacer":
			desHacerReahcer();
			break;
		}
	}

	private void desHacerReahcer() {
		final UndoManager undoManager=new UndoManager();
		Document documento=controlador.miVentana.getAreaDetexto().getDocument();
		
		documento.addUndoableEditListener(new UndoableEditListener() {
			
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				undoManager.addEdit(e.getEdit());
			}
		});
		
		controlador.miVentana.getAreaDetexto().getActionMap().put("Deshacer", new AbstractAction("Deshacer") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(undoManager.canUndo()) {
						undoManager.canUndo();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		controlador.miVentana.getAreaDetexto().getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Deshacer");

		controlador.miVentana.getAreaDetexto().getActionMap().put("Reshacer", new AbstractAction("Reshacer") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(undoManager.canRedo()) {
						undoManager.canRedo();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		controlador.miVentana.getAreaDetexto().getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Rehacer");
	}

	private void salir() {

		int Opcion = JOptionPane.showConfirmDialog(null, "¿Desea salir de la aplicacion?");
        
        switch (Opcion) {
            case JOptionPane.YES_OPTION:
                System.exit(0);
                break;
            case JOptionPane.NO_OPTION:
                return;
        }
	}

	private void realizarBusqueda() {
		//***Intento 1
//		JTextArea auxArea=null;
//		//auxArea=controlador.miVentana.getAreaDetexto();
//		String textoABuscar=JOptionPane.showInputDialog(controlador.miVentana.getAreaDetexto(), "Texto a buscar");
//		String textoTotal=controlador.miVentana.getAreaDetexto().getText();
//		System.out.println(textoABuscar);
//		int posicion=textoTotal.indexOf(textoABuscar);
//		System.out.println(posicion);
//		//String textoOriginal=controlador.miVentana.getAreaDetexto().getSelectedText();
//		
//			Caret seleccion=controlador.miVentana.getAreaDetexto().getCaret();
//			
//			int posicionInicial = 0;
//			if (seleccion.getDot() == seleccion.getMark())
//			{
//			   System.out.println("no existe");
//			} else {
//				posicionInicial=seleccion.getDot();
//			}
//			textoTotal=controlador.miVentana.getAreaDetexto().getText();
//			posicion=textoTotal.indexOf(textoABuscar,posicionInicial);
//			controlador.miVentana.getAreaDetexto().setCaretPosition(posicionInicial);
//			controlador.miVentana.getAreaDetexto().moveCaretPosition(posicionInicial+textoABuscar.length());
		
		//**Intento 2
		Highlighter highlighter=controlador.miVentana.getAreaDetexto().getHighlighter();
		highlighter.removeAllHighlights();
		String textoTotal=controlador.miVentana.getAreaDetexto().getText();
		String textoABuscar=JOptionPane.showInputDialog(controlador.miVentana.getAreaDetexto(), "Texto a buscar");
		for (int i = 0; i < textoTotal.length(); i++) {
		char c=textoTotal.charAt(i);
		if (textoABuscar.indexOf(c)>=0) {
			try {
				highlighter.addHighlight(i, i+1, DefaultHighlighter.DefaultPainter);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		}
		//**Intento 3
//		DefaultHighlighter.DefaultHighlightPainter miHighlightPainter=new DefaultHighlightPainter(Color.YELLOW);
//		String textoABuscar=JOptionPane.showInputDialog(controlador.miVentana.getAreaDetexto(), "Texto a buscar");
//		
//		try {
//			
//			Highlighter highlighter=controlador.miVentana.getAreaDetexto().getHighlighter();
//			Document document=controlador.miVentana.getAreaDetexto().getDocument();
//			String textoTotal;
//			textoTotal = document.getText(0, document.getLength());
//			int pos=0;
//			while ((pos=textoTotal.toUpperCase().indexOf(textoABuscar.toUpperCase().length(),pos))>=0) {
//				highlighter.addHighlight(pos, pos+textoABuscar.length(), miHighlightPainter);
//				pos+=textoABuscar.length();
//			}
//			
//		} catch (BadLocationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
	}

	private void seleccionaColorFondo() {
		controlador.miVentana.getAreaDetexto()
		.setBackground(selectorColor.showDialog(controlador.miVentana.getAreaDetexto(), "Selecciona color de la fuente", Color.BLACK));

	}

	private void seleccionarColorFuente() {
		controlador.miVentana.getAreaDetexto()
		.setForeground(selectorColor.showDialog(controlador.miVentana.getAreaDetexto(), "Selecciona color de la fuente", Color.BLACK));
	}

	private void seleccionarFuente() {
		 JFontChooser seleccionarFuente=new JFontChooser(controlador.miVentana);
		 int op=seleccionarFuente.showDialog();
		 if (op==JFontChooser.OK_OPTION) {
			controlador.miVentana.getAreaDetexto().setFont(seleccionarFuente.getFont());
		}
	}

	private void archivoNuevo() {
		controlador.miVentana.getAreaDetexto().setText("");
	}

	private void guardarArchivo() {

		int op = selecctorFicheros.showSaveDialog(controlador.miVentana);

		if (op == JFileChooser.APPROVE_OPTION) {

			archivo = selecctorFicheros.getSelectedFile();
			System.out.println(selecctorFicheros.getSelectedFile());
		}
		almacenarArchivo();

	}

	private void almacenarArchivo() {

		try {

			mlFileWriter = new FileWriter(archivo);
			pr = new PrintWriter(mlFileWriter);

			texto = controlador.miVentana.getAreaDetexto().getText();
			mlFileWriter.write(texto);

		} catch (IOException ex) {

			JOptionPane.showMessageDialog(controlador.miVentana, "No se puede guardar");

		} finally {

			if (mlFileWriter != null) {

				try {

					mlFileWriter.close();
					pr.close();

				} catch (IOException ex) {
					JOptionPane.showMessageDialog(controlador.miVentana, "No se puede guardar");
				}
			}
		}
	}

	private void abrirArchivo() {
		int seleccion = selecctorFicheros.showOpenDialog(controlador.miVentana);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File fichero = selecctorFicheros.getSelectedFile();
			try {
				BufferedReader bReader = new BufferedReader(new FileReader(fichero));
				String texto = bReader.toString();
				String texto2 = "";
				while ((texto = bReader.readLine()) != null) {
					texto2 = texto + bReader.readLine();
					controlador.miVentana.getAreaDetexto().setText(texto2);
				}

				bReader.close();
			} catch (Exception e1) {
				JOptionPane.showInternalMessageDialog(controlador.miVentana, "Error de acceso al fichero",
						"Fichero no encontrado", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void iniciarAcciones() {
		this.putValue(Action.NAME, nombre);
		this.putValue(Action.SMALL_ICON,
				new ImageIcon(imagen.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke(c, Event.CTRL_MASK));
	}

}
