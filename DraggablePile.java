import java.awt.*;
import java.util.Vector;

public class DraggablePile extends Pile implements Draggable{
	protected boolean isDragging = false;

	public DraggablePile(Pile pile){
		super(pile);
	}

	public DraggablePile(Vector vector){
		super(vector);
	}

	public boolean isDragging(){
		return isDragging;
	}

	public boolean contentsAreValid(){
		final char[] CONTIGUOUS = {'A','2','3','4','5','6','7','8','9','T','J','Q','K'};
		int continuity = -1;

		Card temp = get(0);

		SuitType suit = temp.getSuit();

		for(int j=0; j<CONTIGUOUS.length; j++){
			if(temp.getFaceValue() == CONTIGUOUS[j]){
				continuity = j;
				break;
			}
		}

		System.out.println("Continuity starting at: "+CONTIGUOUS[continuity]+" with suit: "+suit);
		if(continuity == -1){
			return false;
		}
		// System.out.println("Passed continuity check");

		int size = getSize();
		for(int i=0; i<size; i++){
			Card c = get(i);
			System.out.println("Checking: "+c.getFaceValue()+" with suit: "+c.getSuit());

			if(!c.isFaceUp()){
				System.out.println("Face down card, exiting");
				return false;
			}else if(c.getFaceValue()==CONTIGUOUS[continuity] && c.getSuit().equals(suit)){
				continuity--;
			}else{
				System.out.println("Something went wrong, exiting");
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
		int size = getSize();
		for(int i=0; i<size; i++){
			Card c = get(i);
			c.setCentre(x, y+(c.getFontHeight()+2*FONT_BUFFER_HEIGHT)*i);
			set(i, c);
		}
	}
}