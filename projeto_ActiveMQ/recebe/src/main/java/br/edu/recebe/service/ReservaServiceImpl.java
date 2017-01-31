package br.edu.recebe.service;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import br.edu.recebe.model.Reserva;
import br.edu.recebe.model.ReservaRepository;

@Service
@Validated
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository repository;

    @Autowired
    public ReservaServiceImpl(final ReservaRepository repository) {
        this.repository = repository;
    }
    
    public Reserva getById(int id) {    
    	return repository.findOne(id);
    }
    
	public List<Reserva> listAllReservas() {
		return repository.findAll();
	}
	
    @Transactional
    public Reserva save(@NotNull @Valid final Reserva participant) {
    	
        Reserva existing = repository.findOne(participant.getId());
/*        
        if (existing != null) {
            throw new ReservaAlreadyExistsException(String.format("There already exists a participant with email=%s", participant.getName()));
        }
 */
        
        return repository.save(participant);
    }

	public ReservaRepository getRepository() {
		return repository;
	}


	   public void delete(Reserva reserva) {    
	       repository.delete(reserva);
	    }
	    


}
