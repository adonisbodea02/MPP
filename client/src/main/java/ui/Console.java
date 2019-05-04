package ui;

import domain.Book;
import domain.Client;
import ro.ubb.dto.BookDTO;
import ro.ubb.dto.BooksDTO;
import ro.ubb.dto.ClientDTO;
import ro.ubb.dto.ClientsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class Console {

    @Autowired
    RestTemplate restTemplate;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    private Map<Integer, Runnable> commands = new HashMap<>();

    private void setup(){
        commands.put(1, this::addBook);
        commands.put(2, this::printAllBooks);
        commands.put(3, this::updateBook);
        commands.put(4, this::deleteBook);
        commands.put(5, this::addClient);
        commands.put(6, this::printAllClients);
        commands.put(7, this::updateClient);
        commands.put(8, this::deleteClient);
        commands.put(0, () -> System.exit(0));
    }

    private void printCommands() {
        System.out.println("0.Exit");
        System.out.println("1.Add a book");
        System.out.println("2.View all books");
        System.out.println("3.Update book");
        System.out.println("4.Remove a book");
        System.out.println("5.Add a client");
        System.out.println("6.View all clients");
        System.out.println("7.Update a client");
        System.out.println("8.Remove client");
    }

    public void runConsole() {

        setup();

        printCommands();

        int option = readInteger("Input your option: ");

        Optional<Integer> o = Optional.of(option);
        o = o.filter(e -> e > 9);
        o.ifPresent(e -> System.out.println("No such option"));

        commands.get(option).run();

        runConsole();
    }

    private int readInteger(String message) {
        System.out.println(message);
        int returned = -1;
        try {
            returned = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("I/O exception");
            e.printStackTrace();
        } catch (NumberFormatException nex) {
            System.out.println("Invalid integer");
            return readInteger(message);
        }
        return returned;
    }

    private Book readBook(){
        String title, genre;
        int year;
        System.out.println("Give title: ");
        try {
            title = reader.readLine();
            System.out.println("Give genre: ");
            genre = reader.readLine();
            year = readInteger("Give year: ");
            return new Book(title, genre, year);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addBook(){
        Book b = readBook();
        if (b != null) {
            BookDTO dto = BookDTO.builder()
                    .title(b.getTitle())
                    .genre(b.getGenre())
                    .year(b.getYear())
                    .build();
            restTemplate.postForObject("http://localhost:8080/api/books",
                    dto, BookDTO.class);
        }
        else
            System.out.println("Invalid book!");
    }

    private Client readClient(){
        String name, dob;
        System.out.println("Give name: ");
        try {
            name = reader.readLine();
            System.out.println("Give date of birth: ");
            dob = reader.readLine();
            return new Client(name, dob);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addClient(){
        Client c = readClient();
        if (c != null) {
            ClientDTO dto = ClientDTO.builder()
                    .name(c.getName())
                    .dateOfBirth(c.getDateOfBirth())
                    .build();
            restTemplate.postForObject("http://localhost:8080/api/clients/",
                    dto, ClientDTO.class);
        }
        else
            System.out.println("Invalid book!");
    }

    private void deleteBook() {
        long ID = (long) readInteger("Give book ID:");
        restTemplate.delete("http://localhost:8080/api/books/{id}",ID);
    }

    private void deleteClient() {
        long ID = (long) readInteger("Give client ID:");
        restTemplate.delete("http://localhost:8080/api/clients/{id}",ID);
    }

    private void printAllBooks() {
        BooksDTO dto = restTemplate.getForObject("http://localhost:8080/api/books", BooksDTO.class);
        if(dto != null)
            dto.getBooks().forEach(System.out::println);
    }

    private void printAllClients() {

        ClientsDTO clientsDto=restTemplate.getForObject("http://localhost:8080/api/clients", ClientsDTO.class);

        if(clientsDto != null)
            clientsDto.getClients().forEach(System.out::println);
    }

    private void updateClient(){
        long id = (long) readInteger("Give client ID:");
        ClientDTO dto=restTemplate.getForObject("http://localhost:8080/api/clients/{id}",
                ClientDTO.class,id);
        if(dto==null){
            System.out.println("Client does not exist");
            return;
        }

        System.out.println("Give the new name: ");
        String newName = null;
        try {
            newName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Give the new date of birth: ");
        String newDOB = null;
        try {
            newDOB = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dto.setName(newName);
        dto.setDateOfBirth(newDOB);
        restTemplate.put("http://localhost:8080/api/clients/{id}",
                dto,
                dto.getId()
        );
    }

    private void updateBook(){
        long id = (long) readInteger("Give book ID:");
        BookDTO dto=restTemplate.getForObject("http://localhost:8080/api/books/{id}",
                BookDTO.class,id);
        if (dto==null){
            System.out.println("Book does not exist");
            return;
        }


        System.out.println("Give the new title: ");
        String newTitle = "default_title";
        try {
            newTitle = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Give the new genre: ");
        String newGenre = "default_genre";
        try {
            newGenre = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int year = readInteger("Give the new year: ");
        dto.setTitle(newTitle);
        dto.setGenre(newGenre);
        dto.setYear(year);
        restTemplate.put(
                "http://localhost:8080/api/books/{id}",
                dto,
                dto.getId()
        );
    }

}
