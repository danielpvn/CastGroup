package br.com.cast.entidades;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "curso")
@Audited
@AuditTable("cursolog")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idcurso")
	private Long idCurso;
	
	@LastModifiedBy
	@Column(name = "usuario")
	private String usuario = "Daniel";
	
	
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
	
	
}
