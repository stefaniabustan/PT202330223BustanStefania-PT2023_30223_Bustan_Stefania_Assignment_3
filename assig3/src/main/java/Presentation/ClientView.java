package Presentation;

import BusinessLogic.ClientBLL;
import DataAcces.ClientDAO;
import Model.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.input.*;

import java.awt.*;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * interfata pentru client
 */
public class ClientView  {
    private ClientController controller;
    private   ObservableList itemi;
    private   List<Client> clienti=null;
   private   ListView lv;
    private   Client clientul=null;
    public  Scene getClientScene(Stage p) {

        VBox tot=new VBox();
        HBox stdr=new HBox();
        this.clientul=new Client();
        this.clienti=new ArrayList<Client>();
        Label title = new Label("Clients");
        title.setFont(Font.font("TIMES NEW ROMAN", FontWeight.EXTRA_BOLD, 45));
        title.setTextFill(Color.WHITE);
        title.autosize();

        HBox top=new HBox();
        top.getChildren().add( title);
        top.setAlignment(Pos.TOP_CENTER);
        top.setPadding(new Insets(30,0,0,0));

        VBox stanga= new VBox();
        VBox dreapta= new VBox();

        HBox id = new HBox();
        id.setAlignment(Pos.TOP_LEFT);
        Label idtxt = new Label("ID:");
        idtxt.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        idtxt.setTextFill(Color.WHITE);
        TextField idd = new TextField();
        idd.setPromptText("...");
        idd.setFont(new Font("Arial", 15));
        idd.setStyle(
                "-fx-text-inner-color: white; -fx-background-color: #2a2222 ;\n" + "-fx-background-radius: 15px;");
        idd.setBackground(
                new Background(new BackgroundFill(Color.web("#2a2222", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        idd.setPrefSize(320, 25);
        id.getChildren().addAll(idtxt, idd);
        id.setSpacing(27);
        id.setPadding(new Insets(0,0,0,30));

        HBox name = new HBox();
        name.setAlignment(Pos.TOP_LEFT);
        Label numetxt = new Label("Name:");
        numetxt.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        numetxt.setTextFill(Color.WHITE);
        TextField nume = new TextField();
        nume.setPromptText("...");
        nume.setFont(new Font("Arial", 15));
        nume.setStyle(
                "-fx-text-inner-color: white; -fx-background-color: #2a2222 ;\n" + "-fx-background-radius: 15px;");
        nume.setBackground(
                new Background(new BackgroundFill(Color.web("#2a2222", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        nume.setPrefSize(320, 25);
        name.getChildren().addAll(numetxt, nume);
        name.setSpacing(5);
        name.setPadding(new Insets(0,0,0,30));

        HBox adresa = new HBox();
        adresa.setAlignment(Pos.TOP_LEFT);
        Label adresatxt = new Label("Adresa:");
        adresatxt.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        adresatxt.setTextFill(Color.WHITE);
        TextField adresaa = new TextField();
        adresaa.setPromptText("...");
        adresaa.setFont(new Font("Arial", 15));
        adresaa.setStyle(
                "-fx-text-inner-color: white; -fx-background-color: #2a2222 ;\n" + "-fx-background-radius: 15px;");
        adresaa.setBackground(
                new Background(new BackgroundFill(Color.web("#2a2222", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        adresaa.setPrefSize(320, 25);
        adresa.getChildren().addAll(adresatxt, adresaa);
        adresa.setSpacing(10);
        adresa.setPadding(new Insets(0,0,0,30));

        BackgroundImage myBI = new BackgroundImage(
                new Image("https://wallpaperboat.com/wp-content/uploads/2020/04/green-aesthetic-wallpaper-free.jpg", 1070, 700,
                        false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        stanga.setSpacing(15);
        stanga.getChildren().addAll(id,name, adresa);

        /**dreapta
         *
         */

        ExtrageFields.retrieveProperties(clientul);
        String colName[]=ExtrageFields.extractFields.toArray(new String[0]);
        ExtrageFields.extractFields.clear();
        TableView tableView = new TableView();
        TableColumn<Client, String> column1 =new TableColumn<>(colName[1]);
        column1.setCellValueFactory( new PropertyValueFactory<>("nume"));
        TableColumn<Client, String> column2 =
                new TableColumn<>(colName[2]);
        column2.setCellValueFactory( new PropertyValueFactory<>("adresa"));
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        column1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));
        column2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));

        column1.setResizable(false);
        column2.setResizable(false);
        tableView.setPrefSize(300,0);

        VBox addClient=new VBox();
        itemi = FXCollections.observableArrayList();
        lv = new ListView();
        lv.setItems(itemi);
        lv.setEditable(false);
        lv.setPrefSize(500, 300);

        Button add = new Button("Add");
        add.setStyle("-fx-background-color: #2a2222; -fx-background-radius: 15px;");
        add.setPrefSize(120, 60);
        add.setTextFill(Color.WHITE);

        Button scoate = new Button("Remove");
        scoate.setStyle("-fx-background-color: #2a2222; -fx-background-radius: 15px;");
        scoate.setPrefSize(120, 60);
        scoate.setTextFill(Color.WHITE);

        Button save = new Button("Save");
        save.setStyle("-fx-background-color: #2a2222; -fx-background-radius: 15px;");
        save.setPrefSize(120, 60);
        save.setTextFill(Color.WHITE);

        Button refr = new Button("Refresh");
        refr.setStyle("-fx-background-color: #2a2222; -fx-background-radius: 15px;");
        refr.setPrefSize(120, 60);
        refr.setTextFill(Color.WHITE);

        HBox butoane=new HBox();
        butoane.getChildren().addAll(add, scoate, save, refr);
        butoane.setSpacing(10);

        VBox tabel=new VBox();
        tabel.getChildren().addAll(tableView,lv);
        tabel.setSpacing(-20);

        addClient.getChildren().addAll(tabel, butoane);
       // addClient.getChildren().addAll(tableView,table, butoane);
        addClient.setSpacing(15);
        addClient.setSpacing(20);
        addClient.setAlignment(Pos.CENTER);

        dreapta.getChildren().addAll(addClient);
        dreapta.setAlignment(Pos.TOP_RIGHT);

        stdr.getChildren().addAll(stanga,dreapta);
        stdr.setSpacing(35);

        Button back = new Button("Back");
        back.setStyle("-fx-background-color: #2a2222; -fx-background-radius: 15px;");
        back.setPrefSize(100, 60);
        back.setTextFill(Color.WHITE);

        HBox backh=new HBox();
        backh.getChildren().add(back);
        backh.setAlignment(Pos.BOTTOM_RIGHT);
        backh.setPadding(new Insets(0,20,20,0));
       // back.setAlignment(Pos.BOTTOM_RIGHT);

        tot.getChildren().addAll(top,stdr,backh);
        tot.setBackground(new Background(myBI));
        tot.setSpacing(20);

        /**addListener
         *
         */
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                p.setScene(StartScene.getStartScene(p));
            }
        });

        lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                // TODO Auto-generated method stub
                int p = lv.getSelectionModel().getSelectedIndex();
                if (p >= 0) {
                      clientul= (Client) itemi.get(p);
                      idd.setText(String.valueOf(clientul.getId()));
                      nume.setText(clientul.getNume());
                      adresaa.setText(clientul.getAdresa());
                }
            }
        });


        /**butoanele
         *
         */
        add.setOnAction(new EventHandler() {

            @Override
            public void handle(Event arg0) {
                // TODO Auto-generated method stub
                int idul= Integer.parseInt(idd.getText());
                Client c=new Client(idul,nume.getText(), adresaa.getText());
               controller=new ClientController(itemi,  "add");
               controller.OperationClient(c);

            }
        });
        scoate.setOnAction(new EventHandler() {

            @Override
            public void handle(Event arg0) {
                // TODO Auto-generated method stub
                int idul= Integer.parseInt(idd.getText());
                Client c=new Client(idul,nume.getText(), adresaa.getText());
                controller=new ClientController(itemi,  "delete");
                controller.OperationClient(clientul);

            }
        });

        save.setOnAction(new EventHandler() {

            @Override
            public void handle(Event arg0) {
                // TODO Auto-generated method stub
                int idul= Integer.parseInt(idd.getText());
                Client c=new Client(idul,nume.getText(), adresaa.getText());
                controller=new ClientController(itemi,  "update");
                controller.OperationClient(c);


            }
        });
        refr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                refresh();
            }
        });


        Scene scene = new Scene(tot, 1070, 700);
        refresh();
        return scene;

    }
    public  void refresh( ) {

        itemi.clear();
        controller=new ClientController(itemi,"add");
        itemi=controller.viewList(1);

        lv.setItems(itemi);
    }


}