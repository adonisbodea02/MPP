package service;

import domain.Client;

import java.util.Optional;
import java.util.Set;

public interface ClientServiceInterface extends Service<Long, Client>{
    Optional<Client> addClient(Client c);
    Optional<Client> removeClient(Long id);
    Set<Client> getAllClients();
    Optional<Client> updateClient(long id, Client c);
    Optional<Client> findClient(Long id);

}
