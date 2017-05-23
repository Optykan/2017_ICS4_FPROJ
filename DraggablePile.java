import java.awt.*;
import java.util.Vector;

public class DraggablePile extends Pile implements Draggable{
	private boolean isDragging = false;

	public DraggablePile(Pile pile){
		super(pile);
	}

	public DraggablePile(Vector vector){
		super(vector);
	}
	public boolean contentsAreValid(){
		final char[] CONTIGUOUS = {'A','2','3','4','5','6','7','8','9','T','J','Q','K'};
		int i = getSize();
		int continuity = -1;

		Card temp = get(0);

		for(int j=0; j<CONTIGUOUS.length; j++){
			if(temp.getFaceValue() == CONTIGUOUS[i]){
				continuity = i;
				break;
			}
		}
		if(continuity == -1){
			return false;
		}

		while(i-->0){
			Card c = get(i);
			if(!c.isFaceUp()){
				return false;
			}else if(c.getFaceValue()==CONTIGUOUS[continuity]){
				continuity++;
			}else{
				return false;
			}
		}
		return true;
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