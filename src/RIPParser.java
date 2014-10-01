import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class RIPParser {
	ArrayList list;
	
	// in the map: 1= wall, 0=free
	public RIPParser(String fileName) throws Exception {
		//define variables
		List< List<Integer> > map = new ArrayList< List<Integer> >();
		List goals = new ArrayList();
		List initState = new ArrayList();
		//open file
		List row = new ArrayList();
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
				map.add(row);
			}
			row = new ArrayList();
			//for each item go through switch, add to map
			currentRowNumber += 1;
		}
		System.out.println(map);
		System.out.println(goals);
		System.out.println(initState);
		
		//save as list to return
		ArrayList list = new ArrayList();
		list.add(map);
		list.add(goals);
		list.add(initState.get(1));
		this.list = list;
		//return list;
	}

	public ArrayList getList() {
		return this.list;
	}
	
	public static void main(String [] args){
		try {
			RIPParser par = new RIPParser("src/challenge.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
