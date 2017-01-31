package br.edu.envia.service;

import java.util.List;

import br.edu.envia.model.Usuario;

public interface UsuarioService {

	Usuario save(Usuario Usuario);

	Usuario getById(int id);
	
	void delete(Usuario Usuario);
	 
	List<Usuario> listAllUsuarios();

}
