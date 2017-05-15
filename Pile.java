import java.util.Vector;
import java.awt.*;

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

	public CardClass peek(){
		return (Card)deck.elementAt(0);
	}

	public boolean isEmpty(){
		return deck.isEmpty();
	}

	public boolean hasRuns(){
		//check to see if any runs exist in this pile
		
	}
	public Pile getRuns(){
		return new Pile();
	}

	public void enqueue(Card card){
		deck.add(card);
	}

	public Card dequeue(){
		return (Card)deck.remove(0);
	}

	public void insertCardAt(Card card, int position){
		deck.insertElementAt(card, position);
	}

	public void removeCardAt(int position){
		deck.removeElementAt(position);
	}

	public void push(Card c){
		insertCardAt(c, 0);
	}

	public Card pop(){
		return deck.dequeue();
	}

	public void draw(Graphics g){
		if(deck.isEmpty()){
			Point p = getCentre();
			c.fill3DRect((int)p.x, (int)p.y, getWidth(), getHeight(), true);
		}else{
			peek().draw(c);
		}
	}

	public Vector getVector(){
		return deck;
	}

}
