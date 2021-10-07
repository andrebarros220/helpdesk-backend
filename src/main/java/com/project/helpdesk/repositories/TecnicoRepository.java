package com.project.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.helpdesk.domain.Pessoa;
import com.project.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository <Tecnico, Integer>{

}
