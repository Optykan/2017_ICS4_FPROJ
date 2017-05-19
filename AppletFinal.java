import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class AppletFinal extends Applet implements ActionListener, MouseListener, MouseMotionListener{
    Graphics g;   // declares a graphics canvas for drawing
    DraggablePile selectedPile;

    public void init (){
    	g = getGraphics ();
    }

    //when the mouse clicks (press and release) a component
    public void mouseClicked(MouseEvent e){
        //dont want to do anything here?
    }

    //when the mouse enters a component
    public void mouseEntered(MouseEvent e){

    }

    //when the mouse exits a component
    public void mouseExited(MouseEvent e){

    }

    //invoked when a mouse button is pressed on a component
    public void mousePressed(MouseEvent e){
        DraggablePile pile = resolveDraggablePile(e.getX(), e.getY());
        if (pile != null){
            selectedPile = pile;
            pile.startDrag();
        }
    }

    //invoked when the mouse is released on a component
    public void mouseReleased(MouseEvent e){
        selectedPile.stopDrag();
        selectedPile = null;
    }

    //MouseMotionListener
    public void mouseDragged(MouseEvent e){
        if(selectedPile instanceof DraggablePile){
            selectedPile.updatePosition(e.getX(), e.getY());
        }
    }
    //MouseMotionListener
    public void mouseMoved(MouseEvent e){

    }

    //implemented from ActionListener
    public void actionPerformed(ActionEvent e){

    }

    public boolean action(ActionEvent e){

    }

    public void paint (Graphics g){

    }

    public DraggablePile resolveDraggableRegion(int x, int y){

    }
} 
