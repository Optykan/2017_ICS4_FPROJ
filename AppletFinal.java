import java.applet.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.event.*;
import java.util.Stack;

public class AppletFinal extends Applet implements ActionListener, MouseListener, MouseMotionListener, ImageObserver{
	Graphics g;   // declares a graphics canvas for drawing
	DraggablePile selectedPile;
	int origin = -1;
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
		// drawStack.push(distribute);

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
		new Thread(){
			public void run(){
				while(true){
					repaint();
					try{
						//16.67 ~= 17 ~= 60fps
						Thread.sleep(17);
					}catch(Exception e){

					}
				}
			}
		}.start();

		// final char[] values = {'K','Q','J','T','9','8','7','6','5','4','3','2'};
		// for(int i=0; i<12; i++){
		// 	Card c = new Card(values[i], SuitType.DIAMOND);
		// 	c.setFaceUp(true);
		// 	piles[0].push(c);
		// }
		// distribute.push(new Card('A', SuitType.DIAMOND));
	}

	public void start(){
		System.out.println("start");
	}

	//when the mouse clicks (press and release) a component
	public void mouseClicked(MouseEvent e){
	//dont want to do anything here?
		// System.out.println("clicked");
	}

	//when the mouse enters a component
	public void mouseEntered(MouseEvent e){
		// System.out.println("entered");
	}

	//when the mouse exits a component
	public void mouseExited(MouseEvent e){
		// System.out.println("exit");
	}

	//invoked when a mouse button is pressed on a component
	public void mousePressed(MouseEvent e){
		if(distribute.containsPoint(e.getX(), e.getY())){
			distributeCards();
		}else{	
			int index = resolvePile(e.getX(), e.getY());
			System.out.println("Target: "+index);
			DraggablePile pile = resolveDraggablePile(index, e.getX(), e.getY());
			if (pile != null){
				System.out.println("Resolved pile");
				origin = index;
				pile.updatePosition(e.getX(), e.getY());
				pile.dumpContents();
				selectedPile = pile;
				selectedPile.startDrag();
			}
		}
	}

	//invoked when the mouse is released on a component
	public void mouseReleased(MouseEvent e){
		if(selectedPile != null){
			selectedPile.stopDrag();

			returnDraggableToPile(e.getX(), e.getY());

			selectedPile = null;	
			origin = -1;
		}
		for(int i=0; i<piles.length; i++){
			Card c = piles[i].pop();
			if(c != null){
				if(!c.isFaceUp()){
					c.setFaceUp(true);
				}	
				piles[i].push(c);
			}
		}
		checkForRuns();
	}

	//MouseMotionListener
	public void mouseDragged(MouseEvent e){
		if(selectedPile instanceof Draggable){
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

	public void update(Graphics g){
		Graphics offgc;
		Image offscreen = null;
		Dimension d = size();

		offscreen = createImage(d.width, d.height);
		offgc = offscreen.getGraphics();
		offgc.setColor(getBackground());
		offgc.fillRect(0, 0, d.width, d.height);
		offgc.setColor(getForeground());
		paint(offgc);
		g.drawImage(offscreen, 0, 0, this);
	}


	public void paint(Graphics g){
		for(int i=0; i<piles.length; i++){
			piles[i].draw(g);
		}
		distribute.draw(g);
		if(selectedPile != null){
			selectedPile.draw(g);
		}
	}

	public void checkForRuns(){
		for(int i=0; i<10; i++){
			int index = piles[i].getRunIndex();
			if(index >= 0){
				System.out.println("Attempting to retrieve runs");
				Deck p = piles[i].getRun(index);
				p.dumpContents();
				p = null;
			}
		}
	}

	public void distributeCards(){
		boolean canDeal = true;
		for(int i=0; i<10; i++){
			if(piles[i].isEmpty()){
				canDeal = false;
				break;
			}
		}
		if(canDeal){
			for(int i=0; i<10; i++){
				Card c = distribute.pop();
				c.setFaceUp(true);
				piles[i].push(c);
			}	
		}
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
		return true;
	}

	public DraggablePile resolveDraggablePile(int index, int x, int y){
		if(index >= 0  && index < piles.length){
			return piles[index].resolveDraggablePile(x, y);
		}else if (index == -1){
			return null;
		}
		throw new ArrayIndexOutOfBoundsException("Invalid index: "+index);
	}

	public DraggablePile resolveDraggablePile(int x, int y){
		int index = resolvePile(x, y);
		return resolveDraggablePile(index, x, y);
	}

	public int resolvePile(int x, int y){
		for(int i=0; i<piles.length; i++){
			if(piles[i].containsPoint(x, y)){
				return i;
			}
		}
		return -1;
	}

	public void returnDraggableToPile(int x, int y){
		boolean returned = false;
		for(int i=0; i<piles.length; i++){
			if(piles[i].containsPoint(x, y)){
				if(piles[i].isValidReturn(selectedPile)){
					piles[i].addAll(selectedPile.getVector());
					returned = true;
				}
				break;
			}
		}
		if(!returned){
			piles[origin].addAll(selectedPile.getVector());
		}
	}
} 
