package GUI;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Entidades.Caramelo;
import Entidades.Entidad;
import Entidades.EntidadLogica;
import Entidades.Glaseado;
import Logica.Juego;
import Logica.Nivel;
import Logica.Tiempo;
import temas.Tema;
import ManejadorAnimaciones.CentralAnimaciones;


public class Ventana extends JFrame implements VentanaAnimable, VentanaNotificable {
	
	protected Juego mi_juego;
	protected Nivel mi_nivel;
	protected Tiempo mi_tiempo;
	protected Tema mi_tema;
	protected CentralAnimaciones central_animaciones;

	protected int filas;
	protected int columnas;
	protected int animaciones_pendientes;
	protected int total_caramelos_objetivo;
	protected int total_entidades_objetivo_especial;
	protected int color;
	protected int corazones;
	protected int movimientos;
	protected int caramelo_obj;
	protected int size_label = 90;
	
	
	protected boolean obj1;
	protected boolean obj2;
	protected boolean iniciador_tiempo=true;
	public boolean bloquear_teclado;
	

	protected JPanel panel_principal = new JPanel();
	protected JPanel panel_fondo;
	protected JPanel panel_objetivos;
	protected JPanel panel_puntaje;
	protected JPanel panel_reglas;

	protected JLayeredPane layeredPane2= new JLayeredPane();
	
	protected ImageIcon fondo,vida;
    
	protected JLabel label;
	protected JLabel lbl_puntaje;
	protected JLabel lbl_movimientos;
	protected JLabel lbl_obj1;
	protected JLabel lbl_obj2;
	protected JLabel lbl_reglas;

	

