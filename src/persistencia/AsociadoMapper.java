package persistencia;
import modelo.ambulancia.Ambulancia;
import modelo.ambulancia.Asociado;
import modelo.individuos.AsociadoPersistible;
import modelo.individuos.Persona;
public class AsociadoMapper {
	/**
	 * Convierte un objeto {@link Asociado} en su representación {@link AsociadoDTO}.
	 *
	 * <p><b>Precondiciones:</b></p>
	 * <ul>
	 *   <li><code>asociado</code> no debe ser {@code null}.</li>
	 *   <li><code>asociado.getPersona()</code> no debe ser {@code null}.</li>
	 *   <li>Los atributos de {@link Persona} (dni, nombre, teléfono, domicilio, ciudad) 
	 *       deben estar inicializados y no ser vacíos.</li>
	 * </ul>
	 *
	 * @param asociado el objeto del modelo que se desea convertir.
	 * @return un {@link AsociadoDTO} con los datos de la persona asociada.
	 */
	// De modelo (Asociado) → DTO (para persistencia / capa vista, etc.)
    public static AsociadoDTO toDTO(Asociado asociado) {
        Persona persona = asociado.getPersona(); 
        return new AsociadoDTO(
                persona.getDni(),
                persona.getNombreyapellido(),
                persona.getTelefono(),
                persona.getDomicilio(),
                persona.getCiudad()
        );
    }

    /**
     * Convierte un objeto {@link AsociadoDTO} en su representación del modelo {@link Asociado}.
     *
     * <p><b>Precondiciones:</b></p>
     * <ul>
     *   <li><code>dto</code> no debe ser {@code null}.</li>
     *   <li>Los atributos de <code>dto</code> (dni, nombre, teléfono, domicilio, ciudad) 
     *       deben estar inicializados y no ser vacíos.</li>
     *   <li><code>ambulanciaUnica</code> no debe ser {@code null}.</li>
     * </ul>
     *
     * @param dto el objeto de transferencia de datos que contiene la información del asociado.
     * @param ambulanciaUnica la instancia única de {@link Ambulancia} que se asignará al asociado.
     * @return un {@link Asociado} construido a partir del DTO y la ambulancia proporcionada.
     */
    
    // De DTO → modelo (Asociado) usando tu AsociadoPersistible
    public static Asociado fromDTO(AsociadoDTO dto, Ambulancia ambulanciaUnica) {
        // Podés usar Persona o directamente AsociadoPersistible
        Persona persona = new AsociadoPersistible(
                dto.getDni(),
                dto.getNombreyapellido(),
                dto.getTelefono(),
                dto.getDomicilio(),
                dto.getCiudad()
        );

        return new Asociado(persona, ambulanciaUnica);
    }
}
