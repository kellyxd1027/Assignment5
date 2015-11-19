package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;
	boolean loop=true;
	ArrayList<String> s=new ArrayList<String>();
	String forjoin="";
	Object v=null;
	int in=0;


	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		while(loop){
		   Tuple tuple= leftChild.next();
			if(tuple!=null){
				tuples1.add(tuple);
			}
			else{
				loop=false;
				for(Attribute q:tuples1.get(0).getAttributeList()){
					s.add(q.getAttributeName());
				}
			}
		}
		while(true){
		Tuple ltuple= rightChild.next();
		
		if(ltuple!=null){
			for(Attribute a:ltuple.getAttributeList()){
				if(s.contains(a.getAttributeName())){
					forjoin=a.getAttributeName();
					v=a.getAttributeValue();
					 in=s.indexOf(a.attributeName);
				}
			}
			for(Tuple w:tuples1){
				if(w.getAttributeValue(in)==v||w.getAttributeValue(in).equals(v)){
					ArrayList<Attribute> e=new ArrayList<Attribute>();
					for(Attribute q:ltuple.getAttributeList()){
						if(!q.attributeName.equals(forjoin)){
						e.add(q);
						}
						
					}
					for(Attribute r:w.getAttributeList()){
						e.add(r);
					}
					return new Tuple(e);
				}
			}
			
		}
		else{
			return null;
		}
		}
		
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}