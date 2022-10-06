
public class Produto {
	
	private int tempoProducao;
	private int tempoRemocao;
	private String qualidade;
	private String tipo;
	
	public Produto(String tipo) {
		this.tipo = tipo;
		this.tempoRemocao = 0;
	}

	public int getTempoProducao() {
		return tempoProducao;
	}

	public void setTempoProducao(int tempoProd) {
		this.tempoProducao = tempoProd;
	}
	
	public int getTempoRemocao() {
		return tempoRemocao;
	}

	public void setTempoRemocao(int tempoRemocao) {
		this.tempoRemocao = tempoRemocao;
	}

	public String getQualidade() {
		return qualidade;
	}

	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return tipo + " " + qualidade + ": produzido em " + tempoProducao + "ms, e removido em " + tempoRemocao + "ms.";
	}	
}
