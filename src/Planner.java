import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Hashtable;

public class Planner {
	
	LinkedList<stateNode> forwardQ = new LinkedList<stateNode>();
	LinkedList<stateNode> backwardQ = new LinkedList<stateNode>();
	Hashtable<String, stateNode> fHash = new Hashtable<String, stateNode>(); 
	Hashtable<String, stateNode> bHash = new Hashtable<String, stateNode>();
	int[] actionSet;//change
	ArrayList<ArrayList<Integer>> map;
	ArrayList<ArrayList<String>> goals;
	stateNode initState;
	//TODO change this to enum 
	String[] actions = {"LEFT","RIGHT","UP","DOWN"};
	
	public Planner(String filename) throws Exception{
		RIPParser parse = new RIPParser(filename);
		this.map = parse.getMap();
		this.goals = parse.getGoals();
		this.initState = new stateNode(parse.getInit(), null, "START");
	}
	
	public void solve(){
		forwardQ.add(this.initState);
		fHash.put(this.initState.getBlockState(), this.initState);
		int nodesExplored = 0;
		
		while(!forwardQ.isEmpty()){
			stateNode curr = forwardQ.removeFirst();

			for(String a : actions){
				stateNode next = transition(curr, a);//get Transition
				if(next != null && next.isGoal(this.goals)){//check if we found a solution
					System.out.println("Found a solution!");
					System.out.println("Nodes Explored: "+nodesExplored);
					PrintSolution(next);
					return;
				}
				if(next != null){
					String stateK = next.botLocation + next.getBlockState();

					if (!fHash.containsKey(stateK)){//If there is a legal transition that has not been visited yet 
						forwardQ.add(next);
						nodesExplored++;

						//if(fHash.contains(curr.getBlockState())){
						//fHash.remove(curr.getBlockState());
						fHash.put(stateK, next);
						//}
					}
				}
			}
		}
		System.out.println("No Solution Found");
	}
	
