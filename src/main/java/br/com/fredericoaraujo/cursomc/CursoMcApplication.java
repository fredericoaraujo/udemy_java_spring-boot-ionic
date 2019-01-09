package br.com.fredericoaraujo.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fredericoaraujo.cursomc.domain.Categoria;
import br.com.fredericoaraujo.cursomc.domain.Cidade;
import br.com.fredericoaraujo.cursomc.domain.Cliente;
import br.com.fredericoaraujo.cursomc.domain.Endereco;
import br.com.fredericoaraujo.cursomc.domain.Estado;
import br.com.fredericoaraujo.cursomc.domain.Produto;
import br.com.fredericoaraujo.cursomc.domain.enums.TipoCliente;
import br.com.fredericoaraujo.cursomc.repositories.CategoriaRepository;
import br.com.fredericoaraujo.cursomc.repositories.CidadeRepository;
import br.com.fredericoaraujo.cursomc.repositories.ClienteRepository;
import br.com.fredericoaraujo.cursomc.repositories.EnderecoRepository;
import br.com.fredericoaraujo.cursomc.repositories.EstadoRepository;
import br.com.fredericoaraujo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

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
		
		Estado mg = new Estado(null, "Minas Gerais");
		Estado sp = new Estado(null, "São Paulo");
		
		Cidade uberlandia = new Cidade(null, "Uberlândia", mg);
		Cidade saoPaulo = new Cidade(null, "São Paulo", sp);
		Cidade campinas = new Cidade(null, "Campinas", sp);
		
		mg.getCidades().addAll(Arrays.asList(uberlandia));
		sp.getCidades().addAll(Arrays.asList(saoPaulo, campinas));
		
		estadoRepository.saveAll(Arrays.asList(mg, sp));
		cidadeRepository.saveAll(Arrays.asList(uberlandia, saoPaulo, campinas));
		
		Cliente maria = new Cliente(null, "Maria da Silva", "maria@gmail.com", "12345678900", TipoCliente.PESSOA_FISICA);
		
		maria.getTelefones().addAll(Arrays.asList("12345-6789", "98765-4321"));
		
		Endereco e1 = new Endereco(null, "Rua das flores", "103", "casa", "XPTO", "70000-000", uberlandia, maria);
		Endereco e2 = new Endereco(null, "Rua Franciso Sinkus", "19A", "casa", "São Miguel PTA", "08000-000", saoPaulo, maria);
		
		maria.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(maria);
		enderecoRepository.saveAll(maria.getEnderecos());

	}

}
