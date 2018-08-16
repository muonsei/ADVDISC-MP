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
	ArrayList<Vector> vectorList = new ArrayList<Vector>();
	int ROWS;
	int COLS;

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
            int newRows, newCols;

            // Check if valid for multiplication
            if (this.COLS == other.ROWS)
            {
                // Initialize product matrix
                newRows = this.ROWS;
                newCols = other.COLS;
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
                return new Matrix(insideList, newRows);
            }
        return null;
     }
     	
    /* -------------------- An implementation of a function that performs Gauss-Jordan Elimination to find the determinant of the matrix. (10 points) ---------*/
    	//The function must incorporate an implementation of Gauss-Jordan Elimination.
		//Usage example: Given a Matrix m, the function call m.det() should return the determinant of the matrix.
    public Double det()
    {
        if (this.ROWS != this.COLS)
            return null;

        double determinant = 1;

        // Perform Gauss Jordan
        for(int i=0;i<this.vectorList.size();i++)
        {
            if(this.vectorList.get(i).data[i] == 0)
            {
                boolean isReplaced = false;
                for(int j=i+1;!isReplaced && j<this.ROWS;j++)
                {
                    if(this.vectorList.get(i).data[j] != 0) {
                        for(int k=0;k<this.vectorList.size();k++)
                        {
                            double tempSwap = this.vectorList.get(k).data[i];
                            this.vectorList.get(k).data[i] = this.vectorList.get(k).data[j];
                            this.vectorList.get(k).data[j] = tempSwap;
                        }
                        isReplaced = true;
                    }
                }
            }

            double[] pivotRowArray = new double[this.vectorList.size()];
            double pivotElement = this.vectorList.get(i).data[i];
            //System.out.println("Pivot = " + pivotElement + " CurrDet = " + determinant);
            for(int j=i;j<this.vectorList.size();j++) {
                if(pivotElement != 0)
                {
                    this.vectorList.get(j).data[i] /= pivotElement;
                    pivotRowArray[j] = this.vectorList.get(j).data[i];
                }
            }
            // Pivot element lang gagalawin for determinant
            determinant /= pivotElement;

            // Reduce bottom to row echelon form
            for(int k=i+1;k<this.vectorList.get(0).data.length;k++) // Go from pivot to last row
            {
                double multiplier = this.vectorList.get(i).data[k]; // Yung magiging 0 na element
                if (multiplier != 0.0) {
                    for(int j=i;j<this.vectorList.size();j++) // Left to right excluding 0th columns 
                        this.vectorList.get(j).data[k] = (pivotRowArray[j] * multiplier) - this.vectorList.get(j).data[k];
                }
            }


            // Reduce top to row echelon form
            for(int k=i-1;k>=0;k--) // Go from pivot to first row
            {
                double multiplier = this.vectorList.get(i).data[k]; // Yung magiging 0 na element
                if (multiplier != 0.0) {
                    for(int j=i;j<this.vectorList.size();j++) // Left to right excluding 0th columns
                        this.vectorList.get(j).data[k] = this.vectorList.get(j).data[k] - (pivotRowArray[j] * multiplier);
                }
            }

            //printMatrix();
        }

        return new Double((1 / determinant) * -1);
    }


    /* -------------------- An implementation of a function that finds the inverse of the matrix ------------------------------- */
    	//The function must incorporate an implementation of Gauss-Jordan Elimination. The function must return a null value if the matrix is not invertible; the matrix does not have an inverse.
    	//Usage example: Given a Matrix m, m.inverse() should return a matrix containing the matrix inverse of m.
    public Matrix inverse() {
        // clone the matrix
        Matrix copy = this.clone();

        if (det() == null) // if determinant is null
            return null; // determinant does not exist

        // build an identity matrix with same dimensions as current matrix, to be used as "augmented matrix"
        Matrix identity = new Matrix(ROWS);

        // augment identity matrix to clone
        for (Vector v : identity.vectorList) {
            copy.vectorList.add(v);
        }
        copy.COLS = copy.vectorList.size();

        // TODO FOR GAVIN: perform GJE on copy
        for(int i=0;i<copy.ROWS;i++)
        {
            if(copy.vectorList.get(i).data[i] == 0)
            {
                boolean isReplaced = false;
                for(int j=i+1;!isReplaced && j<copy.ROWS;j++)
                {
                    if(copy.vectorList.get(j).data[i] != 0){
                        for(int k=0;k<copy.vectorList.size();k++)
                        {
                            double tempSwap = copy.vectorList.get(k).data[i];
                            copy.vectorList.get(k).data[i] = copy.vectorList.get(k).data[j];
                            copy.vectorList.get(k).data[j] = tempSwap;
                        }
                        isReplaced = true;
                    }
                }
            }

            double[] pivotRowArray = new double[copy.vectorList.size()];
            double pivotElement = copy.vectorList.get(i).data[i];
            for(int j=i;j<copy.vectorList.size();j++) {
                if(pivotElement != 0)
                {
                    copy.vectorList.get(j).data[i] /= pivotElement;
                    pivotRowArray[j] = copy.vectorList.get(j).data[i];
                }
            }

            // Reduce bottom to row echelon form
            for(int k=i+1;k<copy.vectorList.get(0).data.length;k++) // Go from pivot to last row
            {
                double multiplier = copy.vectorList.get(i).data[k]; // Yung magiging 0 na element
                if (multiplier != 0.0) {
                    for(int j=i;j<copy.vectorList.size();j++) // Left to right excluding 0th columns
                        copy.vectorList.get(j).data[k] = (pivotRowArray[j] * multiplier) - copy.vectorList.get(j).data[k];
                }
            }


            // Reduce top to row echelon form
            for(int k=i-1;k>=0;k--) // Go from pivot to first row
            {
                double multiplier = copy.vectorList.get(i).data[k]; // Yung magiging 0 na element
                if (multiplier != 0.0) {
                    for(int j=i;j<copy.vectorList.size();j++) // Left to right excluding 0th columns
                        copy.vectorList.get(j).data[k] = copy.vectorList.get(j).data[k] - (pivotRowArray[j] * multiplier);
                }
            }

        }
        // get the latter half of the matrix, which was originally an identity matrix
        copy.vectorList = new ArrayList<Vector>(copy.vectorList.subList(copy.vectorList.size()/2, copy.vectorList.size()));
        copy.COLS = copy.vectorList.size();

        // return copy
        return copy;
    }

    /* -------------------- An implementation of a function that transposes the matrix ------------------------------- */
    public Matrix transpose() {
        // Create new Matrix
        int newRows = this.COLS;
        int newCols = this.ROWS;
        ArrayList<Vector> newVectorList = new ArrayList<Vector>();
        for(int i=0;i<newCols;i++)
            newVectorList.add(new Vector(new double[newRows], newRows));
        Matrix transposedMatrix = new Matrix(newVectorList, newRows);

        for(int i=0;i<newCols;i++)
        {
            for(int j=0;j<newRows;j++)
            {
                transposedMatrix.vectorList.get(i).data[j] = this.vectorList.get(j).data[i];
            }
        }

        return transposedMatrix;
    }

    /* ------------------ Add-On Functions --------------- */
    // Prints the matrix
    public void printMatrix()
    {
        for(int i=0;i<this.ROWS;i++)
        {
            System.out.print("[ ");
            for(int j=0;j<this.COLS;j++)
            {
                System.out.print(this.vectorList.get(j).data[i] + " ");
            }
            System.out.println("]");
        }
        System.out.println();
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
        ArrayList<Vector> cloneList = new ArrayList<>();
        for (Vector a : this.vectorList) {
            double[] newArray = new double[ROWS];
            System.arraycopy(a.data, 0, newArray, 0, a.data.length);
            cloneList.add(new Vector(a.data, ROWS));
        }

        return new Matrix(cloneList, ROWS);
    }
}
