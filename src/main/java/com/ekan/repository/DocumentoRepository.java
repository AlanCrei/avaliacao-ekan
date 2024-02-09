package com.ekan.repository;

import java.util.List;

import com.ekan.model.Documento;

public interface DocumentoRepository {
	
	List<Documento> listar();
	Documento buscar(Long id);
	Documento salvar(Documento documento);
	void remover(Long id);
}
