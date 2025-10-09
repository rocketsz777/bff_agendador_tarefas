package com.javanauta.bff_agendador.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javanauta.bff_agendador.business.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TarefasDTORequest {

    private String nometarefa;
    private String descricao;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
}
