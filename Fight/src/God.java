
public class God {

	String name;
	int HP;
	String NormalMove;
	String SpecialMove;
	int nDamage;
	int sDamage;
	
	God(String _name,String _NormalMove,String _SpecialMove,int _nDamage,int _sDamage,int _HP ){
		this.name = _name;
		this.NormalMove=_NormalMove;
		this.SpecialMove=_SpecialMove;
		this.nDamage = _nDamage;
		this.sDamage = _sDamage;
		this.HP = _HP;
		System.out.println("A God Named " + this.name + " is Created");
		
	}
	
	public int doNormalMove() {
		return this.nDamage;
	}
	
	public int doSpecialMove() {
		return this.sDamage;
	}
	
	public void getDamaged (int damage) {
		
		this.HP = this.HP -damage;
	}
	
	
	
}
