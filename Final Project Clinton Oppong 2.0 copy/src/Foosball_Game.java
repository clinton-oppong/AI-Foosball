/** Final Project of a Semi-Simulation of Foosball
 * @author Clinton Oppong 
 * <br>December 2019
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Foosball_Game extends Application {
	public BorderPane borderPane;
	private static final int width = 800;
	private static final int height = 600;
	private double ballX = 483;
	private double ballY = 348;
	private static final double BALL_R = 20;
	private int ballYSpeed = 1;
	private int ballXSpeed = 1;
	private static final Integer STARTTIME = 30;
	private static final Integer STARTTIME_2 = 60;
	private static final Integer STARTTIME_3 = 180;
	private static final Integer STARTTIME_4 = 300;
	private Label timerLabel = new Label();
	private int ScoreLabel_1 = 1;
	private int ScoreLabel_2 = 1;
	private Text text = new Text(new Integer(ScoreLabel_1).toString());
	private Text text_1 = new Text(new Integer(ScoreLabel_2).toString());
	private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
	private IntegerProperty scores_1 = new SimpleIntegerProperty(ScoreLabel_1);
	private IntegerProperty scores_2 = new SimpleIntegerProperty(ScoreLabel_2);
	private Label timerLabel_2 = new Label();
	private Label timerLabel_3 = new Label();
	private Label timerLabel_4 = new Label();
	
	private static final int KEYBOARD_MOVEMENT_DELTA = 20; 
	private static final Duration TRANSLATE_DURATION = Duration.seconds(0.25);
	private boolean Begin;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		Image image = new Image(new FileInputStream("background.jpg"));
		ImageView imageView = new ImageView(image);
		imageView.setX(210); 
		imageView.setY(-130); 
		imageView.setFitHeight(990); 
		imageView.setFitWidth(900);
		imageView.setPreserveRatio(true); 
		imageView.setRotate(imageView.getRotate() + 90);
		
		borderPane = new BorderPane();
		
		Canvas canvas = new Canvas(width, height);
		
		GraphicsContext graphical = canvas.getGraphicsContext2D();
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), e -> collision(graphical)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		Group group = new Group ();
		group.getChildren().addAll(imageView);
		stage.setTitle("FoosBall Game");
		stage.setScene(new Scene(new StackPane(canvas)));
		canvas.setOnMouseClicked(e ->  Begin = true);
		stage.show();;
		//moveCircleOnKeyPress(scene, ball, player1, player2,player3,player4);
		timeline.play();
		

	}

			public void collision(GraphicsContext graphical) {
				
				Players Players = new Players();
				
				graphical.setFill(Color.BLACK);
				graphical.fillRect(10, 9, width, height);
				graphical.setFont(Font.font(25));
				if(Begin) {
					ballX+=ballXSpeed;
					ballY+=ballYSpeed;
					if(ballX < width - Players.width  / 4) {
						Players.A1_POS = ballY - Players.lenght / 2;
					}  else {
						Players.A1_POS =  ballY > Players.A1_POS + Players.lenght / 2 ?Players.A1_POS += 1: Players.A1_POS - 1;
					}
					graphical.fillOval(ballX, ballY, BALL_R, BALL_R);
					
				} else {
					graphical.setStroke(Color.YELLOW);
					graphical.setTextAlign(TextAlignment.CENTER);
					graphical.strokeText("Click to Start", width / 2, height / 2);
					
					ballX = 483;
					ballY = 348;
					
					ballXSpeed = new Random().nextInt(2) == 0 ? 1: -1;
					ballYSpeed = new Random().nextInt(2) == 0 ? 1: -1;
					
				}	
					
				if(ballY > height || ballY < 0) ballYSpeed *=-1;
				if(ballX < Players.Player1_POS - Players.width) {
					Players.scoreP2++;
					Begin = false;
				}		
					
				if(ballX > Players.A1_POS + Players.width) {  
					Players.scoreP1++;
					Begin = false;
				}	
					
				if( ((ballX + BALL_R > Players.A1_POS && ballY >= Players.A1_POS && ballY <= Players.A1_POS + Players.lenght) || 
						((ballX < Players.Player1_POS + Players.width) && ballY >= Players.Player1_POS && ballY <= Players.Player1_POS + Players.lenght))) {
						ballYSpeed += 1 * Math.signum(ballYSpeed);
						ballXSpeed += 1 * Math.signum(ballXSpeed);
						ballXSpeed *= -1;
						ballYSpeed *= -1;
					}	
					
				graphical.fillText(Players.scoreP1 + "\t\t\t\t\t\t\t\t" + Players.scoreP2, width / 2, 100);
				graphical.fillRect(Players.A1_POS, 360 , Players.width, Players.lenght);
				graphical.fillRect(Players.Player1_POS, 30 , Players.width , Players.lenght);
			}
					
}					




