package com.javanauta.bff_agendador.controller;

import com.javanauta.bff_agendador.business.TarefasService;
import com.javanauta.bff_agendador.business.dto.in.TarefasDTORequest;
import com.javanauta.bff_agendador.business.dto.out.TarefasDTOResponse;
import com.javanauta.bff_agendador.business.enums.StatusNotificacaoEnum;
import com.javanauta.bff_agendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro de tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar tarefas de usuários", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(
            @RequestBody TarefasDTORequest dto,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por período", description = "Busca tarefas cadastradas por períodos")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por e-mail do usuário",
            description = "Busca tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        List<TarefasDTOResponse> tarefas = tarefasService.buscaTarefasPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por ID", description = "Deleta tarefas cadastradas por ID")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de tarefas", description = "Altera o status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas", description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(
            @RequestBody TarefasDTORequest dto,
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }
}
