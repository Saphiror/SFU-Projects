import java.util.*;


public class RipProtocol2{

	public static void main(String args[]){
		//new router objects

		Router r1 = new Router();
		Router r2 = new Router();
		Router r3 = new Router();
		Router r4 = new Router();
		Router r5 = new Router();
		Router r6 = new Router();

		//Create router array
		Router[] routers = new Router[6];
		routers[0] = r1;
		routers[1] = r2;
		routers[2] = r3;
		routers[3] = r4;
		routers[4] = r5;
		routers[5] = r6;
	
		String routerName;
		//create routers, set Neighbours
		for(int i=0; i<6; i++){
			routers[i].createRouter(i+1);
			
			if(routers[i].getNumber()!=6){
				routers[i].setNeighbour(routers[i+1]); //connect routers
			}
			if(routers[i].getNumber()!=1){
				routers[i].setNeighbour(routers[i-1]);
			}
			System.out.println("ROUTING TABLE FOR: R"+routers[i].getNumber());
			System.out.println(routers[i].displayRoutingTable());
		}

		boolean updated1=true;
		boolean updated2=true;
		boolean convergence = false;
		int convergence_counter =0;
		int count=0;

		//Broadcast time
		while(count!=2){
			for(int i=0; i<6; i++){

				//broadcast from router[i] to neighours
				System.out.println(routers[i].getName()+" is sending routing table to neighours.");
				if(routers[i].getNumber()!=6){
					updated1 = routers[(i+1)].updateRoutingTable(routers[i].getRoutingTable(),routers[i].getName());
				}
				if(routers[i].getNumber()!=1){
					updated2 = routers[(i-1)].updateRoutingTable(routers[i].getRoutingTable(),routers[i].getName());
				}

				//check if updated
				if(!updated1 && !updated2){
					convergence_counter++;//need all routers to converge (6 routers)
					if(convergence_counter==6){
						convergence = true;
					}
				}
				if(updated1 && routers[i].getNumber()!=6){
					//convergence_counter=0;//reset counter
					System.out.println("[UPDATE] ROUTING TABLE FOR: R"+routers[i+1].getNumber());
					System.out.println(routers[i+1].displayRoutingTable());
				
				}
				if(updated2 && routers[i].getNumber()!=1){
					//convergence_counter=0;//reset counter
					System.out.println("[UPDATE] ROUTING TABLE FOR: R"+routers[i-1].getNumber());
					System.out.println(routers[i-1].displayRoutingTable());	
				}

			}

			if(count==0){
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("R1 IS DISCONNECTED FROM N1");
				routers[0].modifyRoutingTabe("N1", 16);
				System.out.println("-------------------------------------------------------------------------\n");

			}

			for(int i=5; i>=0;i--){
				System.out.println(routers[i].getName()+" is sending routing table to neighours.");
				if(routers[i].getNumber()!=6){
					updated1 = routers[(i+1)].updateRoutingTable(routers[i].getRoutingTable(),routers[i].getName());
				}
				if(routers[i].getNumber()!=1){
					updated2 = routers[(i-1)].updateRoutingTable(routers[i].getRoutingTable(),routers[i].getName());
				}

				//check if updated
				if(!updated1 && !updated2){
					convergence_counter++;//need all routers to converge (6 routers)
					if(convergence_counter==6){
						convergence = true;
					}
				}
				if(updated1 && routers[i].getNumber()!=6){
					convergence_counter=0;//reset counter
					System.out.println("[UPDATE] ROUTING TABLE FOR: R"+routers[i+1].getNumber());
					System.out.println(routers[i+1].displayRoutingTable());
				
				}
				if(updated2 && routers[i].getNumber()!=1){
					convergence_counter=0;//reset counter
					System.out.println("[UPDATE] ROUTING TABLE FOR: R"+routers[i-1].getNumber());
					System.out.println(routers[i-1].displayRoutingTable());	
				}

			}
			count++;
		}
			
		System.out.println("Routing Tables converge.");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\nFinal Routing Tables of Routers:");

		for(int i=0; i<6; i++){
			System.out.println("ROUTING TABLE FOR: R"+routers[i].getNumber());
			System.out.println(routers[i].displayRoutingTable());
		}
	}
}

class Router{
	String name;
	int routerNum;
	List<String> destinations = new ArrayList<String>();
	List<String> nextHops = new ArrayList<String>();
	List<Integer> hops = new ArrayList<Integer>();

	//int routerNum;
	String connectedNet;
	int routingTableLength;
	List<Router> neighbours = new ArrayList<Router>();
	HashMap<String, Integer> routingTable = new HashMap<String, Integer>();


