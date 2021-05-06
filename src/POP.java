import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class POP {

    public void display(String text,String title)

    {

        Stage stage = new Stage();// stage creating
        StackPane pane = new StackPane();// Main pane
        Scene scene = new Scene(pane, 400, 340);// creating scene
        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));// setting background color of main pane
        pane.setPadding(new Insets(10, 10, 10, 10)); // setting padding
        stage.initModality(Modality.APPLICATION_MODAL);// adding modality
        // Main box
        VBox MainBox = new VBox(20);// main Vbox
        MainBox.setAlignment(Pos.CENTER);// setting alignment


        //Content
        Label Text = new Label(text);// text label
        Button cButton = new Button("  Close it");// close button
        cButton.setOnAction(e -> stage.close());// action

        // Styling
        cButton.setFont(Font.font("Abadi", FontWeight.BOLD, FontPosture.ITALIC, 16));
        cButton.setTextFill(Color.MAGENTA);
        Text.setFont(Font.font("Abadi", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 30));
        Text.setTextFill(Color.STEELBLUE);

        //setting stage

        MainBox.getChildren().addAll(Text, cButton);// adding content to main box
        pane.getChildren().add(MainBox);// adding main box to  main pane

        stage.setTitle(title);// setting stage title
        stage.setScene(scene);// setting scene
        stage.showAndWait();
    }
}
