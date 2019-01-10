package br.com.fredericoaraujo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fredericoaraujo.cursomc.domain.Cliente;
import br.com.fredericoaraujo.cursomc.exception.ObjectNotFoundException;
import br.com.fredericoaraujo.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado de  Id: " + id + ", do tipo: " + Cliente.class.getName()));
	}

}
