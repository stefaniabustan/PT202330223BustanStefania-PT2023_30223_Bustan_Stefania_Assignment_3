package Presentation;



import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StartScene {
    Connection connection = null;


    public static Scene getStartScene(Stage p ) {
        VBox root = new VBox();
        Scene scene = new Scene(root, 800, 700);


        Button client = new Button("CLIENTS");
        client.setStyle("-fx-background-color: #2a2222; -fx-background-radius: 15px;");
        client.setPrefSize(200, 50);
        client.setFont(new Font(20));
        client.setTextFill(Color.WHITE);

        Button product = new Button("PRODUCTS");
        product.setStyle("-fx-background-color: #2a2222; -fx-background-radius: 15px;");
        product.setPrefSize(200, 50);
        product.setFont(new Font(20));
        product.setTextFill(Color.WHITE);

        Button order = new Button("ORDERS");
        order.setStyle("-fx-background-color: #2a2222; -fx-background-radius: 15px;");
        order.setPrefSize(200, 50);
        order.setFont(new Font(20));
        order.setTextFill(Color.WHITE);

        Label title = new Label();
        title.setText("Tea House" );
        title.setFont(Font.font("Algerian", 40));
        title.autosize();
        //

        HBox h1 = new HBox();
        h1.getChildren().addAll(client,product,order);
        h1.setSpacing(15);
        h1.setAlignment(Pos.CENTER);


        root.getChildren().addAll(title,h1);
        root.setSpacing(350);
        root.setPadding(new Insets(50,0,0,0));
        root.setAlignment(Pos.TOP_CENTER);
        BackgroundImage myBI = new BackgroundImage(
                new Image("https://roseveartea.co.uk/wp-content/uploads/2019/11/IMG_20200106_104034-scaled-e1600789320637.jpg", 800, 700, false,
                        true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        client.setOnMouseClicked(new EventHandler() {

            @Override
            public void handle(Event arg0) {
                ClientView viewC=new ClientView();
                // TODO Auto-gener
                p.setScene(viewC.getClientScene(p));
            }

        });
        product.setOnMouseClicked(new EventHandler() {

            @Override
            public void handle(Event arg0) {
                // TODO Auto-gener
                ProductView viewP=new ProductView();
                p.setScene(viewP.getProductScene(p));
            }

        });
        order.setOnMouseClicked(new EventHandler() {

            @Override
            public void handle(Event arg0) {
                // TODO Auto-gener
                OrderView viewO=new OrderView();
                p.setScene(viewO.getOrderScene(p));
            }

        });
        return scene;

    }

    ///


}

