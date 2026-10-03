package com.example.estagio.controller;

import com.example.estagio.entity.Tarefa;
import com.example.estagio.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private TarefaRepository tarefaRepository;

    public TarefaController(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @PostMapping
    //obs como o desafio é bem simples, eu preferi fazer dessa forma porque recebe o objeto direto, mais em outras aplicações não se faz isso.
    public ResponseEntity<Tarefa> cadastrar(@RequestBody Tarefa tarefa) {
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listar(@RequestParam(required = false) String responsavel){
        if (responsavel == null){
            return ResponseEntity.ok(tarefaRepository.findByResponsavelContainingIgnoreCase(responsavel));
        }

        return ResponseEntity.ok(tarefaRepository.findAll());
    }

    @GetMapping("/DATA")
    public ResponseEntity<List<Tarefa>> buscarPorData(@RequestParam LocalDate data){
        return ResponseEntity.ok(tarefaRepository.findByDataEntrega(data));
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Tarefa>> listarPendentes(@RequestParam String responsavel){
        if (responsavel == null){ }
            return ResponseEntity.ok(tarefaRepository.findByConcluidaFalseAndResponsavelContainingIgnoreCase(responsavel));
        }
        return ResponseEntity.ok(tarefaRepository.findByConcluidaFalse());
    }
}
