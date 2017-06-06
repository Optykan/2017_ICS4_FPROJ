import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.File;

public class Card extends Shape { 
	private boolean isFaceUp=false;
	private SuitType suit = SuitType.DIAMOND;
	private CardSizeType size = CardSizeType.LARGE;
	private char value = 'A';

	public Card() { 
		super();
		setSize(size);
		setSuit(SuitType.DIAMOND);
	}

	public Card(char paramChar, SuitType paramSuitType) {
		super();
		setFaceValue(paramChar);
		setSuit(paramSuitType);
		setSize(size);
	}

	public void setFaceValue(char paramChar) {
		String str = "3456789TJQKA2";
		if (str.indexOf(paramChar) != -1) {
			value = paramChar;
		} else {
			throw new IllegalArgumentException("Card value " + paramChar + "is not in range " + str);
		}
	}

	public char getFaceValue(){
		return value;
	}

	public void setSuit(SuitType paramSuitType) {
		suit = paramSuitType;
		if(paramSuitType.equals(SuitType.DIAMOND) || paramSuitType.equals(SuitType.HEART)){
			setColor(Color.RED);
		}else{
			setColor(Color.BLACK);
		}
	}

	public SuitType getSuit() {
		return suit;
	}

	public void setSize(CardSizeType paramCardSizeType){
		size = paramCardSizeType;
		super.setHeight(paramCardSizeType.getHeight());
		super.setWidth(paramCardSizeType.getWidth());
	}

	public CardSizeType getSize() {
		return size;
	}

	public boolean isFaceUp(){
		return isFaceUp;
	}

	public void setFaceUp(boolean b){
		isFaceUp = b;
	}

	public int getFontHeight(){
		return (int)(0.2D*getHeight());
	}


	public void draw(Graphics g)  {
		if(!isFaceUp()){
			java.awt.Point localPoint = getCentre();
			g.setColor(Color.BLUE);
			g.fillRect((int)(localPoint.getX() - getWidth() / 2), (int)(localPoint.getY() - getHeight() / 2), getWidth(), getHeight());
			g.setColor(Color.BLACK);
			g.drawRect((int)(localPoint.getX() - getWidth() / 2), (int)(localPoint.getY() - getHeight() / 2), getWidth(), getHeight());
		}else{

			Font localFont = new Font("SanSerif", Font.PLAIN, (int)Math.round(0.75D * getHeight()*0.2));

			Suit localObject;

			if (suit.equals(SuitType.DIAMOND)) {
				localObject = new Diamond();
			} else if (suit.equals(SuitType.CLUB)) {
				localObject = new Club();
			} else if (suit.equals(SuitType.HEART)) {
				localObject = new Heart();
			} else {
				localObject = new Spade();
			}

			localObject.setHeight((int)(getHeight() * 0.25D));
			localObject.setWidth((int)(getWidth() * 0.25D));
			localObject.setCentre(getCentre());

			java.awt.Point localPoint = getCentre();
			g.setColor(Color.WHITE);
			g.fillRect((int)(localPoint.getX() - getWidth() / 2), (int)(localPoint.getY() - getHeight() / 2), getWidth(), getHeight());
			g.setColor(getColor());
			g.drawRect((int)(localPoint.getX() - getWidth() / 2), (int)(localPoint.getY() - getHeight() / 2), getWidth(), getHeight());

			localObject.draw(g);

			localObject.setCentre((int)localPoint.getX()+getWidth()/2-15, (int)localPoint.getY()-getHeight()/2+15);
			localObject.setHeight((int)(getFontHeight()/1.5));
			localObject.draw(g);

			localObject.setCentre((int)localPoint.getX()-getWidth()/2+15, (int)localPoint.getY()+getHeight()/2-15);
			localObject.draw(g);

			g.setFont(localFont);
			g.drawString(Character.toString(getFaceValue()), (int)(localPoint.getX() - getWidth() / 2)+5, (int)(localPoint.getY() - getHeight() / 2 + 0.2D*getHeight()));
			g.drawString(Character.toString(getFaceValue()), (int)(localPoint.getX() + getWidth() / 2)-15, (int)(localPoint.getY() + getHeight() / 2 - 0.1D*getHeight()));
		}
	}

	public void setHeight(int paramInt) {}

	public void setWidth(int paramInt) {}
}
