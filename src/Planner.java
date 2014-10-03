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

	public stateNode transition(stateNode curr, String action){
		
		ArrayList bot = curr.getBotLocation();
		int botx = Integer.parseInt((String)bot.get(1));
		int boty = Integer.parseInt((String)bot.get(2));
		
		if(action == "LEFT"){
			if(!collide(botx-1, boty)){//an empty space update here
				for(int i = 0; i < curr.getBlockLocation().size(); i++){//check to see if collide with a block
					if(Integer.parseInt(curr.getBlockLocation().get(i).get(1)) == botx-1){//see if a bot x intersects with a block
						if(Integer.parseInt(curr.getBlockLocation().get(i).get(2)) == boty){//see if a bot y intersects with a block update here
							int blockCollied = i;
							if(!collide(botx-2, boty)){//update here
								boolean nothBlock = true;
								for(int j = 0; j < curr.getBlockLocation().size(); j++){//check to see if collide with a block
									if((Integer.parseInt(curr.getBlockLocation().get(j).get(1)) == (botx-2) && Integer.parseInt(curr.getBlockLocation().get(j).get(2)) == (boty))){//see if there is a block at the pushed spot
										nothBlock = false;
									}
								}
								if(nothBlock){
									
									ArrayList ne = new ArrayList();
									ArrayList x = (ArrayList) curr.getBotLocation().clone();
									x.set(1,Integer.toString(botx-1));//update here
									ne.add(x);
									for (int k = 0; k < curr.getBlockLocation().size(); k++){
										ArrayList temp = (ArrayList) curr.getBlockLocation().get(k).clone();

										if(k == blockCollied){
											 temp.set(1,Integer.toString(botx-2));//update here
										}
										ne.add(temp);

									}
									return new stateNode(ne, curr, "LEFT");//update here
								}else{
									return null;//can't move this direction because other block blocking way
								}
							}else{
								return null;//can't move because wall blocking block path
							}
						}
					}
				}
				
				//no block found next to bot
				ArrayList ne = new ArrayList();
				ArrayList x = (ArrayList) curr.getBotLocation().clone();
				x.set(1,Integer.toString(botx-1));
				ne.add(x);
				for (int k = 0; k < curr.getBlockLocation().size(); k++){
					ne.add(curr.getBlockLocation().get(k).clone());

				}
				return new stateNode(ne, curr, "LEFT");
			}
		}else if(action == "RIGHT"){
			if(!collide(botx+1, boty)){//an empty space update here
				for(int i = 0; i < curr.getBlockLocation().size(); i++){//check to see if collide with a block
					if(Integer.parseInt(curr.getBlockLocation().get(i).get(1)) == botx+1){//see if a bot x intersects with a block
						if(Integer.parseInt(curr.getBlockLocation().get(i).get(2)) == boty){//see if a bot y intersects with a block update here
							int blockCollied = i;
							if(!collide(botx+2, boty)){//update here
								boolean nothBlock = true;
								for(int j = 0; j < curr.getBlockLocation().size(); j++){//check to see if collide with a block
									if((Integer.parseInt(curr.getBlockLocation().get(j).get(1)) == (botx+2) && Integer.parseInt(curr.getBlockLocation().get(j).get(2)) == (boty))){//see if there is a block at the pushed spot
										nothBlock = false;
									}
								}
								if(nothBlock){
									
									ArrayList ne = new ArrayList();
									ArrayList x = (ArrayList) curr.getBotLocation().clone();
									x.set(1,Integer.toString(botx+1));//update here
									ne.add(x);
									for (int k = 0; k < curr.getBlockLocation().size(); k++){
										ArrayList temp = (ArrayList) curr.getBlockLocation().get(k).clone();

										if(k == blockCollied){
											 temp.set(1,Integer.toString(botx+2));//update here
										}
										ne.add(temp);

									}
									return new stateNode(ne, curr, "RIGHT");//update here
								}else{
									return null;//can't move this direction because other block blocking way
								}
							}else{
								return null;//can't move because wall blocking block path
							}
						}
					}
				}
				
				//no block found next to bot
				ArrayList ne = new ArrayList();
				ArrayList x = (ArrayList) curr.getBotLocation().clone();
				x.set(1,Integer.toString(botx+1));
				ne.add(x);
				for (int k = 0; k < curr.getBlockLocation().size(); k++){
					ne.add(curr.getBlockLocation().get(k).clone());

				}
				return new stateNode(ne, curr, "RIGHT");
			}
		}else if(action == "UP"){
			if(!collide(botx, boty-1)){//an empty space update here
				for(int i = 0; i < curr.getBlockLocation().size(); i++){//check to see if collide with a block
					if(Integer.parseInt(curr.getBlockLocation().get(i).get(1)) == botx){//see if a bot x intersects with a block
						if(Integer.parseInt(curr.getBlockLocation().get(i).get(2)) == boty-1){//see if a bot y intersects with a block update here
							int blockCollied = i;
							if(!collide(botx, boty-2)){//update here
								boolean nothBlock = true;
								for(int j = 0; j < curr.getBlockLocation().size(); j++){//check to see if collide with a block
									if((Integer.parseInt(curr.getBlockLocation().get(j).get(1)) == botx && Integer.parseInt(curr.getBlockLocation().get(j).get(2)) == (boty-2))){//see if there is a block at the pushed spot
										nothBlock = false;
									}
								}
								if(nothBlock){
									
									ArrayList ne = new ArrayList();
									ArrayList x = (ArrayList) curr.getBotLocation().clone();
									x.set(2,Integer.toString(boty-1));//update here
									ne.add(x);
									for (int k = 0; k < curr.getBlockLocation().size(); k++){
										ArrayList temp = (ArrayList) curr.getBlockLocation().get(k).clone();

										if(k == blockCollied){
											 temp.set(2,Integer.toString(boty-2));//update here
										}
										ne.add(temp);

									}
									return new stateNode(ne, curr, "UP");//update here
								}else{
									return null;//can't move this direction because other block blocking way
								}
							}else{
								return null;//can't move because wall blocking block path
							}
						}
					}
				}
				
				//no block found next to bot
				ArrayList ne = new ArrayList();
				ArrayList x = (ArrayList) curr.getBotLocation().clone();
				x.set(2,Integer.toString(boty-1));
				ne.add(x);
				for (int k = 0; k < curr.getBlockLocation().size(); k++){
					ne.add(curr.getBlockLocation().get(k).clone());

				}
				return new stateNode(ne, curr, "UP");
			}
		}else if(action == "DOWN"){
			if(!collide(botx, boty+1)){//an empty space update here
				for(int i = 0; i < curr.getBlockLocation().size(); i++){//check to see if collide with a block
					if(Integer.parseInt(curr.getBlockLocation().get(i).get(1)) == botx){//see if a bot x intersects with a block
						if(Integer.parseInt(curr.getBlockLocation().get(i).get(2)) == boty+1){//see if a bot y intersects with a block update here
							int blockCollied = i;
							if(!collide(botx, boty+2)){//update here
								boolean nothBlock = true;
								for(int j = 0; j < curr.getBlockLocation().size(); j++){//check to see if collide with a block
									if((Integer.parseInt(curr.getBlockLocation().get(j).get(1)) == botx && Integer.parseInt(curr.getBlockLocation().get(j).get(2)) == (boty+2))){//see if there is a block at the pushed spot
										nothBlock = false;
									}
								}
								if(nothBlock){
									
									ArrayList ne = new ArrayList();
									ArrayList x = (ArrayList) curr.getBotLocation().clone();
									x.set(2,Integer.toString(boty+1));//update here
									ne.add(x);
									for (int k = 0; k < curr.getBlockLocation().size(); k++){
										ArrayList temp = (ArrayList) curr.getBlockLocation().get(k).clone();

										if(k == blockCollied){
											 temp.set(2,Integer.toString(boty+2));//update here
										}
										ne.add(temp);

									}
									return new stateNode(ne, curr, "DOWN");//update here
								}else{
									return null;//can't move this direction because other block blocking way
								}
							}else{
								return null;//can't move because wall blocking block path
							}
						}
					}
				}
				
				//no block found next to bot
				ArrayList ne = new ArrayList();
				ArrayList x = (ArrayList) curr.getBotLocation().clone();
				x.set(2,Integer.toString(boty+1));
				ne.add(x);
				for (int k = 0; k < curr.getBlockLocation().size(); k++){
					ne.add(curr.getBlockLocation().get(k).clone());

				}
				return new stateNode(ne, curr, "DOWN");
			}
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
		
		private stateNode(ArrayList state, stateNode parent, String action){
			this.botLocation = (ArrayList<String>) state.get(0);
			this.blockLocation = new ArrayList<ArrayList<String>>();
			for(int i = 1; i < state.size(); i++){
				this.blockLocation.add((ArrayList<String>)state.get(i));
			}
			this.parent = parent;
			this.action = action;
		}
		
		public ArrayList getBotLocation() {
			return this.botLocation;
		}

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


