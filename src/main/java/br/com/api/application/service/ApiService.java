package br.com.api.application.service;

import br.com.api.application.DTO.DetalheReclamacaoCreate;
import br.com.api.application.DTO.ReclamacaoCreateDto;
import br.com.api.application.DTO.ReclamacaoResponse;
import br.com.api.application.exception.ApiException;


public interface ApiService {
 
	/**
	 * cria reclamacao
	 * @param reclamacao
	 * @return
	 * @throws ApiException
	 */
	ReclamacaoResponse createReclamacao (ReclamacaoCreateDto reclamacao) throws  ApiException;
	
	/**
	 * Atualizar reclamacao gravada
	 * @param reclamacaoIn
	 * @return
	 * @throws ApiException
	 */
	public ReclamacaoResponse updateReclamacao(ReclamacaoCreateDto reclamacaoIn) throws ApiException ;
	
	/**
	 * adicona item reclamacao
	 * @param detalhes
	 * @return
	 * @throws ApiException
	 */
	ReclamacaoResponse addDetalheReclamacao( DetalheReclamacaoCreate detalhes) throws ApiException;
	
	/**
	 * retorna sumario de reclamacoes
	 * @param localidade
	 * @param empresa
	 * @return
	 * @throws ApiException
	 */
	Long getQtdeReclamacoes(String localidade, String empresa ) throws ApiException;
	 
	
}
