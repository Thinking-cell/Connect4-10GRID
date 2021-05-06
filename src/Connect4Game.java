import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Connect4Game {
    // To draw Grid box for main grid
    private void drawGridBox(double x, double y, GraphicsContext gc){
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(x,y,50,50);
    }
    // to draw blue setpiece
    public void drawBlueP(double x, double y, GraphicsContext gc){
        gc.setLineWidth(3);
        gc.setStroke(Color.VIOLET);
        gc.strokeOval(x+5,y+5,40,40);
        gc.setFill(Color.BLUE);
        gc.fillOval(x+5,y+5,40,40);
    }
    // To draw red setpiece
    private void drawRedP(double x, double y, GraphicsContext gc){
        gc.setLineWidth(3);
        gc.setStroke(Color.VIOLET);
        gc.strokeOval(x+5,y+5,40,40);
        gc.setFill(Color.RED);
        gc.fillOval(x+5,y+5,40,40);
    }

    public void displayBoard(GraphicsContext gc){

        for(int i=0;i<10;i++){for(int j=0;j<10;j++){drawGridBox(i*50,j*50,gc);}}
    }

    public void wipeBoard(GraphicsContext gc){
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0,0,500,500);

    }

    public void displaySetpieces(int[][] rec,GraphicsContext gc){
        // To draw blue Setpiece
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (rec[i][j]==0) {
                    drawBlueP( j*50, i*50, gc);
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (rec[i][j]==1) {
                    drawRedP( j*50, i*50, gc);
                }
            }
        }

    }

    public int matrixColumn(double x){
        int returnValue= (int) x;
        returnValue=returnValue/50;
        return returnValue;
    }
    public int matrixRow(double y){
        int returnValue= (int) y;
        returnValue=returnValue/50;
        return returnValue;
    }

    public boolean checkPosValidity(int[][] rec){
        boolean returnValue=false;
        int newPosx=0;
        int newPosy=0;
        for (int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(rec[i][j]==2||rec[i][j]==3){
                    newPosx=i;
                    newPosy=j;

                }
            }
        }
        boolean C1=false;
        boolean C2=false;


        if(newPosx==9){C1=true;
        }else {
            if (rec[newPosx+1][newPosy]!=5) {
                C2=true;
            }
        }
        if(C1||C2){returnValue=true;}

        return returnValue;
    }

    public boolean checkWINNER(int[][] rec){
        for(int turnV=0;turnV<2;turnV++){//turn loop for both player
            for(int i=0;i<10;i++){//row loop
                for(int j=0;j<10;j++){//column loop
                    if(rec[i][j]==turnV){//check run for each setpiece
                        //test

                        for(int check=0;check<8;check++){//checking in all 8 dirctions

                            int changeINrow=0;
                            int changeINcol=0;

                            switch (check) {
                                case 0 -> {
                                    changeINrow = -1;
                                    changeINcol = 0;
                                }
                                case 1 -> {
                                    changeINrow = -1;
                                    changeINcol = 1;
                                }
                                case 2 -> {
                                    changeINrow = 0;
                                    changeINcol = 1;
                                }
                                case 3 -> {
                                    changeINrow = 1;
                                    changeINcol = 1;
                                }
                                case 4 -> {
                                    changeINrow = 1;
                                    changeINcol = 0;
                                }
                                case 5 -> {
                                    changeINrow = 1;
                                    changeINcol = -1;
                                }
                                case 6 -> {
                                    changeINrow = 0;
                                    changeINcol = -1;
                                }
                                case 7 -> {
                                    changeINrow = -1;
                                    changeINcol = -1;
                                }
                            }
                            List<Boolean> checkWinList=new ArrayList<Boolean>(Arrays.asList(false,false,false));
                            for(int forward=1;forward<4;forward++){//check in each direction 3 times

                                boolean C1=true;
                                try{//if there is no element
                                    int testCheck=rec[i+((changeINrow)*forward)][j+((changeINcol)*forward)];
                                }catch(IndexOutOfBoundsException e){
                                    C1=false;
                                    //test

                                    break;
                                }

                                if(C1) {
                                    if (rec[i+((changeINrow)*forward)][j+((changeINcol)*forward)]==turnV) {//checking for similar setpiece in each direction
                                        checkWinList.set(forward-1,true);
                                    } else {
                                        checkWinList.set(forward-1, false);
                                    }

                                }else{checkWinList.set(forward-1,false);}

                                if(checkWinList.get(0)&&checkWinList.get(1)&&checkWinList.get(2)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }




}
