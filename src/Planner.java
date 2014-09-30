import java.util.LinkedList;
import java.util.Hashtable;
import java.util.List;


public class Planner {
	
	LinkedList<Integer> forwardQ, backwardQ;
	Hashtable<Integer, Integer> fHash, bHash;
	int goal, soln;//change
	int[] actionSet;//change
	
	public Planner(){
		this.forwardQ = new LinkedList<Integer>();
		this.backwardQ = new LinkedList<Integer>();
		this.fHash = new Hashtable<Integer, Integer>();
		this.bHash = new Hashtable<Integer, Integer>();
		this.actionSet = new int[5];
		this.goal = 5;//change
		this.soln = 10;//change
	}
	
	public void solve(){
		//use bidirectional breadth first search?
		while(!this.forwardQ.isEmpty() && !this.backwardQ.isEmpty()){
			
			int fCurr = this.forwardQ.removeFirst();
			int bCurr = this.backwardQ.removeFirst();
			
			for(int a : actionSet){//change
				int fNext = transistion(fCurr, a);//change
				int bNext = bTransition(bCurr,a);//change
				
				if(this.bHash.contains(fNext)){
					return;
				}else{
					this.fHash.add(fNext,fNext);
					this.forwardQ.add(fNext);
				}

				if(this.fHash.contains(bNext)){
					return;
				}else{
					this.bHash.add(bNext,bNext);
					this.backwardQ.add(bNext);
				}
				
				
			}
		}
		return;
	}
}
