import java.awt.*;

public abstract class Shape {
	private Color color = Color.RED;
	private int height = 100;
	private int width = 100;
	private Point centre;

	public Shape(){
		centre = new Point(0,0);
	}

	public void setColor(Color c){
		color=c;
	}

	public Color getColor(){
		return color;
	}

	public void setHeight(int h){
		height = h;
	}

	public int getHeight(){
		return height;
	}

	public void setWidth(int w){
		width = w;
	}

	public int getWidth(){
		return width;
	}

	public void setCentre(int x, int y){
		centre.setLocation(x, y);
	}

	public void setCentre(Point p){
		centre = p;
	}

	public Point getCentre(){
		return centre;
	}

	public void erase (Graphics g){
		Color cOldColor = getColor ();
		setColor (Color.white);
		draw (g);
		setColor (cOldColor);
	}      

	public abstract void draw(Graphics g); 


}
