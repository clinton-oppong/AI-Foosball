import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class players_and_AI extends MainFile {
	
	final Circle ball =  Ball_Creator();

	//Players
	public Circle player1 = Player1_Creator();
	public Circle player2 = Player2_Creator();
	public Circle player3 = Player3_Creator();
	public Circle player4 = Player4_Creator();
	public Circle player5 = Player5_Creator();

	//AI
	public Circle AI_1 = AI1_Creator();
	public Circle AI_2 = AI2_Creator();
	public Circle AI_3 = AI3_Creator();
	public Circle AI_4 = AI4_Creator();
	public Circle AI_5 = AI5_Creator();
	
	
	public Circle AI2_Creator() {
		Circle AI_2 = new Circle(700,140, 20, Color.BLUE);
		AI_2.setOpacity(10);
		return AI_2;
	}
	public Circle AI3_Creator() {
		Circle AI_3 = new Circle(700,590, 20, Color.BLUE);
		AI_3.setOpacity(10);
		return AI_3;
	}
	public Circle AI4_Creator() {
		Circle AI_4 = new Circle(550,360, 20, Color.BLUE);
		AI_4.setOpacity(10);
		return AI_4;
	}
	public Circle AI5_Creator() {
		Circle AI_5 = new Circle(300,360, 20, Color.BLUE);
		AI_5.setOpacity(10);
		return AI_5;
	}
	public Circle Ball_Creator() {
		final Circle ball = new Circle(10, 150, 10, Color.HOTPINK);
		ball.setOpacity(10);
		ball.relocate(483,348);
		return ball;
	}
	
	public Circle AI1_Creator() {
		Circle AI_1 = new Circle(980,360, 15, Color.BLACK);
		AI_1.setOpacity(10);
		return AI_1;
	}

	//Create Players
	public Circle Player1_Creator() {
		Circle player1 = new Circle(30,360, 15, Color.WHITE);
		player1.setOpacity(5);
		return player1;
	}
	private Circle Player2_Creator() {
		Circle player2 = new Circle(300 ,140, 20, Color.RED);
		player2.setOpacity(10);
		return player2;
	}
	public Circle Player3_Creator() {

		Circle player3 = new Circle(300,590, 20, Color.RED);
		player3.setOpacity(10);
		return player3;
	}
	public Circle Player4_Creator() {
		Circle player4 = new Circle(450,360, 20, Color.RED);
		player4.setOpacity(10);
		return player4;
	}
	public Circle Player5_Creator() {
		Circle player5 = new Circle(700,360, 20, Color.RED);
		player5.setOpacity(10);
		return player5;
	}
	
	
}
