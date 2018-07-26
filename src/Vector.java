/*
 * MIRAFUENTES, Troy Michael
 * SANCHEZ, Mark Gavin
 * VALENZUELA, Jolene
 * ADVDISC S17
 */

import java.util.List;

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
    }

     /*
      * A proper implementation of a constructor, converting an already-existing array/list of data
      * from a rudimentary data structure into the vector class.
      */
    public Vector (double[] array, int dimension) {
        if (array.length == dimension) {
            data = new double[dimension];
            for (int i = 0; i < array.length; i++)
                data[i] = array[i]; // copies array content
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
    public Vector Gauss_Jordan (List<Vector> vectors, int dimension, Vector constants) {
        // TODO: Do Gauss-Jordan Elimination here

        // If no solution exists return null
        return null;
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
    public int span (List<Vector> vectors, int dimension) {
        // TODO: Do Gauss-Jordan Elimination

        // What will be the parameter for Vector constants when Gauss-Jordan Elimination is called?
        // Zero ba lahat?

        /*
         * How to get the Span according to StackOverflow
         *
         * For example, given this set of vectors: {[1,3,3],[0,0,1],[1,3,1]}
         * The span will be:
         * a[1, 3, 3] + b[0, 0, 1] + c[1, 3, 1], which would be [a + c, 3a + 3c, 3a + b + c],
         * where a, b, and c are arbitrary constants.
         */

        return 0; // TODO: Change this into actual span
    }
}