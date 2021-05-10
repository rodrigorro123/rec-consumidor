package br.com.api.application.controller;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.application.DTO.DetalheReclamacaoCreate;
import br.com.api.application.DTO.ReclamacaoCreateDto;
import br.com.api.application.exception.ApiException;
import br.com.api.application.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;


/**
 * Classe de controller da api de rec-consumidor
 * @author rodrigo
 *
 */
@RestController
@Api(value = "Java dev backend")
@CrossOrigin
@RequestMapping("/rec-consumidor")
@RequiredArgsConstructor
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Registro processado com sucesso"),
		@ApiResponse(code = 201, message = "Registro criado com sucesso"),
		@ApiResponse(code = 202, message = "Registro alterado com sucesso"),
		@ApiResponse(code = 400, message = "Requisiçao invalida"),
		@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado"),
		@ApiResponse(code = 428, message = "Pre-Requisito necessario"),
		@ApiResponse(code = 500, message = "Erro interno")
	     })
public class ApiController {

	private final ApiService reclamacaoService;


	/**
	 * metodo para retornar quantidade de reclamações geradas
	 * @param reclamacao
	 * @return
	 * @throws ApiException 
	 */
	@GetMapping(path = "/totais")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "metodo para retornar reclamacao com seu respectivo total")
	@Produces(value=MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getReclamacao (@RequestParam(required = false, name = "localidade" ) String localidade,
										 	@RequestParam(required = false, name = "empresa") String empresa) throws ApiException {
		
		if(Strings.isEmpty(empresa) && Strings.isEmpty(localidade)) {
			throw ApiException.preconditionFailed("Campos nao informados", "Favor informar 1 campo para pesquisa");
		}
		return ResponseEntity.ok(reclamacaoService.getQtdeReclamacoes(localidade, empresa).toString() );
				
	}

	/**
	 * metodo para criar reclamacao
	 * @param reclamacao
	 * @return
	 * @throws ApiException 
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "metodo para criar reclamacao")
	@Produces(value=MediaType.APPLICATION_JSON)
	public ResponseEntity<?> createReclamacao (@Valid @RequestBody ReclamacaoCreateDto reclamacao) throws ApiException {
			
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(reclamacaoService.createReclamacao(reclamacao));
	}
	
	/**
	 * metodo para adicionar detalhe reclamacao
	 * @param reclamacao
	 * @return
	 * @throws ApiException 
	 */
	@PatchMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = "metodo para adicionar detalhe reclamacao")
	@Produces(value=MediaType.APPLICATION_JSON)
	public ResponseEntity<?> addDetalheReclamacao (@Valid @RequestBody DetalheReclamacaoCreate detalhereclamacao) throws ApiException {

		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(reclamacaoService.addDetalheReclamacao(detalhereclamacao));
	}
	
	/**
	 * metodo para atualizar reclamacao
	 * @param reclamacao
	 * @return
	 * @throws ApiException 
	 */
	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = "metodo para atualizar reclamacao")
	@Produces(value=MediaType.APPLICATION_JSON)
	public ResponseEntity<?> atualizarReclamacao (@Valid @RequestBody ReclamacaoCreateDto reclamacao) throws ApiException {

		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(reclamacaoService.updateReclamacao(reclamacao));

	}


}
