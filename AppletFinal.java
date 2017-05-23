import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;


public class AppletFinal extends Applet implements ActionListener, MouseListener, MouseMotionListener{
	Graphics g;   // declares a graphics canvas for drawing
	DraggablePile selectedPile;
	Pile[] piles = new Pile[10];
	Pile distribute = new Pile();

	public void init (){
		g = getGraphics ();
		for(int i=0; i<piles.length; i++){
			piles[i] = new Pile();
			piles[i].setCentre((CardSizeType.MEDIUM.getWidth()+20)*i, 100);
		}
		distribute.loadStandardDeck();
		distribute.loadStandardDeck();
		distribute.shuffle();
		for(int i=0; i<4; i++){
			for(int j=0; j<6; j++){
				piles[i].push(distribute.pop());
				if(j==6){
					Card c = piles[i].pop();
					c.setFaceUp(true);
					piles[i].push(c);
				}
			}
		}
		for(int i=4;i<10;i++){
			for(int j=0; j<5; j++){
				piles[i].push(distribute.pop());
				if(j==5){
					Card c = piles[i].pop();
					c.setFaceUp(true);
					piles[i].push(c);
				}
			}
		}
	}

	public void start(){
		System.out.println("start");
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
		return false;
	}

	public void paint (Graphics g){
		// g.drawString ("Hello World", 25, 50);
		// System.out.println("draw");
		for(int i=0; i<10; i++){
			piles[i].draw(g);
		}
		distribute.draw(g);
	}

	public DraggablePile resolveDraggablePile(int x, int y){
		return null;
	}
} 
