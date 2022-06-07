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

@Entity
@Table(name = "series")
public class Serie implements Serializable {
	
	static EntityManagerFactory emf;
	static EntityManager em;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	private String genero;
	private String dataL;
	private Integer nota;
	private int temporadas;
	
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
	public int getTemporadas() {
		return temporadas;
	}
	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
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
	// ------------------------------ TABELA SERIES ------------------------- 

	public static List<Serie> listarSeries() {
		iniciarEm();
		List<Serie> series = 
				em.createQuery("from Serie", Serie.class)
				.getResultList();
		return series;
	}


	public static Integer salvarS(Serie serie) {
		iniciarEm();
		em.getTransaction().begin();
		em.persist(serie);
		em.getTransaction().commit();
		return serie.getId();
	}

	public static Serie localizarS(Integer id) {
		iniciarEm();
		Serie serie = em.find(Serie.class, id);
		return serie;
	}


	public static void atualizarS(Serie serie) {
		iniciarEm();
		em.getTransaction().begin();
		em.merge(serie);
		em.getTransaction().commit();
	}

	public static void apagarS(Integer id) {
		iniciarEm();
		Serie serie = em.find(Serie.class, id);
		em.getTransaction().begin();
		em.remove(serie);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unused")
	private static void imprimirS(Serie serie) {
		iniciarEm();
				System.out.println(serie);
				
	}
	
	public static void imprimirSeries(List<Serie> series)
	{
		for (Serie serie : series) {
			System.out.println();
			System.out.println("------------------");
			System.out.println();
			System.out.println("ID: " + serie.getId());
			System.out.println("Nome: " + serie.getNome());
			System.out.println("Gênero: " + serie.getGenero());
			System.out.println("Data lançamento: " + serie.getDataL());
			System.out.println("Nota: " + serie.getNota());
			System.out.println("Número de temporadas: " + serie.getTemporadas());
		}
	}
	
	public  void CadastroSerie(Scanner leitor) {
			
		System.out.println("\n\n---------CADASTRAR SÉRIES---------\n");
			System.out.print("Informe o nome do série: ");
			setNome(leitor.nextLine());
			System.out.print("Informeo gênero da série: ");
			setGenero(leitor.nextLine());
			System.out.print("Informe a data de lançamento da série: ");
			setDataL(leitor.nextLine());		
			System.out.print("Informe a nota da série: ");
			setNota(leitor.nextInt());	
			System.out.print("Informe a quantidades de temporadas da série: ");
			setTemporadas(leitor.nextInt());
			leitor.nextLine();
			
			
	}
}