	public void createRouter(int num){
		routerNum = num;
		name = "R"+Integer.toString(routerNum);
		connectedNet = "N"+Integer.toString(routerNum);
		destinations.add(connectedNet);
		nextHops.add("-");
		hops.add(1);

		routingTableLength = 1;
		routingTable.put(destinations.get(0), hops.get(0));//For passing on 

	}
	public void setNeighbour(Router router){
		neighbours.add(router);
	}
	public HashMap<String, Integer> getRoutingTable(){

		for(int i=0; i<routingTableLength; i++){
			routingTable.put(destinations.get(i),hops.get(i));
		}
		return routingTable;
	}
	public String getName(){
		return name;
	}
	public int getNumber(){
		return routerNum;
	}
	public String displayRoutingTable(){
		//Print routing table info
		String output = "Destination\tNext-Hop Router\tHops\n";
		for(int i=0; i<routingTableLength; i++){
			output += destinations.get(i)+"\t\t"+nextHops.get(i)+"\t\t"+Integer.toString(hops.get(i))+"\n";
		}
		//output = destinations.get(i)+"\t"+nextHops.get(i)+"\t"+Integer.toString(hops.get(i));
		output+="\n";
		return output;
	}
	public void modifyRoutingTabe(String d, int h){
    	routingTable.remove(d);
		int list_index = destinations.indexOf(d);
		//replace
		hops.set(list_index, h);
		//update routing table
		routingTable.put(d,h);
		System.out.println(displayRoutingTable());
	}

	public boolean updateRoutingTable(HashMap<String, Integer> routes, String router_name){
		String rec_router = router_name;
		HashMap<String, Integer> recTable = new HashMap<String, Integer>();

		boolean update = false;

		recTable.putAll(routes);
		//System.out.println(recTable);
		//Update Algorithm
		String[] recDest = new String[recTable.size()]; //Take all destination values put into an array
		recDest = recTable.keySet().toArray(recDest);
		
		//Update if otherRoutingTable has destination Routing table does not
		//System.out.println(recDest.length);
		int recHop=0;//hop from received
		int hop=0;//hop from current
		//System.out.println(recTable);
		
		for(int i=0; i<recDest.length; i++){

			if(recTable.get(recDest[i])==16){
			   	routingTable.remove(recDest[i]);

    			int list_index = destinations.indexOf(recDest[i]);
    			//replace
    			hops.set(list_index, 16);
    			if(nextHops.get(list_index).equals("-")){
    				nextHops.set(list_index, "-");	
    			}
    			else{
    				nextHops.set(list_index, rec_router);
    			}
    			
    			//update routing table
    			routingTable.put(recDest[i], 16);
    			update = true;
			}
			else if(!(routingTable.containsKey(recDest[i]))){//situation 1
				//add to routing table
				recHop=recTable.get(recDest[i]);//get hop from destination
        		//routingTable.remove(recDest[i]);
        		if(recHop==16){
					recHop-=1;
				}
        		routingTable.put(recDest[i],recHop+1);
        		destinations.add(recDest[i]);
        		hops.add(recHop+1);
        		nextHops.add(rec_router);
        		routingTableLength++;
        		update = true;
        		
			}		
			else if(routingTable.containsKey(recDest[i])){
				//Update if otherRoutingTable has shorter hops to same destination
				//check for comparison
				//if match then check key's
					//if rec key < routingTable key remove routingTable entry and put new rec key and value
					//else nada
				recHop=recTable.get(recDest[i]);

				hop = routingTable.get(recDest[i]);
				if(hop==16){
					update = false;
				}
        		else if(recHop<hop && hop<16){//situation 2
        			//remove
        			routingTable.remove(recDest[i]);

        			int list_index = destinations.indexOf(recDest[i]);
        			//replace
        			hops.set(list_index, recHop+1);
        			nextHops.set(list_index, rec_router);
        			
        			//update routing table
        			routingTable.put(recDest[i],recHop+1);
        			update = true;
        		}
        		else if(recHop+1!=hop && recHop-1!=hop){//situation 3
        			//remove
        			routingTable.remove(recDest[i]);

        			int list_index = destinations.indexOf(recDest[i]);

        			//replace
        			hops.set(list_index, recHop+1);
        			nextHops.set(list_index, rec_router);
        			
        			//update routing table
        			routingTable.put(recDest[i],recHop+1);
        			update = true;

        		}
			}
			else{

			}
		}
		return update;
	}
}