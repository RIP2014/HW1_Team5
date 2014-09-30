import java.util.PriorityQueue;


public class Planner {
	
	PriorityQueue<Integer> stack;
	int goal, soln;//change
	int[] actionSet;//change
	
	public Planner(){
		this.stack = new PriorityQueue<Integer>();
		this.actionSet = new int[5];
		this.goal = 5;//change
		this.soln = 10;//change
	}
	
	public void solve(){
		this.stack.add(6);
		//use bidirectional breadth first search?
		while(!this.stack.isEmpty() || this.soln == this.goal){
			int curr = this.stack.remove();
			for(int a : actionSet){//change
				int next = transistion(curr, a);//change
				if(next == this.goal){
					return;//chnage will have to get plan
				}
				int cost = aStar(next);//change
				this.stack.add(next,cost);//change
			}
		}
		
		return;
	}
	
	public int aStar(int next){
		
		return 0;
	}
}
