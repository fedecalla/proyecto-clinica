package modelo.individuos;

public class AsociadoPersistible extends Persona {
    
    // Constructor que llama al constructor de la clase abstracta Persona
    public AsociadoPersistible(String dni, String nombreyapellido, String telefono, String domicilio, String ciudad) {
        super(dni, nombreyapellido, telefono, domicilio, ciudad);
    }
}
