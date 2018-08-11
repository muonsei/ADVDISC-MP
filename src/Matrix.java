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
	ArrayList<Vector> vectorList = new ArrayList<>();
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
    	//baliktad ba?
    	ROWS = dimension;
	    COLS = vectorList.size();
    }
    //A proper implementation of a constructor, converting an already-existing array/list of data from a rudimentary data structure into the vector class.
    	//the dimension variable refers to the length of each vector inside list
    public Matrix (ArrayList<Vector> list, int dimension){
    	for(Vector a: list)
    	{
    		vectorList.add(a);
    	}
    	//baliktad ba?
    	ROWS = dimension;
	    COLS = vectorList.size();
    }


     /*------------------ An implementation of function for matrix multiplication ------------------*/

     //Function header to be used: Matrix times (Matrix other)
     	//Usage example: Assuming a Matrix a and Matrix b exists, a.times(b) should output the matrix multiplication of a and b.
		//Errors for size mismatches when multiplying matrices must also be handled.
     public Matrix times(Matrix other)
     {
     		ArrayList<Vector> insideList = new ArrayList<>();
            System.out.println("This.Cols = " + this.COLS + " Other.Rows = " + other.ROWS);

            // Check if valid for multiplication
            if (this.COLS == other.ROWS)
            {
                // Initialize product matrix
                int newRows = this.ROWS;
                int newCols = other.COLS;
                int area = newRows * newCols;

                // Put into list of vectors
                for(int i=0;i<newCols;i++)
                    insideList.add(new Vector(newRows));

                /*** Start of Multiplication ***/
                // Pababa of multiplicand
                for(int i=0;i<this.ROWS;i++)
                {   
                    // Left to Right of multiplier
                    for(int j=0;j<other.COLS;j++)
                    {
                        double tempProduct = 0;
                        // Left to Right of multiplicand
                        for(int k=0;k<this.COLS;k++)
                        {
                            tempProduct += this.vectorList.get(k).data[i] *
                                            other.vectorList.get(j).data[k];
                        }
                        insideList.get(j).data[i] = tempProduct;
                    }
                }
            }

            return new Matrix(insideList, insideList.size());
     }
     	
    /* -------------------- An implementation of a function that performs Gauss-Jordan Elimination to find the determinant of the matrix. (10 points) ---------*/
    	//The function must incorporate an implementation of Gauss-Jordan Elimination.
		//Usage example: Given a Matrix m, the function call m.det() should return the determinant of the matrix.
    public double det()
    {
        double determinant = 1;

        // Perform Gauss Jordan
        for(int i=0;i<this.vectorList.size();i++)
        {
            if(this.vectorList.get(i).data[i] == 0)
            {
                boolean isReplaced = false;
                for(int j=i+1;!isReplaced && j<this.vectorList.size();j++)
                {
                    if(this.vectorList.get(j).data[i] != 0)
                        isReplaced = true;
                }
            }

            double[] pivotRowArray = new double[this.vectorList.size()];
            double pivotElement = this.vectorList.get(i).data[i];
            System.out.println("Pivot = " + pivotElement + " CurrDet = " + determinant);
            for(int j=i;j<this.vectorList.size();j++) {
                if(pivotElement != 0)
                {
                    this.vectorList.get(j).data[i] /= pivotElement;
                    pivotRowArray[j] = this.vectorList.get(j).data[i];
                }
            }
            // Pivot element lang gagalawin for determinant
            if(pivotElement != 0)
                determinant /= pivotElement;

            // Reduce bottom to row echelon form
            for(int k=i+1;k<this.vectorList.get(0).data.length;k++) // Go from pivot to last row
            {
                double multiplier = this.vectorList.get(i).data[k]; // Yung magiging 0 na element
                for(int j=i;j<this.vectorList.size();j++) // Left to right excluding 0th columns
                    this.vectorList.get(j).data[k] = (pivotRowArray[j] * multiplier) - this.vectorList.get(j).data[k];
            }


            // Reduce top to row echelon form
            for(int k=i-1;k>=0;k--) // Go from pivot to first row
            {

                double multiplier = this.vectorList.get(i).data[k]; // Yung magiging 0 na element
                for(int j=i;j<this.vectorList.size();j++) // Left to right excluding 0th columns
                    this.vectorList.get(j).data[k] = this.vectorList.get(j).data[k] - (pivotRowArray[j] * multiplier);
            }

        }

        return (1 / determinant) * -1;
    }


    /* -------------------- An implementation of a function that finds the inverse of the matrix ------------------------------- */
    	//The function must incorporate an implementation of Gauss-Jordan Elimination. The function must return a null value if the matrix is not invertible; the matrix does not have an inverse.
    	//Usage example: Given a Matrix m, m.inverse() should return a matrix containing the matrix inverse of m.
    public Matrix inverse() {
        if (det() == 0) // if determinant is 0
            return null; // determinant does not exist

        // clone the matrix
        Matrix copy = this.clone();

        // build an identity matrix with same dimensions as current matrix, to be used as "augmented matrix"
        Matrix identity = new Matrix(ROWS);

        // TODO: perform GJE on matrix copy while performing the same operations on the identity matrix
    }


    /* ------------------ Add-On Functions --------------- */
    // Prints the matrix
    public void printMatrix()
    {
    	for(Vector a: vectorList)
    	{
    		for(int i = 0; i < a.data.length; i++)
    		System.out.print(a.data[i] + " ");
    	System.out.println("");
    	}
    }

    // Checks if the matrix is an identity matrix
    public boolean isIdentity() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < vectorList.size(); j++) {
                if (i == j && vectorList.get(j).data[i] != 1) // if within diagonal and not 1
                    return false;
                else if (vectorList.get(j).data[i] != 0) // if not within diagonal and not 0
                    return false;
            }
        }

        return true;
    }

    // Clones a matrix
    public Matrix clone() {
        int dimension = ROWS; // THIS IS SO CONFUSING

        ArrayList<Vector> cloneList = new ArrayList<>();
        for (Vector a : vectorList) {
            double[] newArray = new double[dimension];
            System.arraycopy(a.data, 0, newArray, 0, a.data.length);
            cloneList.add(new Vector(newArray, dimension));
        }

        return new Matrix(cloneList, dimension);
    }

    /*
	public static void main(String args[])
	{
		Matrix g = new Matrix(3);
		Matrix z = new Matrix(3);
		//g.printMatrix();
		(g.times(z)).printMatrix();
		//g.checkVertical(1);
	}
    */
}
