package br.edu.envia.model;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.envia.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Usuario findByNome(String nome);

	public Usuario findOne(int id);
	
}
