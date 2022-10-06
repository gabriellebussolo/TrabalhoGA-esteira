
public class removeBons implements Runnable {

	private Esteira esteira;

	public removeBons(Esteira esteira) {
		this.esteira = esteira;
	}

	@Override
	public void run() {
		while (true) {
			Produto removido = esteira.remover("bom");
			
			if (removido != null) {
				System.out.printf("RB: " + esteira + " >>> %s %s (%dms) removido em %dms\n", removido.getTipo(),
						removido.getQualidade(), removido.getTempoProducao(), removido.getTempoRemocao());
			}
		}
	}

}
