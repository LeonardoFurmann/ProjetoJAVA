package br.edu.up;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.up.model.Filme;
import br.edu.up.model.Pessoa;
import br.edu.up.model.Serie;


public class Programa {
	
	static EntityManagerFactory emf;
	static EntityManager em;

	public static void main(String[] args) {
		
		emf = Persistence.createEntityManagerFactory("prj-jpa-sqlite");
		em = emf.createEntityManager();
		
		
//		Cadastro();

}
	
public static void Cadastro() {
	Scanner leitor = new Scanner(System.in);
		Pessoa novaPessoa = new Pessoa();
		
		System.out.println("Informe seu nome: ");
		novaPessoa.setNome(leitor.nextLine());
		System.out.println("Informe seu email: ");
		novaPessoa.setEmail(leitor.nextLine());
		System.out.println("Informe seu número: ");
		novaPessoa.setNumero(leitor.nextInt());
		leitor.nextLine();
		System.out.println("Informe sua senha: ");
		novaPessoa.setSenha(leitor.nextLine());		
		
//		salvarP(novaPessoa);
		
		leitor.close();
	}

	
	
// ------------------------- TABELA FILME -------------------------------- 

	public static List<Filme> listarFilmes() {
		List<Filme> filmes = 
				em.createQuery("from Filme", Filme.class)
				.getResultList();
		return filmes;
	}
	

	public static Integer salvar(Filme filme) {
		em.getTransaction().begin();
		em.persist(filme);
		em.getTransaction().commit();
		return filme.getId();
	}

	public static Filme localizar(Integer id) {
		Filme filme = em.find(Filme.class, id);
		return filme;
	}
	

	public static void atualizar(Filme filme) {
		em.getTransaction().begin();
		em.merge(filme);
		em.getTransaction().commit();
	}

	public static void apagar(Integer id) {
		Filme filme = em.find(Filme.class, id);
		em.getTransaction().begin();
		em.remove(filme);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unused")
	private static void imprimir(Filme filme) {
				System.out.println(filme);
    }


	
	
	
// ------------------------- TABELA PESSOA -------------------------------- 

public static List<Pessoa> listarPessoas() {
	List<Pessoa> pessoas = 
			em.createQuery("from Pessoa", Pessoa.class)
			.getResultList();
	return pessoas;
}


public static Integer salvarP(Pessoa pessoa) {
	em.getTransaction().begin();
	em.persist(pessoa);
	em.getTransaction().commit();
	return pessoa.getId();
}

public static Pessoa localizarP(Integer id) {
	Pessoa pessoa = em.find(Pessoa.class, id);
	return pessoa;
}


public static void atualizarP(Pessoa pessoa) {
	em.getTransaction().begin();
	em.merge(pessoa);
	em.getTransaction().commit();
}

public static void apagarP(Integer id) {
	Pessoa pessoa = em.find(Pessoa.class, id);
	em.getTransaction().begin();
	em.remove(pessoa);
	em.getTransaction().commit();
}

@SuppressWarnings("unused")
private static void imprimir(Serie pessoa) {
			System.out.println(pessoa);
}





// ------------------------------ TABELA SERIES ------------------------- 

public static List<Serie> listarSeries() {
	List<Serie> series = 
			em.createQuery("from Serie", Serie.class)
			.getResultList();
	return series;
}


public static Integer salvarS(Serie serie) {
	em.getTransaction().begin();
	em.persist(serie);
	em.getTransaction().commit();
	return serie.getId();
}

public static Serie localizarS(Integer id) {
	Serie serie = em.find(Serie.class, id);
	return serie;
}


public static void atualizarS(Serie serie) {
	em.getTransaction().begin();
	em.merge(serie);
	em.getTransaction().commit();
}

public static void apagarS(Integer id) {
	Serie serie = em.find(Serie.class, id);
	em.getTransaction().begin();
	em.remove(serie);
	em.getTransaction().commit();
}

@SuppressWarnings("unused")
private static void imprimirS(Serie serie) {
			System.out.println(serie);
			

}
}