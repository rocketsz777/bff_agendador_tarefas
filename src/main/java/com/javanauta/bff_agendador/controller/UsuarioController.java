package com.javanauta.bff_agendador.controller;


import com.javanauta.bff_agendador.business.UsuarioService;
import com.javanauta.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.javanauta.bff_agendador.business.dto.in.LoginRequestDTO;
import com.javanauta.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.javanauta.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.javanauta.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bff_agendador.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bff_agendador.business.dto.out.ViaCepDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuario")
@RestController
@RequiredArgsConstructor
@Tag(name = "Usuario" , description = "Cadastro e login e usuarios")

public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation (summary = "Salvar Usuarios" , description = "Cria um novo usuario")
    @ApiResponse(responseCode = "200" , description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "409" , description = "Usuario ja cadastrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation (summary = "Login de Usuarios" , description = "login de usuario")
    @ApiResponse(responseCode = "200" , description = "Usuario logado")
    @ApiResponse(responseCode = "401" , description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO) {
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation (summary = "Buscar dados de Usuarios por email"
            , description = "Buscar dados do usuario")
    @ApiResponse(responseCode = "200" , description = "Usuario encontrado")
    @ApiResponse(responseCode = "403" , description = "Usuario nao cadastrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")

    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization" , required = false) String token) {

        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email , token));
    }

    @DeleteMapping("/{email}")
    @Operation (summary = "Deleta usuario por email" , description = "Deleta usuario")
    @ApiResponse(responseCode = "200" , description = "Usuario deletado")
    @ApiResponse(responseCode = "403" , description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    @ApiResponse(responseCode = "401" , description = "Credenciais invalidas")
    public ResponseEntity<Void> deleteUsuarioPorEmail(@PathVariable String email ,
                                                      @RequestHeader(name = "Authorization" , required = false) String token) {
        usuarioService.deletaUsuarioPorEmail(email , token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary ="Atualizar Dados de Usuario",
    description = "Atualizar dados de usuario")
    @ApiResponse(responseCode = "200" , description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "403" , description = "Usuario nao cadastrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    @ApiResponse(responseCode = "401" , description = "Credenciais invalidas")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                                                  @RequestHeader(name = "Authorization" , required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary ="Atualizar Dados de endereco",
            description = "Atualizar dados de endereco")
    @ApiResponse(responseCode = "200" , description = "Endereco atualizado com sucesso")
    @ApiResponse(responseCode = "403" , description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    @ApiResponse(responseCode = "401" , description = "Credenciais invalidas")

    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization" , required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto,token));
    }

    @PutMapping("/telefone")
    @Operation(summary ="Atualizar Dados de Telefone",
            description = "Atualizar dados de Telefone")
    @ApiResponse(responseCode = "200" , description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403" , description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    @ApiResponse(responseCode = "401" , description = "Credenciais invalidas")

    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization" , required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto,token));
    }

    @PostMapping("/endereco")
    @Operation(summary ="Salvar Dados de endereco",
            description = "Salvar dados de endereco")
    @ApiResponse(responseCode = "200" , description = "Endereco salvo com sucesso")
    @ApiResponse(responseCode = "403" , description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    @ApiResponse(responseCode = "401" , description = "Credenciais invalidas")

    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization" , required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
        //olhar depois
    }

    @PostMapping("/telefone")
    @Operation(summary ="Salva Dados de telefone",
            description = "Salva dados de telefone")
    @ApiResponse(responseCode = "200" , description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403" , description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    @ApiResponse(responseCode = "401" , description = "Credenciais invalidas")

    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone (@RequestBody TelefoneDTORequest dto ,
                                                                 @RequestHeader ("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary ="Busca endereco pelo cep",
            description = "Busca dados de endereco recebendo um cep")
    @ApiResponse(responseCode = "200" , description = "Dados de endereco")
    @ApiResponse(responseCode = "400" , description = "Cep Inválido")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")

    public ResponseEntity<ViaCepDTOResponse> buscarEndereco (@PathVariable ("cep") String cep){
        return ResponseEntity.ok(usuarioService.buscarEnderecoPorCep(cep));

    }
}
