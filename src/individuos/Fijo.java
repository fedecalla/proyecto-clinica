package individuos;

public class Fijo extends DecoratorContrato{

	public Fijo(IMedico m) {
		super(m);
	}

	@Override
	public double getHonorario() {
		return super.encapsulado.getHonorario()*1.1;
	}

}
