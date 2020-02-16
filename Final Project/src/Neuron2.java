import java.util.ArrayList;
import java.util.Random;

public class Neuron2
{
	private double beforeSig;  
	private ArrayList<Double> sums; 
	private ArrayList<Double> inputs;
	private ArrayList<Double> weights;
	private Double output;
	private int numberOfInputs = 0;
	private Double bias = 1.0;
	private static Random rand = new Random();
	
	public Neuron2(int numberOfInputs) 
	{
		this.numberOfInputs = numberOfInputs;
		inputs = new ArrayList<>(numberOfInputs);
		weights = new ArrayList<>(numberOfInputs);
	}
	
	public Neuron2()
	{
		
	}
	
	//initializes random weights and biases
	public void init() 
	{
		for (int i = 0; i < numberOfInputs; i++)
		{
			double newWeight = rand.nextDouble();
			this.weights.add(newWeight);
		}
		//this.weights.add(bias);
	}

	//sets input(s)
	public void setInput(ArrayList<Double> list)
	{
		inputs = list;
	}

	//calculates the sigmoid value
	public void calc() 
	{
		double temp = 0.0;
		if (numberOfInputs > 0)
		{
			if (inputs != null && weights != null) 
			{
				for (int i = 0; i <= numberOfInputs; i++)
				{
					if (i == numberOfInputs)
					{
						// this is where the bias would go if you need it
					} else
					{
						temp += inputs.get(i) * weights.get(i);
					}
				}
			}
		}
		beforeSig = temp; 
		output = sigmoid(temp);
	}

	public static double[][] matrixMultiply(double[][] m1, double[][] m2) {	
		int r1 = m1.length; 
		int c2 = m2[0].length; 
		int c1 = m1[0].length; 
		
		double[][] product = new double[r1][c2]; 
		
		//three for loops to multiply matricies
		for(int i = 0; i < r1; i++)
		{
			for(int j = 0; j < c2; j++)
			{
				for(int k = 0; k < c1; k++)
				{
					product[i][j] += m1[i][k] * m2[k][j]; 
				}
			}
		}
		return product;
	}
	
	public static double[][] matrixSubtract(double[][] m1, double[][] m2)
	{
		double[][] temp = new double[m1.length][m1[0].length]; 
		
		for(int i = 0; i < m1.length; i ++)
		{
			for(int j = 0; j < m1[0].length; j++)
			{
				temp[i][j] = m1[i][j] - m2[i][j]; 
			}
		}
		
		return temp; 
	}
	
	public static double[][] matrixAdd(double[][] m1, double[][] m2)
	{
		double[][] temp = new double[m1.length][m1[0].length]; 
		
		for(int i = 0; i < m1.length; i ++)
		{
			for(int j = 0; j < m1[0].length; j++)
			{
				temp[i][j] = m1[i][j] + m2[i][j]; 
			}
		}
		
		return temp; 
	}
	
	public static double[][] scalar(double[][] m1, double m2) 
	{
        int m = m1.length;
        int n = m1[0].length;
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                m1[i][j] = m1[i][j] * m2;
        
        return m1;
    }
	
	public static double dot(double[] x, double[] y) {
        double sum = 0.0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }
	
	public static double[][] dotProduct(double[][] m1, double[][] m2)
	{
		double[][] sum = new double[m1.length][m2[0].length];  
		m2 = flip(m2); 
		
		for(int i = 0; i < sum.length; i++)
		{
			for(int j = 0; j < sum[0].length; j++)
			{
				sum[i][j] = dot(m1[i], m2[j]);
			}
		}
		
		return sum; 
	}
	
	public static boolean canMultiply(double[][] m1, double[][] m2) {
		//returns false if either of the matricies lengths are zero
		if(m1.length == 0 || m2.length == 0)
			return false; 
		
		//returns true if the column of the first (m1) matches the second (m2)
		if(m1[0].length == m2.length)
			return true;
		
		return false;
	}
	
	public static double[][] flip(double[][] m1)
	{
		double[][] temp = new double[m1[0].length][m1.length];
		
		for(int i = 0; i < m1.length; i++)
		{
			for(int j = 0; j < m1[0].length; j++)
			{
				temp[j][i] = m1[i][j]; 
			}
		}
		
		return temp; 
	}
	
	public static double[][] transpose(double[][] m1) 
	{
        int m = m1.length;
        int n = m1[0].length;
        double[][] m2 = new double[n][m];
        
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
                m2[j][i] = m1[i][j];
        }
        
        return m2;
    }
	
	// returns sigmoid value
	public double sigmoid(double val) 
	{
		//1/(1+e^-x)
		return 1.0 / (1.0 + Math.exp(-val));
	}
	
	public double dSigmoid(double val)
	{
		//e^-x / (1+e^-x)^2
		return Math.exp(-val)/Math.pow(1 + Math.exp(-val), 2); 
	}
	
	public double dSum()
	{	
		return dSigmoid(beforeSig);
	}
	
	public void changeWeightsOut(double deltaOutputSum, ArrayList<Double> list)
	{
		for(int i = 0; i < list.size(); i ++)
			weights.set(i, weights.get(i) + (deltaOutputSum/list.get(i))); 
	}

	public void changeWeightsHidden(double deltaOutputSum, double hiddenOuter)
	{
		double a = deltaOutputSum / (hiddenOuter * beforeSig);
		for(int i = 0; i < weights.size(); i++)
		{
			weights.set(i, weights.get(i) + a); 
		}
	}
	
	public ArrayList<Double> getWeights()
	{
		return weights; 
	}
	
	public ArrayList<Double> getInputs()
	{
		return inputs; 
	}
	
	public ArrayList<Double> getSums()
	{
		return sums; 
	}
	
	public double getBeforeSig()
	{
		return beforeSig;
	}
	
	public void setWeights(ArrayList<Double> list)
	{
		this.weights = list; 
	}
	
	public void changeOutput(double a)
	{
		output = a; 
	}
	
	//gets the output
	public double getOutput()
	{
		return output;
	}
}
