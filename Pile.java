import java.util.Vector;
import java.awt.*;
import java.util.Collections;

//0 is the bottom of the vector (very last card)
//cards are added starting from the top
public class Pile extends Deck{
	public Pile(){
		super();
	}
	public Pile(Pile pile){
		super(pile);
	}

	public Pile(Vector vector){
		super(vector);
	}

	public boolean hasRuns(){
		final char[] compare = {'K','Q','J','T','9','8','7','6','5','4','3','2','A'};
		Object[] cards = deck.toArray();
		int compareIndex = 0;
		for(int i=0; i<cards.length; i++){
			//make sure we dont check the cards that are facedown
			if(!((Card)cards[i]).isFaceUp())
				continue;

			//if we found a run
			if(compareIndex == 13)
				return true;

			if(((Card)cards[i]).getFaceValue() == compare[compareIndex]){
				//if the face value of this card is equal to the comapre index 
				compareIndex++;
			}else{
				compareIndex = 0;
			}
		}

		//we have to check this again on the outside in case the run ends on the last card
		if(compareIndex == 13)
			return true;

		return false;
	}
	public Pile getRun(){
		final char[] compare = {'K','Q','J','T','9','8','7','6','5','4','3','2','A'};
		Object[] cards = deck.toArray();
		int compareIndex = 0;

		Pile res = new Pile();

		for(int i=0; i<cards.length; i++){
			//make sure we dont check the cards that are facedown
			if(!((Card)cards[i]).isFaceUp())
				continue;

			//if we found a run
			if(compareIndex == 13)
				return res;

			if(((Card)cards[i]).getFaceValue() == compare[compareIndex]){
				//if the face value of this card is equal to the comapre index 
				compareIndex++;
				res.push((Card)deck.elementAt(i));
			}else{
				//compare failed, delete everything
				res = new Pile();
				compareIndex = 0;
			}
		}

		//we have to check this again on the outside in case the run ends on the last card
		if(compareIndex == 13){
			int i = cards.length;
			//since the run must always be at the end of the deck, we can go from the top card to the 13th card
			while(i-->cards.length-13){
				deck.removeElementAt(i);
			}
			return res;
		}

		return null;
	}

	public void push(Card card){
		Point p = getCentre();
		int y = (int)p.getY();

		card.setCentre((int)p.getX(), y+(card.getFontHeight()+2*FONT_BUFFER_HEIGHT)*getSize());
		deck.add(card);
	}

	public void draw(Graphics g){
		Card test = new Card();
		if(deck.isEmpty()){
			Point p = getCentre();
			g.setColor(Color.BLACK);
			g.fillRect((int)p.x-test.getWidth()/2, (int)p.y-test.getHeight()/2, test.getWidth(), test.getHeight());
		}else{
			int s=getSize();
			for(int i=0; i<s; i++){
				get(i).draw(g);
			}
		}
	}

	public DraggablePile resolveDraggablePile(int x, int y){
		int length = getSize();
		Point centre = getCentre();
		Card c = new Card();

		//if we're out of x bounds then
		if(x <= centre.getX()-c.getWidth()/2 || x >= centre.getX()+c.getWidth()/2){
			System.out.println("out of bounds you fool");
			return null;
		}

		//at the time that I'm writing this I have a good idea of what this code is doing (May 2017, 2017)
		//...and now only God knows (May 29, 2017)
		for(int i=0; i<length; i++){
			c = get(i);
			//if we are on the last card
			if(i == length-1){
				System.out.println("checking last card");
				int topY = (int)centre.getY() + (length-1)*(2*FONT_BUFFER_HEIGHT+c.getFontHeight())-c.getHeight()/2;
				if(y >= topY && y<=topY+c.getHeight()){
					Pile p = new Pile();
					p.setCentre(c.getCentre());
					p.push(removeCardAt(i));
					return new DraggablePile(p);
				}else{
					return null;
				}
			}else{
				int topY = (int)centre.getY()+i*(2*FONT_BUFFER_HEIGHT+c.getFontHeight())-c.getHeight()/2;
				int bottomY = topY + 2*FONT_BUFFER_HEIGHT+c.getFontHeight();
				//if we determine that we're resolving some other card inside the deck
				if(y >= topY && y <= bottomY){
					Pile res = new Pile();
					res.setCentre((int)centre.getX(), (int)centre.getY()-c.getHeight()/2-i*(2*FONT_BUFFER_HEIGHT+c.getFontHeight()));
					for(int j=i; j<length; j++){
						//we use i here because if we remove a j, then the entire vector shifts by 1, resulting in an ArrayIndexOutOfBoundsException
						res.push(removeCardAt(i));
					}
					DraggablePile drag = new DraggablePile(res);
					drag.setCentre((int)centre.getX(), (int)centre.getY()-c.getHeight()/2-i*(2*FONT_BUFFER_HEIGHT+c.getFontHeight()));
					if(!drag.contentsAreValid()){
						//add the references back to the original stack
						addAll(res);
						return null;
					}else{
						return drag;
					}
				}else{
					continue;
				}
			}
		}
		return null;
	}

	public boolean containsPoint(int x, int y){
		int size = getSize();
		int width = getWidth();
		Point p = getCentre();
		int cx = (int)p.getX();
		int cy = (int)p.getY();
		Card test = new Card();

		return x>=cx-width/2 && x<= cx+width/2 && y>=cy-(test.getHeight()/2) && y<=cy+(size-1)*(2*FONT_BUFFER_HEIGHT+test.getFontHeight())+test.getHeight()/2;
	}

}
