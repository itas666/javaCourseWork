/*
This exercise is incomplete
The part that adds functionality is missing completely as I focused on the design
The design is also incomplete, as I did not find a good way to do this with procedures and did everything manually
*/

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class task3 extends Application{
    @Override
    public void start (Stage primaryStage){
        int screenWidth = 800;
        int screenHeight = 600;
        int gapSpace = 5;
        int columns = 5;
        int rows = 4;

        GridPane pane = new GridPane();
        pane.setHgap(gapSpace);
        pane.setVgap(gapSpace);

        int defaultWidth = ((screenWidth-(gapSpace*(columns+1)))/columns);
        int defaultHeight = ((screenHeight-(gapSpace*(rows+1)))/columns);
        Color boxColor = Color.rgb(50, 100, 100);
        GridPane[][] rectangleGrid = new GridPane[columns][rows];
        ColumnConstraints col0 = new ColumnConstraints(defaultWidth, defaultWidth, defaultWidth);
        RowConstraints row0 = new RowConstraints(defaultHeight*0.20, defaultHeight*0.20, defaultHeight*0.20);
        RowConstraints row1 = new RowConstraints(defaultHeight*0.15, defaultHeight*0.15, defaultHeight*0.15);
        RowConstraints row2 = new RowConstraints(defaultHeight*0.65, defaultHeight*0.65, defaultHeight*0.65);
        row0.setValignment(VPos.CENTER);
        row1.setValignment(VPos.CENTER);
        row2.setValignment(VPos.CENTER);
        col0.setFillWidth(true);

        for(int x = 0; x < columns; x++){
            for(int y = 0; y < rows; y++){
                if(x==2 && y == 2 || x== 0){}
                else{
                    rectangleGrid[x][y] = new GridPane();
                    pane.add(rectangleGrid[x][y], x, y);
                    rectangleGrid[x][y].setStyle("-fx-background-color: rgb(50,100,100);");
                    rectangleGrid[x][y].setGridLinesVisible(true);

                    rectangleGrid[x][y].getRowConstraints().addAll(row0, row1, row2);
                    rectangleGrid[x][y].getColumnConstraints().addAll(col0);
                }
            }
        }

        int x = 0;
        int y = 0;
        //0,0 -- name box
        rectangleGrid[x][y] = new GridPane();
        pane.add(rectangleGrid[x][y], x, y);
        rectangleGrid[x][y].setStyle("-fx-background-color: rgb(50,100,100);");
        rectangleGrid[x][y].setGridLinesVisible(true);

        RowConstraints auxRowConstr0 = new RowConstraints();
        RowConstraints auxRowConstr1 = new RowConstraints();

        auxRowConstr0.setPrefHeight(defaultHeight*0.75);
        auxRowConstr1.setPrefHeight(defaultHeight*0.25);

        rectangleGrid[x][y].getRowConstraints().add(0, auxRowConstr0);
        rectangleGrid[x][y].getRowConstraints().add(1, auxRowConstr1);
        Text nameText = new Text("Ivana");
        nameText.setFont(Font.font("Arial Black", 20));
        nameText.setFill(Color.WHITE);

        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].setStyle("-fx-background-color: DARKORANGE;");

        rectangleGrid[x][y].add(nameText,0,0);
        rectangleGrid[x][y].add(new Rectangle(defaultWidth, defaultHeight*0.25, Color.BLACK),0,1);

        rectangleGrid[x][y].setHalignment(nameText, HPos.LEFT);
        rectangleGrid[x][y].setValignment(nameText, VPos.TOP);
        //0,1 -- photo box
        y++;
        rectangleGrid[x][y] = new GridPane();
        pane.add(rectangleGrid[x][y], x, y);
        rectangleGrid[x][y].setStyle("-fx-background-color: rgb(50,100,100);");
        rectangleGrid[x][y].setGridLinesVisible(true);

        auxRowConstr0 = new RowConstraints();

        auxRowConstr0.setPrefHeight(defaultHeight*1.2);

        rectangleGrid[x][y].getRowConstraints().add(0, auxRowConstr0);
        
        Circle userPic = new Circle(10000,100000, defaultWidth/2, new ImagePattern(new Image("/img/user.jpg")));
        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(userPic,0,0);
        rectangleGrid[x][y].setStyle("-fx-background-color: Black;");
        //0,2 -- Empty
        y++;
        rectangleGrid[x][y] = new GridPane();
        pane.add(rectangleGrid[x][y], x, y);
        rectangleGrid[x][y].setStyle("-fx-background-color: Black;");
        //0,3 -- Back and Home buttons
        y++;
        rectangleGrid[x][y] = new GridPane();
        pane.add(rectangleGrid[x][y], x, y);
        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].setStyle("-fx-background-color: Black;");
        //1,0 -- Time min:sec
        y=0;
        x++;
        Text timeText = new Text("time");
        timeText.setFont(Font.font("Arial Black", 14));
        timeText.setFill(Color.WHITE);
        Text minSecText = new Text("min:sec");
        minSecText.setFont(Font.font("Arial Black", 12));
        minSecText.setFill(Color.WHITE);
        Text minSecCount = new Text("16:30");
        minSecCount.setFont(Font.font("Arial Black", 48));
        minSecCount.setFill(Color.WHITE);

        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(timeText, 0, 0);
        rectangleGrid[x][y].add(minSecText, 0, 1);
        rectangleGrid[x][y].add(minSecCount, 0, 2);
        rectangleGrid[x][y].setStyle("-fx-background-color: rgb(50,100,100);");

        rectangleGrid[x][y].setHalignment(timeText, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(minSecText, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(minSecCount, HPos.CENTER);
        //1,1 -- Heart Rate
        y++;
        Text hr = new Text("Heart rate");
        hr.setFont(Font.font("Arial Black", 14));
        hr.setFill(Color.WHITE);
        Text bm = new Text("beats/minute");
        bm.setFont(Font.font("Arial Black", 12));
        bm.setFill(Color.WHITE);
        Text bmCount = new Text("93");
        bmCount.setFont(Font.font("Arial Black", 48));
        bmCount.setFill(Color.WHITE);

        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(hr,0,0);
        rectangleGrid[x][y].add(bm,0,1);
        rectangleGrid[x][y].add(bmCount,0,2);
        
        rectangleGrid[x][y].setHalignment(hr, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(bm, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(bmCount, HPos.CENTER);
        //1,2 -- Effort
        y++;
        Text effortText = new Text("Effort Level");
        effortText.setFont(Font.font("Arial Black", 14));
        effortText.setFill(Color.WHITE);
        Text effortLevel = new Text("15");
        effortLevel.setFont(Font.font("Arial Black", 48));
        effortLevel.setFill(Color.WHITE);
        Text plusEffort = new Text("+");
        plusEffort.setFont(Font.font("Arial Black", 24));
        plusEffort.setFill(Color.CYAN);
        Text minusEffort = new Text("-");
        minusEffort.setFont(Font.font("Arial Black", 24));
        minusEffort.setFill(Color.CYAN);

        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(effortText,0,0);
        rectangleGrid[x][y].add(effortLevel,0,2);
        rectangleGrid[x][y].add(plusEffort,0,2);
        rectangleGrid[x][y].add(minusEffort,0,2);

        
        rectangleGrid[x][y].setHalignment(effortText, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(effortLevel, HPos.CENTER);
        rectangleGrid[x][y].setValignment(plusEffort, VPos.BOTTOM);
        rectangleGrid[x][y].setHalignment(plusEffort, HPos.RIGHT);
        rectangleGrid[x][y].setValignment(minusEffort, VPos.BOTTOM);
        rectangleGrid[x][y].setHalignment(minusEffort, HPos.LEFT);
        //1,3 -- Blank
        y++;
        rectangleGrid[x][y].setStyle("-fx-background-color: Black;");
        //2,0 -- Calories
        y=0;
        x++;

        Text calText1 = new Text("Calories");
        calText1.setFont(Font.font("Arial Black", 14));
        calText1.setFill(Color.WHITE);
        Text calText2 = new Text("Kcal");
        calText2.setFont(Font.font("Arial Black", 12));
        calText2.setFill(Color.WHITE);
        Text calCount = new Text("571");
        calCount.setFont(Font.font("Arial Black", 48));
        calCount.setFill(Color.WHITE);
        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(calText1,0,0);
        rectangleGrid[x][y].add(calText2,0,1);
        rectangleGrid[x][y].add(calCount,0,2);
        rectangleGrid[x][y].setAlignment(Pos.TOP_CENTER);


        rectangleGrid[x][y].setHalignment(calText1, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(calText2, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(calCount, HPos.CENTER);
        //2,1 -- Countdown
        y++;
        Text timeText1 = new Text("Time");
        timeText1.setFont(Font.font("Arial Black", 14));
        timeText1.setFill(Color.WHITE);
        Text timeText2 = new Text("min:sec");
        timeText2.setFont(Font.font("Arial Black", 12));
        timeText2.setFill(Color.WHITE);
        Text timerText = new Text("20:00");
        timerText.setFont(Font.font("Arial Black", 14));
        timerText.setFill(Color.WHITE);
        ProgressBar timer = new ProgressBar(0);

        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(timeText1, 0, 0);
        rectangleGrid[x][y].add(timeText2, 0, 1);
        rectangleGrid[x][y].add(timer, 0, 2);
        rectangleGrid[x][y].add(timerText, 0, 2);
        
        rectangleGrid[x][y].setHalignment(timeText1, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(timeText2, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(timer, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(timerText, HPos.CENTER);
        rectangleGrid[x][y].setValignment(timerText, VPos.BOTTOM);
        //2,2 -- Pause
        y++;
        rectangleGrid[x][y] = new GridPane();
        pane.add(rectangleGrid[x][y], x, y);
        rectangleGrid[x][y].setStyle("-fx-background-color: rgb(50,100,100);");
        rectangleGrid[x][y].setGridLinesVisible(true);

        auxRowConstr0 = new RowConstraints();
        auxRowConstr1 = new RowConstraints();
        RowConstraints auxRowConstr2 = new RowConstraints();

        auxRowConstr0.setPrefHeight(defaultHeight/2);
        auxRowConstr1.setPrefHeight(defaultHeight/2);
        auxRowConstr2.setPrefHeight(0);
        auxRowConstr2.setPrefHeight(0);
        auxRowConstr2.setPrefHeight(0);

        rectangleGrid[x][y].getRowConstraints().add(0, auxRowConstr0);
        rectangleGrid[x][y].getRowConstraints().add(1, auxRowConstr1);
        rectangleGrid[x][y].getRowConstraints().add(2, auxRowConstr2);


        Rectangle pauseStop = new Rectangle(defaultWidth, defaultHeight/2, Color.RED);
        Rectangle coolDown = new Rectangle(defaultWidth, defaultHeight/2, Color.PURPLE);
        Text pauseText = new Text("PAUSE\n STOP");
        pauseText.setFont(Font.font("Arial Black", 16));
        pauseText.setFill(Color.WHITE);
        Text cdText = new Text("COOLDOWN");
        cdText.setFont(Font.font("Arial Black", 16));
        cdText.setFill(Color.WHITE);
        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(pauseStop, 0, 0);
        rectangleGrid[x][y].add(pauseText, 0, 0);
        rectangleGrid[x][y].add(coolDown, 0, 1);
        rectangleGrid[x][y].add(cdText, 0, 1);

        rectangleGrid[x][y].setHalignment(pauseText, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(cdText, HPos.CENTER);


        //2,3 -- Blank
        y++;
        rectangleGrid[x][y].setStyle("-fx-background-color: Black;");
        //3,0 -- Distance
        y=0;
        x++;
        Text distText1 = new Text("Distance");
        distText1.setFont(Font.font("Arial Black", 14));
        distText1.setFill(Color.WHITE);
        Text distText2 = new Text("km");
        distText2.setFont(Font.font("Arial Black", 12));
        distText2.setFill(Color.WHITE);
        Text distCount = new Text("1.51");
        distCount.setFont(Font.font("Arial Black", 48));
        distCount.setFill(Color.WHITE);

        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(distText1, 0, 0);
        rectangleGrid[x][y].add(distText2, 0, 1);
        rectangleGrid[x][y].add(distCount, 0, 2);

        rectangleGrid[x][y].setHalignment(distText1, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(distText2, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(distCount, HPos.CENTER);

        //3,1 -- Power
        y++;
        Text powText1 = new Text("Power");
        powText1.setFont(Font.font("Arial Black", 14));
        powText1.setFill(Color.WHITE);
        Text powText2 = new Text("watts");
        powText2.setFont(Font.font("Arial Black", 12));
        powText2.setFill(Color.WHITE);
        Text powCount = new Text("216");
        powCount.setFont(Font.font("Arial Black", 48));
        powCount.setFill(Color.WHITE);
        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(powText1, 0, 0);
        rectangleGrid[x][y].add(powText2, 0, 1);
        rectangleGrid[x][y].add(powCount, 0, 2);

        rectangleGrid[x][y].setHalignment(powText1, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(powText2, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(powCount, HPos.CENTER);
        

        //3,2 -- Speed
        y++;
        Text spdText1 = new Text("Speed");
        spdText1.setFont(Font.font("Arial Black", 14));
        spdText1.setFill(Color.WHITE);
        Text spdText2 = new Text("spm");
        spdText2.setFont(Font.font("Arial Black", 12));
        spdText2.setFill(Color.WHITE);
        Text spdCount = new Text("94");
        spdCount.setFont(Font.font("Arial Black", 48));
        spdCount.setFill(Color.WHITE);

        rectangleGrid[x][y].add(spdText1, 0, 0);
        rectangleGrid[x][y].add(spdText2, 0, 1);
        rectangleGrid[x][y].add(spdCount, 0, 2);

        rectangleGrid[x][y].setHalignment(spdText1, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(spdText2, HPos.CENTER);
        rectangleGrid[x][y].setHalignment(spdCount, HPos.CENTER);
        //3,3 -- Blank
        y++;
        rectangleGrid[x][y].setStyle("-fx-background-color: Black;");
        //4,0 -- TV
        y=0;
        x++;
        
        Text tvText = new Text("TV");
        tvText.setFont(Font.font("Arial Black", 14));
        tvText.setFill(Color.WHITE);

        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(tvText, 0, 0);
        rectangleGrid[x][y].add(new Text("PHOTO"), 0, 2);

        rectangleGrid[x][y].setStyle("-fx-background-color: Cyan;");
        //4,1 -- Utube
        y++;
        Text tubeText = new Text("uTube");
        tubeText.setFont(Font.font("Arial Black", 14));
        tubeText.setFill(Color.WHITE);

        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(tubeText, 0, 0);
        rectangleGrid[x][y].add(new Text("PHOTO"), 0, 2);

        rectangleGrid[x][y].setStyle("-fx-background-color: Cyan;");
        //4,2 -- Tuneup
        y++;
        
        Text tuneText = new Text("tuneUp");
        tuneText.setFont(Font.font("Arial Black", 14));
        tuneText.setFill(Color.WHITE);

        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        rectangleGrid[x][y].add(tuneText, 0, 0);
        rectangleGrid[x][y].add(new Text(""), 0, 1);
        rectangleGrid[x][y].add(new Text("PHOTO"), 0, 2);

        rectangleGrid[x][y].setStyle("-fx-background-color: Cyan;");
        //4,3 -- BT and volume
        y++;

        rectangleGrid[x][y].setStyle("-fx-background-color: Black;");
        rectangleGrid[x][y].minWidth(defaultWidth);
        rectangleGrid[x][y].minHeight(defaultHeight);
        
        pane.setPrefSize(defaultWidth, defaultHeight);
        pane.setAlignment(Pos.CENTER);
        //pane.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(pane, screenWidth, screenHeight);
        primaryStage.setTitle("Task 3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}