package com.ekan.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ekan.exception.DadosNaoEncontradosException;
import com.ekan.exception.ErroPersistenciaException;
import com.ekan.model.Beneficiario;
import com.ekan.model.Documento;
import com.ekan.service.CadastroBeneficiarioService;

@RestController
@RequestMapping("/beneficiario")
public class BeneficiarioController {
	
	@Autowired
	private CadastroBeneficiarioService cadastroBeneficiarioService;
	
	@GetMapping("/listarTodos")
	public List<Beneficiario> listarTodos(){
		List<Beneficiario> beneficiarios = cadastroBeneficiarioService.listar();
		return beneficiarios;
	}
	
	@GetMapping("/listarPorId/{beneficiarioId}")
	public ResponseEntity<Beneficiario> buscarPorId(@PathVariable Long beneficiarioId){
		Beneficiario beneficiario = cadastroBeneficiarioService.buscarPorId(beneficiarioId);
		if (beneficiario != null) {
			return ResponseEntity.ok(beneficiario);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public Beneficiario adicionar(@RequestBody Beneficiario beneficiario) {
		return cadastroBeneficiarioService.salvar(beneficiario);	
	}
	
	@PutMapping("/{beneficiarioId}")
	public ResponseEntity<Beneficiario> atualizar(@PathVariable Long beneficiarioId, 
			@RequestBody Beneficiario beneficiario){
		
		Beneficiario beneficiarioAtual = cadastroBeneficiarioService.buscarPorId(beneficiarioId);
		
		if (beneficiarioAtual != null) {
			BeanUtils.copyProperties(beneficiario, beneficiarioAtual, "id");
			cadastroBeneficiarioService.salvar(beneficiarioAtual);
			return ResponseEntity.ok(beneficiarioAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{beneficiarioId}")
	public ResponseEntity<Beneficiario> remover(@PathVariable Long beneficiarioId) {
		
		try {
			cadastroBeneficiarioService.excluir(beneficiarioId);
			return ResponseEntity.noContent().build();
			
		} catch (DadosNaoEncontradosException e) {
			return ResponseEntity.notFound().build();
			
		} catch (ErroPersistenciaException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();	
		}
	}
	
	@GetMapping("/buscarDocumentosPorIdBeneficiario/{beneficiarioId}")
	public ResponseEntity<List<Documento>> buscarDocumentos(@PathVariable Long beneficiarioId){
		
		Beneficiario beneficiario = cadastroBeneficiarioService.buscarPorId(beneficiarioId);
		
		if (beneficiario != null) {
			List<Documento>  documentos = beneficiario.getDocumentos().stream()
                    .collect(Collectors.toList());
			
			return ResponseEntity.ok(documentos);
		}
		
		return ResponseEntity.notFound().build();
	}
}
