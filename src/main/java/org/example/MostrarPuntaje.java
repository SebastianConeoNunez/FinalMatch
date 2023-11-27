package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MostrarPuntaje extends JFrame {
    private JTable tabla;

    public MostrarPuntaje() {

        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalmatch", "root", "sebastian2810");

            // Consulta SQL
            String consulta = "SELECT * FROM firsttable ORDER BY puntacion DESC LIMIT 100";
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(consulta);


            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Posición"); // Nueva columna "Posición"
            modelo.addColumn("Nombre");
            modelo.addColumn("Puntacion");


            int posicion = 1;
            while (resultado.next()) {
                String jugador = resultado.getString("Nombre");
                int puntaje = resultado.getInt("puntacion");
                modelo.addRow(new Object[]{posicion, jugador, puntaje}); // Agregar posición
                posicion++;
            }


            tabla = new JTable(modelo);
            tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());


            JScrollPane scrollPane = new JScrollPane(tabla);
            add(scrollPane);


            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setTitle("Mejores Puntajes Historico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
