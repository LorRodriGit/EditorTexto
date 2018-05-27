package vista;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;

public class Vista extends JFrame {
	private JPanel panelAreaTexto, panelMenu;
	private JTextArea areaDetexto;
	private JScrollPane bar;
	private JMenuBar barraMenu;
	private JMenu menuArchivo, menuEditar, menuFormato, menuColor;
	private JMenuItem itemcopiar, itemcortar, itempegar, itembuscar, itemabrir, itemguardar, itnuevo, itFuente,
			itColorTexto, itColorFondo, itSalir;
	private JToolBar barraHerramientas;
	JButton btCopiar, btCortar, btPegar, btBuscar, btAbrir, btnuevo, btGuardar, btfuente, btColorFondo, btColorFuente,
			btUndo, btRedo;
	private Reloj reloj = new Reloj();

	public Vista() {
		construuirAreaDeTexto();
		construirBarraMenu();
		contruirVentanaPrincipal();
		ActionMap acciones = areaDetexto.getActionMap();

	}

	private void construirBarraMenu() {
		ActionMap acciones = areaDetexto.getActionMap();
		// Copiar
		Action accioncopy = acciones.get(DefaultEditorKit.copyAction);
		ImageIcon imgCopiar = new ImageIcon("src/iconos/copiar.png");
		accioncopy.putValue(Action.NAME, "Copiar");
		accioncopy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('C', Event.CTRL_MASK));
		accioncopy.putValue(Action.SMALL_ICON,
				new ImageIcon(imgCopiar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));

		
		Action accionCut = acciones.get(DefaultEditorKit.cutAction);
		ImageIcon imgCortar = new ImageIcon("src/iconos/cortar.png");
		accionCut.putValue(Action.NAME, "Cortar");
		accionCut.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('X', Event.CTRL_MASK));
		accionCut.putValue(Action.SMALL_ICON,
				new ImageIcon(imgCortar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		// Pegar
		ImageIcon imgPegar = new ImageIcon("src/iconos/pegar.png");
		Action accionesPaste = acciones.get(DefaultEditorKit.pasteAction);
		accionesPaste.putValue(Action.NAME, "Pegar");
		accionesPaste.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('V', Event.CTRL_MASK));
		accionesPaste.putValue(Action.SMALL_ICON,
				new ImageIcon(imgPegar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));

		panelMenu = new JPanel();
		panelMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
		barraMenu = new JMenuBar();
		menuArchivo = new JMenu("Archivo");
		menuArchivo.setMnemonic('A');
		menuEditar = new JMenu("Editar");
		menuEditar.setMnemonic('E');
		menuFormato = new JMenu("Formato");
		menuFormato.setMnemonic('F');
		itnuevo = new JMenuItem("Nuevo");
		menuArchivo.add(itnuevo);
		itemabrir = new JMenuItem("Abrir");
		menuArchivo.add(itemabrir);
		itemguardar = new JMenuItem("Guardar");
		menuArchivo.add("Guardar");
		itSalir = new JMenuItem("Salir");
		menuArchivo.add(new JSeparator());
		menuArchivo.add(itSalir);

		itemcopiar = new JMenuItem(accioncopy);
		menuEditar.add(itemcopiar);

		itemcortar = new JMenuItem(accionCut);
		menuEditar.add(itemcortar);
		itempegar = new JMenuItem(accionesPaste);
		menuEditar.add(itempegar);
		menuEditar.add(new JSeparator());
		itembuscar = new JMenuItem("Buscar");
		menuEditar.add(itembuscar);
		itFuente = new JMenuItem("Fuente");
		menuFormato.add(itFuente);
		menuColor = new JMenu("Color");
		menuFormato.add(menuColor);
		itColorTexto = new JMenuItem("Texto");
		itColorFondo = new JMenuItem("Fondo");
		menuColor.add(itColorTexto);
		menuColor.add(itColorFondo);

		barraMenu.add(menuArchivo);
		barraMenu.add(menuEditar);
		barraMenu.add(menuFormato);
		panelMenu.add(barraMenu);
	}

	private void construuirAreaDeTexto() {
		crearpanelAreaTexto();
		crearBarraHerramientas();

		areaDetexto.setLineWrap(true);
		areaDetexto.setWrapStyleWord(true);
		panelAreaTexto.add(barraHerramientas, BorderLayout.NORTH);
		panelAreaTexto.add(bar, BorderLayout.CENTER);
		panelAreaTexto.add(reloj, BorderLayout.SOUTH);
	}

	private void crearBarraHerramientas() {
		ActionMap acciones = areaDetexto.getActionMap();
		// Copiar
		Action accioncopy = acciones.get(DefaultEditorKit.copyAction);
		ImageIcon imgCopiar = new ImageIcon("src/iconos/copiar.png");
		accioncopy.putValue(Action.NAME, "Copiar");
		accioncopy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('C', Event.CTRL_MASK));
		accioncopy.putValue(Action.SMALL_ICON,
				new ImageIcon(imgCopiar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));

		// accioncopy.putValue (Action.SMALL_ICON, new
		// ImageIcon("src/iconos/copiar.png"));
		// Cortar
		Action accionCut = acciones.get(DefaultEditorKit.cutAction);
		ImageIcon imgCortar = new ImageIcon("src/iconos/cortar.png");
		accionCut.putValue(Action.NAME, "Cortar");
		accionCut.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('X', Event.CTRL_MASK));
		accionCut.putValue(Action.SMALL_ICON,
				new ImageIcon(imgCortar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		// Pegar
		ImageIcon imgPegar = new ImageIcon("src/iconos/pegar.png");
		Action accionesPaste = acciones.get(DefaultEditorKit.pasteAction);
		accionesPaste.putValue(Action.NAME, "Pegar");
		accionesPaste.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('V', Event.CTRL_MASK));
		accionesPaste.putValue(Action.SMALL_ICON,
				new ImageIcon(imgPegar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));

		barraHerramientas = new JToolBar();
		btCopiar = new JButton(accioncopy);
		btCortar = new JButton(accionCut);
		btPegar = new JButton(accionesPaste);
		btBuscar = new JButton();
		btAbrir = new JButton();
		btnuevo = new JButton();
		btGuardar = new JButton();
		btfuente = new JButton();
		btColorFondo = new JButton();
		btColorFuente = new JButton();
		btUndo = new JButton();
		btRedo = new JButton();
		barraHerramientas.add(btnuevo);
		barraHerramientas.add(btAbrir);
		barraHerramientas.add(btGuardar);
		barraHerramientas.add(new JSeparator());
		barraHerramientas.add(btCopiar);
		barraHerramientas.add(btCortar);
		barraHerramientas.add(btPegar);
		barraHerramientas.add(btBuscar);
		barraHerramientas.add(new JSeparator());
		barraHerramientas.add(btUndo);
		barraHerramientas.add(btRedo);
		barraHerramientas.add(new JSeparator());
		barraHerramientas.add(btfuente);
		barraHerramientas.add(btColorFuente);
		barraHerramientas.add(btColorFondo);

	}

	private void crearpanelAreaTexto() {
		panelAreaTexto = new JPanel();
		panelAreaTexto.setLayout(new BorderLayout());
		areaDetexto = new JTextArea(25, 80);
		bar = new JScrollPane(areaDetexto);

	}

	private void contruirVentanaPrincipal() {
		this.setVisible(true);
		this.setTitle("Editor de texto");
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(panelMenu, BorderLayout.NORTH);
		this.getContentPane().add(panelAreaTexto, BorderLayout.CENTER);
		pack();
	}

	public JPanel getPanelAreaTexto() {
		return panelAreaTexto;
	}

	public JPanel getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(JPanel panelMenu) {
		this.panelMenu = panelMenu;
	}

	public JTextArea getAreaDetexto() {
		return areaDetexto;
	}

	public void setAreaDetexto(JTextArea areaDetexto) {
		this.areaDetexto = areaDetexto;
	}

	public JScrollPane getBar() {
		return bar;
	}

	public void setBar(JScrollPane bar) {
		this.bar = bar;
	}

	public JMenuBar getBarraMenu() {
		return barraMenu;
	}

	public void setBarraMenu(JMenuBar barraMenu) {
		this.barraMenu = barraMenu;
	}

	public JMenu getMenuArchivo() {
		return menuArchivo;
	}

	public void setMenuArchivo(JMenu menuArchivo) {
		this.menuArchivo = menuArchivo;
	}

	public JMenu getMenuEditar() {
		return menuEditar;
	}

	public void setMenuEditar(JMenu menuEditar) {
		this.menuEditar = menuEditar;
	}

	public JMenuItem getItemcopiar() {
		return itemcopiar;
	}

	public void setItemcopiar(JMenuItem itemcopiar) {
		this.itemcopiar = itemcopiar;
	}

	public JMenuItem getItemcortar() {
		return itemcortar;
	}

	public void setItemcortar(JMenuItem itemcortar) {
		this.itemcortar = itemcortar;
	}

	public JMenuItem getItempegar() {
		return itempegar;
	}

	public void setItempegar(JMenuItem itempegar) {
		this.itempegar = itempegar;
	}

	public JMenuItem getItembuscar() {
		return itembuscar;
	}

	public void setItembuscar(JMenuItem itembuscar) {
		this.itembuscar = itembuscar;
	}

	public JMenuItem getItemabrir() {
		return itemabrir;
	}

	public void setItemabrir(JMenuItem itemabrir) {
		this.itemabrir = itemabrir;
	}

	public JMenuItem getItemguardar() {
		return itemguardar;
	}

	public void setItemguardarcomo(JMenuItem itemguardar) {
		this.itemguardar = itemguardar;
	}

	public JMenuItem getNuevo() {
		return itnuevo;
	}

	public void setNuevo(JMenuItem nuevo) {
		this.itnuevo = nuevo;
	}

	public JToolBar getBarraHerramientas() {
		return barraHerramientas;
	}

	public void setBarraHerramientas(JToolBar barraHerramientas) {
		this.barraHerramientas = barraHerramientas;
	}

	public JButton getBtCopiar() {
		return btCopiar;
	}

	public void setBtCopiar(JButton btCopiar) {
		this.btCopiar = btCopiar;
	}

	public JButton getBtCortar() {
		return btCortar;
	}

	public void setBtCortar(JButton btCortar) {
		this.btCortar = btCortar;
	}

	public JButton getBtPegar() {
		return btPegar;
	}

	public void setBtPegar(JButton btPegar) {
		this.btPegar = btPegar;
	}

	public void setPanelAreaTexto(JPanel panelAreaTexto) {
		this.panelAreaTexto = panelAreaTexto;
	}

	public JButton getBtBuscar() {
		return btBuscar;
	}

	public void setBtBuscar(JButton btBuscar) {
		this.btBuscar = btBuscar;
	}

	public JButton getBtAbrir() {
		return btAbrir;
	}

	public void setBtAbrir(JButton btAbrir) {
		this.btAbrir = btAbrir;
	}

	public JButton getBtnuevo() {
		return btnuevo;
	}

	public void setBtnuevo(JButton btnuevo) {
		this.btnuevo = btnuevo;
	}

	public JButton getBtGuardar() {
		return btGuardar;
	}

	public void setBtGuardar(JButton btGuardar) {
		this.btGuardar = btGuardar;
	}

	public void setItemguardarComo(JMenuItem itemguardarComo) {
		this.itemguardar = itemguardarComo;
	}

	public JButton getBtfuente() {
		return btfuente;
	}

	public void setBtfuente(JButton btfuente) {
		this.btfuente = btfuente;
	}

	public void setItemguardar(JMenuItem itemguardar) {
		this.itemguardar = itemguardar;
	}

	public JMenu getMenuFormato() {
		return menuFormato;
	}

	public void setMenuFormato(JMenu menuFormato) {
		this.menuFormato = menuFormato;
	}

	public JMenu getMenuColor() {
		return menuColor;
	}

	public void setMenuColor(JMenu menuColor) {
		this.menuColor = menuColor;
	}

	public JMenuItem getItnuevo() {
		return itnuevo;
	}

	public void setItnuevo(JMenuItem itnuevo) {
		this.itnuevo = itnuevo;
	}

	public JMenuItem getItFuente() {
		return itFuente;
	}

	public void setItFuente(JMenuItem itFuente) {
		this.itFuente = itFuente;
	}

	public JMenuItem getItColorTexto() {
		return itColorTexto;
	}

	public void setItColorTexto(JMenuItem itColorTexto) {
		this.itColorTexto = itColorTexto;
	}

	public JMenuItem getItColorFondo() {
		return itColorFondo;
	}

	public void setItColorFondo(JMenuItem itColorFondo) {
		this.itColorFondo = itColorFondo;
	}

	public JButton getBtColorFondo() {
		return btColorFondo;
	}

	public void setBtColorFondo(JButton btColorFondo) {
		this.btColorFondo = btColorFondo;
	}

	public JButton getBtColorFuente() {
		return btColorFuente;
	}

	public void setBtColorFuente(JButton btColorFuente) {
		this.btColorFuente = btColorFuente;
	}

	public JMenuItem getItSalir() {
		return itSalir;
	}

	public void setItSalir(JMenuItem itSalir) {
		this.itSalir = itSalir;
	}

	public JButton getBtUndo() {
		return btUndo;
	}

	public void setBtUndo(JButton btUndo) {
		this.btUndo = btUndo;
	}

	public JButton getBtRedo() {
		return btRedo;
	}

	public void setBtRedo(JButton btRedo) {
		this.btRedo = btRedo;
	}

}
