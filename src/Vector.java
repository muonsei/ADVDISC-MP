/*
 * MIRAFUENTES, Troy Michael
 * SANCHEZ, Mark Gavin
 * VALENZUELA, Jolene
 * ADVDISC S17
 */

import java.util.ArrayList;

// A proper implementation of a vector function via the usage of a List-like data structure. (5 points)
public class Vector {
    // Attributes
    double[] data;
    int dimension;

    /*------------------ CONSTRUCTORS ------------------*/

    /*
     * A proper implementation of a default constructor that initializes the vector as a zero vector of a
     * given dimension.
     */
    public Vector(int dimension) {
        data = new double[dimension]; // default value for new double arrays are 0.0
        this.dimension = dimension;
    }

    /*
     * A proper implementation of a constructor, converting an already-existing array/list of data
     * from a rudimentary data structure into the vector class.
     */
    public Vector (double[] array, int dimension) {
        if (array.length == dimension) {
            data = new double[dimension];
            // copies array content
            this.dimension = dimension;
            System.arraycopy(array, 0, data, 0, array.length);
        }
    }

    /*-------------------- FUNCTIONS --------------------*/

    /*
     * Usage example: Assuming a Vector v and int b exists, v.scale(b) should scale the elements of v by
     * b and return the scaled vector v. The elements inside v must be changed and be correctly scaled by b.
     */
    public Vector scale (double scalar) {
        double[] scaledVector = new double[dimension];

        for (int i = 0; i < dimension; i++)
            scaledVector[i] = data[i] * scalar;

        return new Vector(scaledVector, dimension);
    }

    /*
     * Assuming both Vector v and w exist, v.add(w) should return the vector sum between v and w.
     * The elements inside v must be changed and be a correct result of the operation of Vector
     * addition between v and w.
     */
    public Vector add (Vector addend) {
        double[] sumVector = new double[dimension];

        if (this.dimension == addend.dimension) {
            for (int i = 0; i < dimension; i++)
                sumVector[i] = this.data[i] + addend.data[i];
        }

        return new Vector(sumVector, this.dimension);
    }
    public int getGCD(int first, int second)
    {
        if(first != 0 && second == 0)
            return first;
        else if(first == 0 && second != 0)
            return second;
        else if(first == 0 && second == 0)
            return 0;
        else{
            if(first > second)
                return getGCD(first%second, second);
            else
                return getGCD(first, second%first);
        }
    }
    public int getLCM(int first, int second)
    {
        return first*second/getGCD(first,second);
    }

    /*
     * The function must be static-like in nature, and must be callable from the Vector class.
     * See usage example for more details.
     * The function must be a proper implementation of Gauss-Jordan Elimination, which reduces
     * the given list of vectors into unit vectors via row operations.
     * Usage example: Given a list of vectors vecList, an integer dim, and a Vector c,
     * Vector.Gauss_Jordan (vecList, dim, c) should return a Vector containing the solution
     * to the corresponding system of linear equations. Ex. [x y z w] = [2 1 3 5]
     * If an unsolvable configuration is given (e.g. an input with only [2 1] in vectors
     * and [2 3] in constants), the function must return a null pointer instead of the solution
     * Vector to denote no solution.
     */
    public static Vector Gauss_Jordan (ArrayList<Vector> vectors, int dimension, Vector constants) {
        // TODO: Do Gauss-Jordan Elimination here
        // Add constants/result to the vectors
        vectors.add(constants);

        // Traverse through the "rows"
        for(int i=0;i<vectors.size();i++)
        {
            // Sort by putting zeros below
            if(vectors.get(i).data[i] == 0)
            {
                boolean isReplaced = false;
                for(int j=i+1;!isReplaced;j++)
                {
                    // Check if nonzero in ith element
                    if(vectors.get(j).data[i] != 0) {
                        // Swap whole rows
                        isReplaced = true;
                    }
                }
            }

            double[] pivotRowArray = new double[vectors.size()];
            // Make ith element into 1
            double pivotElement = vectors.get(i).data[i];
            for(int j=i;j<vectors.size();j++) {
                if(pivotElement != 0)
                {
                    vectors.get(j).data[i] /= pivotElement;
                    pivotRowArray[j] = vectors.get(j).data[i];//rewrie the array
                }
            }

            // Reduce bottom to row echelon form
            for(int k=i+1;k<dimension;k++) // Go from pivot to last row
            {
                double multiplier = vectors.get(i).data[k];	// Yung magiging 0 na element
                for(int j=i;j<vectors.size();j++) // Left to right excluding 0th columns
                    vectors.get(j).data[k] = (pivotRowArray[j] * multiplier) - vectors.get(j).data[k];
            }


            // Reduce top to row echelon form
            for(int k=i-1;k>=0;k--) // Go from pivot to first row
            {

                double multiplier = vectors.get(i).data[k];	// Yung magiging 0 na element
                for(int j=i;j<vectors.size();j++) // Left to right excluding 0th columns
                    vectors.get(j).data[k] = vectors.get(j).data[k] - (pivotRowArray[j] * multiplier);
            }
        }

        Vector tobeReturned = vectors.remove(vectors.size()  - 1);
        // If no solution exists return null
        boolean  checking = true;
        //loop to check if nxn
        for(int i = 0; i < vectors.size(); i++)
            if(vectors.size() != vectors.get(i).data.length)
                checking = false;
        //checking if there's a zero row
        for(int i = 0; i < vectors.size(); i++)
            for(int x = 0; x < vectors.get(i).data.length; x++)
                if(i == x && vectors.get(i).data[x] != 1)
                    checking = false;

        if(checking == false)
            return null;
        else
            return tobeReturned;
    }

    /*
     * The function must be static-like in nature, and must be callable from the Vector class.
     * See usage example for more details.
     * The function must call the Gauss_Jordan function within it; i.e. the calculation for
     * span must include Gauss-Jordan Elimination.
     * Usage example: Given a list of Vectors vecList, and an integer dim denoting the dimension
     * of a vector inside vecList, Vector.span(vecList, dim) should return an integer variable
     * containing the span of the set of vectors.
     */
    public static int span (ArrayList<Vector> vectors, int dimension) {
        // Perform Gauss-Jordan Elimination here
        Gauss_Jordan(vectors, dimension, new Vector(dimension));
        int ctr, span = 0;

        for (int row = 0; row < dimension; row++) { // iterate through rows
            ctr = 0;
            for (Vector vector : vectors) { // iterate through columns
                if (vector.data[row] != 0) // count all instances of non-zero elements in row
                    ctr++;
            }

            if (ctr > 0) // if counter is 0 (all elements are zero)
                span++;
        }

        return span;
    }

}