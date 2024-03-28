package br.com.solocraft.repository;

import br.com.solocraft.model.RelatorioIndividual;
import br.com.solocraft.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioIndividualRepository extends JpaRepository<RelatorioIndividual, Long> {

    public RelatorioIndividual findByTask (Task task);
}
