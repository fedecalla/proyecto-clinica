package medicos;

public class Temporal extends DecoratorContrato{

	public Temporal(IMedico m) {
		super(m);
	}

	@Override
	public double getHonorario() {
		return super.encapsulado.getHonorario()*1.05;
	}

}
