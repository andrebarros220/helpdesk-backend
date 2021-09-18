package com.project.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Integer>{

}
