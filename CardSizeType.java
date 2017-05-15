public class CardSizeType {
    public static final CardSizeType SMALL = new CardSizeType(60);
    public static final CardSizeType MEDIUM = new CardSizeType(80);
    public static final CardSizeType LARGE = new CardSizeType(100);
    public static final CardSizeType XLARGE = new CardSizeType(120);
    
    int height = 60;

    private CardSizeType(int val){
        height = val;
    }

    public String toString(){
        return Integer.toString(height);
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return (int)Math.round(height*0.7);
    }
}
