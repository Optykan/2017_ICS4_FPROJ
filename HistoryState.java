class HistoryState{
	Pile[] piles = null;
	Deck distribute = null;
	public HistoryState(Pile[] p, Deck d){
		piles = p;
		distribute = d;
	}
	public Pile[] getPiles(){
		return piles;
	}
	public Deck getDistribute(){
		return distribute;
	}
}