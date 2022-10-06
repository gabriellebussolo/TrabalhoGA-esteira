
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Esteira {

	private LinkedList<Produto> esteira;
	private LinkedList<Produto> historico;
	private Lock lock = new ReentrantLock();
	private Condition esteiraVaziaCond = lock.newCondition();
	private int cont;

	public Esteira() {
		esteira = new LinkedList<Produto>();
		historico = new LinkedList<Produto>();
	}

	public LinkedList<Produto> getHistorico() {
		return historico;
	}

	public boolean checaVazio() {
		return esteira.isEmpty();
	}

	public void add(Produto fruta) {
		try {
			lock.lock();
			historico.add(fruta); // adiciona o produto ao historico
			esteira.add(fruta); // adiciona o produto no final da esteira
			System.out.println("A: " + this.toString());
			esteiraVaziaCond.signal(); // manda sinal avisando que a esteira nao esta mais vazia
		} finally {
			lock.unlock();
		}
	}

	public Produto remover(String qualidade) {
		try {
			lock.lock();
			while (esteira.isEmpty())
				esteiraVaziaCond.await(); // espera a esteira ter um produto

			// faz selecao conforme qualidade do produto
			if (esteira.peek().getQualidade().equalsIgnoreCase(qualidade)) {
				int tempRemocao = (int) (1000 + (Math.random() * 501));
				esteira.peek().setTempoRemocao(tempRemocao); // gera um numero de 1000 a 1500
				Thread.sleep(esteira.peek().getTempoRemocao());

				Produto removido = esteira.poll();
				historico.get(cont).setTempoRemocao(tempRemocao);
				return removido;
			}
			return null;
		} catch (InterruptedException e1) {
			return null;
		} finally {
			lock.unlock();
		}
	}

	public String toString() {
		if (esteira.isEmpty())
			return "[ ]";
		else {

			String s = "[";
			int n = esteira.size();
			for (int i = n - 1; i >= 1; i--) {
				s += esteira.get(i).getTipo() + " " + esteira.get(i).getQualidade() + " ("
						+ esteira.get(i).getTempoProducao() + "ms) | ";
			}
			s += esteira.peek().getTipo() + " " + esteira.peek().getQualidade() + " ("
					+ esteira.peek().getTempoProducao() + "ms)]";
			return s;
		}
	}
}
