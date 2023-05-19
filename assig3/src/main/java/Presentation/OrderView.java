package Presentation;

import Model.Client;
import Model.Orders;
import Model.Product;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.SQLException;
/**
 * interfata pentru order
 */
public class OrderView {
    private OrderController controller;
    private ProductController controllerProd;
    private ClientController controllerClient;
    private   ObservableList itemi;
    private   ObservableList lis1;
    private   ObservableList lis2;
    private   ListView lv;
    private Orders comanda=null;
    public  Scene getOrderScene(Stage p ) {

        VBox tot=new VBox();
        HBox stdr=new HBox();
        Label title = new Label("Orders");
        title.setFont(Font.font("TIMES NEW ROMAN", FontWeight.EXTRA_BOLD, 45));
        title.setTextFill(Color.WHITE);
        title.autosize();

        Label eroare = new Label("");
        eroare.setTextFill(Color.DARKRED);
        eroare.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 16));

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

        HBox clnt = new HBox();
        clnt.setAlignment(Pos.TOP_LEFT);
        Label clientss = new Label("Client:");
        clientss.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        clientss.setTextFill(Color.WHITE);
         lis1 = FXCollections.observableArrayList();

        lis1.clear();
        controllerClient=new ClientController(lis1,"add");
        lis1=controllerClient.viewList(2);


        ComboBox filtruClnt = new ComboBox(lis1);
        filtruClnt.getSelectionModel().selectFirst();
        filtruClnt.setPrefSize(300, 20);
        clnt.getChildren().addAll(clientss, filtruClnt);
        clnt.setSpacing(10);
        clnt.setPadding(new Insets(0,0,0,30));

        HBox prod = new HBox();
        prod.setAlignment(Pos.TOP_LEFT);
        Label products = new Label("Product:");
        products.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        products.setTextFill(Color.WHITE);
         lis2 = FXCollections.observableArrayList();
        lis2.clear();
        controllerProd=new ProductController(lis2,"add");
        lis2=controllerProd.viewList(2);

        ComboBox filtruProd = new ComboBox(lis2);
        filtruProd.getSelectionModel().selectFirst();
        filtruProd.setPrefSize(300, 20);
        prod.getChildren().addAll(products, filtruProd);
        prod.setSpacing(10);
        prod.setPadding(new Insets(0,0,0,30));

        HBox cantitate = new HBox();
        cantitate.setAlignment(Pos.TOP_LEFT);
        Label cantitatetxt = new Label("Cantitate:");
        cantitatetxt.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        cantitatetxt.setTextFill(Color.WHITE);
        TextField cantitatee = new TextField();
        cantitatee.setPromptText("...");
        cantitatee.setFont(new Font("Arial", 15));
        cantitatee.setStyle(
                "-fx-text-inner-color: white; -fx-background-color: #2a2222 ;\n" + "-fx-background-radius: 15px;");
        cantitatee.setBackground(
                new Background(new BackgroundFill(Color.web("#2a2222", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        cantitatee.setPrefSize(320, 25);
        cantitate.getChildren().addAll(cantitatetxt, cantitatee);
        cantitate.setSpacing(10);
        cantitate.setPadding(new Insets(0,0,0,30));

        BackgroundImage myBI = new BackgroundImage(
                new Image("https://wallpaperboat.com/wp-content/uploads/2020/04/green-aesthetic-wallpaper-free.jpg", 1070, 700,
                        false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        stanga.setSpacing(15);
        stanga.getChildren().addAll(id,clnt, prod,cantitate, eroare);

        /**dreapta
         *
         */
        Orders oo=new Orders();
        ExtrageFields.retrieveProperties(oo);
        String colName[]=ExtrageFields.extractFields.toArray(new String[0]);
        ExtrageFields.extractFields.clear();
        TableView tableView = new TableView();
        TableColumn<Orders, String> column1 =new TableColumn<>(colName[1]);
        column1.setCellValueFactory( new PropertyValueFactory<>("numeclient"));
        TableColumn<Orders, String> column2 =new TableColumn<>(colName[2]);
        column1.setCellValueFactory( new PropertyValueFactory<>("numeprodus"));
        TableColumn<Orders, String> column3 =
                new TableColumn<>(colName[3]);
        column2.setCellValueFactory( new PropertyValueFactory<>("cantitate"));
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        column1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        column2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        column3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.4));

        column1.setResizable(false);
        column2.setResizable(false);
        column3.setResizable(false);
        tableView.setPrefSize(400,0);


        VBox addOrder=new VBox();
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
        butoane.getChildren().addAll(add, scoate, save,refr);
        butoane.setSpacing(10);

        VBox tabel=new VBox();
        tabel.getChildren().addAll(tableView,lv);
        tabel.setSpacing(-20);

        addOrder.getChildren().addAll(tabel, butoane);
        addOrder.setSpacing(20);
        addOrder.setAlignment(Pos.CENTER);

        dreapta.getChildren().addAll(addOrder);
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

        tot.getChildren().addAll(top,stdr,backh);
        tot.setBackground(new Background(myBI));
        tot.setSpacing(20);

        /**addListener
         *
         */
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                p.setScene(StartScene.getStartScene(p ));
            }
        });

        lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                // TODO Auto-generated method stub
                int p = lv.getSelectionModel().getSelectedIndex();
                if (p >= 0) {
                    comanda= (Orders) itemi.get(p);
                    idd.setText(String.valueOf(comanda.getIdorder()));
                   // filtruProd.getSelectionModel().getSelectedIndex(9);
                    cantitatee.setText(String.valueOf(comanda.getCantitate()));
                }
            }
        });

        /**butoane
         *
         */
        add.setOnAction(new EventHandler() {

            @Override
            public void handle(Event arg0) {
                // TODO Auto-generated method stub
                int idul= Integer.parseInt(idd.getText());
                String produs= String.valueOf(filtruProd.getValue());
                String client= String.valueOf(filtruClnt.getValue());
                int stk=Integer.parseInt(cantitatee.getText());
                Orders o=new Orders(idul,produs,client,Integer.parseInt(cantitatee.getText()));
                //getIdProdus dupa numeProdus

                controller=new OrderController(itemi,  "add");
                try {
                    Product p=controller.productOrder(String.valueOf(filtruProd.getValue()));
                    stk=p.getCantitate()-stk;
                    if(stk>=0)
                    {
                        p.setCantitate(stk);
                        controller.OperationOrder(o);
                        ProductController cp;
                        System.out.println(p.getCantitate()+" "+stk);
                        if(stk==0) {
                            cp = new ProductController(itemi, "delete");
                            System.out.println("se sterge");
                        }
                        else  cp=new ProductController(itemi,  "update");
                        cp.OperationProduct(p);
                        eroare.setText("");
                    }

                    else{
                        eroare.setText("        Stock insuficient!");
                    }


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        scoate.setOnAction(new EventHandler() {

            @Override
            public void handle(Event arg0) {
                // TODO Auto-generated method stub
                controller=new OrderController(itemi,  "delete");
                controller.OperationOrder(comanda);
                eroare.setText("");
            }

        });
        save.setOnAction(new EventHandler() {

            @Override
            public void handle(Event arg0) {
                // TODO Auto-generated method stub
                int idul= Integer.parseInt(idd.getText());
                String produs= String.valueOf(filtruProd.getValue());
                String client= String.valueOf(filtruClnt.getValue());
                Orders o=new Orders(idul,produs,client,Integer.parseInt(cantitatee.getText()));
                controller=new OrderController(itemi,  "update");
                int stk=Integer.parseInt(cantitatee.getText());

                try {
                    Product p=controller.productOrder(String.valueOf(filtruProd.getValue()));
                    stk=p.getCantitate()-stk;
                    if(stk>=0)
                    {
                        p.setCantitate(stk);
                        controller.OperationOrder(o);
                        ProductController cp;
                        System.out.println(p.getCantitate()+" "+stk);
                        if(stk==0) {
                            cp = new ProductController(itemi, "delete");
                            System.out.println("se sterge");
                        }
                        else  cp=new ProductController(itemi,  "update");
                        cp.OperationProduct(p);
                        eroare.setText("");
                    }

                    else{
                        eroare.setText("        Stock insuficient!");
                    }


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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
        controller=new OrderController(itemi,"add");
        itemi=controller.viewList();

        lv.setItems(itemi);

    }
}
