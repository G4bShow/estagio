package com.example.estagio.repository;

import com.example.estagio.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByResponsavelContainingIgnoreCase(String responsavel);

    List<Tarefa> findByDataEntrega(LocalDate dataEntrega);

    List<Tarefa> findByConcluidaFalse();

    List<Tarefa> findByConcluidaFalseAndResponsavelContainingIgnoreCase(String responsavel);
}
