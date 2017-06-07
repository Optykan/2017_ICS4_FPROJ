class HistoryState{
	Pile[] piles = null;
	Deck distribute = null;
	public HistoryState(Pile[] p, Deck d){
		//clone so its not a reference
		piles = new Pile[p.length];
		System.arraycopy(p, 0, piles, 0, p.length);
		distribute = new Deck(d.getVector());
	}
	public Pile[] getPiles(){
		return piles;
	}
	public Deck getDistribute(){
		return distribute;
	}
}