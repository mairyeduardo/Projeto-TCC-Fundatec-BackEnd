package br.com.solocraft.repository;

import br.com.solocraft.model.Cliente;
import br.com.solocraft.model.Usuario;
import com.mysql.cj.xdevapi.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Cliente findByNome(String nome);

    public Cliente findByTelefone(String telefone);

    public Cliente findByUsuario(Usuario usuario);
}
