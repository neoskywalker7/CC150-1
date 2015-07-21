/*
4.7 B
*/
// SOL 2 DFS time O(P + D)
public class Question4_7_B {
	
	public static Stack<Project> findBuildOrder(String[] projects, String[][] dependencies) {
		Graph graph = buildGraph(projects, dependencies);
		return orderProjects(graph.getNodes());
	}
	
	public static Stack<Project> orderProjects(ArrayList<Project> projects) {
		Stack<Project> stack = new Stack<Project>();
		for (Project project : projects) {
			if (project.getState() == Project.State.BLANK) {
				if (!doDFS(project, stack)) {
					return null;
				}
			}
		}
		return stack;
	}
	
	public static boolean doDFS(Project project, Stack<Project> stack) {
		if (project.getState() == Project.State.PARTIAL) {
			return false;
		}
		
		if (project.getState() == Project.State.BLANK) {
			project.setState(Project.State.PARTIAL);
			ArrayList<Project> children = project.getChildren();
			for (Project child : children) {
				if (!doDFS(child, stack)) {
					return false;
				}
			}
			project.setState(Project.State.COMPLETE);
			stack.push(project);
		}
		return true;
	}
	
	public static Graph buildGraph(String[] projects, String[][] dependencies) {
		Graph graph = new Graph();
		for (String project : projects) {
			graph.getOrCreateNode(project);
		}
		
		for (String[] dependency : dependencies) {
			String first = dependency[0];
			String second = dependency[1];
			graph.addEdge(first, second);
		}
		
		return graph;
	}
	
	public static class Graph {
		private ArrayList<Project> nodes = new ArrayList<Project>();
		private HashMap<String, Project> map = new HashMap<String, Project>();
		
		public Project getOrCreateNode(String name) {
			if (!map.containsKey(name)) {
				Project node = new Project(name);
				nodes.add(node);
				map.put(name, node);
			}
			
			return map.get(name);
		}
		
		public void addEdge(String startName, String endName) {
			Project start = getOrCreateNode(startName);
			Project end = getOrCreateNode(endName);
			start.addNeighbor(end);
		}
		
		public ArrayList<Project> getNodes() { return nodes; }
	}
	
	public static class Project {
		public enum State {COMPLETE, PARTIAL, BLANK};
		private State state = State.BLANK;
		public State getState() {
			return state;
		}
		public void setState(State st) {
			state = st;
		}
		
		private ArrayList<Project> children = new ArrayList<Project>();
		private HashMap<String, Project> map = new HashMap<String, Project>();
		private String name;
		private int dependencies = 0;
		
		public Project(String n) {
			name = n;
		}
		
		public void addNeighbor(Project node) {
			if (!map.containsKey(node.getName())) {
				children.add(node);
				node.incrementDependencies();
			}
		}
		
		public void incrementDependencies() {
			dependencies++;
		}
		public void decrementDependencies() {
			dependencies--;
		}
		
		public String getName() {
			return name;
		}
		public ArrayList<Project> getChildren() {
			return children;
		}
		public int getNumberDependencies() {
			return dependencies;
		}
	}
}

