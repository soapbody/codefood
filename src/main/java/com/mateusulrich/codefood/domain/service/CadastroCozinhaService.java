package com.mateusulrich.codefood.domain.service;


import com.mateusulrich.codefood.domain.exception.CozinhaNãoEncontradaException;
import com.mateusulrich.codefood.domain.exception.EntidadeEmUsoException;
import com.mateusulrich.codefood.domain.model.Cozinha;
import com.mateusulrich.codefood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCozinhaService {
	public static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	@Autowired
	private CozinhaRepository cozinhaRepository;
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save (cozinha);
	}
	@Transactional
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById (cozinhaId);
			cozinhaRepository.flush ();

		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNãoEncontradaException (cozinhaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException (
					String.format(MSG_COZINHA_EM_USO, cozinhaId));
		}
	}

	public Cozinha buscarCozinha(Long cozinhaId) {
		return cozinhaRepository.findById (cozinhaId).orElseThrow (() -> new CozinhaNãoEncontradaException (cozinhaId));
	}

}
