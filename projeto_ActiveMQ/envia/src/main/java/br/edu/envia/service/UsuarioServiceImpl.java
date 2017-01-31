package br.edu.envia.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.edu.envia.model.Usuario;
import br.edu.envia.model.UsuarioRepository;


@Service
@Validated
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(final UsuarioRepository repository) {
        this.repository = repository;
    }
    
    public Usuario getById(int id) {    
    	return repository.findOne(id);
    }
    
	public List<Usuario> listAllUsuarios() {
		return repository.findAll();
	}
	
    @Transactional
    public Usuario save(@NotNull @Valid final Usuario participant) {
    	
        Usuario existing = repository.findOne(participant.getId());
/*        
        if (existing != null) {
            throw new UsuarioAlreadyExistsException(String.format("There already exists a participant with email=%s", participant.getName()));
        }
 */
        
        return repository.save(participant);
    }

	public UsuarioRepository getRepository() {
		return repository;
	}
	
	  public void delete(Usuario Usuario) {    
	       repository.delete(Usuario);
	    }
	    
}
