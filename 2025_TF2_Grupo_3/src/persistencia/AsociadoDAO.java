package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.Asociado; // clase Asociado (que tiene Persona) 
import modelo.individuos.Persona;

public class AsociadoDAO {

    public AsociadoDAO() {
		super();
	}

    /**
     * Verifica si existe un asociado en la base de datos a partir de su DNI.
     *
     * <p><b>Precondiciones:</b></p>
     * <ul>
     *   <li><code>dni</code> no debe ser {@code null} ni vacío.</li>
     *   <li>Debe existir una conexión válida a la base de datos mediante {@link ConexionManager#getConnection()}.</li>
     *   <li>El {@link PreparedStatement} generado no debe ser {@code null}.</li>
     * </ul>
     *
     * <p><b>Postcondiciones:</b></p>
     * <ul>
     *   <li>Si el DNI existe en la tabla <code>Asociados</code>, el método devuelve {@code true}.</li>
     *   <li>Si no existe, devuelve {@code false}.</li>
     * </ul>
     *
     * @param dni Documento Nacional de Identidad del asociado a verificar.
     * @return {@code true} si el asociado existe en la base de datos, {@code false} en caso contrario.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    
	// --- Método Auxiliar: 1. Verificar Duplicados por DNI ---

    private boolean existeAsociado(String dni) throws SQLException {
    	assert dni != null && !dni.isEmpty() : "El DNI no puede ser null ni vacío";
    	
        String sql = "SELECT 1 FROM Asociados WHERE dni = ?";
        // Asegúrate de que ConexionManager.getConnection() esté correcto
        try (Connection conn = ConexionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	
        	assert conn != null : "La conexión no puede ser null";
            assert ps != null : "El PreparedStatement no puede ser null";
            
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                // Si rs.next() es true, significa que encontró al menos una fila.
                return rs.next(); 
            }
        }
    }

    /**
     * Da de alta un nuevo {@link Asociado} en la base de datos.
     *
     * <p><b>Precondiciones:</b></p>
     * <ul>
     *   <li><code>asociado</code> no debe ser {@code null}.</li>
     *   <li>El objeto {@link Asociado} debe contener una {@link Persona} válida con todos sus atributos inicializados
     *       (dni, nombre, teléfono, domicilio y ciudad).</li>
     *   <li>El DNI del asociado no debe ser {@code null} ni vacío.</li>
     *   <li>No debe existir previamente un asociado con el mismo DNI en la base de datos 
     *       (se verifica con {@link #existeAsociado(String)}).</li>
     *   <li>Debe existir una conexión válida a la base de datos mediante {@link ConexionManager#getConnection()}.</li>
     * </ul>
     *
     * <p><b>Postcondiciones:</b></p>
     * <ul>
     *   <li>Si las precondiciones se cumplen, se inserta un nuevo registro en la tabla <code>Asociados</code>
     *       con los datos del objeto proporcionado.</li>
     *   <li>El método imprime un mensaje de confirmación en la salida estándar indicando el alta exitosa.</li>
     *   <li>Si ya existía un asociado con el mismo DNI, se lanza una {@link IllegalArgumentException}.</li>
     * </ul>
     *
     * @param asociado el objeto del modelo que se desea dar de alta en la base de datos.
     * @throws SQLException si ocurre un error al acceder o modificar la base de datos.
     * @throws IllegalArgumentException si ya existe un asociado con el mismo DNI.
     */
    
    
    // --- 2. Implementar ALTA de Asociado (CREATE) ---

