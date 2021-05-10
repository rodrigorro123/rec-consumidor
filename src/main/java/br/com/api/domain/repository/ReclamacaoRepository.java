package br.com.api.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.api.domain.entity.Reclamacao;

/**
 * interface de acesso aos dados
 * 
 * @author rodrigo
 *
 */
public interface ReclamacaoRepository extends MongoRepository<Reclamacao, String> {

	Long countByEmpresaAndLocalidade(String empresa, String localidade);

	Long countByEmpresa(String empresa);

	Long countByLocalidade(String localidade);

}
