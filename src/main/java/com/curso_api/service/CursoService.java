package com.curso_api.service;

import com.curso_api.dto.CursoRequestDTO;
import com.curso_api.dto.CursoResponseDTO;
import com.curso_api.entity.Curso;
import com.curso_api.entity.Instrutor;
import com.curso_api.mapper.CursoMapper;
import com.curso_api.repository.CursoRepository;
import com.curso_api.repository.InstrutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    private final InstrutorRepository instrutorRepository;

    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, InstrutorRepository instrutorRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.instrutorRepository = instrutorRepository;
        this.cursoMapper = cursoMapper;
    }

    public List<CursoResponseDTO> listarTodos(){
        return cursoRepository.findAll().stream().map(curso -> cursoMapper.toResponse(curso)).toList();
    }

    public CursoResponseDTO buscarPorId(Long id){
        return cursoMapper.toResponse(cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado")));
    }

    public void deletar(Long id){
        buscarPorId(id);

        cursoRepository.deleteById(id);
    }

    public CursoResponseDTO criarCurso(CursoRequestDTO cursoRequestDTO){
        Curso curso = cursoMapper.toEntity(cursoRequestDTO);

        Instrutor instrutor = instrutorRepository.findById(cursoRequestDTO.instrutorId())
                        .orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));

        //usa o instrutor que ja existe
        curso.setInstrutor(instrutor);

        return cursoMapper.toResponse(cursoRepository.save(curso));
    }

    public CursoResponseDTO atualizar(Long cursoId, CursoRequestDTO cursoRequestDTO){
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso nao encontrado"));

        Instrutor instrutor = instrutorRepository.findById(cursoRequestDTO.instrutorId())
                .orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));

        cursoMapper.update(cursoRequestDTO, curso);

        curso.setInstrutor(instrutor);

        return cursoMapper.toResponse(cursoRepository.save(curso));
    }

}
