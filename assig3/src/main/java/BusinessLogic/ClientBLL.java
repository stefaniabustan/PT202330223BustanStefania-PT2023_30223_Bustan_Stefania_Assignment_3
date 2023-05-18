package BusinessLogic;

import Model.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BusinessLogic.Validators.AdresaValidator;
import BusinessLogic.Validators.NumeValidator;
import BusinessLogic.Validators.Validator;
import DataAcces.ClientDAO;
import Model.Client;
import Presentation.ClientView;
import javafx.collections.ObservableList;


public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    private ObservableList list;

    public ClientBLL( ObservableList list) {

        this.clientDAO = new ClientDAO();
        this.list = list;
    }

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new AdresaValidator());
        validators.add(new NumeValidator());

        clientDAO = new ClientDAO();
    }

    public Client findClientById(int id) throws SQLException {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }
    public ObservableList viewClient(int tip)
    {
        try {
            this.list= clientDAO.viewClients(tip);
            return this.list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    public void addClient(Client c)
    {
        clientDAO.insert(c);
    }
    public void deleteClient(Client c)
    {
        clientDAO.delete(c);
    }
    public void updateClient(Client c)
    {
        clientDAO.update(c);
    }
}