	public Ventana (Juego j, Nivel nivel, Tema tema) {
		mi_juego = j;
		mi_nivel=nivel;
		mi_tema = tema;
		
		filas = mi_juego.get_filas();
		columnas = mi_juego.get_columnas();
		corazones = mi_juego.get_vidas();
		animaciones_pendientes = 0;
		bloquear_teclado=false;
		
		this.central_animaciones = new CentralAnimaciones(this);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				cierreDeJugo();
			}
		});
		
		configurarObjetivos();
		
	} 

	private void cierreDeJugo(){
		mi_juego.dejarDeContarPuntaje();
	}


	//getters
	
	public int get_size_label() {
		return size_label;
	}

	public void inicializar() {
		if(corazones==0){
			mi_juego.dejarDeContarPuntaje();
			mostrarMensajeFinDelJuego();
		}

		definirFrame();
		establecerImagenDeFondo();
		definirPanelFondo();
		definirPanelTablero();
		definirPanelObjetivos();
		definirPanelPuntaje();
		definirPanelReglas();
		cargarPanelReglas();
		cargarPanelObjetivos();
		cargarPanelFondo();
		cargarFrame();

		  KeyListener miOyenteDeTeclado =   (new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {	
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT: 	{ if (!bloquear_teclado) mi_juego.mover_jugador(Juego.IZQUIERDA); break; }
					case KeyEvent.VK_RIGHT: { if (!bloquear_teclado) mi_juego.mover_jugador(Juego.DERECHA); break; }
					case KeyEvent.VK_UP: 	{ if (!bloquear_teclado) mi_juego.mover_jugador(Juego.ARRIBA);break; }
					case KeyEvent.VK_DOWN: 	{ if (!bloquear_teclado) mi_juego.mover_jugador(Juego.ABAJO); break; }
					case KeyEvent.VK_W:		{ if (!bloquear_teclado) mi_juego.intercambiar_entidades(Juego.ARRIBA); break; }
					case KeyEvent.VK_S:		{ if (!bloquear_teclado) mi_juego.intercambiar_entidades(Juego.ABAJO); break; }
					case KeyEvent.VK_A:		{ if (!bloquear_teclado) mi_juego.intercambiar_entidades(Juego.IZQUIERDA); break; }
					case KeyEvent.VK_D:		{ if (!bloquear_teclado) mi_juego.intercambiar_entidades(Juego.DERECHA); break; } 
				}
			}
		});
		panel_principal.addKeyListener(miOyenteDeTeclado);
		panel_principal.setFocusable(true);
	}


	public void configurarObjetivos(){
		//obtiene la cantidad de caramelos que el jugador debe detonar para pasar de nivel
		total_caramelos_objetivo=mi_nivel.get_total_caramelos_objetivo();

		//puede que no tenga que explotar caramelos, asi que verifica que la cantidad sea distinta de 0
		if(total_caramelos_objetivo!=0){
			
			color = mi_nivel.get_color_obj();
			char color = mi_nivel.get_color_obj();

			switch(color) {
				case 'G': {
					caramelo_obj = 1;
					break;
				} 
				case 'O': {
					caramelo_obj = 2;
					break;
				} 
				case 'Y': {
					caramelo_obj = 3;
					break;
				} 
				case 'R': {
					caramelo_obj = 4;
					break;
				} 
				case 'B': {
					caramelo_obj = 5;
					break;
				} 
				case 'P': {
					caramelo_obj = 6;
					break;
				} 
			}
		}

		total_entidades_objetivo_especial = mi_nivel.get_total_entidad();

		mi_tiempo = mi_nivel.getTiempo();
		if( mi_tiempo.getTiempoRestante()==0){
			iniciador_tiempo = false;
		}

		movimientos = mi_nivel.getMovimientos();
	} 



	//Definicion componentes graficos
	
	public void definirFrame(){
		setResizable(true);	//TODO: cambiar despues de usar el debugger
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
	}
	
	public void establecerImagenDeFondo(){
		fondo = new ImageIcon(this.getClass().getResource(mi_tema.get_path_fondo()));
		label = new JLabel(fondo);
		label.setOpaque(true);
		label.setBounds(0,0,getWidth(), getHeight());
	}

	public void definirPanelFondo(){
		panel_fondo = new JPanel();
        panel_fondo.setLayout(new BorderLayout());
		panel_fondo.setOpaque(true);
		panel_fondo.setBounds(350,0,getWidth()/2, getHeight());
        panel_fondo.setBackground(new Color(20, 20, 20, 100)); 
		Border roundedBorder = new LineBorder(new Color(255, 255, 255, 20), 4, true);
		Border shadowBorder = new EmptyBorder(5, 8, 5, 5);
		Border compoundBorder = new CompoundBorder(roundedBorder, shadowBorder);
		panel_fondo.setBorder(compoundBorder);
	}

	public void definirPanelTablero(){
        panel_principal.setBounds(40,150,size_label * filas, size_label * columnas);
		panel_principal.setLayout(null);
        panel_principal.setBackground(Color.WHITE);
	}

	public void definirPanelPuntaje(){
		panel_puntaje = new JPanel();
        panel_puntaje.setBounds(0,0,250,25);
		lbl_puntaje = new JLabel();
		lbl_puntaje.setText("Vas 0 puntos");
		panel_puntaje.add(lbl_puntaje);
	}

	public void definirPanelReglas(){
		panel_reglas=new JPanel();
		panel_reglas.setBounds(1100,0,250,50);
		panel_reglas.setLayout(new BoxLayout(panel_reglas, BoxLayout.Y_AXIS));
		
		JLabel titulo_panel_reglas= new JLabel();
		titulo_panel_reglas.setText("¡REGLAS ACTIVADAS!");
		panel_reglas.add(titulo_panel_reglas);
	}

	public void definirPanelObjetivos(){
		panel_objetivos = new JPanel();
        panel_objetivos.setBounds(12,-5,panel_fondo.getWidth()-50, 150);
		panel_objetivos.setLayout(new GridLayout(2,1));
        panel_objetivos.setBackground(new Color(255, 255, 255, 128));
	}

	public void cargarPanelReglas(){
		List<String> lista_de_reglas= mi_nivel.get_reglas();
		for(String s: lista_de_reglas){
			JLabel regla_activada = new JLabel();
			regla_activada.setText(s);
			panel_reglas.add(regla_activada);
		}
		
	}

	//Cargar componentes graficos

	public void cargarPanelFondo(){
		JLayeredPane layeredPane2 = new JLayeredPane();
		layeredPane2.setBounds(350,0,getWidth()/2, getHeight());
		layeredPane2.add(panel_objetivos, Integer.valueOf(0));
		layeredPane2.add(panel_principal,Integer.valueOf(1));
		panel_fondo.add(layeredPane2,BorderLayout.CENTER);
	}

	public void cargarFrame(){
		JLayeredPane layeredPane1 = new JLayeredPane();
		layeredPane1.setBounds(0, 0, getWidth(), getHeight());
		layeredPane1.add(label, JLayeredPane.DEFAULT_LAYER);
		layeredPane1.add(panel_fondo, JLayeredPane.PALETTE_LAYER);
		add(panel_puntaje);
		add(panel_reglas);
		add(layeredPane1);
	}

	

	public void cargarPanelObjetivos(){
		JPanel panelSuperior = new JPanel(new GridLayout(1,3));
		JPanel panelInferior = new JPanel(new GridLayout(1,3));

		//Vida
		//-- crea un panelVidas
		JPanel panelVida = new JPanel(new BorderLayout());
		panelVida.setBorder(new RoundBorder(10));
		//--carga su correspondiente imagen
		vida = new ImageIcon(this.getClass().getResource(mi_tema.get_path_icono_vidas(corazones)));
		Image imgEscalada = vida.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		Icon iconoEscalado = new ImageIcon(imgEscalada);
		//--crea la label donde se colocara la imagen
		JLabel vidas= new JLabel();
		//--carga y define propiedades de la label
		vidas.setIcon(iconoEscalado);
		//-- carga los elementos a su panel
		panelVida.add(vidas, BorderLayout.CENTER);
		//fin panel vidas

		//Movimientos
		//-- crea un panelMovimientos
		JPanel panelMovimientos = new JPanel(new BorderLayout());
		panelMovimientos.setBorder(new RoundBorder(10));
		//-- crea y configura una label que contendra la cantidad de movimientos disponibles
		lbl_movimientos= new JLabel();
		lbl_movimientos.setBounds(50,50,3,3);
		lbl_movimientos.setText("Movimientos:" +movimientos);
		//-- carga los elementos a su panel
		panelMovimientos.add(lbl_movimientos, BorderLayout.CENTER);
		//fin panelMovimientos

		//Temporizador
		//-- configura propiedades del temporizador
		JPanel panelTemporizador = new JPanel(new BorderLayout());
		panelTemporizador.setBorder(new RoundBorder(10));
		if(iniciador_tiempo!=false){
			mi_tiempo.setBounds(30,20,10,3);
			mi_tiempo.iniciarTiempo();
			panelTemporizador.add(mi_tiempo);
		}
		//fin panel temporizador
	
		//Objetivo de tipo caramelo
		JPanel panelObjetivoCaramelo = new JPanel(new BorderLayout());
		panelObjetivoCaramelo.setBorder(new RoundBorder(10));
		if(total_caramelos_objetivo!=0){
			//-- busca y configura la imagen del caramelo que corresponda
			Entidad e = new Caramelo(mi_juego.getTablero(),-1, -1, caramelo_obj);
			ImageIcon caramelo = new ImageIcon(this.getClass().getResource(mi_tema.get_path_imagen(e)));
			Image img = caramelo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			Icon icono = new ImageIcon(img);
			//-- crea una label para cargar la imagen y la configura
			lbl_obj1= new JLabel();
			lbl_obj1.setBounds(50,30,150,150);
			lbl_obj1.setIcon(icono);
			lbl_obj1.setText("Restantes:" + total_caramelos_objetivo);
			//agrega los elementos a su panel
			panelObjetivoCaramelo.add(lbl_obj1);
		}
		//fin panel objetivo caramelo

		//Objetivo de tipo glaseado/gelatina
		JPanel panelObjetivoEspecial = new JPanel(new BorderLayout());
		panelObjetivoEspecial.setBorder(new RoundBorder(10));
		if(total_entidades_objetivo_especial!=0){
			//-- busca y configura la imagen del caramelo que corresponda
			Entidad e = new Glaseado(mi_juego.getTablero(),-1, -1, Entidades.Color.BLANCO);
			ImageIcon caramelo = new ImageIcon(this.getClass().getResource(mi_tema.get_path_imagen(e)));
			Image img = caramelo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			Icon icono = new ImageIcon(img);
			//-- crea una label para cargar la imagen y la configura
			lbl_obj2= new JLabel();
			lbl_obj2.setBounds(50,30,150,150);
			lbl_obj2.setIcon(icono);
			lbl_obj2.setText("Glaseados restantes:" + total_entidades_objetivo_especial);
			//agrega los elementos a su panel
			panelObjetivoEspecial.add(lbl_obj2);
		}
		//fin panel objetivo especial

		//--agrega a los paneles superior e inferior los elementos correspondiente
		panelSuperior.add(panelVida);
		panelSuperior.add(panelMovimientos);
		panelSuperior.add(panelTemporizador);
		
		panelInferior.add(panelObjetivoCaramelo);
		panelInferior.add(panelObjetivoEspecial);

		//-- carga los paneles superior e inferior a panel objetivos
		panel_objetivos.add(panelSuperior);
		panel_objetivos.add(panelInferior);
	}

	//Control objetivos
	//-- caramelos_detonados son caramelos comunes, entidades_detonadas se refiere a glaseados
	public void actualizaObjetivos(HashMap<Integer,Integer> entidades_detonadas){

		for(Integer color: entidades_detonadas.keySet()){
			if(caramelo_obj==color){
				if(total_caramelos_objetivo!=0){
					total_caramelos_objetivo=total_caramelos_objetivo-entidades_detonadas.get(color);
					lbl_obj1.setText("Caramelos restantes:" + total_caramelos_objetivo);
				}
			}
			if(color==Integer.valueOf(8)){
				if(total_entidades_objetivo_especial!=0){	
					total_entidades_objetivo_especial=total_entidades_objetivo_especial-entidades_detonadas.get(color);
					if(lbl_obj2!=null){
						lbl_obj2.setText("Glaseados restantes:" + total_entidades_objetivo_especial);
				}
				}
			}
		}
		if (total_caramelos_objetivo <= 0 && total_entidades_objetivo_especial<=0) {
				transicionar();
			}
	}

	public void actualizarPuntaje(int puntaje){
		lbl_puntaje.setText("Vas "+puntaje+ " puntos. ¡Muy bien! ;)");
	}

	public void notificar_tiempo_agotado(){
		ocultar();
		mostrarMensajeDeAviso("tiempo");
	}

	public void restar_movimiento(){
		if(movimientos!=0){
			movimientos--;
			lbl_movimientos.setText("Movimientos: " + movimientos);
		}
		if(movimientos==0){
			ocultar();
			mostrarMensajeDeAviso("movimientos");
		}
	}


	//Agregar entidades graficas 
	public EntidadGrafica agregar_entidad(EntidadLogica e) {
		Celda celda = new Celda(this, e, size_label, mi_tema, e.get_visibilidad());
		panel_principal.add(celda);
		return celda;
	}
	
	//Notificaciones
	
	public void notificarse_animacion_en_progreso() {
		synchronized(this){
			animaciones_pendientes ++;
			bloquear_teclado = true;
		}
	}
	
	public void notificarse_animacion_finalizada() {
		synchronized(this){
			animaciones_pendientes --;
			bloquear_teclado = animaciones_pendientes > 0;
		}
	}


	public void ocultar() {
		this.setVisible(false);
	}

	
	public void reiniciar() {
		mi_juego.resetear_nivel();
	}


	public void transicionar() {
		JOptionPane.showMessageDialog(null,"¡Felicitaciones! Pasaste de nivel :D");
		mi_juego.siguienteNivel();
	}


	public void animar_intercambio(Celda celda) {
		central_animaciones.animar_intercambio(celda);
	}
	
	public void animar_cambio_foco(Celda celda) {
		central_animaciones.animar_cambio_foco(celda);
	}
	
	public void animar_detonacion(Celda celda) {
		central_animaciones.animar_detonacion(celda);
	}
	
	public void animar_caida(Celda celda) {
		central_animaciones.animar_caida(celda);
	}
	
	public void animar_cambio_visibilidad(Celda celda) {
		central_animaciones.animar_cambio_visibilidad(celda);
	}

	public void animar_cambio_estado(Celda celda, int color) {
		central_animaciones.animar_cambio_estado_tdp2(celda, color);
	}
	
	public void eliminar_celda(Celda celda) {
		panel_principal.remove(celda);
		panel_principal.repaint();
	}


	public void mostrar() {
		this.setVisible(true);
	}

	protected void mostrarMensajeDeAviso(String mensaje_situacion){
		// Configurar el aspecto de la ventana de diálogo
        UIManager.put("OptionPane.background", Color.white); // Color rosa claro
        //UIManager.put("Panel.background", Color.white); // Color rosa claro
        //UIManager.put("OptionPane.messageForeground", Color.BLACK);

        // Crear el panel personalizado con tonos rosas
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 182, 193)); // Color rosa claro

        // Crear el mensaje y configurar su apariencia
        JLabel mensaje = new JLabel("<html><font color='black'>¡Te quedaste sin "+ mensaje_situacion +"!</font></html>");
        mensaje.setHorizontalAlignment(JLabel.CENTER);
        panel.add(mensaje, BorderLayout.CENTER);

        // Crear botones personalizados
        JButton reintentarButton = new JButton("Reintentar");
        JButton salirButton = new JButton("Salir");

        // Agregar acciones a los botones
        reintentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				reiniciar();
				((JDialog) SwingUtilities.getRoot((Component) e.getSource())).dispose();//Cierra el mensaje
            
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar el programa
				mi_juego.dejarDeContarPuntaje();
                System.exit(0);
            }
        });

        // Agregar botones al panel
        JPanel botonesPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 fila, 2 columnas
        botonesPanel.add(reintentarButton);
        botonesPanel.add(salirButton);
        panel.add(botonesPanel, BorderLayout.SOUTH);

        // Crear el JOptionPane personalizado
        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

        // Configurar el título de la ventana de diálogo
        JDialog dialog = optionPane.createDialog(":( ¡Error!");

        // Mostrar la ventana de diálogo
        dialog.setVisible(true);
	}

	protected void mostrarMensajeFinDelJuego(){
		// Configurar el aspecto de la ventana de diálogo
        UIManager.put("OptionPane.background", new Color(255, 182, 193)); // Color rosa claro
        UIManager.put("Panel.background", new Color(255, 182, 193)); // Color rosa claro
        UIManager.put("OptionPane.messageForeground", Color.BLACK);

        // Crear el panel personalizado con tonos rosas
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 182, 193)); // Color rosa claro

        // Crear el mensaje y configurar su apariencia
        JLabel mensaje = new JLabel("<html><font color='black'>¡Te quedaste sin vidas!</font></html>");
        mensaje.setHorizontalAlignment(JLabel.CENTER);
        panel.add(mensaje, BorderLayout.CENTER);

        // Crear botones personalizados
        JButton salirButton = new JButton("Salir");

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar el programa
                System.exit(0);
            }
        });

        // Agregar botones al panel
        JPanel botonesPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 fila, 2 columnas
        botonesPanel.add(salirButton);
        panel.add(botonesPanel, BorderLayout.SOUTH);

        // Crear el JOptionPane personalizado
        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

        // Configurar el título de la ventana de diálogo
        JDialog dialog = optionPane.createDialog(":( ¡Error!");

        // Mostrar la ventana de diálogo
        dialog.setVisible(true);
	}


	public void resetear(Juego juego, int filas, int columnas) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'resetear'");
	}



	//Clase interna para aplicar borde redondeado a los paneles
	public class RoundBorder extends AbstractBorder {
    	private int radius;

    	public RoundBorder(int radius) {
        	this.radius = radius;
    	}

    	@Override
    	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        	Graphics2D g2d = (Graphics2D) g.create();
        	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        	g2d.setColor(Color.BLACK); // Puedes cambiar el color del borde según tus preferencias
        	g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        	g2d.dispose();
    	}

    	@Override
    	public Insets getBorderInsets(Component c) {
        	return new Insets(radius, radius, radius, radius);
    	}

    	@Override
    	public Insets getBorderInsets(Component c, Insets insets) {
        	insets.left = insets.top = insets.right = insets.bottom = radius;
        	return insets;
   		 
		}
	}

	static class TranslucentPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Configurar la transparencia
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f)); // Ajusta el valor de transparencia (0.0f a 1.0f)
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
    }


} //Fin clase Ventana
