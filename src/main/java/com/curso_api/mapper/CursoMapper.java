package com.curso_api.mapper;

import com.curso_api.dto.CursoRequestDTO;
import com.curso_api.dto.CursoResponseDTO;
import com.curso_api.entity.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    //Ele mapeia automaticamente campos com o mesmo
    //em alguns casos que os campos possuem nomes diferentes precisa explicar como sera feito
    @Mapping(source = "instrutor.nome", target = "instrutorNome")
    CursoResponseDTO toResponse(Curso curso);

    //não mapeia o id poruq estanos criando um novo curso
    //o instrutorId não pode ser convertido direto pra uma classe instrutor
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "instrutor", ignore = true)
    Curso toEntity(CursoRequestDTO cursoRequestDTO);

    //o ID NUNCA deve ser alterado uma vez que ja foi salvo
    //o instrutor id nao pode ser direto para uma classe instrutor
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "instrutor", ignore = true)
    void update(CursoRequestDTO dto, @MappingTarget Curso curso);
}
