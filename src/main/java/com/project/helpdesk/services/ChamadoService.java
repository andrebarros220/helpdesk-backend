package com.project.helpdesk.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.helpdesk.domain.Chamado;
import com.project.helpdesk.repositories.ChamadoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	
	public Chamado findById(Integer id) throws ObjectNotFoundException {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não econtrado! Id:" + id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}
	
}
