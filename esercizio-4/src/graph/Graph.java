package graph;

public class Graph<T,E> {

	private HashMap<T,Node<T>> nodes;
	private HashMap<Link<T>,E> links;
	private boolean oriented;

	public Graph(boolean oriented) {
		nodes  = new HashMap<T,Node<T>>();
		links = new HashMap<Link<T>,E>;
	}

	public void addNode(T x) {
		nodes.put(x,new Node<>(x));
	}

	public void addLink(T x, T y, E w) {
		Link<T> temp = new Link(nodes.get(x),nodes.get(y));
		links.put(temp,w);

	}

	public boolean isOriented() {
		return oriented;
	}

	public boolean containsNode(T x) {
		return nodes.contains(x);
	}

	public boolean containsLink(T x, T y) {
		return link.contains(new Link<T>(x,y));
	}

	public void deleteNode(T x) {
		nodes.remove(x);
	}

	public void deleteLink(T x, T y) {
		link.remove(new Link<T>(x,y));
	}

	public int size() {
		return nodes.size();
	}

	public int links() {
		return links.size();
	}

	public 







}