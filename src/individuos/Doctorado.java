package individuos;

public class Doctorado extends DecoratorPosgrado{

	public Doctorado(IMedico m) {
		super(m);
	}

	@Override
	public double getHonorario() {
		return super.encapsulado.getHonorario()*1.1;
	}

}
