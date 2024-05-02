class tryStack1 {//Initialisation de la classe

	public static void main( String [] args ) {//Creation du main

		Integer [] arr = new Integer [ 50 ];//Creation d'un integer array de 50 plases (de 0 a 49)

		for( int i = 0; i < 50; i++ ) {// Loop for de 0 a 50
			arr [ i ] = new Integer(i*2);//Crée une nouvelle valeur integer resultante i*2
		}

		printA ( arr );//Appelle la fonction prinA et imprime arr a commenser par arr[0]
		arr = reverse ( arr );//Appele la valeur reverse de sorte a reverser l'ordre de arr
		printA ( arr );//Ligne 11 ""
	} /* main */


	public static Integer [] reverse( Integer [] a ) {//Methode reverse Integer[]→Integer[]
		ArrayStack S = new ArrayStack ( a.length ); //Genere un objet de la classe ArrayStack de la taille de a[]

		Integer [] b = new Integer [ a.length ]; //Crée un nouveu Integer Array de la taille de a[]

		for( int i = 0; i < a.length; i++ ) {//for loop de 0-la longuer de a
			S.push ( a [ i ] );//ajoute toute valeur de a[] au ArrayStack S
		}

		for( int i = 0; i < a.length; i++ ) {//Ligne 22 ""
			b [ i ] = (Integer) (S.pop() );//Ajoute les elements de ArrayStack S a b 
		}//Puisque ArrayStack fonctione par FILO b[] a maintenenat les valeurs de a[] a l'invers

		return b;//retourne b
	} /* reverse */


	public static void printA( Integer [] a ) {//Methode printA Integer[]→void
		System.out.println();//Genere un enter

		for( int i = 0; i < 50; i++ ) {//Ligne 7 ""
			System.out.print ( a [ i ].intValue() + "\t" );//Imprime la valeur a la position i de a + un space tab
		}

		System.out.println();//Genere un enter
	} /* printA */


}

//La methode reverse se sert d'un ArrayStack pour renvenser le contenu d'un array. En se servant d'un 
//stack il est simple de renverser l'ordre puisque son ordre d'entrée est le meme que celui de sortie
//Alors lors que les valeurs sortent elles rentrent a l'envers dans le nouveau Array.

//Par contre, en etant un Array, ce stack a comme taille maximmale la taille indiqueé donc on me pourait
//ajouter plus de d'elements, cella menerais a une erreur de FullStackException de meme que le vider et
//et le traiter menerais a un EmptyStackException
