package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; // ⭐ ¡IMPORTACIÓN NECESARIA!

public class ConexionManager {
    
    private static final String URL = "jdbc:mariadb://localhost:3306/grupo_3"; 
    private static final String USUARIO = "progra_c"; 
    private static final String PASSWORD = "progra_c"; 

    // --- Sentencia SQL para la creación automática de la tabla ---
    private static final String SQL_CREACION = 
        "CREATE TABLE IF NOT EXISTS Asociados (" + 
        "dni VARCHAR(8) PRIMARY KEY, " +
        "nombre VARCHAR(50) NOT NULL, " +
        "telefono VARCHAR(50), " +
        "ciudad VARCHAR(100), " +    
        "domicilio VARCHAR(255) " +  
        ");";

    /**
     * Bloque estático: Carga el Driver y llama a la inicialización del esquema.
     */
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); 
            System.out.println("✅ Driver JDBC de MariaDB cargado correctamente.");
            
            // ⭐ LLAMADA PARA CREAR LA TABLA (SE EJECUTA AQUÍ)
            inicializarEsquema(); 
            
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: No se encontró el driver JDBC.");
            System.err.println("Asegúrate de que el archivo 'mariadb-java-client.jar' esté en el Classpath.");
            e.printStackTrace();
        }
    }
    
    /**
     * Método para obtener una nueva conexión.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
    
    // ⭐ NUEVO MÉTODO PARA CREAR LA TABLA SI NO EXISTE
    private static void inicializarEsquema() {
        try (Connection conn = getConnection(); // Abre la conexión
             Statement stmt = conn.createStatement()) { // Crea un Statement para ejecutar SQL
            
            stmt.execute(SQL_CREACION); // Ejecuta la sentencia DDL (CREATE TABLE)
            System.out.println("✅ Esquema verificado/creado: Tabla 'Asociados' lista.");
            
        } catch (SQLException e) {
            // Este bloque captura errores si, por ejemplo, la base de datos 'grupo_3' no existe.
            if (e.getSQLState().startsWith("42")) { 
                 System.err.println("ERROR FATAL: La Base de Datos 'grupo_3' no existe o hay un problema de credenciales.");
                 System.err.println("Solución: El compañero debe crear la BD vacía 'grupo_3' en su servidor MariaDB.");
            } else {
                 System.err.println("ERROR al inicializar el esquema: " + e.getMessage());
            }
        }
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
