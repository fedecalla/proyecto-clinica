package medicos;

public class Magister extends DecoratorPosgrado{

	public Magister(IMedico m) {
		super(m);
	}

	@Override
	public double getHonorario() {
		return super.encapsulado.getHonorario()*1.05;
	}

}
