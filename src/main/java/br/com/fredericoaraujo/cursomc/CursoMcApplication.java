package br.com.fredericoaraujo.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fredericoaraujo.cursomc.domain.Categoria;
import br.com.fredericoaraujo.cursomc.domain.Produto;
import br.com.fredericoaraujo.cursomc.repositories.CategoriaRepository;
import br.com.fredericoaraujo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria informatica = new Categoria(null, "Informatica");
		Categoria escritorio = new Categoria(null, "Escritório");

		Produto computador = new Produto(null, "Computador", 2000.00);
		Produto impressora = new Produto(null, "Impressora", 800.00);
		Produto mouse = new Produto(null, "Mouse", 80.00);

		informatica.getProdutos().addAll(Arrays.asList(computador, impressora, mouse));
		escritorio.getProdutos().addAll(Arrays.asList(impressora));

		computador.getCategorias().addAll(Arrays.asList(informatica));
		impressora.getCategorias().addAll(Arrays.asList(informatica, escritorio));
		mouse.getCategorias().addAll(Arrays.asList(informatica));

		this.categoriaRepository.saveAll(Arrays.asList(informatica, escritorio));
		this.produtoRepository.saveAll(Arrays.asList(computador, impressora, mouse));

	}

}
