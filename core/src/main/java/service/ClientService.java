package service;

import domain.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ClientRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientService implements ClientServiceInterface{

    private static final Logger log = LoggerFactory.getLogger(
            ClientService.class);

    @Autowired
    private ClientRepository repo;

    @Override
    public Optional<Client> addClient(Client c) {
        log.trace("add client client={}", c);
        Optional<Client> added= Optional.of(repo.save(c));
        log.trace("method finished ---");
        return added;
    }

    @Override
    public Optional<Client> removeClient(Long id) {
        log.trace("remove client id={}", id);
        Optional<Client> entity=repo.findById(id);
        entity.ifPresent(e->repo.deleteById(id));
        log.trace("method finished---");
        return entity;
    }

    @Override
    public Set<Client> getAllClients() {
        log.trace("getAllClients ---");
        Set<Client> clients= StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toSet());
        log.trace("result={}",clients);
        return clients;
    }

    @Override
    @Transactional
    public Optional<Client> updateClient(long id, Client c) {
        log.trace("updateClient newClient={}", c);
        Optional<Client> dbClient=repo.findById(c.getId());
        Client result=dbClient.orElse(c);
        result.setName(c.getName());
        result.setDateOfBirth(c.getDateOfBirth());
        log.trace("method finished ---");
        return Optional.of(result);
    }

    @Override
    public Optional<Client> findClient(Long id) {
        log.trace("findClient id={}", id);
        Optional<Client> client= repo.findById(id);
        log.trace("method finished ---");
        return client;
    }
}
