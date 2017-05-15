import java.awt.*;

class Diamond extends Suit implements Drawable{
    // global variables for this class
    // encapsulated data
    private boolean filled = true;

    public DiamondClass(){
        super();
        setColor(Color.red);
    }

    // communicator methods
    public void setIsFilled (boolean newfilled){
        filled = newfilled;
    }

    public boolean isFilled (){
        return filled;
    }

    public void draw (Console c){
        Point point = getCentre();
        int iCentreX = (int)point.getX();
        int iCentreY = (int)point.getY();

        int iWidth = getWidth();
        int iHeight = getHeight();

                // declare two arrays for X & Y coordinates of Diamond
        int iPointsX[] = new int [4];
        int iPointsY[] = new int [4];

        // calculate points on diamond & store in the arrays
        iPointsX [0] = iCentreX - iWidth / 2;
        iPointsY [0] = iCentreY;
        iPointsX [1] = iCentreX;
        iPointsY [1] = iCentreY - iHeight / 2;
        iPointsX [2] = iCentreX + iWidth / 2;
        iPointsY [2] = iCentreY;
        iPointsX [3] = iCentreX;
        iPointsY [3] = iCentreY + iHeight / 2;

        // draw the diamond using methods available from the Console object (c)
        c.setColor (this.getColor());
        if (filled){
            c.fillPolygon (iPointsX, iPointsY, 4);
        }else{
            c.drawPolygon (iPointsX, iPointsY, 4);
        }
    }


    public void draw (Graphics g){
        Point point = getCentre();
        int iCentreX = (int)point.getX();
        int iCentreY = (int)point.getY();

        int iWidth = getWidth();
        int iHeight = getHeight();
        // declare two arrays for X & Y coordinates of Diamond
        int iPointsX[] = new int [4];
        int iPointsY[] = new int [4];

        // calculate points on diamond & store in the arrays
        iPointsX [0] = iCentreX - iWidth / 2;
        iPointsY [0] = iCentreY;
        iPointsX [1] = iCentreX;
        iPointsY [1] = iCentreY - iHeight / 2;
        iPointsX [2] = iCentreX + iWidth / 2;
        iPointsY [2] = iCentreY;
        iPointsX [3] = iCentreX;
        iPointsY [3] = iCentreY + iHeight / 2;

        // draw the diamond using methods available from the Console object (c)
        g.setColor (this.getColor());
        if (filled){
            g.fillPolygon (iPointsX, iPointsY, 4);
        }else{
            g.drawPolygon (iPointsX, iPointsY, 4);
        }

    }
}
