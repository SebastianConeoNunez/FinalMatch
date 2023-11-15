package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;


public class NewPlayerFirst extends JFrame{
    private ImageIcon wallpapper;
    private ImageIcon INombre;
    private JLabel label;
    private JLabel ImagenNombre;
    private JButton Jugar;

    private JButton Guardar;
    private JButton Atras;

    private TextField Nombre;

    private JLabel IDtuyo;
    private JLabel NombreTuya;

    private String NombreJugador;
    private int IDNUMERAL;

    public NewPlayerFirst(){
        setLayout(new BorderLayout()); //cogeTodoElcomponente
        wallpapper= new ImageIcon("C:\\Users\\chevi\\Downloads\\Fondo-Futbol.jpg");
        INombre= new ImageIcon("C:\\Users\\chevi\\Downloads\\Nombre.png");
        label = new JLabel(wallpapper);
        ImagenNombre= new JLabel(INombre);
        add(label);





        setSize(wallpapper.getIconWidth(), wallpapper.getIconHeight());
        label.setLayout(null);

        Jugar = new JButton("Nuevo Jugador");
        Atras=new JButton("Atras");

        Guardar= new JButton("Guardar");

        Nombre= new TextField(15);
        Nombre.setEditable(true);


        label.add(Nombre);
        label.add(ImagenNombre);
        label.add(Jugar);
        label.add(Guardar);
        label.add(Atras);

        Guardar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent guardar) {
                        System.out.println("¡Se hizo clic en el botón guardar!");
                        NombreJugador= Nombre.getText();
                        IDNUMERAL= new IdPlayers().IdPlayers();

                        IDtuyo= new JLabel("Tu id es: " + IDNUMERAL +"");
                        label.add(IDtuyo);
                        IDtuyo.setBounds(100,300,200,20);
                        IDtuyo.setForeground(Color.WHITE);

                        Guardar.setEnabled(false);


                        System.out.println(IDNUMERAL);
                        System.out.println(NombreJugador);



                        String url = "jdbc:mysql://localhost:3306/finalmatch";
                        String usuario = "root";
                        String contraseña = "sebastian2810";

                        java.sql.Date fechaActual = new java.sql.Date(new java.util.Date().getTime());


                        String sql = "INSERT INTO firsttable (ID,Nombre,NivelMax,Puntacion,FechaUltimaPartida) VALUES (" + IDNUMERAL + ",'" + NombreJugador + "',0,0,'" + fechaActual + "')";

                        try {
                            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
                            PreparedStatement statement = conexion.prepareStatement(sql);
                            statement.executeUpdate();

                            System.out.println("Registro agregado correctamente.");

                            conexion.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }







                    }

                }
        );

        setIDNUMERAL(IDNUMERAL);

        Nombre.setBounds(200, 150, 200, 20);
        ImagenNombre.setBounds(110, 90, INombre.getIconWidth(), INombre.getIconHeight());
        Guardar.setBounds(250,200,Guardar.getPreferredSize().width, 38);
        Atras.setBounds(460,300,Atras.getPreferredSize().width, 38);

        Atras.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evA) {
                        new PrimaryInterface();
                        dispose();

                    }
                }
        );



        setTitle("Nuevo usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);


    }

    public int getIDNUMERAL(){
        return IDNUMERAL;
    }

    public void setIDNUMERAL(int a){
        IDNUMERAL=a;
    }

}
