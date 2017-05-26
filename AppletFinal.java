import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class AppletFinal extends Applet implements ActionListener, MouseListener, MouseMotionListener{
	Graphics g;   // declares a graphics canvas for drawing
	DraggablePile selectedPile;
	Pile[] piles = new Pile[10];
	Deck distribute = new Deck();

	public void p(Object m){
		System.out.println(m);
	}

	public void init (){
		g = getGraphics ();
		addMouseListener(this);
		addMouseMotionListener(this);
		for(int i=0; i<piles.length; i++){
			piles[i] = new Pile();
			piles[i].setCentre((CardSizeType.LARGE.getWidth()+20)*i+50, 100);
		}
		distribute.setCentre(1000,500);
		distribute.loadStandardDeck();
		distribute.loadStandardDeck();
		distribute.shuffle();

		for(int i=0; i<4; i++){
			for(int j=0; j<6; j++){
				piles[i].push(distribute.pop());
			}
		}
		for(int i=4;i<10;i++){
			for(int j=0; j<5; j++){
				piles[i].push(distribute.pop());
			}
		}
		for(int i=0; i<10; i++){
			Card c = piles[i].pop();
			c.setFaceUp(true);
			piles[i].push(c);
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
			System.out.println("pile");
			selectedPile = pile;
			pile.startDrag();
			System.out.println(pile.get(0));
		}
	}

	//invoked when the mouse is released on a component
	public void mouseReleased(MouseEvent e){
		if(selectedPile != null){
			selectedPile.stopDrag();
			selectedPile = null;
		}
	}

	//MouseMotionListener
	public void mouseDragged(MouseEvent e){
		if(selectedPile instanceof DraggablePile){
			selectedPile.updatePosition(e.getX(), e.getY());
		}
	}
	//MouseMotionListener
	public void mouseMoved(MouseEvent e){
		// System.out.println("mousemoved");
	}

	//implemented from ActionListener
	public void actionPerformed(ActionEvent e){
		System.out.println("actionPerformed");
	}

	public boolean action(ActionEvent e){
		System.out.println("action");
		return true;
	}

	public void paint (Graphics g){
		// g.drawString ("Hello World", 25, 50);
		System.out.println("draw");
		for(int i=0; i<10; i++){
			piles[i].draw(g);
		}
		distribute.draw(g);
	}

	public DraggablePile resolveDraggablePile(int x, int y){
		for(int i=0; i<piles.length; i++){
			if(piles[i].containsPoint(x, y)){
				return piles[i].resolveDraggablePile(x, y);
			}
		}
		return null;
	}
} 
