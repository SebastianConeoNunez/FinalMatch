package org.example;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import java.sql.*;

public class MostrarPuntaje extends JFrame {
    private JTable tabla;

    public MostrarPuntaje() {
        // Conexión a la Base de Datos
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalmatch", "root", "sebastian2810");

            // Consulta SQL
            String consulta = "SELECT * FROM firsttable ORDER BY puntacion DESC LIMIT 10";
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(consulta);

            // Crear un modelo de tabla
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Nombre");
            modelo.addColumn("Puntacion");

            // Llenar el modelo con los resultados de la consulta
            while (resultado.next()) {
                String jugador = resultado.getString("Nombre");
                int puntaje = resultado.getInt("puntacion");
                modelo.addRow(new Object[]{jugador, puntaje});
            }

            // Crear y configurar la tabla
            tabla = new JTable(modelo);
            tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());

            // Agregar la tabla a un JScrollPane (en caso de que haya muchos resultados)
            JScrollPane scrollPane = new JScrollPane(tabla);
            add(scrollPane);

            // Cerrar la conexión
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setTitle("Mejores Puntajes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);




    }
}
