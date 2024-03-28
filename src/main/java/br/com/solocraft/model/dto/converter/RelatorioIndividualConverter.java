package br.com.solocraft.model.dto.converter;

import br.com.solocraft.model.RelatorioIndividual;
import br.com.solocraft.model.dto.RelatorioIndividualResponseDTO;
import br.com.solocraft.model.dto.TaskResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class RelatorioIndividualConverter {


    public static RelatorioIndividualResponseDTO converterEntidadeParaDTO(RelatorioIndividual relatorioIndividual){
        RelatorioIndividualResponseDTO relatorioIndividualResponseDTO = new RelatorioIndividualResponseDTO();
        relatorioIndividualResponseDTO.setId(relatorioIndividual.getId());
        relatorioIndividualResponseDTO.setIdTarefa(relatorioIndividual.getTask().getId());
        relatorioIndividualResponseDTO.setTituloTarefa(relatorioIndividual.getTask().getTitulo());
        relatorioIndividualResponseDTO.setDescricaoTarefa(relatorioIndividual.getTask().getDescricao());
        relatorioIndividualResponseDTO.setValorServico(relatorioIndividual.getTask().getValorServico());
        relatorioIndividualResponseDTO.setCustoAtual(relatorioIndividual.getTask().getCustoAtual());
        relatorioIndividualResponseDTO.setDataInicio(relatorioIndividual.getTask().getDataInicio());
        relatorioIndividualResponseDTO.setDataFinal(relatorioIndividual.getTask().getDataFinal());
        relatorioIndividualResponseDTO.setTotalDeDias(relatorioIndividual.getTotalDeDias());
        relatorioIndividualResponseDTO.setEnderecoServico(relatorioIndividual.getTask().getEnderecoServico());
        relatorioIndividualResponseDTO.setNomeCliente(relatorioIndividual.getTask().getCliente().getNome());
        relatorioIndividualResponseDTO.setValorLiquido(relatorioIndividual.getValorLiquido());

        return relatorioIndividualResponseDTO;
    }
}
