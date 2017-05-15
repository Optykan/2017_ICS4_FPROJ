public interface Draggable{
	public void onDragStart(int x, int y);
	public void onDragEnd(int x, int y);
	public void updatePosition(int x, int y);
}