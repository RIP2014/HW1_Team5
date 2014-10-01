import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class RIPParser {
	ArrayList list;
	ArrayList<ArrayList<Integer>> map;
	ArrayList<ArrayList<String>> goals, initState; 
	
	// in the map: 1= wall, 0=free
	public RIPParser(String fileName) throws Exception {
		//define variables
		this.map = new ArrayList<ArrayList<Integer>>();
		this.goals = new ArrayList<ArrayList<String>>();
		this.initState = new ArrayList<ArrayList<String>>();
		//open file
		ArrayList<Integer> row = new ArrayList<Integer>();
		ArrayList newGoal;
		
		FileReader input = new FileReader(fileName);
		BufferedReader buffRead = new BufferedReader(input);
		String currLine = null;
		//for each row
		int currentRowNumber = 0;
		while ((currLine = buffRead.readLine()) != null) {
			if (currLine.charAt(0) == '$') {
					String[] data = currLine.split(",");
					//get robot data
					ArrayList bot = new ArrayList();
					String[] botData = data[1].split(" ");
					bot.add(botData[0]);
					bot.add(botData[1]);
					bot.add(botData[2]);
					initState.add(bot);
					//get each block data
					for (int j=2; j< data.length; j++) {
						ArrayList block = new ArrayList();
						String[] splitData = data[j].split(" ");
						block.add(splitData[0]);
						block.add(splitData[1]);
						block.add(splitData[2]);
						initState.add(block);
					}
				} else {
				for (int i=0; i<currLine.length(); i++) {
				
					switch(currLine.charAt(i)) {
						case 'O': row.add(0);
						      break;
						case 'X': row.add(1);
							  break;
						case 'G': row.add(0);
							  newGoal = new ArrayList();
							  newGoal.add(i);
							  newGoal.add(currentRowNumber);
							  newGoal.add('G');
							  goals.add(newGoal);
							  break;
						case '1': row.add(0);
							  newGoal = new ArrayList();
							  newGoal.add(i);
							  newGoal.add(currentRowNumber);
							  newGoal.add('1');
							  goals.add(newGoal);
							  break;
						case '2': row.add(0);
							  newGoal = new ArrayList();
							  newGoal.add(i);
							  newGoal.add(currentRowNumber);
							  newGoal.add('2');
							  goals.add(newGoal);
							  break;
						case '3': row.add(0);
							  newGoal = new ArrayList();
							  newGoal.add(i);
							  newGoal.add(currentRowNumber);
							  newGoal.add('3');
							  goals.add(newGoal);
							  break;
					}
				}
				}
			if (row.size() != 0) {
				map.add((ArrayList<Integer>) row);
			}
			row = new ArrayList();
			//for each item go through switch, add to map
			currentRowNumber += 1;
		}
		//System.out.println(map);
		//System.out.println(goals);
		//System.out.println(this.initState.get(1).get(2));
		
	
	}

	
	public ArrayList<ArrayList<Integer>> getMap(){
		return this.map;
	}
	
	public ArrayList<ArrayList<String>> getGoals(){
		return this.goals;
	}
	
	public ArrayList<ArrayList<String>> getInit(){
		return this.initState;
	}
	
}