    public void altaAsociado(Asociado asociado) throws SQLException, IllegalArgumentException {
        AsociadoDTO dto = AsociadoMapper.toDTO(asociado);
        String dni = dto.getDni();

        // 1. Verificación de duplicados
        if (existeAsociado(dni)) {
            throw new IllegalArgumentException("Ya existe un asociado con el DNI: " + dni);
        }

        // 2. Sentencia INSERT con los 5 campos de la tabla
        String sql = "INSERT INTO Asociados (dni, nombre, telefono, ciudad, domicilio) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dto.getDni());
            ps.setString(2, dto.getNombreyapellido());
            ps.setString(3, dto.getTelefono());
            ps.setString(4, dto.getCiudad());
            ps.setString(5, dto.getDomicilio());

            ps.executeUpdate();
            System.out.println("Asociado con DNI " + dni + " dado de alta exitosamente en BD.");
        }
    }

    /**
     * Elimina un asociado de la base de datos a partir de su DNI.
     *
     * <p><b>Precondiciones:</b></p>
     * <ul>
     *   <li><code>dni</code> no debe ser {@code null} ni vacío.</li>
     *   <li>Debe existir un asociado con el DNI proporcionado en la base de datos 
     *       (se verifica con {@link #existeAsociado(String)}).</li>
     *   <li>Debe existir una conexión válida a la base de datos mediante {@link ConexionManager#getConnection()}.</li>
     *   <li>El {@link PreparedStatement} generado no debe ser {@code null}.</li>
     * </ul>
     *
     * <p><b>Postcondiciones:</b></p>
     * <ul>
     *   <li>Si las precondiciones se cumplen, el registro correspondiente al DNI se elimina de la tabla <code>Asociados</code>.</li>
     *   <li>El método imprime un mensaje de confirmación en la salida estándar indicando la baja exitosa.</li>
     *   <li>Si el DNI no existe en la base de datos, se lanza una {@link Exception} indicando el error.</li>
     * </ul>
     *
     * @param dni Documento Nacional de Identidad del asociado a eliminar.
     * @throws SQLException si ocurre un error al acceder o modificar la base de datos.
     * @throws Exception si el asociado con el DNI proporcionado no existe en la base de datos.
     */
    
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
    
    /**
     * Obtiene todos los asociados de la base de datos y los devuelve como una lista de {@link AsociadoDTO}.
     *
     * <p><b>Precondiciones:</b></p>
     * <ul>
     *   <li>Debe existir una conexión válida a la base de datos mediante {@link ConexionManager#getConnection()}.</li>
     *   <li>La tabla <code>Asociados</code> debe estar creada y accesible.</li>
     *   <li>Los campos <code>dni</code>, <code>nombre</code>, <code>telefono</code>, <code>domicilio</code> y <code>ciudad</code> 
     *       deben estar correctamente definidos en la tabla.</li>
     * </ul>
     *
     * <p><b>Postcondiciones:</b></p>
     * <ul>
     *   <li>Se devuelve una lista de objetos {@link AsociadoDTO} con los datos obtenidos de la base de datos.</li>
     *   <li>Si no existen registros en la tabla, se devuelve una lista vacía.</li>
     * </ul>
     *
     * @return una lista de {@link AsociadoDTO} con todos los asociados registrados en la base de datos.
     * @throws SQLException si ocurre un error al acceder o consultar la base de datos.
     */
    
    
    // --- 4. Implementar LISTADO de Asociados (READ) ---
    // Este método cumple el requisito de "Cargar los datos desde la base al iniciar la aplicación"

 
    

    public List<AsociadoDTO> listarAsociadosDTO() throws SQLException {
        List<AsociadoDTO> lista = new ArrayList<>();
        String sql = "SELECT dni, nombre, telefono, ciudad, domicilio FROM Asociados";

        try (Connection conn = ConexionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AsociadoDTO dto = new AsociadoDTO(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("domicilio"),
                        rs.getString("ciudad")
                );
                lista.add(dto);
            }
        }
        return lista;
    }
    
    /**
     * Obtiene todos los asociados de la base de datos y los devuelve como una lista de objetos del modelo {@link Asociado}.
     *
     * <p><b>Precondiciones:</b></p>
     * <ul>
     *   <li><code>ambulanciaUnica</code> no debe ser {@code null}.</li>
     *   <li>Debe existir una conexión válida a la base de datos mediante {@link ConexionManager#getConnection()}.</li>
     *   <li>La tabla <code>Asociados</code> debe estar creada y accesible.</li>
     *   <li>Los campos <code>dni</code>, <code>nombre</code>, <code>telefono</code>, <code>domicilio</code> y <code>ciudad</code> 
     *       deben estar correctamente definidos en la tabla.</li>
     * </ul>
     *
     * <p><b>Postcondiciones:</b></p>
     * <ul>
     *   <li>Se devuelve una lista de objetos {@link Asociado} construidos a partir de los datos obtenidos de la base de datos.</li>
     *   <li>Si no existen registros en la tabla, se devuelve una lista vacía.</li>
     * </ul>
     *
     * @param ambulanciaUnica la instancia única de {@link Ambulancia} que se asignará a cada asociado creado.
     * @return una lista de {@link Asociado} con todos los asociados registrados en la base de datos.
     * @throws SQLException si ocurre un error al acceder o consultar la base de datos.
     */
    
    public ArrayList<Asociado> listarAsociados(Ambulancia ambulanciaUnica) throws SQLException {
    	ArrayList<Asociado> asociados = new ArrayList<>();

        for (AsociadoDTO dto : listarAsociadosDTO()) {
            Asociado asociado = AsociadoMapper.fromDTO(dto, ambulanciaUnica);
            asociados.add(asociado);
        }

        return asociados;
    }
}
