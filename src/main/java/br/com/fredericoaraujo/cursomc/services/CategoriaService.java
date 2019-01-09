package br.com.fredericoaraujo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fredericoaraujo.cursomc.domain.Categoria;
import br.com.fredericoaraujo.cursomc.exception.ObjectNotFoundException;
import br.com.fredericoaraujo.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado de  Id: " + id + ", do tipo: " + Categoria.class.getName()));
	}

}
