package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;//???
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;
	boolean loop=true;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		
		while(loop){
			Tuple tuple= child.next();
				if(tuple!=null){

					if( child.from.equals(whereTablePredicate)) {
						for( Attribute a:tuple.getAttributeList()){
							if(a.getAttributeName().equals(whereAttributePredicate)){
								String s=new String(""+a.getAttributeValue());
								if(s.equals(whereValuePredicate)){
									return tuple;
								}
						
							}
						}
					} else {
						return tuple;
					}
				}
				else{
					return null;
				}
				
		}
		return null;
			
			
			
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}