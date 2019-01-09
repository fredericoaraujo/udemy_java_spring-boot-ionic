package br.com.fredericoaraujo.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fredericoaraujo.cursomc.domain.Categoria;
import br.com.fredericoaraujo.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria informatica = new Categoria(null, "Informatica");
		Categoria escritorio = new Categoria(null, "Escritório");
		
		this.categoriaRepository.saveAll(Arrays.asList(informatica, escritorio));
		System.out.println("*****************************************");
		System.out.println(this.categoriaRepository.getOne(1));
		System.out.println("*****************************************");
		
		
	}

}

