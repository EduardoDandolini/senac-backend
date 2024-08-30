package com.arquitetura.senac.mapper;

import com.arquitetura.senac.dto.UsuarioRequest;
import com.arquitetura.senac.dto.UsuarioResponse;
import com.arquitetura.senac.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    Usuario toUsuario(UsuarioRequest dto);

    Usuario toUsuario(UsuarioResponse usuarioResponse);

    UsuarioResponse toResponse(UsuarioRequest dto);

    UsuarioResponse toResponse(Usuario entity);

    List<UsuarioResponse> toResponseList(List<Usuario> entity);
}
