import java.awt.*;

public class Card extends Shape implements Drawable { 
    private boolean isFaceUp=false;
    private SuitType suit = SuitType.DIAMOND;
    private CardSizeType size = CardSizeType.SMALL;
    private char value = 'A';

    public Card() { 
        super();
        setSize(size);
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


    public void draw(Graphics g)  {
        Font localFont = new Font("SanSerif", 0, (int)Math.round(0.2D * getHeight()));

        SuitClass localObject;

        if (suit.equals(SuitType.DIAMOND)) {
            localObject = new DiamondClass();
        } else if (suit.equals(SuitType.CLUB)) {
            localObject = new ClubClass();
        } else if (suit.equals(SuitType.HEART)) {
            localObject = new HeartClass();
        } else {
            localObject = new SpadeClass();
        }

        g.setColor(getColor());

        localObject.setHeight((int)(getHeight() * 0.25D));
        localObject.setWidth((int)(getWidth() * 0.25D));
        localObject.setCentre(getCentre());

        java.awt.Point localPoint = getCentre();
        g.drawRect((int)(localPoint.getX() - getWidth() / 2), (int)(localPoint.getY() - getHeight() / 2), getWidth(), getHeight());

        localObject.draw(g);

        g.setFont(localFont);
        g.drawString(Character.toString(getFaceValue()), (int)(localPoint.getX() - getWidth() / 2), (int)(localPoint.getY() - getHeight() / 2 + 0.2D * getHeight()));
    }

    public void setHeight(int paramInt) {}

    public void setWidth(int paramInt) {}

    public void draw(Graphics paramGraphics) {}
}
