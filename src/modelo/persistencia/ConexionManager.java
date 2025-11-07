package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionManager {
    
    // --- ⚠️ Parámetros de Conexión a tu BD 'grupo_3' ---
    // URL: jdbc:tipo_bd://host:puerto/nombre_bd
    private static final String URL = "jdbc:mariadb://localhost:3306/grupo_3"; 
    
    // USUARIO: Típicamente 'root' si usas XAMPP/WAMP/MariaDB por defecto.
    private static final String USUARIO = "progra_c"; 
    
    // PASSWORD: Normalmente es vacía ("") si no la configuraste.
    private static final String PASSWORD = "progra_c"; 

    /**
     * Bloque estático: Se ejecuta una sola vez cuando la JVM carga esta clase.
     * Su función es cargar el Driver JDBC de MariaDB.
     */
    static {
        try {
            // Carga la clase del driver de MariaDB.
            Class.forName("org.mariadb.jdbc.Driver"); 
            System.out.println("Driver JDBC de MariaDB cargado correctamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: No se encontró el driver JDBC de MariaDB.");
            System.err.println("Asegúrate de que el archivo 'mariadb-java-client.jar' esté en el Classpath.");
            e.printStackTrace();
        }
    }
    
    /**
     * Método público para obtener una nueva conexión a la base de datos.
     * @return Una instancia de java.sql.Connection.
     * @throws SQLException Si ocurre un error al intentar conectar.
     */
    public static Connection getConnection() throws SQLException {
        // DriverManager intenta establecer la conexión con los parámetros definidos.
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    /**
     * Método de prueba simple para verificar la configuración de la conexión.
     */
    public static void probarConexion() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("PRUEBA EXITOSA: Conexión a 'grupo_3' establecida.");
            }
        } catch (SQLException e) {
            System.err.println("PRUEBA FALLIDA: Error al intentar conectar a la BD. Revisa URL, USUARIO y PASSWORD.");
            e.printStackTrace();
        }
    }
}
