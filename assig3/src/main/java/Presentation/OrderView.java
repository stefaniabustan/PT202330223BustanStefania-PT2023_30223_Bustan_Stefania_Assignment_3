package Presentation;

import Model.Client;
import Model.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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
        //aiciccc
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
        Label cantitatetxt = new Label("Stock:");
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
                new Image("https://wallpaperboat.com/wp-content/uploads/2020/04/green-aesthetic-wallpaper-free.jpg", 1000, 700,
                        false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        stanga.setSpacing(15);
        stanga.getChildren().addAll(id,clnt, prod,cantitate);

        //dreapta
        TextField search = new TextField();
        search.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 15));
        search.setStyle("-fx-background-color: white; -fx-background-radius: 15px; ");
        search.setBackground(
                new Background(new BackgroundFill(Color.web("#2a2222", 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        search.setPrefSize(500, 40);
        Image srcc = new Image(
                "https://www.clipartmax.com/png/full/473-4732129_computer-icons-google-search-clip-art-transprent-%E2%93%92-transparent-background-magnifying-glass.png",
                20, 20, false, true);
        ImageView im = new ImageView(srcc);

        HBox src = new HBox();
        src.setSpacing(-40);
        src.setAlignment(Pos.CENTER_LEFT);
        src.getChildren().addAll(search, im);
        src.setPadding(new Insets(0, 0, -10, 0));


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

        addOrder.getChildren().addAll(src,lv, butoane);
        addOrder.setSpacing(15);
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
        // back.setAlignment(Pos.BOTTOM_RIGHT);

        tot.getChildren().addAll(top,stdr,backh);
        tot.setBackground(new Background(myBI));
        tot.setSpacing(20);

        //addListener
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                p.setScene(StartScene.getStartScene(p ));
            }
        });

        //butoane
        add.setOnAction(new EventHandler() {

            @Override
            public void handle(Event arg0) {
                // TODO Auto-generated method stub
                int idul= Integer.parseInt(idd.getText());
                String produs= String.valueOf(filtruProd.getItems().get(0));
                String client= String.valueOf(filtruClnt.getItems().get(0));
                Orders o=new Orders(idul,produs,client,Integer.parseInt(cantitatee.getText()));
                controller=new OrderController(itemi,  "add");
                controller.OperationOrder(o);

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

        refr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                refresh();
            }
        });

        Scene scene = new Scene(tot, 1000, 700);
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
