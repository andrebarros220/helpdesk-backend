package com.project.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository <Pessoa, Integer>{

}
