import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.List;


public class Planner {
	
	LinkedList<stateNode> forwardQ = new LinkedList<stateNode>();
	LinkedList<stateNode> backwardQ = new LinkedList<stateNode>();
	Hashtable<String, stateNode> fHash = new Hashtable<String, stateNode>(); 
	Hashtable<String, stateNode> bHash = new Hashtable<String, stateNode>();
	int[] actionSet;//change
	ArrayList<ArrayList<Integer>> map;
	ArrayList<ArrayList<String>> goals;
	stateNode initState;
	String[] actions = {"LEFT","RIGHT","UP","DOWN"};
	
	public Planner(String filename) throws Exception{
		RIPParser parse = new RIPParser(filename);
		this.map = parse.getMap();
		this.goals = parse.getGoals();
		this.initState = new stateNode(parse.getInit(), null);
	}
	
	public void solve(){
		forwardQ.add(this.initState);
		fHash.put(this.initState.getBlockState(), this.initState);
		stateNode curr = forwardQ.removeFirst();
		System.out.println(Integer.parseInt(curr.getBlockLocation().get(2).get(1)));

		/*
		while(!forwardQ.isEmpty()){
			stateNode curr = forwardQ.removeFirst();
			for(String a : actions){
				stateNode next = transition(curr, a);//get Transition
				
				if(next.isGoal(this.goals)){//check if we found a solution
					System.out.println("Found a solution!");
				}
				
				if (next != null && !fHash.contains(next.getBlockState())){//If there is a legal transition that has not been visited yet 
					forwardQ.add(next);
					//if(fHash.contains(curr.getBlockState())){
						//fHash.remove(curr.getBlockState());
					fHash.put(next.getBlockState(), next);
					//}
				}
			}
		}*/
	
	}
	
	public stateNode transition(stateNode curr, String action){
		
		ArrayList bot = curr.getBotLocation();
		int botx = (Integer) bot.get(1);
		int boty = (Integer) bot.get(2);
		
		if(action == "LEFT"){
			if(collide(botx-1, boty)){//an empty space
				for(int i = 1; i < curr.getBlockLocation().size(); i++){//check to see if collide with a block
					//if(((ArrayList<Integer>) curr.getBlockLocation().get(i)).get(1) == Integer.toString(botx)){
						
				//	}
				}
			}
		
		}else if(action == "RIGHT"){
			
		}else if(action == "UP"){
			
		}else if(action == "DOWN"){
			
		}
		
		return null;
	}
	
	public boolean collide(int x, int y){
		if(map.get(x).get(y) == 0){
			return true;
		}
		
		return false;
	}
	
	
	private class stateNode{
		
		ArrayList<ArrayList<String>> blockLocation;
		ArrayList<String> botLocation;
		stateNode parent;
		
		private stateNode(ArrayList state, stateNode parent){
			//System.out.println(state.get(4));
			this.botLocation = (ArrayList<String>) state.get(0);
			this.blockLocation = new ArrayList<ArrayList<String>>();
			for(int i = 1; i < state.size(); i++){
				this.blockLocation.add((ArrayList<String>)state.get(i));
			}
			this.parent = parent;
		}
		
		public ArrayList getBotLocation() {
			return this.botLocation;
		}

		public boolean isGoal(ArrayList goals) {
			if(goals.containsAll(this.blockLocation)){
				return true;
			}
			return false;
		}
		
		private ArrayList<ArrayList<String>> getBlockLocation(){
			return this.blockLocation;
		}

		private String getBlockState(){
			return this.blockLocation.toString();
		}
		
		private stateNode getParent(){
			return this.parent;
		}
		
		private void setParent(stateNode parent){
			this.parent = parent;
		}
		
	}
	
	public static void main(String [] args){
		try {
			Planner par = new Planner("src/challenge.txt");
			par.solve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


