package br.com.cast.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cast.entidades.Categoria;
import br.com.cast.repositorios.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> getAll(){
		return repository.findAll();
	}
}
