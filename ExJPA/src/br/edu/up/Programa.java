package br.edu.up;

//import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import br.edu.up.model.Filme;
import br.edu.up.model.Pessoa;
import br.edu.up.model.Serie;


public class Programa {
	
	static EntityManagerFactory emf;
	static EntityManager em;

	public static void main(String[] args) {
		
		emf = Persistence.createEntityManagerFactory("prj-jpa-sqlite");
		em = emf.createEntityManager();
		
		
		Menu();

}
	

	public static void Menu() {
		Scanner leitor = new Scanner(System.in);
	
		int op = 0;
		do {
			System.out.println("\n\n---------MENU---------\n");
			System.out.println(" 1 - Cadastrar Pessoa");
			System.out.println(" 2 - Cadastrar Filme");
			System.out.println(" 3 - Cadastrar Série");
			System.out.println(" 4 - Listar Pessoas");
			System.out.println(" 5 - Listar Filmes");
			System.out.println(" 6 - Listar Séries");
			System.out.println(" 7 - Remover Pessoa");
			System.out.println(" 8 - Remover Filme");
			System.out.println(" 9 - Remover Série");
			System.out.println(" 10 - Sair");
			System.out.print("\nInforme uma opção: ");
			op = leitor.nextInt();
			leitor.nextLine();
	
		switch (op) {
		case 1:
			Pessoa novaPessoa = new Pessoa();
			novaPessoa.CadastroPessoa(leitor);
			Pessoa.salvarP(novaPessoa);
			break;
		case 2:
			Filme novoFilme = new Filme();
			novoFilme.CadastroFilme(leitor);
			Filme.salvarF(novoFilme);
			break;
		case 3:
			Serie novaSerie = new Serie();
			novaSerie.CadastroSerie(leitor);
			Serie.salvarS(novaSerie);
			break;
		case 4:
			Pessoa.imprimirPessoas(Pessoa.listarPessoas());
			break;
		case 5:
			Filme.imprimirFilmes(Filme.listarFilmes());
			break;
		case 6:
			Serie.imprimirSeries(Serie.listarSeries());
			break;
		case 7:
			System.out.println("Digite o id: ");
			Pessoa.apagarP(leitor.nextInt());
			break;
		case 8:
			System.out.println("Digite o id: ");
			Filme.apagar(leitor.nextInt());
			break;
		case 9:
			System.out.println("Digite o id: ");
			Serie.apagarS(leitor.nextInt());
			break;
		 default:
			System.out.println("Opção Inválida");
			break;
			}
		
		
		
		}while(op != 10);
	
		leitor.close();
	}
}