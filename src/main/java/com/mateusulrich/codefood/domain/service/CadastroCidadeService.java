package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.CidadeNaoEncontradaException;
import com.mateusulrich.codefood.domain.exception.EntidadeEmUsoException;
import com.mateusulrich.codefood.domain.model.Cidade;
import com.mateusulrich.codefood.domain.model.Estado;
import com.mateusulrich.codefood.domain.repository.CidadeRepository;
import com.mateusulrich.codefood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {
	public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	public Cidade buscarCidadePorId(Long id) {
		return cidadeRepository.findById(id).orElseThrow (() -> new CidadeNaoEncontradaException (id));
	}
	public Cidade salvar(Cidade cidade) {
		Estado estado = cadastroEstadoService.buscarEstado (cidade.getEstado().getId());
		cidade.setEstado(estado);
		return cidadeRepository.save (cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById (cidadeId);

		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException (cidadeId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException (
					String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}


}
