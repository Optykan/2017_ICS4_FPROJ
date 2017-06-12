import java.util.Stack;

public final class HistoryStateManager{
	Stack history = new Stack();

	public static void save(Pile[] p, Deck d){
		history.push(new HistoryState(p, d));
	}

	public static HistoryState retrieve(){
		if(!history.empty()){
			return (HistoryState)history.pop();
		}
		return null;
	}
}