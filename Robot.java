import java.util.Scanner;


public class Robot {
	
	private int currentX;
	private int currentY;
	private int previousX;
	private int previousY;
	private boolean lastMoveX = true;
	
	Robot(){
		this.currentX = 0;
		this.currentY = 5;
	}
	
	Robot(int currentX,int currentY){
		this.previousX = 0;
		this.previousY = 5;
		this.currentX = currentX;
		this.currentY = currentY;
	}
	
	public void moveX(int dx){
		this.previousX = this.currentX;
		this.currentX = this.currentX+dx;
		this.previousY = this.currentY;
		this.lastMoveX = true;
	}
	
	public void moveY(int dy){
		this.previousX = this.currentX;
		this.previousY = this.currentY;
		this.currentY = this.currentY+dy;
		this.lastMoveX = false;
	}
	
	public void printCurrentCoordinates(){
		System.out.println(this.currentX+" "+this.currentY);
	}
	
	public void printLastCoordinates(){
		System.out.println(this.previousX+" "+this.previousY);
	}
	
	public void printLastMove(){	
		if(this.lastMoveX)
			System.out.println("x "+ (this.currentX - this.previousX));
		else
			System.out.println("y "+ (this.currentY - this.previousY));
	}
	
private static final Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int x = scan.nextInt();
		int y = scan.nextInt();
		int dx = scan.nextInt();
		int dy = scan.nextInt();
		
		Robot firstRobot = new Robot();
	    firstRobot.printCurrentCoordinates();
	    
	    Robot secondRobot = new Robot(x, y);
	    secondRobot.printCurrentCoordinates();
	    
	    for (int i = 1; i < 3; i++) {
	        secondRobot.moveX(dx);
	        //System.out.println("x moved");
	        secondRobot.printLastMove();
	        secondRobot.printCurrentCoordinates();
	        secondRobot.moveY(dy);

	        //System.out.println("y moved");
	        //secondRobot.printCurrentCoordinates();
	        secondRobot.printLastCoordinates();

	        dx += i * i;
	        dy -= i * i;
	        
	    }		
	}
}
