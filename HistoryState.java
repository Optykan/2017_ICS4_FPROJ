class HistoryState{
	Pile[] piles = null;
	Deck distribute = null;
	public HistoryState(Pile[] p, Deck d){
		//clone so its not a reference
		piles = new Pile[p.length];
		for(int i=0; i<p.length; i++){
			piles[i] = new Pile(p[i]);
			piles[i].setCentre(p[i].getCentre());
		}
		distribute = new Deck(d.getVector());
	}
	public Pile[] getPiles(){
		return piles;
	}
	public Deck getDistribute(){
		return distribute;
	}
}