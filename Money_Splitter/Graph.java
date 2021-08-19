// import java.text.NumberFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class Graph {
    private HashMap<String, HashMap<String, Double>> adjacencyMap;
    private ArrayList<String> dataMap;  

    public Graph() {
        this.adjacencyMap = new HashMap<>();
        this.dataMap = new ArrayList<>();
    }

    // Adds vertex to graph, if already there, throws exception.
    public void addVertex(String vertexName) {
		if (this.dataMap.contains(vertexName)) {
			throw new IllegalArgumentException("Vertex already in graph");
		}
		this.dataMap.add(vertexName);
	}

    /**
     * Adds directed edge from start to end.
     * If any vertex is not in graph, throws exception.
     * If edge already exists, adds the cost to it.
     * @param startVertexName
     * @param endVertexName
     * @param cost
     */
    public void addDirectedEdge(String startVertexName, String endVertexName, Double cost) {

        this.checkTwoVerticesInGraph(startVertexName, endVertexName);

        // HashMap<String, Integer> adjEdges = this.adjacencyMap.get(startVertexName);

        if (this.adjacencyMap.containsKey(startVertexName)) { // start has any adjacent vertices
            // System.out.println("has edges");
            HashMap<String, Double> adjEdges = this.adjacencyMap.get(startVertexName);
            if (adjEdges.containsKey(endVertexName)) { //edge exists - update cost
                Double prevCost = adjEdges.get(endVertexName);
                Double newCost = prevCost + cost;
                adjEdges.replace(endVertexName, prevCost, newCost);
            } else { // edge does not exist - create it
                adjEdges.put(endVertexName, cost);
            }
            // update adjacencyMap with updated vertices and costs
            this.adjacencyMap.replace(startVertexName, adjEdges);
        } else { // Adds edge with cost to startVertex
            HashMap<String, Double> adjEdges = new HashMap<>();
            adjEdges.put(endVertexName, cost);
            this.adjacencyMap.put(startVertexName, adjEdges);
        }
    }

    

    public void updateGraph() {
        
        for (String start : this.dataMap) {
            if (!this.adjacencyMap.containsKey(start)) {
                continue;
            }
            for (String end : this.adjacencyMap.get(start).keySet()) {
                Double costDiff = getCost(start, end);
                if (costDiff >= 0) {
                    setCost(start, end, costDiff);
                    if (this.adjacencyMap.get(end) != null && this.adjacencyMap.get(end).containsKey(start)) {
                        setCost(end, start, 0.0);
                    }
                }
            }
        }
    }

    public Double getCost(String startVertexName, String endVertexName) {
		this.checkTwoVerticesInGraph(startVertexName, endVertexName);
        Double startDiff = this.adjacencyMap.get(startVertexName).get(endVertexName);
        Double endDiff = 0.0;
        if (this.adjacencyMap.containsKey(startVertexName) && this.adjacencyMap.containsKey(endVertexName)) {
            if (this.adjacencyMap.get(endVertexName).containsKey(startVertexName)) {
                endDiff = this.adjacencyMap.get(endVertexName).get(startVertexName);
            }
        }
		return startDiff - endDiff;
	}

    

    public ArrayList<String> getEdges() {
        return this.dataMap;
    }

    public ArrayList<String> getNamesExcept(String edge) {
        ArrayList<String> updatedArrList = new ArrayList<>();
        for (String s : this.dataMap) {
            if (!s.equals(edge)) {
                updatedArrList.add(s);
            }
        }
        return updatedArrList;
    }

    public ArrayList<String> getNamesExcept(ArrayList<String> edges) {
        ArrayList<String> updatedArrList = new ArrayList<>();
        for (String s : this.dataMap) {
            if (!edges.contains(s)) {
                updatedArrList.add(s);
            }
        }
        return updatedArrList;
    }

    public String toString() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        String outputString = "\n";
        for (String start : this.dataMap) {
            if (!this.adjacencyMap.containsKey(start)) {
                continue;
            }
            for (String end : this.adjacencyMap.get(start).keySet()) {
                Double costBetween = getCost(start, end);
                if (costBetween > 0) {
                    outputString += start + " owes " + end + " " + currencyFormat.format(costBetween) + "\n";
                }
            }
            outputString += "\n";
        }
        return outputString;
    }

    /*
     * Private methods below 
     */
    private void checkOneVertexInGraph(String vertexName) {
		if (!this.dataMap.contains(vertexName)) {
			throw new IllegalArgumentException("Vertex is not in the graph");
		}
	}

	private void checkTwoVerticesInGraph(String startVertexName,
			String endVertexName) {
		if (!(this.dataMap.contains(startVertexName)
				&& this.dataMap.contains(endVertexName))) {
			throw new IllegalArgumentException(
					"One or more vertices are not in the graph");
		}
	}

    private void setCost(String startVertexName, String endVertexName, Double cost) {
        this.checkTwoVerticesInGraph(startVertexName, endVertexName);
        HashMap<String, Double> adjEdges = this.adjacencyMap.get(startVertexName);
        adjEdges.put(endVertexName, cost);
    }
}