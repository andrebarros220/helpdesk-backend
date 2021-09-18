package com.project.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository <Chamado, Integer>{

}
