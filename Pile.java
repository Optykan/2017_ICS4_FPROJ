import java.util.Stack;

class Pile extends Stack{
	public void push(Card card){
		if(!(card instanceof Card))
			throw new IllegalArgumentException("Passed an instance of "+card.getName());

		super.push(card);
	}
}