/*  CSI2114 Lab 3 - lab3.java
 *
 *  Class to check balanced brackets in math expressions
 *
 *  Usage: java bracketsBalance <exp>
 *
 *  by Jeff Souza
 *
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

class bracketsBalance {

	private boolean bBalance( String exp ) {

		ArrayStack stack = new ArrayStack();//Cree le stack pour les parenthese
        int cc=0;//Compretur de characteres '(' et ')'
        
        for (int i = 0; i < exp.length(); i++) {//Loop le string a la recherche de parentheses
            char x = exp.charAt(i);//variable x deviens le char a la possition i

            if (x=='('||x=='['||x=='{') {//Si paranthese d'ouverture, ajouter au stack et incrementer cc
                stack.push(x);
                cc++;
                continue;
            }

            if ((x==')'||x==']'||x=='}')&&stack.isEmpty()){//Si parenhese de fermeture et que le stack est vide False
                return false; //Ex: si phrase )Bonjour(, declare automatiquement comme non balancé
            }else if ((x==')'&&stack.top()!="(")||(x=='}'&&stack.top()!="{")||(x==']'&&stack.top()!="[")){
                return false;
            }else if (x==')'||x==']'||x=='}'){//Si ')' et que le stack est pas vide, enleve une '(' du stack 
                cc++;
                stack.pop();
            }
        }

        if (cc==0){//Si cc est 0, il y a de parentheses
            System.out.println("Sans parentheses");
            return false;//Pas de parenthese ∴ rien a balancer, so false
        }else{
            return stack.isEmpty();//Si apres avoir itteré la liste et que cc est pas 0, alors toutes les parenthese furent traités
        }// Banlancé ∴ true

	} /* bBalance */


	public static void main( String [] args ) {

        Scanner scn= new Scanner(System.in);//Scanner pour gareder l'entree de l'ussager
        System.out.println("Rentrez votre phrase a verifier: ");
        String line=scn.nextLine();//Phrase a verifier par l'ussager

		bracketsBalance b = new bracketsBalance();
		boolean result = b.bBalance (line);

		if( result ) System.out.println ( "The expression is balanced." );
		else System.out.println ( "The expression is NOT balanced." );
	} /* main */


}
