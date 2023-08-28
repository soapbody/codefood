package com.mateusulrich.codefood.api.assembler;

import com.mateusulrich.codefood.api.model.UsuarioDTO;
import com.mateusulrich.codefood.api.model.input.UsuarioAttInput;
import com.mateusulrich.codefood.api.model.input.UsuarioInput;
import com.mateusulrich.codefood.api.model.input.UsuarioSenhaAttInput;
import com.mateusulrich.codefood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class UsuarioMapper {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioDTO toDTO (Usuario usuario) {
		return modelMapper.map (usuario, UsuarioDTO.class);
	}
	public List<UsuarioDTO> toCollectionDTO(Collection<Usuario> usuarios) {
		return usuarios.stream ()
				.map (usuario -> toDTO (usuario))
				.collect(Collectors.toList());
	}
	public Usuario toDomainObject (UsuarioInput usuarioInput) {
		return modelMapper.map (usuarioInput, Usuario.class);
	}
	public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario) {;
		modelMapper.map (usuarioInput, usuario);
	}
	public void copyAttToDomainObject(UsuarioAttInput usuarioAttInput, Usuario usuario) {;
		modelMapper.map (usuarioAttInput, usuario);
	}
	public void updatePasswordToDomainObject(UsuarioSenhaAttInput usuarioSenhaAttInput, Usuario usuario) {;
		modelMapper.map (usuarioSenhaAttInput, usuario);
	}
}
