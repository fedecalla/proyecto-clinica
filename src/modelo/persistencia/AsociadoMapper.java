package modelo.persistencia;
import modelo.ambulancia.Asociado;
import modelo.ambulancia.Ambulancia;
import modelo.individuos.Persona;
import modelo.individuos.AsociadoPersistible;
public class AsociadoMapper {
	// De modelo (Asociado) → DTO (para persistencia / capa vista, etc.)
    public static AsociadoDTO toDTO(Asociado asociado) {
        Persona persona = asociado.getPersona();  // asumo que tenés este getter

        return new AsociadoDTO(
                persona.getDni(),
                persona.getNombreyapellido(),
                persona.getTelefono(),
                persona.getDomicilio(),
                persona.getCiudad()
        );
    }

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
