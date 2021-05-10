package br.com.api.application.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ReclamacaoResponse{

	private String _id;
	private String titulo;
	private String descricao;
	private String empresa;
	private String localidade;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dtCriacao;
	private List<DetalheReclamacao> detalhes;
				
}
