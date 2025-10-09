package com.javanauta.bff_agendador.business.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javanauta.bff_agendador.business.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TarefasDTOResponse {

    private String id;
    private String nometarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
