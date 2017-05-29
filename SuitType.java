
public class SuitType {
	public static final SuitType DIAMOND = new SuitType("diamond");
	public static final SuitType CLUB = new SuitType("club");
	public static final SuitType HEART = new SuitType("heart");
	public static final SuitType SPADE = new SuitType("spade");

	String value;

	private SuitType(String v){
		value = v;
	}

	public String toString(){
		return value;
	}

	public boolean equals(SuitType s){
		return s.toString().equals(value);
	}
}