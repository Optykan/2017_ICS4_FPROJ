import java.util.Vector;
import java.awt.*;
import java.util.Collections;

//0 is the bottom of the vector (very last card)
//cards are added starting from the top
public class Pile extends Shape implements Drawable{
	Vector deck = new Vector();

	public Pile(){
		super();
	}
	public Pile(Pile pile){
		super();
		deck.addAll(pile.getVector());
	}

	public Pile(Vector vector){
		super();
		deck.addAll(vector);
	}

	public void loadStandardDeck(){
		final SuitType[] suits = {SuitType.DIAMOND, SuitType.CLUB, SuitType.HEART, SuitType.SPADE};
		final char[] values = {'K','Q','J','T','9','8','7','6','5','4','3','2','A'};
		for(int i=0; i<4; i++){
			for(int j=0; j<13; j++){
				Card p = new Card(values[j], suits[i]);
				p.setFaceUp(false);
				push(p);
			}
		}
	}

	public void shuffle(){
		Collections.shuffle(deck);
	}

	public Card peek(){
		//unconventional, but ok
		return (Card)deck.lastElement();
	}

	public boolean isEmpty(){
		return deck.isEmpty();
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

	public Card get(int index){
		return (Card)deck.elementAt(index);
	}

	public int getSize(){
		return deck.size();
	}

	// public void enqueue(Card card){
	// 	throw new MethodNotImplementedException();
	// }

	// public Card dequeue(){
	// 	throw new MethodNotImplementedException();
	// 	// return (Card)deck.remove(0);
	// }

	// public void insertCardAt(Card card, int position){
	// 	throw new MethodNotImplementedException();
	// 	// deck.insertElementAt(card, position);
	// }

	// public void removeCardAt(int position){
	// 	throw new MethodNotImplementedException();
	// 	// deck.removeElementAt(position);
	// }

	public void push(Card card){
		final int FONT_BUFFER_HEIGHT = 10;
		Point p = getCentre();
		int y = (int)p.getY();

		card.setCentre((int)p.getX(), y+(card.getFontHeight()+2*FONT_BUFFER_HEIGHT)*deck.size());
		deck.add(card);
	}

	public Card pop(){
		return (Card)deck.remove(deck.size()-1);
	}

	public void draw(Graphics g){
		if(deck.isEmpty()){
			Point p = getCentre();
			g.fill3DRect((int)p.x, (int)p.y, getWidth(), getHeight(), true);
		}else{
			int s=getSize();
			for(int i=0; i<s; i++){
				get(i).draw(g);
			}
		}
	}

	public Vector getVector(){
		return deck;
	}

}
