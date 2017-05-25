public abstract class Suit extends Shape {
	public Suit(){
		super();
	}

	public void setWidth(int w){
	//ensure consistent width and heights by overriding super methoods
		super.setWidth(w);
		super.setHeight((int) (w*5/4));
	}

	public void setHeight(int h){
		super.setWidth((int)(h*4/5));
		super.setHeight(h);
	}
}
