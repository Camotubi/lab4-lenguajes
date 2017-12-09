package abstractStructures;

import java.util.List;

public class Node<T> {
	 private T data;
     private Node<T> parent;
     private List<Node<T>> children;
     
     public Node(T data, List<Node<T>> children)
     {
    	 this.setData(data);
    	 this.children = children;
     }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}
	
	public String print(String prefix, boolean isTail) {
        String out = (prefix + (isTail ? "└── " : "├── ") + data);
        for (int i = 0; i < children.size() - 1; i++) {
            out +="\n"+children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0) {
           out+="\n"+ children.get(children.size() - 1).print(prefix + (isTail ?"    " : "│   "), true);
        }
        return out;
    }
}
