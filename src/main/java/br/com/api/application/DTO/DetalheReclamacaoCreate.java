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
public class DetalheReclamacaoCreate {

	@NotEmpty
	private String _id;
	private DetalheReclamacao detalhes;

}
