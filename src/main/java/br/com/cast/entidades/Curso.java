package br.com.cast.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "curso")
public class Curso extends Log implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idcurso")
	private Long idCurso;
	
	
	@Column(name="descricao",nullable = false)
	private String descricao;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="datainicio",nullable = false)
	private LocalDate dataInicio;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="datatermino",nullable = false)
	private LocalDate dataTermino;
	
	@Column(name="alunos")
	private Integer alunos;
	
		
	@JoinColumn(name = "categoria")
	@ManyToOne
	private Categoria categoria;
	
	public Curso() {
		//TODO Auto-generated constructor stub
	}

	public Curso(Long idCurso, String descricao, LocalDate dataInicio, LocalDate dataTermino, Integer alunos,
			Categoria categoria) {
		super();
		this.idCurso = idCurso;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.alunos = alunos;
		this.categoria = categoria;
	}

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Integer getAlunos() {
		return alunos;
	}

	public void setAlunos(Integer alunos) {
		this.alunos = alunos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
}
