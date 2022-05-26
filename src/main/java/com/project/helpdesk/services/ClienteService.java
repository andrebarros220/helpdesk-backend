package com.project.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.project.helpdesk.domain.Pessoa;
import com.project.helpdesk.domain.Cliente;
import com.project.helpdesk.domain.dtos.ClienteDTO;
import com.project.helpdesk.repositories.PessoaRepository;
import com.project.helpdesk.repositories.ClienteRepository;
import com.project.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado ! Id: " + id) );
	}

	public List<Cliente> findall() {
	
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		ValidaporCpfEEmail(objDTO);
		Cliente newOBJ = new Cliente(objDTO); 
		return repository.save(newOBJ); 	
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		
		if(!objDTO.getSenha().equals(oldObj.getSenha())) 
			objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		
		ValidaporCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}  
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		
		if(obj.getChamados().size() > 0){
			throw new DataIntegrityViolationException("Cliente possui ordem de serviço e não pode ser deletado");
		}
		
		repository.deleteById(id);
	}
	
	private void ValidaporCpfEEmail(ClienteDTO objDTO) {
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
