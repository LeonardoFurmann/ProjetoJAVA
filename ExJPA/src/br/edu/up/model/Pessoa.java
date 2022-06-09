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
@Table(name = "usuario")
public class Pessoa implements Serializable {

	
	static EntityManagerFactory emf;
	static EntityManager em;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	private Integer numero;
	private String email;
	private String senha;
	
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
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	// ------------------------- TABELA PESSOA -------------------------------- 

	public static List<Pessoa> listarPessoas() {
		iniciarEm();
		List<Pessoa> pessoas = 
				em.createQuery("from Pessoa", Pessoa.class)
				.getResultList();
		return pessoas;
	}


	public static Integer salvarP(Pessoa pessoa) {
		iniciarEm();
		em.getTransaction().begin();
		em.persist(pessoa);
		em.getTransaction().commit();
		return pessoa.getId();
	}

	public static Pessoa localizarP(Integer id) {
		iniciarEm();
		Pessoa pessoa = em.find(Pessoa.class, id);
		return pessoa;
	}


	public static void atualizarP(Pessoa pessoa) {
		iniciarEm();
		em.getTransaction().begin();
		em.merge(pessoa);
		em.getTransaction().commit();
	}

	public static void apagarP(Integer id) {
		iniciarEm();
		Pessoa pessoa = em.find(Pessoa.class, id);
		em.getTransaction().begin();
		em.remove(pessoa);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unused")
	private static void imprimir(Serie pessoa) {
		iniciarEm();
				System.out.println(pessoa);
	}
	
	public static void imprimirPessoas(List<Pessoa> pessoas)
	{
		for (Pessoa pessoa : pessoas) {
			System.out.println();
			System.out.println("------------------");
			System.out.println();
			System.out.println("ID: " + pessoa.getId());
			System.out.println("Nome: " + pessoa.getNome());
			System.out.println("Email: " + pessoa.getEmail());
		}
	}
	
	public  void CadastroPessoa(Scanner leitor) {
			
		System.out.println("\n\n---------CADASTRAR PESSOAS---------\n");
			System.out.print("Informe seu nome: ");
			setNome(leitor.nextLine());
			System.out.print("Informe seu email: ");
			setEmail(leitor.nextLine());
			System.out.print("Informe seu número: ");
			setNumero(leitor.nextInt());
			leitor.nextLine();
			System.out.print("Informe sua senha: ");
			setSenha(leitor.nextLine());		
			
		}
}

