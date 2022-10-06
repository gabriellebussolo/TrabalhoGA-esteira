
public class adicionaProduto implements Runnable {

	private Esteira esteira;
	private Produto fruta;
	private String tipoFruta;
	private String[] qualidades = { "bom", "ruim" };
	private int qual = 0;
	private int quantProd;

	public adicionaProduto(Esteira esteira, String tipo, int quantProd) {
		this.esteira = esteira;
		this.tipoFruta = tipo;
		this.quantProd = quantProd;
	}

	@Override
	public void run() {
		for(int i = 0; i < quantProd; i++) {
			fruta = new Produto(tipoFruta);

			qual = (int) (Math.random() * 2);
			fruta.setQualidade(qualidades[qual]);

			if (fruta.getTipo().equalsIgnoreCase("morango"))
				fruta.setTempoProducao((int) (2000 + (Math.random() * 1001))); // gera um numero entre 2000 e 3000
			else if (fruta.getTipo().equalsIgnoreCase("limao"))
				fruta.setTempoProducao((int) (3000 + (Math.random() * 1001))); // gera um numero entre 3000 e 4000
			else
				fruta.setTempoProducao((int) (4000 + (Math.random() * 1001))); // gera um numero entre 4000 e 5000

			try {
				Thread.sleep(fruta.getTempoProducao());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			esteira.add(fruta);
		}

	}

}
