package ro.ubb.controller;

import ro.ubb.converter.ClientConverter;
import domain.Client;
import ro.ubb.dto.ClientDTO;
import ro.ubb.dto.ClientsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ClientServiceInterface;

import java.util.Optional;
import java.util.Set;

@RestController
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientServiceInterface clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    Set<ClientDTO> getAllClients() {
        log.trace("getAllClients --- method entered");

        Set<Client> clients = clientService.getAllClients();
        Set<ClientDTO> dtos = clientConverter.convertModelsToDTOs(clients);
        ClientsDTO result = new ClientsDTO(dtos);

        log.trace("getAllClients: result={}", result);

        return dtos;
    }

    @RequestMapping(value="/clients",method = RequestMethod.POST)
    ClientDTO saveClient(@RequestBody ClientDTO clientDto){
        log.trace("saveClient: ro.ubb.dto={}",clientDto);

        Optional<Client> saved = clientService.addClient(clientConverter.convertDTOToModel(clientDto));
        ClientDTO result=clientConverter.convertModelToDTO(saved.get());

        log.trace("saveClient: result={}",result);

        return result;
    }

    @RequestMapping(value="/clients/{id}",method = RequestMethod.PUT)
    ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDto){
        log.trace("updateClient: ro.ubb.dto={}", clientDto);
        Optional<Client> updated = clientService.updateClient(id, clientConverter.convertDTOToModel(clientDto));
        ClientDTO result=clientConverter.convertModelToDTO(updated.get());

        log.trace("updateClient: result={}", result);

        return result;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> removeClient(@PathVariable Long id) {
        log.trace("removeClient: id={}", id);
        boolean deleted = false;
        Optional<Client> client=clientService.removeClient(id);
        if (client.isPresent())
            deleted = true;
        log.trace("deleteClient --- method finished");
        if (deleted)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/clients/{id}",method = RequestMethod.GET)
    ClientDTO findOneClient(@PathVariable Long id){
        log.trace("findOneClient: id={}",id);
        boolean found = false;
        ClientDTO result = null;
        Optional<Client> client=clientService.findClient(id);
        if (client.isPresent())
            found = true;
        if (found)
            result = clientConverter.convertModelToDTO(client.get());

        log.trace("findOneClient: ro.ubb.dto={}",result);
        return result;
    }
}