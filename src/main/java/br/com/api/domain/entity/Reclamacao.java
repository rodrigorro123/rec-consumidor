package br.com.api.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.transaction.Transactional;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.application.DTO.DetalheReclamacao;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe de mapeamento do banco para a reclamacao
 * 
 * @author rodrigo
 *
 */
@Transactional
@Data
@NoArgsConstructor
@Document(collection = "Reclamacao")
public class Reclamacao {

	@Id
	private String _id;
	private String titulo;
	private String descricao;
	private String empresa;
	private String localidade;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dtCriacao = LocalDateTime.now();

	private List<DetalheReclamacao> detalhes = new ArrayList<>();

}
