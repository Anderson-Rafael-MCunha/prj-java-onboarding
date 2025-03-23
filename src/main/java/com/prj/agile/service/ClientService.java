package com.prj.agile.service;

import com.prj.agile.dto.request.ClientRequestDTO;
import com.prj.agile.dto.response.ClientDTO;
import com.prj.agile.entity.Client;
import com.prj.agile.entity.ClientType;
import com.prj.agile.entity.Address;
import com.prj.agile.entity.Phone;
import com.prj.agile.mapper.AddressMapper;
import com.prj.agile.mapper.ClientMapper;
import com.prj.agile.mapper.ClientTypeMapper;
import com.prj.agile.mapper.PhoneMapper;
import com.prj.agile.repository.ClientRepository;
import com.prj.agile.repository.ClientTypeRepository;
import com.prj.agile.repository.AddressRepository;
import com.prj.agile.repository.PhoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientTypeRepository clientTypeRepository;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;

    public ClientService(ClientRepository clientRepository, ClientTypeRepository clientTypeRepository,
                         AddressRepository addressRepository, PhoneRepository phoneRepository) {
        this.clientRepository = clientRepository;
        this.clientTypeRepository = clientTypeRepository;
        this.addressRepository = addressRepository;
        this.phoneRepository = phoneRepository;
    }

    //POST
    @Transactional
    public ClientDTO createClient(ClientRequestDTO dto) {

        ClientType clientType = clientTypeRepository.save(ClientTypeMapper.toEntity(dto.getClientType()));

        Address address = addressRepository.save(AddressMapper.toEntity(dto.getClientAddress()));

        List<Phone> phones = dto.getPhones().stream().map(PhoneMapper::toEntity).collect(Collectors.toList());

        Client client = clientRepository.save(ClientMapper.toEntity(dto, clientType, address, phones));

        for (Phone phone : phones) {
            phone.setClient(client);
            phoneRepository.save(phone);
        }

        return ClientMapper.toDTO(client);
    }

    // GET
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(ClientMapper::toDTO).collect(Collectors.toList());
    }

    // GET
    public Optional<ClientDTO> getClientById(Integer id) {
        return clientRepository.findById(id).map(ClientMapper::toDTO);
    }

    // GET
    public Optional<ClientDTO> getClientByDocument(String document) {
        return clientRepository.findByDocument(document).map(ClientMapper::toDTO);
    }

    //PUT
    @Transactional
    public Optional<ClientDTO> updateClient(Integer id, ClientRequestDTO dto) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            return Optional.empty();
        }

        Client existingClient = clientOptional.get();
        existingClient.setName(dto.getName());
        existingClient.setDocument(dto.getDocument());
        existingClient.setBirthDate(dto.getBirthDate());
        existingClient.setPep(dto.getPep());
        existingClient.setEmail(dto.getEmail());
        existingClient.setStatus(dto.getStatus());

        // Atualizar ClientType
        if (dto.getClientType() != null) {
            ClientType clientType = ClientTypeMapper.toEntity(dto.getClientType());
            existingClient.setClientType(clientType);
        }

        // Atualizar Address
        if (dto.getClientAddress() != null) {
            Address address = AddressMapper.toEntity(dto.getClientAddress());
            address = addressRepository.save(address);
            existingClient.setClientAddress(address);
        }

        // Atualizar Phones
        if (dto.getPhones() != null) {
            List<Phone> phones = dto.getPhones().stream().map(PhoneMapper::toEntity).toList();
            existingClient.getPhones().clear();
            for (Phone phone : phones) {
                phone.setClient(existingClient);
                phoneRepository.save(phone);
                existingClient.getPhones().add(phone);
            }
        }

        return Optional.of(ClientMapper.toDTO(clientRepository.save(existingClient)));
    }


    //DELETE
    @Transactional
    public boolean deleteClient(Integer id) {
        Optional<Client> clientOpt = clientRepository.findById(id);

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();

            // Desassociando possíveis referências transientes antes da exclusão
            client.setClientType(null);
            client.setClientAddress(null);
            client.getPhones().clear();
            clientRepository.save(client);

            clientRepository.delete(client);
            return true;
        }
        return false;
    }

}
