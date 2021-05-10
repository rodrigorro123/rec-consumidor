package br.com.api.application.DTO;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ReclamacaoCreateDto{

	private String _id;
	@NotEmpty
	 private String titulo;
	@NotEmpty
	 private String descricao;
	@NotEmpty
	 private String empresa;
	@NotEmpty
	 private String localidade;
				
}
