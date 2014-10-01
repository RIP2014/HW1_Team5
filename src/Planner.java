import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.List;


public class Planner {
	
	LinkedList<stateNode> forwardQ, backwardQ;
	Hashtable<String, stateNode> fHash, bHash;
	int[] actionSet;//change
	List< List<Integer> > map;
	List goals, initState;
	stateNode root;

	
	public Planner(String filename) throws Exception{
		RIPParser parse = new RIPParser(filename);
		this.map = parse.getMap();
		this.goals = parse.getGoals();
		this.initState = parse.getInit();
		this.root = new stateNode();

	}
	
	public void solve(){
	
	}
	
	
	private class stateNode{
		
		int state;
		stateNode parent;
		
		private stateNode(){
			this.state = 5;
			this.parent = null;
		}
	}
}


