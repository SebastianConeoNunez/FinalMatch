package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class PrimaryInterface extends JFrame {

    private JButton Jugar;
    private JButton ID;
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

    public  PrimaryInterface () {
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
        RankingSemanal= new JLabel("Ranking Semanal");
        RankingSemanal.setBounds(440,320,100,20);

        label.setLayout(null);
        setTitle("Interfaz Primaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


        Jugar = new JButton("Nuevo Jugador");
        ID= new JButton("Tengo un ID");


        Jugar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("¡Se hizo clic en el botón!");
                        new NewPlayerFirst();
                        dispose();
                    }
                }
                );
        ID.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ID) {
                        System.out.println("¡Se hizo clic en el botón ID");
                        new IDlogin();
                        dispose();

                    }
                }

        );

        RankingGlobal.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e1) {
                System.out.println("¡Se hizo clic en el texto!");
                new MostrarPuntaje();

                dispose();


            }
        });
        RankingMensual.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e2) {
                System.out.println("¡Se hizo clic en el texto Mensual!");

            }
        });
        RankingSemanal.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e3) {
                System.out.println("¡Se hizo clic en el texto Semanal!");
            }
        });

        label.add(Jugar);
        label.add(ID);
        label.add(RankingGlobal);
        label.add(RankingMensual);
        label.add(RankingSemanal);


        botonX= 150;
        botonY = 200;
        BotonID_X=350;
        BotonID_Y= botonY;


        Jugar.setBounds(botonX, botonY, Jugar.getPreferredSize().width, 38);
        ID.setBounds(BotonID_X, BotonID_Y, ID.getPreferredSize().width, 38);

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





