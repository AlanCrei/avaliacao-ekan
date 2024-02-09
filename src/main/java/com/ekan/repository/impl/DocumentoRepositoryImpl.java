package com.ekan.repository.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ekan.model.Documento;
import com.ekan.repository.DocumentoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class DocumentoRepositoryImpl implements DocumentoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Documento> listar() {
		TypedQuery<Documento> query = manager.createQuery("from Documento", Documento.class);
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public Documento salvar(Documento documento) {
		return manager.merge(documento);
	}
	
	@Override
	public Documento buscar(Long id) {
		return manager.find(Documento.class, id);
	}
	
	@Transactional
	@Override
	public void remover(Long id) {
		Documento documento = buscar(id);
		if(documento == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(documento);
	}	
}
