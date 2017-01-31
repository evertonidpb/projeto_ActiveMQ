package br.edu.recebe.model;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.envia.model.Usuario;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

	public Reserva findOne(int id);

	
}
