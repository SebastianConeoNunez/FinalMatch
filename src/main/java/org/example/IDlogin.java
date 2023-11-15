
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


public class IDlogin extends JFrame{


    private ImageIcon wallpapper;
    private ImageIcon INombre;
    private JLabel label;
    private JLabel ImagenNombre;

    private int IDD;


    private JButton loginn;
    private JButton Atras;

    private TextField IDlogin;

    private JLabel IDtuyo;



    private String cajaId;

    public IDlogin(){
        setLayout(new BorderLayout()); //cogeTodoElcomponente
        wallpapper= new ImageIcon("C:\\Users\\chevi\\Downloads\\Fondo-Futbol.jpg");
        INombre= new ImageIcon("C:\\Users\\chevi\\Downloads\\Nombre.png");
        label = new JLabel(wallpapper);
        ImagenNombre= new JLabel(INombre);
        add(label);





        setSize(wallpapper.getIconWidth(), wallpapper.getIconHeight());
        label.setLayout(null);


        Atras=new JButton("Atras");

        loginn= new JButton("Login");

        IDlogin= new TextField(15);
        IDlogin.setEditable(true);


        label.add(IDlogin);
        label.add(ImagenNombre);
        label.add(loginn);
        label.add(Atras);

        loginn.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent guardar) {
                        System.out.println("¡Se hizo clic en el botón Login!");
                        cajaId= IDlogin.getText();
                        boolean bandera= true;
                        do {
                            cajaId= IDlogin.getText();
                            try {
                                IDD= Integer.parseInt(cajaId);
                                bandera=false;
                                System.out.println("funciona");




                                String url = "jdbc:mysql://localhost:3306/finalmatch";
                                String usuario = "root";
                                String contraseña = "sebastian2810";

                                try {

                                    Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

                                    // Input del usuario
                                    int idUsuario = IDD; // ID ingresado por el usuario

                                    // Consulta SQL
                                    String consulta = "SELECT * FROM firsttable WHERE ID=?";
                                    PreparedStatement statement = conexion.prepareStatement(consulta);
                                    statement.setInt(1, idUsuario);

                                    // Ejecutar la consulta
                                    ResultSet resultado = statement.executeQuery();

                                    // Verificar si el usuario existe
                                    if (resultado.next()) {
                                        System.out.println("Login exitoso");
                                        loginn.setEnabled(false);

                                        new Play(IDD);
                                        dispose();
                                    } else {
                                        System.out.println("ID de usuario incorrecto");
                                    }

                                    // Cerrar la conexión
                                    conexion.close();

                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                            }catch (Exception e){
                                JOptionPane.showMessageDialog(label, "Recuerda que es solo numeros");
                                bandera=false;
                            }

                        }while (bandera);

                    }

                }
        );



        IDlogin.setBounds(200, 150, 200, 20);
        ImagenNombre.setBounds(110, 90, INombre.getIconWidth(), INombre.getIconHeight());
        loginn.setBounds(250,200,loginn.getPreferredSize().width, 38);
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



        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


    }

    public int getIDD(){
        return IDD;
    }



}
