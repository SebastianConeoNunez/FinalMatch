package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MostrarRankingMensual extends JFrame {
    private JTable tabla;

    public MostrarRankingMensual() {

        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalmatch", "root", "sebastian2810");


            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM");
            String fechaActualStr = sdf.format(new Date());

            // Consulta SQL para obtener el ranking mensual
            String consulta = "SELECT * FROM firsttable WHERE DATE_FORMAT(FechaUltimaPartida, '%y-%m') = ? ORDER BY puntacion DESC LIMIT 100";
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1, fechaActualStr);


                try (ResultSet resultado = statement.executeQuery()) {

                    DefaultTableModel modelo = new DefaultTableModel();
                    modelo.addColumn("Posición");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Puntacion");


                    int posicion = 1;
                    while (resultado.next()) {
                        String jugador = resultado.getString("Nombre");
                        int puntaje = resultado.getInt("puntacion");
                        modelo.addRow(new Object[]{posicion, jugador, puntaje});
                        posicion++;
                    }


                    tabla = new JTable(modelo);
                    tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());


                    JScrollPane scrollPane = new JScrollPane(tabla);
                    add(scrollPane);

                    // Cerrar la conexión
                    conexion.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        setTitle("Ranking Mensual");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambio en la operación de cierre
        getContentPane().setBackground(new Color(240, 240, 240)); // Color de fondo
        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }


}
