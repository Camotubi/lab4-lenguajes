package main;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import abstractStructures.Expression;
import abstractStructures.Node;
import abstractStructures.TreeBuilder;

public class Main {

	/*
	 * grammar.add(new Expression("S","x"));
		grammar.add(new Expression("S","y"));
		grammar.add(new Expression("S","z"));
		grammar.add(new Expression("S","S+S"));
		grammar.add(new Expression("S","S-S"));
		grammar.add(new Expression("S","S*S"));
		grammar.add(new Expression("S","S/S"));
		grammar.add(new Expression("S","(S)"));
		
	 */
	public static void main(String args[])
	{
		ArrayList<Expression> grammar = new ArrayList<Expression>();/*showExpressionSetting();*/
		 /*
		  	grammar.add(new Expression("S","x"));
		  	grammar.add(new Expression("S","w"));
			grammar.add(new Expression("S","y"));
			grammar.add(new Expression("S","z"));
			grammar.add(new Expression("S","S+S"));
			grammar.add(new Expression("S","S-S"));
			grammar.add(new Expression("S","S*S"));
			grammar.add(new Expression("S","S/S"));
			grammar.add(new Expression("S","(S)"));
			
			grammar.add(new Expression("S","dia"));
		  	grammar.add(new Expression("S","mes"));
			grammar.add(new Expression("S","ano"));
			/*grammar.add(new Expression("S","z"));
			grammar.add(new Expression("S","S+S"));
			grammar.add(new Expression("S","S-S"));
			grammar.add(new Expression("S","S*S"));
			grammar.add(new Expression("S","S/S"));
			grammar.add(new Expression("S","(S)"));
			*/
		ArrayList<Node<String>> tree=TreeBuilder.analyze(grammar, JOptionPane.showInputDialog("Ingrese Cadena a leer"));
		
		
		 
		
		if(tree==null)
		{
			System.out.println("Error");
		}
		else
		{
			System.out.println("Cadena Aceptada");
			JOptionPane.showMessageDialog(null, "Cadena Aceptada\n"+TreeBuilder.orphans(tree).get(0).print("",true));
			
		}
		
	}
	
	public static ArrayList<Expression> showExpressionSetting()
	{
		String op ="";
		ArrayList<Expression> grammar = new ArrayList<Expression>();
		do
		{
			String lhs = JOptionPane.showInputDialog("Ingrese la sintaxis de la mano izquierda");
			String rhs = JOptionPane.showInputDialog("Ingrese la sintaxis de la mano derecha");
			grammar.add(new Expression(lhs,rhs));
			op=JOptionPane.showInputDialog("Desea ingresar otra Expression?(s/n)");
		}while(!op.equals("n"));
		return grammar;
	}


}
