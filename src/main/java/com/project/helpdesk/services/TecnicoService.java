package com.project.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.project.helpdesk.domain.Pessoa;
import com.project.helpdesk.domain.Tecnico;
import com.project.helpdesk.domain.dtos.TecnicoDTO;
import com.project.helpdesk.repositories.PessoaRepository;
import com.project.helpdesk.repositories.TecnicoRepository;
import com.project.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado ! Id: " + id) );
	}

	public List<Tecnico> findall() {
	
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		ValidaporCpfEEmail(objDTO);
		Tecnico newOBJ = new Tecnico(objDTO); 
		return repository.save(newOBJ); 	
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		ValidaporCpfEEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return repository.save(oldObj);
	}  
	
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		
		if(obj.getChamados().size() > 0){
			throw new DataIntegrityViolationException("Tecnico possui ordem de serviço e não pode ser deletado");
		}
		
		repository.deleteById(id);
	}
	
	private void ValidaporCpfEEmail(TecnicoDTO objDTO) {
		Optional <Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf()); 
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já existente");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já existente");
	   }
		
	}

	

}
