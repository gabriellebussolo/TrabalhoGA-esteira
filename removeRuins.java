
public class removeRuins implements Runnable {

	private Esteira esteira;

	public removeRuins(Esteira esteira) {
		this.esteira = esteira;
	}

	@Override
	public void run() {
		while (true) {
			Produto removido = esteira.remover("ruim");
			
			if (removido != null) {
				System.out.printf("RR: " + esteira + " >>> %s %s (%dms) removido em %dms\n", removido.getTipo(),
						removido.getQualidade(), removido.getTempoProducao(), removido.getTempoRemocao());
			}
		}
	}

}
