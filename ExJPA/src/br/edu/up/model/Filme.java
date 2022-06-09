package br.edu.up.model;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "filmes")
public class Filme implements Serializable {

	static EntityManagerFactory emf;
	static EntityManager em;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	private String diretor;
	private String genero;
	private String dataL;
	private Integer nota;
	
	public static void iniciarEm() {
		emf = Persistence.createEntityManagerFactory("prj-jpa-sqlite");
		em = emf.createEntityManager();
	}
	
	public Integer getId() {
		
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getDataL() {
		return dataL;
	}
	public void setDataL(String dataL) {
		this.dataL = dataL;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}

	// ------------------------- TABELA FILME -------------------------------- 

		public static List<Filme> listarFilmes() {
			iniciarEm();
			List<Filme> filmes = 
					em.createQuery("from Filme", Filme.class)
					.getResultList();
			return filmes;
		}
		

		public static Integer salvarF(Filme filme) {
			iniciarEm();
			em.getTransaction().begin();
			em.persist(filme);
			em.getTransaction().commit();
			return filme.getId();
		}

		public static Filme localizar(Integer id) {
			iniciarEm();
			Filme filme = em.find(Filme.class, id);
			return filme;
		}
		

		public static void atualizar(Filme filme) {
			iniciarEm();
			em.getTransaction().begin();
			em.merge(filme);
			em.getTransaction().commit();
		}

		public static void apagar(Integer id) {
			iniciarEm();
			Filme filme = em.find(Filme.class, id);
			em.getTransaction().begin();
			em.remove(filme);
			em.getTransaction().commit();
		}

		@SuppressWarnings("unused")
		private static void imprimir(Filme filme) {
			iniciarEm();
					System.out.println(filme);
	    }
		
		public static void imprimirFilmes(List<Filme> filmes)
		{
			for (Filme filme : filmes) {
				System.out.println();
				System.out.println("------------------");
				System.out.println();
				System.out.println("ID: " + filme.getId());
				System.out.println("Nome: " + filme.getNome());
				System.out.println("Diretor: " + filme.getDiretor());
				System.out.println("Gênero: " + filme.getGenero());
				System.out.println("Data lançamento: " + filme.getDataL());
				System.out.println("Nota: " + filme.getNota());
			}
		}
		
		public  void CadastroFilme(Scanner leitor) {
				
			System.out.println("\n\n---------CADASTRAR FILMES---------\n");	
				System.out.print("Informe o nome do filme: ");
				setNome(leitor.nextLine());
				System.out.print("Informe o diretor do filme: ");
				setDiretor(leitor.nextLine());
				System.out.print("Informeo gênero do filme: ");
				setGenero(leitor.nextLine());
				System.out.print("Informe a data de lançamento do filme: ");
				setDataL(leitor.nextLine());		
				System.out.print("Informe a nota do filme: ");
				setNota(leitor.nextInt());		
				leitor.nextLine();
				
				
			}

}