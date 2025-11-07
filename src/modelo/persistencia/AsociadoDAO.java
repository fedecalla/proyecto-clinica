package modelo.persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.ambulancia.Asociado; // clase Asociado (que tiene Persona)
import modelo.individuos.AsociadoPersistible;
import modelo.individuos.Persona; // clase Persona
import modelo.ambulancia.Ambulancia; 

public class AsociadoDAO {

    // --- Método Auxiliar: 1. Verificar Duplicados por DNI ---

    private boolean existeAsociado(String dni) throws SQLException {
        String sql = "SELECT 1 FROM Asociados WHERE dni = ?";
        // Asegúrate de que ConexionManager.getConnection() esté correcto
        try (Connection conn = ConexionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                // Si rs.next() es true, significa que encontró al menos una fila.
                return rs.next(); 
            }
        }
    }

    // --- 2. Implementar ALTA de Asociado (CREATE) ---

    public void altaAsociado(Asociado asociado) throws SQLException, IllegalArgumentException {
        Persona persona = asociado.getPersona(); // Obtenemos el objeto Persona
        String dni = persona.getDni();
        
        // 1. Verificación de duplicados
        if (existeAsociado(dni)) {
            throw new IllegalArgumentException("Ya existe un asociado con el DNI: " + dni);
        }

        // 2. Sentencia INSERT con los 5 campos de la tabla
        String sql = "INSERT INTO Asociados (dni, nombre, telefono, ciudad, domicilio) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Asignar los valores del objeto Persona a la sentencia SQL
            ps.setString(1, persona.getDni());
            ps.setString(2, persona.getNombreyapellido()); 
            ps.setString(3, persona.getTelefono());
            ps.setString(4, persona.getCiudad());
            ps.setString(5, persona.getDomicilio());
            
            ps.executeUpdate();
            System.out.println("Asociado con DNI " + dni + " dado de alta exitosamente en BD.");
        } 
    }

    // --- 3. Implementar BAJA de Asociado (DELETE) ---

    public void bajaAsociado(String dni) throws SQLException, Exception {
        if (!existeAsociado(dni)) {
             // Manejo de excepción si el asociado no existe
             throw new Exception("ERROR: Asociado con DNI " + dni + " no encontrado. Imposible dar de baja.");
        }
        
        String sql = "DELETE FROM Asociados WHERE dni = ?";
        try (Connection conn = ConexionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.executeUpdate();
            System.out.println("Asociado con DNI " + dni + " eliminado de la BD.");
        }
    }
    
    // --- 4. Implementar LISTADO de Asociados (READ) ---
    // Este método cumple el requisito de "Cargar los datos desde la base al iniciar la aplicación"

 
    

    public List<Asociado> listarAsociados(Ambulancia ambulanciaUnica) throws SQLException {
        List<Asociado> asociados = new ArrayList<>();
        String sql = "SELECT dni, nombre, telefono, ciudad, domicilio FROM Asociados"; 
        
        try (Connection conn = ConexionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // 2. CREAR el objeto Persona/AsociadoPersistible (SÓLO DATOS)
                Persona persona = new AsociadoPersistible(
                    rs.getString("dni"), 
                    rs.getString("nombre"), 
                    rs.getString("telefono"), 
                    rs.getString("domicilio"), 
                    rs.getString("ciudad")
                );
                
                // 3. CREAR el objeto Asociado (SIMULACIÓN)
                // Se inyecta la instancia única de Ambulancia que recibimos:
                Asociado asociado = new Asociado(persona, ambulanciaUnica);
                asociados.add(asociado);
            }
        } 
        return asociados;
    }
}
