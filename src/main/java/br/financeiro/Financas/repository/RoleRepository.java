package br.financeiro.Financas.repository;

import br.financeiro.Financas.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByNome(String nome);
}
