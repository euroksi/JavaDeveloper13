package org.example;

public class Test {

    public static void main(String[] args) {
        ClientCrudService clientCrudService = new ClientCrudService();

        Client client = new Client();
        clientCrudService.saveClient(client);
        System.out.println("Client saved: " + client);

        Client foundClient = clientCrudService.findClientById(client.getId());
        System.out.println("Found client: " + foundClient);

        clientCrudService.updateClient(client.getId(), "Jane Doe");
        System.out.println("Updated client: " + clientCrudService.findClientById(client.getId()));

        clientCrudService.deleteClient(client.getId());
        System.out.println("Client deleted: " + client.getId());
    }
}
