package br.com.solocraft.repository;

import br.com.solocraft.model.Cliente;
import br.com.solocraft.model.Task;
import br.com.solocraft.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public Task findByTitulo(String titulo);

    public Task findByValorServico(BigDecimal valorServico);

    public Task findByDataInicio(LocalDate dataInicio);

    public Task findByEnderecoServico(String enderecoServico);

    public List<Task> findByUsuario(Usuario usuario);

    public List<Task> findByCliente(Cliente cliente);
}
