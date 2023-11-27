package org.example;
import javax.sound.sampled.*;

import java.util.Random;
import javax.management.relation.RelationNotFoundException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Play extends JFrame  {


    private IDlogin instanciaLogin;




    private ImageIcon wallpapper;
    private JLabel label;

    private int CoordXPatear;
    private int CoordYPatear;

    private int Nivel = 1;
    private int PuntosHechos=0;
    private int Ax;
    private int Ay;
    private int Bx;
    private int By;
    private int Cx;
    private int Cy;
    private int Dx;
    private int Dy;

    private int IDNUMERO;

    private JButton patear;
    private JButton Menu;


    private ImageIcon Balon;
    private ImageIcon ArqueroI;
    private ImageIcon ArqueroC;

    private ImageIcon ArqueroD;

    private ImageIcon ArqueroTI;
    private ImageIcon ArqueroTD;


    private JLabel Balonn;
    private JLabel ArcoI;
    private JLabel ArcoC;
    private JLabel ArcoD;
    private JLabel ArcoIT;
    private JLabel ArcoDT;
    private JLabel ID;
    private JLabel NIVELLABEL;
    private JLabel PuntosLabel;

    private TextField Level;
    private TextField Puntaje;
    private TextField IdJugador;






    public Play(String NombreJugador, int IDD){

        setLayout(new BorderLayout()); //cogeTodoElcomponente
        wallpapper= new ImageIcon("C:\\Users\\chevi\\Downloads\\Fondo-Futbol.jpg");
        label = new JLabel(wallpapper);
        add(label);

        patear= new JButton("Patear");
        label.add(patear);

        Menu= new JButton("Menu");
        label.add(Menu);

        Balon= new ImageIcon("C:\\Users\\chevi\\Downloads\\Balon (1).png");
        Balonn= new JLabel(Balon);

        ArqueroI= new ImageIcon("C:\\Users\\chevi\\Downloads\\Izquierda_111.png");
        ArcoI= new JLabel(ArqueroI);

        ArqueroC= new ImageIcon("C:\\Users\\chevi\\Downloads\\Frente_11.png");
        ArcoC= new JLabel(ArqueroC);

        ArqueroD= new ImageIcon("C:\\Users\\chevi\\Downloads\\Derecha_1.png");
        ArcoD= new JLabel(ArqueroD);

        ArqueroTI= new ImageIcon("C:\\Users\\chevi\\Downloads\\IzquierdaT_11.png");
        ArcoIT= new JLabel(ArqueroTI);

        ArqueroTD= new ImageIcon("C:\\Users\\chevi\\Downloads\\DerechaT__11.png");
        ArcoDT= new JLabel(ArqueroTD);

        IdJugador= new TextField(15);
        IdJugador.setEditable(false);
        Level = new TextField(15);
        Level.setEditable(false);
        Puntaje = new TextField(15);
        Puntaje.setEditable(false);

        ID= new JLabel("ID");
        PuntosLabel= new JLabel("Puntos");
        NIVELLABEL= new JLabel("Nivel");


        label.add(Balonn);
        label.add(ArcoI);
        label.add(ArcoC);
        label.add(ArcoD);
        label.add(ArcoIT);
        label.add(ArcoDT);
        label.add(IdJugador);
        label.add(Level);
        label.add(Puntaje);
        label.add(ID);
        label.add(PuntosLabel);
        label.add(NIVELLABEL);
        label.add(Level);

        IdJugador.setBounds(50, 20, 100, 20);
        ID.setBounds(30, -70, 100, 200);
        ID.setForeground(Color.WHITE);
        PuntosLabel.setBounds(180, -70, 100, 200);
        PuntosLabel.setForeground(Color.WHITE);
        Puntaje.setBounds(230,20,100,20);
        Level.setBounds(400,20,100,20);
        NIVELLABEL.setBounds(350,-70,50,200);
        NIVELLABEL.setForeground(Color.WHITE);



        patear.setBounds(450,290,patear.getPreferredSize().width,38);
        Menu.setBounds(510,20,Menu.getPreferredSize().width,38);
        Balonn.setBounds(280,290,Balon.getIconWidth(), Balon.getIconHeight());
        ArcoC.setBounds(230,120,ArqueroC.getIconWidth(), ArqueroC.getIconHeight());




        Arquero();
        setSize(wallpapper.getIconWidth(), wallpapper.getIconHeight());
        label.setLayout(null);
        addMouseListener(new MyMouseListener());
        setTitle("FinalMatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        patear.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent pateo) {
                        System.out.println("¡Se hizo clic en el botón guardar!");
                        Gol();
                        FueraInferior();
                        patear.setEnabled(false);
                        try {
                            Thread.sleep(0000);
                        }catch (Exception e){
                            System.out.println("E");
                        }
                        ReproducirSonido("C:\\Users\\chevi\\Downloads\\cartoon022.wav");

                    }
                }
        );


        Menu.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent Menu) {
                        System.out.println("¡Se hizo clic en el botón Menu!");
                       new MenuPausa(NombreJugador,IDD);
                       setVisible(false);


                    }
                }
        );




        IDNUMERO= IDD;
        IdJugador.setText(NombreJugador+"");



        setResizable(false);
        setVisible(true);







    }
    @Override
    public void  paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.drawRect(Ax, Ay, Nivel*10, Nivel*10);
     }

    public void ReproducirSonido(String nombreSonido){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }





    public void cerrarJuego() {
        this.dispose();
    }

    private class MyMouseListener extends JFrame implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent evtDisparo) {

            CoordXPatear= evtDisparo.getX() ;
            CoordYPatear= evtDisparo.getY() ;
            System.out.println("X:"+CoordXPatear);
            System.out.println("Y"+CoordYPatear);
            patear.setEnabled(true);
            Balonn.setBounds(280,290,Balon.getIconWidth(), Balon.getIconHeight());
            ArcoC.setBounds(230,120,ArqueroC.getIconWidth(), ArqueroC.getIconHeight());
            ArcoI.setBounds(-1500,-1200,ArqueroI.getIconWidth(), ArqueroI.getIconHeight());
            ArcoD.setBounds(-1500,-1200,ArqueroD.getIconWidth(), ArqueroD.getIconHeight());
            ArcoIT.setBounds(-1500,-1200,ArqueroTI.getIconWidth(), ArqueroTI.getIconHeight());
            ArcoDT.setBounds(-1500,-1200,ArqueroTD.getIconWidth(), ArqueroTD.getIconHeight());
            ReproducirSonido("C:\\Users\\chevi\\Downloads\\arbitro-futbol-.wav");
        }

        // No se usan pero deben ser escritos
        @Override public void mousePressed(MouseEvent evt) { }
        @Override public void mouseReleased(MouseEvent evt) { }
        @Override public void mouseEntered(MouseEvent evt) { }
        @Override public void mouseExited(MouseEvent evt) { }
    }

    public void Arquero () {

        if(Cy-Ay>=170){
            System.out.println("Se trabara");
            Random randonAx = new Random();
            Ax = randonAx.nextInt((400 - 80) + 1) + 80;
            Random randonAy = new Random();
            Ay = randonAy.nextInt((200 - 40) + 1) + 40;
            Bx= Ax+(Nivel*10);
            By= Ay;
            Cx = Ax;
            Cy= Ay+(Nivel*10);
            Dx=Bx;
            Dy= Cy;



        }
        else {
            Random randonAx = new Random();
            Ax = randonAx.nextInt((520 - 100) + 1) + 100;
            Bx= Ax+(Nivel*10);
            while (Bx>500 || Bx<111){
                Ax = randonAx.nextInt((520 - 100) + 1) + 100;
                Bx= Ax+(Nivel*10);
            }
            Random randonAy = new Random();
            Ay = randonAy.nextInt((230 - 65) + 1) + 65;
            By= Ay;
            Cx = Ax;
            Cy= Ay+(Nivel*10);
            Dx=Bx;
            Dy= Cy;
            while (Cy<91 || Cy>278){
                Ay = randonAy.nextInt((230 - 65) + 1) + 65;
                By= Ay;
                Cy= Ay+(Nivel*10);
                Dy= Cy;
            }

            System.out.println("AX: " + Ax);
            System.out.println("Ay: "+ Ay);
            System.out.println("BX: " + Bx);
            System.out.println("BY : "+ By);
            System.out.println("CX: " + Cx);
            System.out.println("Cy: "+ Cy);
            System.out.println("DX: " + Dx);
            System.out.println("DY : "+ Dy);
        }
    }

    public void Gol(){

        Balonn.setBounds(CoordXPatear-20,CoordYPatear-30,Balon.getIconWidth(), Balon.getIconHeight());

        if(CoordXPatear>111 && CoordXPatear<500 && CoordYPatear>91 && CoordYPatear<278){
            if(CoordXPatear<Ax || CoordXPatear>Bx || CoordYPatear<Ay || CoordYPatear>Cy ) {
                System.out.println("GOOOOOOOOOOL");
                ArcoC.setBounds(-2030,-1200,ArqueroC.getIconWidth(), ArqueroC.getIconHeight());

                if (CoordXPatear>250){
                    ArcoI.setBounds(120,120,ArqueroI.getIconWidth(), ArqueroI.getIconHeight());
                }
                else if (CoordXPatear<250){
                    ArcoD.setBounds(300,120,ArqueroD.getIconWidth(), ArqueroD.getIconHeight());
                }

                Nivel = Nivel + 1;
                PuntosHechos= PuntosHechos+3;
                try {
                    Thread.sleep(1000);
                    Level.setText(Nivel+"");
                    Puntaje.setText(PuntosHechos+"");
                }catch (Exception e){
                    System.out.println("E");
                }
                System.out.println("Nivel es:" + Nivel);
                System.out.println("ANCHO: " + (Bx - Ax));
                System.out.println("lARGO: " + (Cy - Ay));
                CoordYPatear=0;
                CoordXPatear=0;
                Arquero();
                repaint();
                java.sql.Date fechaActualizada = new java.sql.Date(new java.util.Date().getTime());
                new Update(Nivel,PuntosHechos,IDNUMERO,fechaActualizada);
                ReproducirSonido("C:\\Users\\chevi\\Downloads\\gol-riquelme (mp3cut.net).wav");

            }else {
                ReproducirSonido("C:\\Users\\chevi\\Downloads\\videoplayback-_1_-_mp3cut.net_.wav");
                System.out.println("La tapo el arquero");
                ArcoC.setBounds(-2030,-1200,ArqueroC.getIconWidth(), ArqueroC.getIconHeight());
                Balonn.setBounds(-2300,-2300,Balon.getIconWidth(), Balon.getIconHeight());
                if (CoordXPatear<250){
                    ArcoIT.setBounds(CoordXPatear,CoordYPatear-30,ArqueroTI.getIconWidth(), ArqueroTI.getIconHeight());
                }
                else if (CoordXPatear>250){
                    ArcoDT.setBounds(CoordXPatear-200,CoordYPatear-50,ArqueroTD.getIconWidth(), ArqueroTD.getIconHeight());
                }





                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ReproducirSonido("C:\\Users\\chevi\\Downloads\\mario-kart-perdiste-perder-loser.wav");
                        JOptionPane.showMessageDialog(label, "Esta vez no fue campeón, pero pronto seremos\n" +
                                "reyes del futchibol \n" +
                                "-Neymar\n" + "\n" +
                                "Tus estadísticas : \n" +
                                "Nivel :" + Nivel + "\n" +
                                "Puntaje :" + PuntosHechos + "\n"
                        );
                        Arquero();
                        repaint();
                        Nivel=1;
                        PuntosHechos= 0;
                        Level.setText(Nivel+"");
                        Puntaje.setText(PuntosHechos+"");
                    }
                });

                timer.setRepeats(false);
                timer.start();



            }
        }else {

            System.out.println("Afuera Fallaste");
            Arquero();
            repaint();
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ReproducirSonido("C:\\Users\\chevi\\Downloads\\mario-kart-perdiste-perder-loser.wav");
                    JOptionPane.showMessageDialog(label, "Esta vez no fue campeón, pero pronto seremos\n" +
                            "reyes del futchibol \n" +
                            "-Neymar\n" + "\n" +
                            "Tus estadísticas : \n" +
                            "Nivel :" + Nivel + "\n" +
                            "Puntaje :" + PuntosHechos + "\n"
                    );
                    Nivel=1;
                    PuntosHechos= 0;
                    Level.setText(Nivel+"");
                    Puntaje.setText(PuntosHechos+"");
                }
            });

            timer.setRepeats(false); // Establece que el temporizador no se repita
            timer.start(); // Inicia el temporizador


        }
    }

    public void FueraInferior(){
        if(CoordYPatear>=271){
            System.out.println("Fuera inferior");
        }
    }







}
