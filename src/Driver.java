import java.util.Scanner;
import java.util.ArrayList;

public class Driver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the number of your choice: ");
		System.out.println("1 - Test Scaling");
		System.out.println("2 - Test Addition");
		System.out.println("3 - Test Gauss-Jordan Elimination and Span");

		int choice = sc.nextInt();

		switch(choice) {
			case 1: testScaling(); break;
			case 2: testAddition(); break;
			case 3: testGaussJordanSpan(); break;
			case 4: testSpan(); break;
		}
	}
	public static void testScaling() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter number of dimensions: ");
		int numDims = sc.nextInt();
		Vector v = new Vector(numDims);

		System.out.print("Enter vector: ");
		for(int i=0; i<numDims; i++) {
			v.data[i] = sc.nextDouble();
		}

		System.out.print("Enter scalar: ");
		double scalar = sc.nextDouble();

		Vector ans = v.scale(scalar);
		for(int i=0; i<numDims; i++) {
			System.out.println(ans.data[i]);
		}
	}

	public static void testAddition() {
		Scanner sc = new Scanner(System.in);

		System.out.println("First Vector:");
		System.out.print("Enter number of dimensions: ");
		int numDims1 = sc.nextInt();
		Vector v1 = new Vector(numDims1);
		System.out.print("Enter vector: ");
		for(int i=0; i<numDims1; i++) {
			v1.data[i] = sc.nextDouble();
		}

		System.out.println("Second Vector:");
		System.out.print("Enter number of dimensions: ");
		int numDims2 = sc.nextInt();
		Vector v2 = new Vector(numDims2);
		System.out.print("Enter vector: ");
		for(int i=0; i<numDims1; i++) {
			v2.data[i] = sc.nextDouble();
		}

		System.out.println("Result vector: ");
		Vector result = v1.add(v2);
		for (int i = 0; i < numDims1; i++) {
			System.out.println(result.data[i]);
		}
	}

	public static void testGaussJordanSpan() {
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
		if(result != null)
		{
			for(int i=0;i<result.data.length;i++)
			{
				if(result.data[i] == -0.0)
					System.out.println("Vector " + (i + 1) + " " + Math.abs(result.data[i]));
				else
					System.out.println("Vector " + (i + 1) + " " + result.data[i]);
			}
			System.out.println("dimension " + result.dimension);
		}
		else
			System.out.println("Answer is null");

		System.out.println("Span: " + Vector.span(testGJE, numDims));
	}

	public static void testSpan() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter number of vectors: ");
		int numVects = sc.nextInt();

		System.out.print("Enter number of dimensions: ");
		int numDims = sc.nextInt();

		ArrayList<Vector> spantest = new ArrayList<Vector>();
		for(int a=0;a<numVects;a++)
		{
			double[] dataArr = new double[numDims];
			System.out.print("Enter Vector #" + (a+1) + ": ");
			for(int b=0;b<numDims;b++)
			{
				dataArr[b] = sc.nextDouble();
			}
			spantest.add(new Vector(dataArr, numDims));
		}

		System.out.println("Span: " + Vector.span(spantest, numDims));
	}
}