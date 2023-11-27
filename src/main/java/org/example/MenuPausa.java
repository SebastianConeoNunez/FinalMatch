package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class MenuPausa extends JFrame {

    private Play instanciaPlay;

    private JButton VolverJuego;
    private JButton Salir;
    private JButton RecordarId;

    private ImageIcon wallpapper;
    private ImageIcon Logo;
    private JLabel label;
    private JLabel logo;
    private JLabel RankingGlobal;
    private JLabel RankingMensual;
    private JLabel RankingSemanal;

    int botonX; //posicion boton en x
    int botonY;// posicion boton en y
    int BotonID_X;
    int BotonID_Y;

    int mouseX;
    int mouseY;

    public MenuPausa (String nombre, int ID) {
        setLayout(new BorderLayout()); //cogeTodoElcomponente
        wallpapper= new ImageIcon("C:\\Users\\chevi\\Downloads\\Fondo-Futbol.jpg");
        Logo = new ImageIcon("C:\\Users\\chevi\\Downloads\\LogoFutbol.png");
        label = new JLabel(wallpapper);
        logo= new JLabel(Logo);
        label.add(logo);
        logo.setBounds(-50, 10, Logo.getIconWidth(), Logo.getIconHeight());

        add(label);
        setSize(wallpapper.getIconWidth(), wallpapper.getIconHeight());

        RankingGlobal= new JLabel("Ranking Global");
        RankingGlobal.setBounds(100,320,100,20);
        RankingMensual= new JLabel("Ranking Mensual");
        RankingMensual.setBounds(270,320,100,20);
        RankingSemanal= new JLabel("Ranking Diario");
        RankingSemanal.setBounds(440,320,100,20);

        label.setLayout(null);
        setTitle("Pausa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


        VolverJuego = new JButton("Reiniciar partida");
        Salir= new JButton("Salir del juego");
        RecordarId= new JButton("Recordar ID");


        VolverJuego.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Reiniciar partida");
                        new Play(nombre,ID);
                        dispose();

                    }
                }
        );
        Salir.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ID) {
                        System.out.println("¡Se hizo clic en el botón ID");
                        if (instanciaPlay != null) {
                            instanciaPlay.cerrarJuego();
                        }

                        dispose();
                        new PrimaryInterface();



                    }
                }

        );

        RankingGlobal.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e1) {
                System.out.println("¡Se hizo clic en el texto!");
                new MostrarPuntaje();



            }
        });
        RankingMensual.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e2) {
                System.out.println("¡Se hizo clic en el texto Mensual!");
                new MostrarRankingMensual();

            }
        });
        RankingSemanal.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e3) {
                System.out.println("¡Se hizo clic en el texto Diario!");
                new MostrarRankingDiario();
            }
        });

        RecordarId.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent eid) {
                System.out.println("¡Se hizo clic en RECORDAR ID!");
                JOptionPane.showMessageDialog(label, "Que paso pibe, aqui esta tu ID campeon : "+ ID +"\n" +
                        "-Messi");

            }
        });

        label.add(VolverJuego);
        label.add(Salir);
        label.add(RecordarId);
        label.add(RankingGlobal);
        label.add(RankingMensual);
        label.add(RankingSemanal);


        botonX= 150;
        botonY = 200;
        BotonID_X=350;
        BotonID_Y= botonY;


        VolverJuego.setBounds(botonX, botonY, VolverJuego.getPreferredSize().width, 38);
        Salir.setBounds(BotonID_X, BotonID_Y, Salir.getPreferredSize().width, 38);
        RecordarId.setBounds(260,250 , RecordarId.getPreferredSize().width, 38);

        addMouseListener(new MyMouseListener());

    }

    private class MyMouseListener implements MouseListener { //se implementa de la interfaz MouseListener con sus metodos

        @Override
        public void mouseClicked(MouseEvent evt) { // se le pasa un objeto tipo MouseEvent que contiene la info
            mouseX= evt.getX() ; // obtiene la coordenada X y la convierte en String
            mouseY= evt.getY() ; //lo mismo
            System.out.println("X: " + mouseX + " Y: " + mouseY);

        }

        // No se usan pero deben ser escritos
        @Override public void mousePressed(MouseEvent evt) { }
        @Override public void mouseReleased(MouseEvent evt) { }
        @Override public void mouseEntered(MouseEvent evt) { }
        @Override public void mouseExited(MouseEvent evt) { }
    }
}


