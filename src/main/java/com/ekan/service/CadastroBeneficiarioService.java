package com.ekan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ekan.exception.ErroPersistenciaException;
import com.ekan.model.Beneficiario;
import com.ekan.repository.BeneficiarioRepository;

@Service
public class CadastroBeneficiarioService {
	
	@Autowired
	private BeneficiarioRepository beneficiarioRepository;
	
	public List<Beneficiario> listar() {
		return beneficiarioRepository.findAll();
	}
	
	public Beneficiario buscarPorId(Long id) {
		return beneficiarioRepository.findById(id).orElse(null);
	}
	
	public Beneficiario salvar(Beneficiario beneficiario) {
		return beneficiarioRepository.save(beneficiario);
	}
	
	public void excluir(Long beneficiarioId) {
		
		try {
			beneficiarioRepository.deleteById(beneficiarioId);	
			
		} catch (EmptyResultDataAccessException  e) {
			throw new ErroPersistenciaException("Beneficiário não encontrado");	
		}	
		
		catch (DataIntegrityViolationException  e) {
			throw new ErroPersistenciaException("Beneficiario não pode ser removido");	
		}
	}
}
