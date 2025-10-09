package com.javanauta.bff_agendador.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EnderecoDTOResponse {

    private long id;

    private String rua ;

    private Long numero ;

    private String complemento ;

    private String cidade ;

    private String estado ;

    private String cep;
}
