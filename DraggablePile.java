import java.awt.*;

public class DraggablePile extends Pile implements Drawable, Draggable{
	public DraggablePile(Pile pile){
		super(pile);
	}

	public DraggablePile(Vector vector){
		super(vector);
	}
	public boolean contentsAreValid(){

	}
	public void draw(Graphics g){

	}
	public void onDragStart(int x, int y){

	}
	public void onDragEnd(int x, int y){

	}
	public void updatePosition(int x, int y){

	}
}