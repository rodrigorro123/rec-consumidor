package br.com.api;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.api.application.DTO.DetalheReclamacao;
import br.com.api.application.DTO.DetalheReclamacaoCreate;
import br.com.api.application.DTO.ReclamacaoCreateDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReclamacaoTest {

	@Autowired
	private MockMvc mockMvc;	

	@Test
	public void testACriarReclamacao() throws Exception {
		
		ReclamacaoCreateDto recl = ReclamacaoCreateDto.builder()
													  .descricao("descricao")
													  .empresa("empresa teste")
													  .localidade("Minha cidade")
													  .titulo("titulo teste")
													  .build();
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(MockMvcRequestBuilders
		.post("/rec-consumidor")
		.contentType(MediaType.APPLICATION_JSON)
		.content( ow.writeValueAsString(recl ) ) )
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful() ) ;
	}

	@Test
	public void testAtualizarReclamacao() throws Exception {
		
		ReclamacaoCreateDto recl = ReclamacaoCreateDto.builder()
				  ._id("60988a9a7d3bc9c8eb1167cf")
				  .descricao("descricao")
				  .empresa("empresa teste")
				  .localidade("Minha cidade")
				  .titulo("titulo teste")
				  .build();
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(MockMvcRequestBuilders
		.put("/rec-consumidor")
		.contentType(MediaType.APPLICATION_JSON)
		.content( ow.writeValueAsString(recl ) ) )
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful() ) ;
	}
	
	@Test
	public void testAddItemReclamacao() throws Exception {
		
		DetalheReclamacao detalhe = DetalheReclamacao.builder()
													 .descricao("descricao teste")
													 .build();
		DetalheReclamacaoCreate recl = DetalheReclamacaoCreate.builder()
				  ._id("60988a9a7d3bc9c8eb1167cf")
				  .detalhes(detalhe)
				  .build();
		
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		
		mockMvc.perform(MockMvcRequestBuilders
		.patch("/rec-consumidor")
		.contentType(MediaType.APPLICATION_JSON)
		.content( ow.writeValueAsString(recl ) ) )
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful() ) ;
	}
 

	@Test
	public void testGetReclamacaos() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
		.get("/rec-consumidor/totais?localidade=localidade&empresa=empresa")
		.contentType(MediaType.APPLICATION_JSON) )
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful() ) ;

	}

}
