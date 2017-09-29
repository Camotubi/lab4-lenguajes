package abstractStructures;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilder {
	
	public static ArrayList<Node<String>> analyze(ArrayList<Expression> grammar, String input)
	{
		ArrayList<Node<String>> tree = stringToNodes(input);
		String lastRepresentation = new String();
		
		do
		{
			ArrayList<Node<String>> orphans = orphans(tree);
			String currentRepresentation = nodesToString(orphans);
			if(lastRepresentation.equals(currentRepresentation))
					return null;
			/*System.out.println(currentRepresentation);*/
			for(Expression expr : grammar)
			{
				for(int i = 0; i <= orphans.size() - expr.rightSyntax.length(); i++)
				{
					List<Node<String>> orphansSubList = orphans.subList(i, expr.rightSyntax.length() + i);
					if(compareStringNodes(orphansSubList, expr.rightSyntax))
					{
						Node<String> parent = new Node<String>(expr.leftSyntax, new ArrayList<Node<String>>());
						for(Node<String> node: orphansSubList)
						{
							node.setParent(parent);
							parent.getChildren().add(node);
							
						}
						
						tree.add(tree.indexOf(orphansSubList.get(orphansSubList.size()-1))+1,parent);
					}
				}
			}
			lastRepresentation=currentRepresentation;
		}while(orphans(tree).size()>1);
		
		return tree;
	}
	
	public static ArrayList<Node<String>> stringToNodes(String input)
	{
		
		ArrayList<Node<String>> nodes = new ArrayList<Node<String>>();
		for(int i = 0; i < input.length() ; i++)
		{
			nodes.add(new Node<String>(input.charAt(i)+"", new ArrayList<Node<String>>()));
		}
		return nodes;
	}
	
	public static ArrayList<Node<String>> orphans(ArrayList<Node<String>> tree)
	{
		ArrayList<Node<String>> orphans = new ArrayList<Node<String>>();
		for(Node<String> node : tree)
		{
			if(node.getParent()==null)
			{
				orphans.add(node);
			}
		}
		return orphans;
	}
	public static String nodesToString(List<Node<String>> nodes)
	{
		String output = new String();
		for(Node<String> node : nodes)
		{
			output = output + node.getData();
		}
		return output;
			
	}
	public static boolean compareStringNodes(List<Node<String>> orphansSubList,String str ) 
	{
		if(orphansSubList.size()!=str.length())
		{
			return false;
		}
		else
		{
			for(int i = 0; i < str.length(); i++)
			{
				if(!(str.charAt(i)+"").equals(orphansSubList.get(i).getData()))
				{
					return false;
				}
			}
			return true;
		}
	}
	
	public static void main(String args[])
	{
		ArrayList<Expression> grammar = new ArrayList<Expression>();
		grammar.add(new Expression("S","x"));
		grammar.add(new Expression("S","y"));
		grammar.add(new Expression("S","z"));
		grammar.add(new Expression("S","S+S"));
		grammar.add(new Expression("S","S-S"));
		grammar.add(new Expression("S","S*S"));
		grammar.add(new Expression("S","S/S"));
		grammar.add(new Expression("S","(S)"));
		ArrayList<Node<String>> tree=TreeBuilder.analyze(grammar, "((x+y)ghgf/z)");
		if(tree==null)
		{
			System.out.println("Error");
		}
		else
		{
			orphans(tree).get(0).print("",true);
		}
		
	}

}