	private void PrintSolution(stateNode curr) {
		String solution = "";
		do{
			solution = curr.action + "\n" + solution;
			curr = curr.getParent();
		}while(curr.parent != null);
		
		System.out.println(solution);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public stateNode transition(stateNode curr, String action){
		
		ArrayList bot = curr.getBotLocation();
		int botx = Integer.parseInt((String)bot.get(1));
		int boty = Integer.parseInt((String)bot.get(2));
		int dX = 0;
		int dY = 0;
		//TODO implement enum for stability
		switch (action) {
		case "UP": dX = 0;
			dY = -1;
			break;
		case "DOWN": dX = 0;
			dY = 1;
			break;
		case "LEFT": dX = -1;
			dY = 0;
			break;
		case "RIGHT": dX = 1;
			dY = 0;
			break;
		}
		
		if(!collide(botx+dX, boty+dY)){//an empty space update here
			for(int i = 0; i < curr.getBlockLocation().size(); i++){//check to see if collide with a block
				if(Integer.parseInt(curr.getBlockLocation().get(i).get(1)) == botx+dX && Integer.parseInt(curr.getBlockLocation().get(i).get(2)) == boty+dY){//see if a bot x or y intersects with a block
					int blockCollied = i;
					if(!collide(botx+2*dX, boty+2*dY)){//update here
						boolean nothBlock = true;
						for(int j = 0; j < curr.getBlockLocation().size(); j++){//check to see if collide with a block
							if((Integer.parseInt(curr.getBlockLocation().get(j).get(1)) == (botx+2*dX) && Integer.parseInt(curr.getBlockLocation().get(j).get(2)) == (boty+2*dY))){//see if there is a block at the pushed spot
								nothBlock = false;
							}
						}
						if(nothBlock){
							if(!curr.goalCollide(botx+2*dX, boty+2*dY, goals)) {
								if(collide(botx+3*dX, boty+3*dY) && (collide(botx+2*dX+(1-Math.abs(dX)), boty+2*dY+(1-Math.abs(dY))) || collide(botx+2*dX-(1-Math.abs(dX)), boty+2*dY-(1-Math.abs(dY))))){
									return null;//pushing block into a corner.
								}
							}
							ArrayList ne = new ArrayList();
							ArrayList x = (ArrayList) curr.getBotLocation().clone();
							x.set(1,Integer.toString(botx+dX));//update x
							x.set(2,Integer.toString(boty+dY));//update y
							ne.add(x);
							for (int k = 0; k < curr.getBlockLocation().size(); k++){
								ArrayList temp = (ArrayList) curr.getBlockLocation().get(k).clone();
								if(k == blockCollied){
									 temp.set(1,Integer.toString(botx+2*dX));//update x
									 temp.set(2,Integer.toString(boty+2*dY));//update y
								}
								ne.add(temp);
							}
							return new stateNode(ne, curr, action);//update here
						}else{
							return null;//can't move this direction because other block blocking way
						}
					}else{
						return null;//can't move because wall blocking block path
					}
				}
			}
			
			//no block found next to bot
			ArrayList ne = new ArrayList();
			ArrayList x = (ArrayList) curr.getBotLocation().clone();
			x.set(1,Integer.toString(botx+dX));
			x.set(2,Integer.toString(boty+dY));
			ne.add(x);
			for (int k = 0; k < curr.getBlockLocation().size(); k++){
				ne.add(curr.getBlockLocation().get(k).clone());

			}
			return new stateNode(ne, curr, action);
		}
		
		return null;
	}
	
	public boolean collide(int x, int y){//if there is a wall return true, else there is no wall and return false
		try{
			if(map.get(y).get(x) != 1 ){

				return false;
			}
		}catch(Exception e){
			return true;
		}
		return true;
	}
	

	
	private class stateNode{
		
		ArrayList<ArrayList<String>> blockLocation;
		ArrayList<String> botLocation;
		String action;
		stateNode parent;
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private stateNode(ArrayList state, stateNode parent, String action){
			this.botLocation = (ArrayList<String>) state.get(0);
			this.blockLocation = new ArrayList<ArrayList<String>>();
			for(int i = 1; i < state.size(); i++){
				this.blockLocation.add((ArrayList<String>)state.get(i));
			}
			this.parent = parent;
			this.action = action;
		}
		
		@SuppressWarnings("rawtypes")
		public ArrayList getBotLocation() {
			return this.botLocation;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public boolean isGoal(ArrayList goals) {
			int p = 0;

			for(int i = 0; i < goals.size(); i++){
				Integer x = ((ArrayList<Integer>) goals.get(i)).get(0);
				Integer y = ((ArrayList<Integer>) goals.get(i)).get(1);

				for(int j = 0; j < this.blockLocation.size(); j++){
					Integer a = Integer.parseInt(((ArrayList<String>) this.blockLocation.get(j)).get(1));
					Integer b = Integer.parseInt(((ArrayList<String>) this.blockLocation.get(j)).get(2));
					
					if(x == a && y == b){
						p++;
					}
				}
			}
			
			if(p == (goals.size())){
				return true;
			}
			return false;
		}
		
		public boolean goalCollide(int x, int y, ArrayList goals) {
			
			for(int i = 0; i < goals.size(); i++){
				Integer xprime = ((ArrayList<Integer>) goals.get(i)).get(0);
				Integer yprime = ((ArrayList<Integer>) goals.get(i)).get(1);
				//int xprime = Integer.parseInt(xprime);
				//int yprime = Integer.parseInt(yprime);
				if (xprime == x && yprime == y) {
					return true;
				}
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
		
		//TODO figure out if this is useful to leave in
		@SuppressWarnings("unused")
		private void setParent(stateNode parent){
			this.parent = parent;
		}
		
	}
	
	public static void main(String [] args){
		try {
			Planner par = new Planner("challenge.txt");
			par.solve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
