package br.com.cast.controle;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.entidades.Curso;
import br.com.cast.servicos.CursoService;


@RestController
@RequestMapping(value = "/cursos")
public class CursoController {
	private static final Logger LOGGER= LoggerFactory.getLogger(CursoController.class);

	@Autowired
	private CursoService service;

	// METODO DE LISTAR CURSOS
	@GetMapping
	public ResponseEntity<List<Curso>> filtroNomeData(
			@RequestParam(required = false) String descricao,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataTermino) {
		LOGGER.info("Curso listado");
		return ResponseEntity.ok(service.filtrar(descricao, dataInicio, dataTermino));
	}
	

	// METODO DE LISTAR POR ID
	@GetMapping("/{idCurso}")
	public ResponseEntity<Curso> getByID(@PathVariable("idCurso") Long idCurso) {
		return ResponseEntity.ok(service.buscaPorId(idCurso));
	}


//========================================================================================================

	// METODO DE INSERIR CURSOS
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> gravar(@RequestBody Curso curso) {
		service.gravar(curso);
		LOGGER.info("Curso inserido");
		return ResponseEntity.ok().body("Cadastrado com Sucesso");
	}
	
//========================================================================================================

	// METODO DE DELETAR CURSOS
	@DeleteMapping("/{idCurso}")
	public ResponseEntity<String> deletar(@PathVariable("idCurso") Long idCurso) {
		service.deletar(idCurso);
		return ResponseEntity.ok().body("deletado");
	}

//========================================================================================================
	
	// METODO DE EDITAR CURSOS
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> editar(@RequestBody Curso curso) {
		service.editar(curso);
		LOGGER.info("Curso editado");
		return ResponseEntity.ok().body("Editado com Sucesso");
	}
}
