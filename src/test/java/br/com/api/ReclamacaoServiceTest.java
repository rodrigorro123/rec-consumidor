package br.com.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.api.application.DTO.DetalheReclamacao;
import br.com.api.application.DTO.DetalheReclamacaoCreate;
import br.com.api.application.DTO.ReclamacaoCreateDto;
import br.com.api.application.DTO.ReclamacaoResponse;
import br.com.api.application.service.ApiService;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class ReclamacaoServiceTest {
	
	@Autowired
	private ApiService service;

	@Test
	public void testACriarReclamacao() {
		try {

			ReclamacaoCreateDto recl = ReclamacaoCreateDto.builder()
					  .descricao("descricao service")
					  .empresa("empresa teste service")
					  .localidade("Minha cidade service")
					  .titulo("titulo service")
					  .build();
			
			ReclamacaoResponse ret = this.service.createReclamacao(recl);

			assertThat(ret.getDescricao()).isEqualTo("descricao service") ;
			assertThat(ret.getDtCriacao() ).isNotNull();
			assertThat(ret.getEmpresa()).isEqualTo("empresa teste service" );
			assertThat(ret.getLocalidade()).isEqualTo("Minha cidade service");
			assertThat(ret.getTitulo()).isEqualTo("titulo service");

		} catch (Exception e) {
			log.error("falha ao realizar testes de criação do reclamacao - {}", e.getMessage());
		}
	}	
	
	
	@Test
	public void testUpdateReclamacao() {
		try {

			ReclamacaoCreateDto recl = ReclamacaoCreateDto.builder()
					  ._id("60988a9a7d3bc9c8eb1167cf")
					  .descricao("descricao service")
					  .empresa("empresa teste service")
					  .localidade("Minha cidade service")
					  .titulo("titulo service")
					  .build();
			
			ReclamacaoResponse ret = this.service.updateReclamacao(recl);

			assertThat(ret.getDescricao()).isEqualTo("descricao service") ;
			assertThat(ret.getDtCriacao() ).isNotNull();
			assertThat(ret.getEmpresa()).isEqualTo("empresa teste service" );
			assertThat(ret.getLocalidade()).isEqualTo("Minha cidade service");
			assertThat(ret.getTitulo()).isEqualTo("titulo service");

		} catch (Exception e) {
			log.error("falha ao realizar testes de atualizacao de reclamacao - {}", e.getMessage());
		}
	}	

	@Test
	public void testAddItemReclamacao() {
		try {

			DetalheReclamacao detalhe = DetalheReclamacao.builder()
					 .descricao("descricao teste")
					 .build();
			DetalheReclamacaoCreate recl = DetalheReclamacaoCreate.builder()
			._id("60988a9a7d3bc9c8eb1167cf")
			.detalhes(detalhe)
			.build();
			
			ReclamacaoResponse ret = this.service.addDetalheReclamacao(recl);
			assertThat(ret.getDetalhes() ).isNotNull();

		} catch (Exception e) {
			log.error("falha ao realizar testes de adicao de item na reclamacao - {}", e.getMessage());
		}
	}	
	
	@Test
	public void testQtdeReclamacao() {
		try {
			Long qtde = this.service.getQtdeReclamacoes("localidade", "empresa");
			
			assertThat(qtde).isGreaterThan(0);
			
		} catch (Exception e) {
			log.error("falha ao realizar testes de qtde de reclamacao - {}", e.getMessage());
		}
	}
}
