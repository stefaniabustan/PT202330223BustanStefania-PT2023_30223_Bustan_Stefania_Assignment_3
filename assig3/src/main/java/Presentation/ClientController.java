package Presentation;

import BusinessLogic.ClientBLL;
import Model.Client;
import javafx.collections.ObservableList;

public class ClientController {
    private  ObservableList list;
    private ClientBLL service;
    private String operation;

    public ClientController(ObservableList list,String operation) {
        this.list = list;
        this.service = new ClientBLL();
        this.operation = operation;
    }



    public ObservableList viewList(int tip)
    {
        return this.service.viewClient(tip);

    }
    public void OperationClient (Client client)
    {
        switch(this.operation) {
            case "add":
                this.service.addClient(client);
                break;
            case "delete":
                this.service.deleteClient(client);
                break;
            default:
                this.service.updateClient(client);
                // code block
        }
    }

}
