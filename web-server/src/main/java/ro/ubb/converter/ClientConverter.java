package ro.ubb.converter;

import domain.Client;
import ro.ubb.dto.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDTO> {

    @Override
    public Client convertDTOToModel(ClientDTO dto) {
        Client client=Client.builder()
                .name(dto.getName())
                .dateOfBirth(dto.getDateOfBirth())
                .build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDTO convertModelToDTO(Client client) {
        ClientDTO clientDTO=ClientDTO.builder()
                .name(client.getName())
                .dateOfBirth(client.getDateOfBirth())
                .build();
        clientDTO.setId(client.getId());
        return clientDTO;
    }
}
