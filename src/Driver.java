import java.util.Scanner;
import java.util.ArrayList;

public class Driver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter number of vectors: ");
		int numVects = sc.nextInt();

		System.out.print("Enter number of dimensions: ");
		int numDims = sc.nextInt();

		ArrayList<Vector> testGJE = new ArrayList<Vector>();
		for(int a=0;a<numVects;a++)
		{
			double[] dataArr = new double[numDims];
			System.out.print("Enter Vector #" + (a+1) + ": ");
			for(int b=0;b<numDims;b++)
			{
				dataArr[b] = sc.nextDouble();
			}
			testGJE.add(new Vector(dataArr, numDims));
		}

		double[] constArr = new double[numDims];
		System.out.print("Enter constants: ");
		for(int b=0;b<numDims;b++)
			constArr[b] = sc.nextDouble();

		Vector result = Vector.Gauss_Jordan(testGJE, numDims, new Vector(constArr, 3));
    	for(int i=0;i<result.data.length;i++)
    		System.out.println("Vector " + (i + 1) + " " + result.data[i]);
    	System.out.println("dimension " + result.dimension);

		/* Test Case
    	ArrayList<Vector> testGJE = new ArrayList<Vector>();
    	testGJE.add(new Vector(new double[]{2, 1}, 1));// 1 2 4 - 3
    	//testGJE.add(new Vector(new double[]{1, 3, 0}, 3));
    	//testGJE.add(new Vector(new double[]{1, 5, 5}, 3));
    	//System.out.println("Span: " + span(testGJE, testGJE.size()));
    	Vector result = Gauss_Jordan(testGJE, 3, new Vector(new double[]{2, 3}, 1));// 5 8 2- 3
    	if(result != null)
    	{
    	for(int i=0;i<result.data.length;i++)
    		System.out.println("Vector " + (i + 1) + " " + result.data[i]);
    	System.out.println("dimension " + result.dimension);
    	//System.out.println("Span2: " + span(testGJE, testGJE.size()));
    	}
    	else
    		System.out.println("null");*/
	}
}