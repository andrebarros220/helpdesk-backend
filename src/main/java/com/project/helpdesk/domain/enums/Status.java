package com.project.helpdesk.domain.enums;

import javax.management.relation.Role;

public enum Status {
	ABERTO(0,"ROLE_ABERTO"), ANDAMENTO(1, "ROLE_ANDAMENTO"), TECNICO(2, "ENCERRADO_TECNICO");
	
	private Integer codigo;
	private String descricao;
	
	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Status toEnum(Integer cod) {
		if (cod == null){
			return null;
		}
		
		for(Status x : Status.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status Inválido");
	}
	
}

