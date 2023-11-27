package org.example;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class Update {
    public Update(int nivel,int puntaje,int ID,java.sql.Date fecha){
        Connection conexion = null;
        PreparedStatement obtenerPuntaje = null;
        PreparedStatement obtenerNivel=null;
        PreparedStatement actualizarPuntaje = null;
        PreparedStatement actualizarNivel= null;
        ResultSet resultado = null;
        ResultSet resultadoNivel= null;
        PreparedStatement actualizarFecha = null;

        try {
            String url = "jdbc:mysql://localhost:3306/finalmatch";
            String usuario = "root";
            String contraseña = "sebastian2810";
            conexion = DriverManager.getConnection(url, usuario, contraseña);


            String obtenerPuntajeSQL = "SELECT Puntacion FROM firsttable  WHERE ID = ?";
            obtenerPuntaje = conexion.prepareStatement(obtenerPuntajeSQL);
            String obtenerNivelSQL = "SELECT NivelMax FROM firsttable  WHERE ID = ?";
            obtenerNivel= conexion.prepareStatement(obtenerNivelSQL);
            int id = ID; // El ID en específico que deseas actualizar
            obtenerPuntaje.setInt(1, id);
            resultado = obtenerPuntaje.executeQuery();
            obtenerNivel.setInt(1,id);
            resultadoNivel = obtenerNivel.executeQuery();

            int puntajeActual = 0;
            int Nivelactual=0;
            if (resultado.next()) {
                puntajeActual = resultado.getInt("Puntacion");
            }
            if (resultadoNivel.next()) {
                Nivelactual = resultadoNivel.getInt("NivelMax");
            }


            int nuevoPuntaje = puntaje;
            int NivelNuevo= nivel;
            if (NivelNuevo > Nivelactual) {

                String actualizarPuntajeSQL = "UPDATE firsttable SET Puntacion = ? WHERE ID = ?";
                String actualizarNivelSQL = "UPDATE firsttable SET NivelMax = ? WHERE ID = ?";
                actualizarPuntaje = conexion.prepareStatement(actualizarPuntajeSQL);
                actualizarPuntaje.setInt(1, nuevoPuntaje);
                actualizarPuntaje.setInt(2, id);
                int filasActualizadas = actualizarPuntaje.executeUpdate();
                System.out.println("Se actualizaron " + filasActualizadas + " filas.");

                actualizarNivel = conexion.prepareStatement(actualizarNivelSQL);
                actualizarNivel.setInt(1, NivelNuevo);
                actualizarNivel.setInt(2, id);
                int filasActualizadaas = actualizarNivel.executeUpdate();
                System.out.println("Se actualizaron " + filasActualizadaas + " filas.");

                String actualizarFechaSQL = "UPDATE firsttable SET FechaUltimaPartida = ? WHERE ID = ?"; // Nueva actualización de fecha
                actualizarFecha = conexion.prepareStatement(actualizarFechaSQL);
                actualizarFecha.setDate(1, fecha);
                actualizarFecha.setInt(2, id);
                int filasFechaActualizadas = actualizarFecha.executeUpdate();
                System.out.println("Se actualizaron " + filasFechaActualizadas + " filas.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (obtenerPuntaje != null) obtenerPuntaje.close();
                if (actualizarPuntaje != null) actualizarPuntaje.close();
                if (conexion != null) conexion.close();

                if (resultadoNivel != null) resultadoNivel.close();
                if (obtenerNivel != null) obtenerNivel.close();
                if (actualizarNivel != null) actualizarNivel.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
