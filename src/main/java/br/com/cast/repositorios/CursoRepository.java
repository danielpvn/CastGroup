package br.com.cast.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cast.entidades.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	@Query("select c from Curso c where c.descricao like Concat('%',:valor,'%')")
	List<Curso> buscaDescricao(@Param("valor") String valor);
	

	@Query(value = "select count(c.idCurso) from Curso c where c.dataInicio between :di and :dt "
			+ "or (c.dataTermino between :di and :dt)" 
			+ "or (c.dataInicio <= :di and c.dataTermino >= :dt)"
			+ "or (c.dataInicio >= :di and c.dataTermino <= :dt)")
	public Long consultaDatas(@Param("di") LocalDate dataInicio, @Param("dt") LocalDate dataTermino);

	@Query(value = "select count(c.idCurso) from Curso c where (c.dataInicio between :di and :dt "
			+ "or (c.dataTermino between :di and :dt)" 
			+ "or (c.dataInicio <= :di and c.dataTermino >= :dt)"
			+ "or (c.dataInicio >= :di and c.dataTermino <= :dt))"
			+ "and (c.idCurso != :idc)")
	public Long consultaDatasEditar(@Param("di") LocalDate dataInicio, @Param("dt") LocalDate dataTermino, @Param("idc") Long idCurso);
	
}
