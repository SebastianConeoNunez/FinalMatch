package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MostrarRankingDiario extends JFrame {
    private JTable tabla;

    public MostrarRankingDiario() {

        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalmatch", "root", "sebastian2810");


            String consulta = "SELECT * FROM firsttable WHERE DATE(FechaUltimaPartida) = CURDATE() ORDER BY puntacion DESC LIMIT 100";
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {

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


                    conexion.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        setTitle("Ranking Diario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 240, 240));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
