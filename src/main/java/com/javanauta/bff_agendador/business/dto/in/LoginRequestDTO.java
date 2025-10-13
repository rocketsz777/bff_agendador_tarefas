package com.javanauta.bff_agendador.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginRequestDTO {

    private String email ;
    private String senha ;
}
