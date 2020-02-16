import java.util.ArrayList;
import java.util.Random;

public class Neuron1 
{
	private double beforeSig;  
	private ArrayList<Double> sums; 
	private ArrayList<Double> inputs;
	private ArrayList<Double> weights;
	private Double output;
	private int numberOfInputs = 0;
	private Double bias = 1.0;
	private static Random rand = new Random();
	
	public Neuron1(int numberOfInputs) 
	{
		this.numberOfInputs = numberOfInputs;
		inputs = new ArrayList<>(numberOfInputs);
		weights = new ArrayList<>(numberOfInputs);
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

	// returns sigmoid value
	private double sigmoid(double val) 
	{
		//1/(1+e^-x)
		return 1.0 / (1.0 + Math.exp(-val));
	}
	
	private double dSigmoid(double val)
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
