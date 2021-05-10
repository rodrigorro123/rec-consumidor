package br.com.api.application.service.impl;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.api.application.DTO.DetalheReclamacaoCreate;
import br.com.api.application.DTO.ReclamacaoCreateDto;
import br.com.api.application.DTO.ReclamacaoResponse;
import br.com.api.application.exception.ApiException;
import br.com.api.application.service.ApiService;
import br.com.api.domain.entity.Reclamacao;
import br.com.api.domain.repository.ReclamacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe para operações primarias
 * 
 * @author rodrigo
 *
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

	private final ReclamacaoRepository recRepository;
	private final ModelMapper mapper;

	@Override
	public ReclamacaoResponse createReclamacao(ReclamacaoCreateDto reclamacaoIn) throws ApiException {
		try {

			Reclamacao recSave = new Reclamacao();
			mapper.map(reclamacaoIn, recSave);
			recSave = recRepository.save(recSave);

			// converte para o objeto de retorno
			return mapper.map(recSave, ReclamacaoResponse.class);
		} catch (Exception e) {
			log.error("Erro ao criar Reclamacao - " + e);
			throw ApiException.badRequest("Erro ao criar Reclamacao", "Erro "+ e.getMessage() );
		}
	}

	@Override
	public ReclamacaoResponse updateReclamacao(ReclamacaoCreateDto reclamacaoIn) throws ApiException {
		try {
			Reclamacao recSave = validaExistReclamacao(reclamacaoIn.get_id());
			
			mapper.map(reclamacaoIn, recSave);
			recSave = recRepository.save(recSave);

			// converte para o objeto de retorno
			return mapper.map(recSave, ReclamacaoResponse.class);
		} catch (Exception e) {
			log.error("Erro ao criar Reclamacao - " + e);
			throw ApiException.badRequest("Erro ao criar Reclamacao", "Erro "+ e.getMessage() );
		}
	}
	
	@Override
	public ReclamacaoResponse addDetalheReclamacao( DetalheReclamacaoCreate detalhes) throws ApiException {
		try {
			Reclamacao recSave = validaExistReclamacao(detalhes.get_id() );

			recSave.getDetalhes().add(detalhes.getDetalhes());
			recSave = recRepository.save(recSave);

			// converte para o objeto de retorno
			return mapper.map(recSave, ReclamacaoResponse.class);
		} catch (RuntimeException e) {
			log.error("Erro ao criar Reclamacao - " + e);
			throw ApiException.badRequest("Erro ao adicionar item na Reclamacao", "Erro "+ e.getMessage() );
			
		}
	}	

	/**
	 * verifica se existe reclamacao na base
	 * 
	 * @param idReclamacao
	 * @return
	 * @throws ApiException
	 */
	private Reclamacao validaExistReclamacao(String idReclamacao) throws ApiException {
		Reclamacao reclamacao = recRepository.findById(idReclamacao).orElse(null);
		if (reclamacao == null) {
			log.error("Reclamacao nao encrontado - " + idReclamacao);
			
			throw ApiException.notFound("Dado não encontrado.",
	                "id de Reclamacao nao localizado");
		}

		return reclamacao;
	}

	/**
	 * 
	 */
	public Long getQtdeReclamacoes(String localidade, String empresa) throws ApiException {
		
		if(Strings.isNotEmpty(empresa) && Strings.isNotEmpty(localidade)) {
			return recRepository.countByEmpresaAndLocalidade(empresa, localidade);
		}else if(Strings.isNotEmpty(empresa)) {
			return recRepository.countByEmpresa(empresa);
		}else {
			return recRepository.countByLocalidade(localidade);
		}
	}

}
