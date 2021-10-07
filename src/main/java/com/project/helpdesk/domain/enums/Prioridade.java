package com.project.helpdesk.domain.enums;

import javax.management.relation.Role;

public enum Prioridade {
	BAIXA(0,"ROLE_BAIXA"), MEDIA(1, "ROLE_MEDIA"), ALTA(2, "ROLE_ALTA");
	
	private Integer codigo;
	private String descricao;
	
	private Prioridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Prioridade toEnum(Integer cod) {
		if (cod == null){
			return null;
		}
		
		for(Prioridade x : Prioridade.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Prioridade Inválido");
	}
	
}

