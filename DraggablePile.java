import java.awt.*;

public class DraggablePile extends Pile implements Drawable, Draggable{
	private boolean isDragging = false;

	public DraggablePile(Pile pile){
		super(pile);
	}

	public DraggablePile(Vector vector){
		super(vector);
	}
	public boolean contentsAreValid(){
		static final char[] CONTIGUOUS = {'K','Q','J','T','9','8','7','6','5','4','3','2','A'};
		int i = getSize();
		int continuity = 0;
		while(i-->0){
			Card c = get(i);
			if(!c.isFaceUp() && continuity == 0){
				return false;
			}else if(!c.isFaceUp() && continuity > 0){
				return true;
			}else if(c.getFaceValue()==CONTIGUOUS[continuity]){
				continuity++:
			}else{
				return false;
			}
		}
		//do i need a return statment out here?
	}
	public void draw(Graphics g){

	}
	public void startDrag(){
		isDragging = true;
	}
	public void stopDrag(){
		isDragging = false;
	}
	public void updatePosition(int x, int y){
		setCentre(x, y);
	}
}