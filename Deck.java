import java.util.Vector;
import java.awt.*;
import java.util.Collections;

//0 is the bottom of the vector (very last card)
//cards are added starting from the top
public class Deck extends Shape{
	protected static final int FONT_BUFFER_HEIGHT = 10;
	protected Vector deck = new Vector();

	public Deck(){
		super();
	}
	public Deck(Pile pile){
		super();
		deck.addAll(pile.getVector());
	}

	public Deck(Vector vector){
		super();
		deck.addAll(vector);
	}

	public void dumpContents(){
		int size = getSize();
		String out = "Size: "+Integer.toString(size)+" || Contents: ";
		for(int i=0; i<size; i++){
			out += Character.toString(get(i).getFaceValue())+" ";
		}
		System.out.println(out);
	}

	public void addAll(Vector vector){
		for(int i=0; i<vector.size(); i++){
			push((Card)vector.get(i));
		}
	}

	public void addAll(Deck deck){
		addAll(deck.getVector());
	}

	public void set(int i, Card c){
		deck.set(i,c);
	}

	public Card peek(){
		//unconventional, but ok
		if(deck.size() == 1){
			return get(0);
		}
		return (Card)deck.lastElement();
	}

	public void loadStandardDeck(){
		// final SuitType[] suits = {SuitType.DIAMOND, SuitType.CLUB, SuitType.HEART, SuitType.SPADE};
		final char[] values = {'K','Q','J','T','9','8','7','6','5','4','3','2','A'};
		for(int i=0; i<4; i++){
			for(int j=0; j<13; j++){
				Card p = new Card(values[j], SuitType.DIAMOND);
				p.setFaceUp(false);
				push(p);
			}
		}
	}

	public void shuffle(){
		Collections.shuffle(deck);
	}

	public boolean isEmpty(){
		return deck.isEmpty();
	}

	public Card get(int index){
		return (Card)deck.elementAt(index);
	}

	public int getSize(){
		return deck.size();
	}

	public void push(Card card){
		card.setCentre(getCentre());
		deck.add(card);
	}

	public Card pop(){
		if(deck.size()==0){
			return null;
		}
		return (Card)deck.remove(deck.size()-1);
	}

	public void draw(Graphics g){
		Card test = new Card();
		if(deck.isEmpty()){
			Point p = getCentre();
			g.setColor(Color.WHITE);
			g.fillRect((int)p.x-test.getWidth()/2, (int)p.y-test.getHeight()/2, test.getWidth(), test.getHeight());
		}else{
			get(0).draw(g);
		}
	}

	public Vector getVector(){
		return deck;
	}

	public boolean containsPoint(int x, int y){
		int size = getSize();
		int width = getWidth();
		Point p = getCentre();
		int cx = (int)p.getX();
		int cy = (int)p.getY();
		Card test = new Card();

		return x>=cx-test.getWidth()/2 && x<=cx+test.getWidth()/2 && y>=cy-test.getHeight()/2 && y<=cy+test.getHeight()/2;
	}

	public Card removeCardAt(int position){
		return (Card)deck.remove(position);
	}

}
