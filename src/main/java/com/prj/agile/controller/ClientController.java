package com.prj.agile.controller;

import com.prj.agile.dto.ClientDTO;
import com.prj.agile.entity.Client;
import com.prj.agile.mapper.ClientMapper;
import com.prj.agile.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Cria um novo cliente", description = "Esse endpoint cria um novo cliente e retorna os dados do cliente criado.")
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        // Converter o DTO para a entidade Client
        Client client = ClientMapper.toEntity(clientDTO);

        // Salvar o cliente (id será gerado automaticamente)
        Client savedClient = clientService.createClient(client);

        // Converter a entidade de volta para o DTO
        ClientDTO savedClientDTO = clientMapper.toDto(savedClient);

        return new ResponseEntity<>(savedClientDTO, HttpStatus.CREATED);
        return ResponseEntity.ok(clientService.createClient(clientDTO));
    }

    @Operation(summary = "Lista todos os clientes", description = "Esse endpoint retorna todos os clientes cadastrados.")
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @Operation(summary = "Busca um cliente pelo ID", description = "Esse endpoint retorna os dados de um cliente específico dado o seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Integer id) {
        System.out.println("=========================== Entrei ========================");
        Optional<ClientDTO> clientDTO = clientService.getClientById(id);
        return clientDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Busca um cliente pelo Documento (CPF/CNPJ)", description = "Esse endpoint retorna os dados de um cliente pelo documento (CPF/CNPJ).")
    @GetMapping("/getByDocument/{document}")
    public ResponseEntity<ClientDTO> getClientByDocument(@PathVariable String document) {
        Optional<ClientDTO> clientDTO = clientService.getClientByDocument(document);
        return clientDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza dados de cliente a partir de um ID", description = "Esse endpoint busca e atualiza os dados de cliente baseado no id e contrato de entrada.")
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Integer id, @RequestBody ClientDTO clientDTO) {
        Optional<ClientDTO> updatedClient = clientService.updateClient(id, clientDTO);
        return updatedClient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta cliente da base por ID", description = "Esse endpoint busca o cliente pelo ID e realiza o delete.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        boolean deleted = clientService.deleteClient(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
