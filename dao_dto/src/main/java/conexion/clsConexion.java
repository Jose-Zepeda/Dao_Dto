package conexion;

import java.sql.*;

public class clsConexion {
    private Connection conn = null;

    // Método para establecer la conexión a la base de datos
    public void conexion() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dao_dto", "root", "d9edde2cc9");
            System.out.println("Conexion realizada exitosamente");
        } catch (SQLException ex) {
            System.out.println("Hubo un error al conectar a la base de datos: " + ex.getMessage());
            throw ex; // Lanza la excepción para que el método llamante pueda manejarla
        }
    }

    // Método para asignar valores y comprobar si coinciden con la base de datos
    public String asignarValores(String username, String password) throws SQLException {
        String dbusername = "";
        String dbpassword = "";
        conexion(); // Establece la conexión

        String sql = "SELECT * FROM tbusuarios WHERE username = ? AND password = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            // Establece los valores de los parámetros de la consulta PreparedStatement
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                // Procesa los resultados aquí
                dbusername = rs.getString("username");
                dbpassword = rs.getString("password");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // Cierra los recursos en bloques finally para asegurar su liberación
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        // Compara los valores de la base de datos con los proporcionados y retorna un resultado
        if (dbusername.equals(username) && dbpassword.equals(password)) {
            return "true";
        } else {
            return "false";
        }
    }
}
