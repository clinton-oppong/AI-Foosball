import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainFile extends Application {
	public BorderPane BPane;
	Button button, clickButton;
	TextField textField;
	//Time Stuff
	private static final Integer STARTTIME = 30;
	private static final Integer STARTTIME_2 = 60;
	private static final Integer STARTTIME_3 = 180;
	private static final Integer STARTTIME_4 = 300;
	private Label timerLabel = new Label();
	private int ScoreLabel_1 = 0;
	private int ScoreLabel_2 = 0;
	private Label text = new Label();
	private Label text_1 = new Label();
	private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
	private Label timerLabel_2 = new Label();
	private Label timerLabel_3 = new Label();
	private Label timerLabel_4 = new Label();
	private static final int KEYBOARD_MOVEMENT_DELTA = 15; 
	private boolean Begin;
	
	@Override
	public void start(Stage stage) throws Exception {

		players_and_AI Players = new players_and_AI();
		//Imported Image or Background
		Image image = new Image(new FileInputStream("background.jpg"));
		ImageView imageView = new ImageView(image);
		imageView.setX(210); 
		imageView.setY(-130); 
		imageView.setFitHeight(990); 
		imageView.setFitWidth(900);
		imageView.setPreserveRatio(true); 
		imageView.setRotate(imageView.getRotate() + 90);


		final Group group = new Group (imageView,Players.ball);
		final Scene scene = new Scene(group, 1000, 650, Color.BLACK);
		moveCircleOnKeyPress(scene, Players.ball, Players.player1, Players.player2,
				Players.player3,Players.player4,Players.player5);
		scene.setOnMouseClicked(e ->  Begin = true);
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
			double dx = (7); //Step on x or velocity
			double dy = (3); //Step on y




			public void handle(ActionEvent t) {
				if (Begin) {
					Players.ball.setLayoutY(Players.ball.getLayoutY() + dy);
					Players.ball.setLayoutX(Players.ball.getLayoutX() + dx);


					if(Players.ball.getLayoutX() <= (-10 + Players.ball.getRadius()) || 
							Players.ball.getLayoutX() >= (1000 - Players.ball.getRadius()) ){

						dx = -dx;

					}

					//If the ball reaches the bottom or top border make the step negative
					if((Players.ball.getLayoutY() >= (500 - Players.ball.getRadius())) || 
							(Players.ball.getLayoutY() <= (-10 + Players.ball.getRadius()))){

						dy = -dy;

					}

					if (Players.ball.intersects(Players.player1.getRadius(), 30, 360, 15)) {

						dx *= -1;
						dy *= -1;
					}

					if (Players.ball.intersects(Players.player2.getRadius(), 300 ,140, 20)) {

						dx *= -1;
						dy *= -1;
					}
					try {
						if(Players.ball.getLayoutY() >= (400 - Players.ball.getRadius())){
							ScoreLabel_1++;
						}
					}catch (Exception e) {
						System.out.println("Something went wrong.");

					}
					if(Players.ball.getLayoutY() <= (-10 + Players.ball.getRadius())){
						ScoreLabel_2++;
					}

				};

			}
		}));



		//time label
		MenuItem borderBt1 = new MenuItem("30 seconds");
		MenuItem borderBt2 = new MenuItem("1 minuets");
		MenuItem borderBt3 = new MenuItem("3 minuets");
		MenuItem borderBt4 = new MenuItem("5 minuets");
		timerLabel.textProperty().bind(timeSeconds.asString());
		timerLabel.setTextFill(Color.YELLOW);
		timerLabel.setStyle("-fx-font-size: 3em;");

		text.setText(Integer.toString(ScoreLabel_1));
		text.setTextFill(Color.RED);
		text.setStyle("-fx-font-size: 3em;");
		text_1.setText(Integer.toString(ScoreLabel_2));
		text_1.setTextFill(Color.BLUE);
		text_1.setStyle("-fx-font-size: 3em;");


		//Buttons Event for Time
		borderBt1.setOnAction(e -> {
			if (timeline != null) {
				timeline.playFromStart();
			}
			timeSeconds.set(STARTTIME);

			timeline.getKeyFrames().add( 
					new KeyFrame(Duration.seconds(30),
							new KeyValue(timeSeconds, 0)));
			timeline.play();
		});


		borderBt2.setOnAction(e -> {
			if (timeline != null) {
				timeline.pause();
			}
			timeSeconds.set(STARTTIME_2);

			timeline.getKeyFrames().add(
					new KeyFrame(Duration.seconds(60),
							new KeyValue(timeSeconds, 0)));
			timeline.playFromStart();
		}
				);

		borderBt3.setOnAction(e -> {
			if (timeline != null) {
				timeline.pause();
			}
			timeSeconds.set(STARTTIME_3);

			timeline.getKeyFrames().add(
					new KeyFrame(Duration.seconds(180),
							new KeyValue(timeSeconds, 0)));
			timeline.playFromStart();
		}
				);

		borderBt4.setOnAction(e -> {
			if (timeline != null) {
				timeline.pause();
			}
			timeSeconds.set(STARTTIME_4);

			timeline.getKeyFrames().add(
					new KeyFrame(Duration.seconds(300),
							new KeyValue(timeSeconds, 0)));
			timeline.playFromStart();
		}
				);
		

		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();


		//Time Menu
		MenuButton menuButton = new MenuButton 
				("Pick a time", null, borderBt1, borderBt2, borderBt3, borderBt4);
		HBox hbox = new HBox(menuButton);
		hbox.setPrefWidth(scene.getWidth());
		hbox.setLayoutY(10);
		hbox.setLayoutX(40);


		// Time Label placement
		VBox vb = new VBox(30);
		vb.setAlignment(Pos.TOP_RIGHT);
		vb.setPrefWidth(scene.getWidth());
		vb.getChildren().addAll(timerLabel, timerLabel_2, timerLabel_3, timerLabel_4);
		vb.setLayoutY(0);
		vb.setLayoutX(-10);


		VBox vb_1 = new VBox(20);
		vb_1.setAlignment(Pos.TOP_RIGHT);
		vb_1.setPrefWidth(scene.getWidth());
		vb_1.getChildren().addAll(text);
		vb_1.setLayoutY(30);
		vb_1.setLayoutX(-450);

		VBox vb_2 = new VBox(20);
		vb_2.setAlignment(Pos.TOP_RIGHT);
		vb_2.setPrefWidth(scene.getWidth());
		vb_2.getChildren().addAll(text_1);
		vb_2.setLayoutY(30);
		vb_2.setLayoutX(-550);

		//For Difficult
		MenuItem borderBt21 = new MenuItem("EASY:  as can be");
		MenuItem borderBt22 = new MenuItem("NORMAL:  for Basic Players");
		MenuItem borderBt23 = new MenuItem("HARD:  Go for it ");
		MenuItem borderBt24 = new MenuItem("INSANE:  Are you sure?");
		
		//Button Event for Difficulty
		borderBt21.setOnAction(e -> { 
			double dx = 20; 
			double dy = 20;
		});

		borderBt22.setOnAction(e -> { 
			double dx = 20; 
			double dy = 20;
		});

		borderBt23.setOnAction(e -> { 
			double dx = 30; 
			double dy = 30;
		});

		borderBt24.setOnAction(e -> { 
			double dx = 50; 
			double dy = 50;
		});

		MenuButton menuButton4 = new MenuButton 
				("Pick a Difficulty", null, borderBt21, borderBt22, 
						borderBt23, borderBt24);
		HBox hbox_4 = new HBox(menuButton4);
		hbox_4.setPrefWidth(scene.getWidth());
		hbox_4.setLayoutY(10);
		hbox_4.setLayoutX(150);


		//For Picking Rounds
		MenuButton menuButton3 = new MenuButton 
				("Pick Amount of Rounds", null);
		HBox hbox_3 = new HBox(menuButton3);
		hbox_3.setPrefWidth(scene.getWidth());
		hbox_3.setLayoutY(10);
		hbox_3.setLayoutX(290);


		//For Game Setting
		MenuItem borderBt25 = new MenuItem("Exit");

		borderBt25.setOnAction(e -> { 
			Platform.exit();
		});
		MenuButton menuButton5 = new MenuButton 
				("Game Setting", null, borderBt25 );
		HBox hbox_5 = new HBox(menuButton5);
		hbox_5.setPrefWidth(scene.getWidth());
		hbox_5.setLayoutY(10);
		hbox_5.setLayoutX(480);

		group.getChildren().addAll(Players.player1,Players.player2,Players.player3,Players.player4,Players.player5);
		group.getChildren().addAll(hbox,hbox_3,hbox_4,hbox_5,vb,vb_1,vb_2);
		group.getChildren().addAll(Players.AI_1,Players.AI_2,Players.AI_3,Players.AI_4,Players.AI_5);

		stage.setTitle("FoosBall Game");
		stage.setScene(scene);
		stage.show();




	}


	private void moveCircleOnKeyPress( Scene scene,final Circle ball, final Circle player1,
			final Circle player2, final Circle player3, final Circle player4, final Circle player5) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case I: player1.setCenterY(player1.getCenterY() - KEYBOARD_MOVEMENT_DELTA); break;
				case Q: player2.setCenterY(player2.getCenterY() - KEYBOARD_MOVEMENT_DELTA); break;
				case W: player3.setCenterY(player3.getCenterY() - KEYBOARD_MOVEMENT_DELTA); break;
				case E: player4.setCenterY(player4.getCenterY() - KEYBOARD_MOVEMENT_DELTA); break;
				case R: player5.setCenterY(player5.getCenterY() - KEYBOARD_MOVEMENT_DELTA); break;

				//case L: player1.setCenterX(player1.getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;

				case K: player1.setCenterY(player1.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
				case A: player2.setCenterY(player2.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
				case S: player3.setCenterY(player3.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
				case D: player4.setCenterY(player4.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
				case F: player5.setCenterY(player5.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
				default:
					break;




				}
			}
		});
	}


	public static void main(String[] args) throws  IOException
	{

		File excelfile = new File("Formation.xlsx");
		FileInputStream fileinput = new FileInputStream(excelfile);
		XSSFWorkbook workbook = new XSSFWorkbook(fileinput);
		XSSFSheet sheet = workbook.getSheetAt(1);
		Iterator<Row> rowIt = sheet.iterator();


		while(rowIt.hasNext()) {
			Row row = rowIt.next();

			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String formations = cell.toString();
				System.out.println(formations);
			}


			System.out.println();
		}
		workbook.close();
		fileinput.close();
		launch(args);

		try {
			byte bWrite []  = {98,70,70,55,30,30,30,30,45,70};
			OutputStream os = new FileOutputStream("Formation.txt");
			for(int x = 0; x < bWrite.length ; x++) {
				os.write( bWrite[x]);   
			}
			os.close();

			InputStream is = new FileInputStream("Fomation.txt");
			int size = is.available();

			for(int i = 0; i < size; i++) {
				System.out.print((char)is.read() + "  ");
			}
			is.close();
		} catch (IOException e) {
			System.out.print("Exception");
		}	
	}
}	



