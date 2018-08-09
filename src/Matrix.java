/*
 * MIRAFUENTES, Troy Michael
 * SANCHEZ, Mark Gavin
 * VALENZUELA, Jolene
 * ADVDISC S17
 */

import java.util.*;

//
public class Matrix {
    //The usage of an Array/List-like structure to store Matrix data as a list of Vectors. You may also store the Matrix as a 2d array.

    //The usage of immutable Integer variables to hold values for the number of rows/columns.
	List<Vector> vectorList = new ArrayList<>();
	final int ROWS;
	final int COLS;

    /*------------------ A proper implementation of a Matrix function via the usage of the created Vector data structure ------------------*/
   

    //A proper implementation of a default constructor that initializes the matrix as an identity matrix of a given dimension.
     public Matrix(int dimension){
    	double[] arrayHolder = new double[dimension];
    	for(int x = 0; x < dimension; x++)
    	{
	    	for(int i = 0; i < dimension; i++)
	    	{
	    		if(x == i)
	    		arrayHolder[i] = 1;
	    		else
	    			arrayHolder[i] = 0;
	    	}
	    	vectorList.add(new Vector(arrayHolder, arrayHolder.length));
    	}
    	ROWS = vectorList.size();
	    COLS = dimension;
    }
    //A proper implementation of a constructor, converting an already-existing array/list of data from a rudimentary data structure into the vector class.
    	//the dimension variable refers to the length of each vector inside list
    public Matrix (List<Vector> list, int dimension){
    	for(Vector a: list)
    	{
    		vectorList.add(a);
    	}
    	ROWS = vectorList.size();
	    COLS = dimension;
    }


     /*------------------ An implementation of function for matrix multiplication ------------------*/

     //Function header to be used: Matrix times (Matrix other)
     	//Usage example: Assuming a Matrix a and Matrix b exists, a.times(b) should output the matrix multiplication of a and b.
		//Errors for size mismatches when multiplying matrices must also be handled.

    /* -------------------- An implementation of a function that performs Gauss-Jordan Elimination to find the determinant of the matrix. (10 points) ---------*/
    	//The function must incorporate an implementation of Gauss-Jordan Elimination.
		//Usage example: Given a Matrix m, the function call m.det() should return the determinant of the matrix.

    /* -------------------- An implementation of a function that finds the inverse of the matrix ------------------------------- */
    	//The function must incorporate an implementation of Gauss-Jordan Elimination. The function must return a null value if the matrix is not invertible; the matrix does not have an inverse.
    	//Usage example: Given a Matrix m, m.inverse() should return a matrix containing the matrix inverse of m.
    /* ------------------ Add-On Functions --------------- */
    public void printMatrix()
    {
    	for(Vector a: vectorList)
    	{
    		for(int i = 0; i < a.data.length; i++)
    		System.out.print(a.data[i] + " ");
    	System.out.println("");
    	}
    }
	public static void main(String args[])
	{
		Matrix g = new Matrix(3);
		g.printMatrix();
	}

}
