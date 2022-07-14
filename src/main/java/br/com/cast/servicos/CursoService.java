package br.com.cast.servicos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.CastGroupApplication;
import br.com.cast.entidades.Curso;
import br.com.cast.exception.ErroDeNegocioException;
import br.com.cast.repositorios.CursoRepository;

@Service
public class CursoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CastGroupApplication.class);

	@Autowired
	private CursoRepository repository;

	@PersistenceContext
	EntityManager em;

	// METODO DE FILTRAR
	public List<Curso> filtrar(String descricao, LocalDate dataInicio, LocalDate dataTermino) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Curso> cq = cb.createQuery(Curso.class);

		Root<Curso> curso = cq.from(Curso.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (descricao != null) {
			Predicate descricaoPredicate = cb.equal(curso.get("descricao"), descricao);
			predicates.add(descricaoPredicate);
		}
		if (dataInicio != null) {
			Predicate dataInicioPredicate = cb.greaterThanOrEqualTo(curso.get("dataInicio"), dataInicio);
			predicates.add(dataInicioPredicate);
		}
		if (dataTermino != null) {
			Predicate dataTerminoPredicate = cb.lessThanOrEqualTo(curso.get("dataTermino"), dataTermino);
			predicates.add(dataTerminoPredicate);
		}
		
		Predicate[]predicateArray = new Predicate[predicates.size()];
		
		predicates.toArray(predicateArray);
		
		cq.where(predicateArray);

		TypedQuery<Curso> query = em.createQuery(cq);
		
		return query.getResultList();
	}

	// METODO DE BUSCAR POR ID
	public Curso buscaPorId(Long idCurso) {
		Optional<Curso> item = repository.findById(idCurso);
		if (item.isEmpty()) {
			throw new ErroDeNegocioException("Curso não encontrado !");
		}
		Curso curso = item.get();
		return curso;

	}

//========================================================================================================================

	// METODO DE GRAVAR CURSO
	@Transactional
	public void gravar(Curso curso) {
		validarGravar(curso);
		repository.save(curso);
	}

	// REGRA DE NEGOCIO DE GRAVAR
	private void validarGravar(Curso curso) {
		if (curso.getDataInicio().isBefore(LocalDate.now())) {
			throw new ErroDeNegocioException("ERRO, data de inicio menor que a data atual");
		}
		Long countcurso = repository.consultaDatas(curso.getDataInicio(), curso.getDataTermino());
		if (countcurso > 0) {
			throw new ErroDeNegocioException("Existe(m) curso(s) planejados(s) dentro do período informado.");
		}
	}

//========================================================================================================================	

	// METODO DE DELETAR CURSO PELO ID
	@Transactional
	public void deletar(long idCurso) {
		validarDeletar(idCurso);
		repository.deleteById(idCurso);
	}

	// REGRA DE NEGOCIO DE DELETAR
	private void validarDeletar(long idCurso) {
		Optional<Curso> cursoOptional = repository.findById(idCurso);
		Curso curso = cursoOptional.get();

		if (curso == null) {
			throw new ErroDeNegocioException("Curso nao encontrado");
		}

		if (curso.getDataTermino().isBefore(LocalDate.now())) {
			throw new ErroDeNegocioException("Exclusao invalida, curso ja realizado");
		}
	}

//========================================================================================================================

	// METODO DE EDITAR
	@Transactional
	public void editar(Curso curso) {
		validarEditar(curso);
		repository.save(curso);
	}
	
	private void validarEditar(Curso curso) {
		Long countcurso = repository.consultaDatasEditar(curso.getDataInicio(), curso.getDataTermino(), curso.getIdCurso());
		if (countcurso > 0) {
			throw new ErroDeNegocioException("Existe(m) curso(s) planejados(s) dentro do período informado.");
		}
		if (curso.getDataInicio().isBefore(LocalDate.now())) {
			throw new ErroDeNegocioException("ERRO, data de inicio menor que a data atual");
		}
	}
}
