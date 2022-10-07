
import java.util.LinkedList;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		Esteira esteira = new Esteira();
		
		Scanner s = new Scanner(System.in);
		System.out.println("Insira a quantidade de produtos que sera produzida para cada tipo:");
		int quantProd = Integer.parseInt(s.next());
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
            	LinkedList<Produto> historicoLimao = new LinkedList<Produto>();
            	LinkedList<Produto> historicoAbacaxi = new LinkedList<Produto>();
            	LinkedList<Produto> historicoMorango = new LinkedList<Produto>();
            	
            	int tempoTotalProdAbacaxi = 0;
            	int tempoTotalProdMorango = 0;
            	int tempoTotalProdLimao = 0;
            	
            	int tempoTotalRemAbacaxi = 0;
            	int tempoTotalRemMorango = 0;
            	int tempoTotalRemLimao = 0;
            	
            	//Separa os produtos do historico entre cada tipo de produto
            	for(int i = 0; i < esteira.getHistorico().size(); i++) {
            		if(esteira.getHistorico().get(i).getTipo().equalsIgnoreCase("limao"))
            			historicoLimao.add(esteira.getHistorico().get(i));
            		else if(esteira.getHistorico().get(i).getTipo().equalsIgnoreCase("abacaxi"))
            			historicoAbacaxi.add(esteira.getHistorico().get(i));
            		else
            			historicoMorango.add(esteira.getHistorico().get(i));
            	}
            	
            	System.out.println("==================================================================");
                System.out.println("\tEsteira Parada. Resumo da producao e remocao:");
                System.out.println("==================================================================");
                System.out.println(" Producao de ABACAXI:");
                for(int i = 0; i < historicoAbacaxi.size(); i++) {
                	tempoTotalProdAbacaxi += historicoAbacaxi.get(i).getTempoProducao();
                	tempoTotalRemAbacaxi += historicoAbacaxi.get(i).getTempoRemocao();
                	System.out.println(" " + historicoAbacaxi.get(i));
                }
                
                System.out.println("\n Producao de LIMAO:");
                for(int i = 0; i < historicoLimao.size(); i++) {
                	tempoTotalProdLimao += historicoLimao.get(i).getTempoProducao();
                	tempoTotalRemLimao += historicoLimao.get(i).getTempoRemocao();
                	System.out.println(" " + historicoLimao.get(i));
                }
                
                System.out.println("\n Producao de MORANGO:");
                for(int i = 0; i < historicoMorango.size(); i++) {
                	tempoTotalProdMorango += historicoMorango.get(i).getTempoProducao();
                	tempoTotalRemMorango += historicoMorango.get(i).getTempoRemocao();
                	System.out.println(" " + historicoMorango.get(i));
                }
                
                System.out.println("-----------------------------------------------------------------");
                System.out.printf(" Total de abacaxis produzidos: %d\n", historicoAbacaxi.size());
                System.out.printf(" Tempo medio de producao: %.2f milisegundos.\n", 1.0*tempoTotalProdAbacaxi/historicoAbacaxi.size());
                System.out.printf(" Tempo medio de remocao: %.2f milisegundos.\n", 1.0*tempoTotalRemAbacaxi/historicoAbacaxi.size());
                System.out.println("-----------------------------------------------------------------");
                System.out.printf(" Total de limões produzidos: %d\n", historicoLimao.size());
                System.out.printf(" Tempo medio de producao: %.2f milisegundos.\n", 1.0*tempoTotalProdLimao/historicoLimao.size());
                System.out.printf(" Tempo medio de remocao: %.2f milisegundos.\n", 1.0*tempoTotalRemLimao/historicoLimao.size());
                System.out.println("-----------------------------------------------------------------");
                System.out.printf(" Total de morangos produzidos: %d\n", historicoMorango.size());
                System.out.printf(" Tempo medio de producao: %.2f milisegundos.\n", 1.0*tempoTotalProdMorango/historicoMorango.size());
                System.out.printf(" Tempo medio de remocao: %.2f milisegundos.\n", 1.0*tempoTotalRemMorango/historicoMorango.size());
                System.out.println("-----------------------------------------------------------------");
            }
        });

		//Threads de entrada de produtos na esteira
		Thread entrada_morango = new Thread(new adicionaProduto(esteira,"morango", quantProd));
		Thread entrada_limao = new Thread(new adicionaProduto(esteira, "limao", quantProd));
		Thread entrada_abacaxi = new Thread(new adicionaProduto(esteira, "abacaxi", quantProd));
		
		//Inicializa as threads
		entrada_morango.start();
		entrada_limao.start();
		entrada_abacaxi.start();
		
		//Threads de saida de produtos da esteira
		Thread saida_bons = new Thread(new removeBons(esteira));
		Thread saida_ruins = new Thread(new removeRuins(esteira));
		
		saida_bons.start();
		saida_ruins.start();
		
		try {
			entrada_morango.join();
			entrada_limao.join();
			entrada_abacaxi.join();
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		System.exit(0);
	}

}
