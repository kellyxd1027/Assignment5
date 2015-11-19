package simpledatabase;
import java.util.ArrayList;


public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	private boolean loop=true;
	ArrayList<Tuple> tuple3=new ArrayList<Tuple>();
	private ArrayList<String> s=new ArrayList<String>();
	int pre=-1;
	Type p=null;
	Tuple temp=null;
	int count=0;
	int count1=0;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		
		while(loop){
			Tuple tuple=child.next();
			if(tuple!=null){
				tuple3.add(tuple);
			}
			else{
				loop=false;
				for(Attribute a:tuple3.get(0).getAttributeList()){
					s.add(a.getAttributeName());
				}
				pre=s.indexOf(orderPredicate);
			//	 p=tuple3.get(0).getAttributeType(pre);
				 if(pre!=-1){
						for(int x=1;x<tuple3.size();x++){
							temp=tuple3.get(x);
							String st=tuple3.get(x).getAttributeValue(pre).toString();
							int j=x-1;
							while(j>=0 && tuple3.get(j).getAttributeValue(pre).toString().compareTo(st)>0){
								tuple3.set(j+1, tuple3.get(j));
								j--;
							}
							tuple3.set(j+1, temp);
						
					}
					
				}
				//else{
					//return null;
				//}
				
			}
		}
		if(pre==-1){
			if(count<tuple3.size()){
				return tuple3.get(count++);
			}
			else{
				return null;
			}
		}
		else {
			if(count<tuple3.size()){
				return tuple3.get(count++);
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
		return child.getAttributeList();
	}

}