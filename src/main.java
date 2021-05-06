import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class main extends Application {

    // to change turns Blue=true, Red=false
    static boolean turn =true;
    // pos 2d array
    static int[][] game= new int[10][10];
    // game object
    static Connect4Game gameRun=new Connect4Game();
    //Add setpiece boolean
    static boolean addSetP=false;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {

        //removing null positions and adding real number as flags
        for(int i=0;i<10;i++){for(int j =0;j<10;j++){game[i][j]=5;}}
        // setting main pane
        Pane root = new Pane();
        // setting background color
        root.setBackground(new Background(new BackgroundFill(Color.IVORY,null,null)));
        Canvas canvas = new Canvas(500,500);
        Scene scene = new Scene(root,700,620);
        // Layout main
        VBox MC=new VBox(15);
        MC.setAlignment(Pos.CENTER);
        MC.setPadding(new Insets(10,20,10,10));
        //Graphic context gc
        GraphicsContext gc= canvas.getGraphicsContext2D();
        // reset button and canvas for the game view
        Label head=new Label("Connect 4");
        head.setFont(Font.font("Abadi", FontWeight.BOLD, FontPosture.ITALIC,55));
        head.setTextFill(Color.SLATEBLUE);

        HBox downBox=new HBox(15);
        downBox.setAlignment(Pos.CENTER);
        //note
        Label note=new Label("Game Rule: Setpieces can only \nbe placed on top of each other.\nMatch upto 4 Setpieces to Win\n in any Direction.");
        note.setFont(Font.font("Abadi", null, FontPosture.ITALIC,12));
        note.setTextFill(Color.SADDLEBROWN);
        //right side buttons
        VBox RS=new VBox(8);
        //Add setpiece Buttons
        Button Addblue = new Button("Add Blue");
        Addblue.setFont(Font.font("Abadi", FontWeight.BOLD, null,25));
        Addblue.setTextFill(Color.BLUE);
        Button Addred = new Button("Add Red");
        Addred.setFont(Font.font("Abadi", FontWeight.BOLD, null,26));
        Addred.setTextFill(Color.TOMATO);
        // reset button
        Button reset=new Button("Reset");
        reset.setFont(Font.font("Abadi", FontWeight.BOLD, null,35));
        reset.setTextFill(Color.TEAL);
        // Initialization
        gameRun.wipeBoard(gc);
        gameRun.displayBoard(gc);

        // Events

        //reset logic
        reset.setOnAction(e->{
            for(int i=0;i<10;i++){for(int j =0;j<10;j++){game[i][j]=5;}}
            turn=true;
            addSetP=false;
            gameRun.wipeBoard(gc);
            gameRun.displayBoard(gc);
            POP resetPrompt=new POP();
            resetPrompt.display("Game Resetted!!","GAME RESET COMPLETE!!");
        });

        Addblue.setOnAction(e-> {addSetP=true;turn=true;});
        Addred.setOnAction(e-> {addSetP=true;turn=false;});

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {


                if(addSetP){
                    if(game[gameRun.matrixRow(e.getY())][gameRun.matrixColumn(e.getX())]==5){
                        if(turn){
                            game[gameRun.matrixRow(e.getY())][gameRun.matrixColumn(e.getX())]=2;
                            addSetP=false;//for Blue
                        }
                        if(!turn){
                            game[gameRun.matrixRow(e.getY())][gameRun.matrixColumn(e.getX())]=3;
                            addSetP=false;//For Red
                        }
                    }else {new Alert(Alert.AlertType.WARNING, "Space Already Taken").showAndWait();}

                }else{new Alert(Alert.AlertType.WARNING, "Select Something first").showAndWait();}

                // game rule check logic: Setpieces can only placed on top of each other
                if(gameRun.checkPosValidity(game)){

                    if(game[gameRun.matrixRow(e.getY())][gameRun.matrixColumn(e.getX())]==2){
                        game[gameRun.matrixRow(e.getY())][gameRun.matrixColumn(e.getX())]=0;
                    }
                    if(game[gameRun.matrixRow(e.getY())][gameRun.matrixColumn(e.getX())]==3){
                        game[gameRun.matrixRow(e.getY())][gameRun.matrixColumn(e.getX())]=1;
                    }
                }else if(game[gameRun.matrixRow(e.getY())][gameRun.matrixColumn(e.getX())]==0||game[gameRun.matrixRow(e.getY())][gameRun.matrixColumn(e.getX())]==1){}
                else{
                    game[gameRun.matrixRow(e.getY())][gameRun.matrixColumn(e.getX())]=5;

                }


                //display game
                gameRun.wipeBoard(gc);
                gameRun.displayBoard(gc);
                gameRun.displaySetpieces(game,gc);

                if(gameRun.checkWINNER(game)){
                    String player="Blue";
                    if(!turn){player="Red";}
                    POP winbox=new POP();
                    winbox.display("Player: "+player+" WINS!", "WINNER");
                    for(int i=0;i<10;i++){for(int j =0;j<10;j++){game[i][j]=5;}
                    }
                    gameRun.wipeBoard(gc);
                    gameRun.displayBoard(gc);
                    gameRun.displaySetpieces(game,gc);

                }else{}


            }
        };
        // Adding children'
        RS.getChildren().addAll(Addblue,Addred,reset,note);
        downBox.getChildren().addAll(canvas,RS);
        MC.getChildren().addAll(head,downBox);
        root.getChildren().add(MC);

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);



        stage.setTitle("Connect4");
        stage.setScene(scene);
        stage.show();
    }
}
