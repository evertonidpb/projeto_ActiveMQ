package br.edu.recebe.service;

import java.util.List;
import br.edu.recebe.model.*;

public interface ReservaService {

	Reserva save(Reserva r);

	Reserva getById(int id);

	 void delete(Reserva reserva);
	
	List<Reserva> listAllReservas();
}
