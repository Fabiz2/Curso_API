package com.curso_api.mapper;

import com.curso_api.dto.InstrutorRequestDTO;
import com.curso_api.dto.InstrutorResponseDTO;
import com.curso_api.entity.Instrutor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InstrutorMapper {

    InstrutorResponseDTO toDTO(Instrutor instrutor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    Instrutor toEntity(InstrutorRequestDTO instrutorRequestDTO);

    @Mapping(target = "id", ignore = true)
    void update(InstrutorRequestDTO instrutorRequestDTO, @MappingTarget Instrutor instrutor);
}
