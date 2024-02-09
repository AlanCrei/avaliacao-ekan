//package com.ekan.repository.impl;
//
//import java.util.List;
//
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ekan.model.Beneficiario;
//import com.ekan.repository.BeneficiarioRepository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//
//@Component
//public class BeneficiarioRepositoryImpl implements BeneficiarioRepository{
//
//	@PersistenceContext
//	private EntityManager manager;
//	
//	@Override
//	public List<Beneficiario> listarTodos() {
//		TypedQuery<Beneficiario> query = manager.createQuery("from Beneficiario", Beneficiario.class);
//		return query.getResultList();
//	}
//	
//	@Override
//	public Beneficiario buscarPorId(Long id) {
//		return manager.find(Beneficiario.class, id);
//	}
//	
//	@Transactional
//	@Override
//	public Beneficiario salvar(Beneficiario beneficiario) {
//		return manager.merge(beneficiario);
//	}
//	
//	@Transactional
//	@Override
//	public void remover(Long id) {
//		Beneficiario beneficiario = buscarPorId(id);
//		if(beneficiario == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
//		manager.remove(beneficiario);	
//	}	
//}
