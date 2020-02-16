import java.util.ArrayList;
import java.util.Random;

public class Neuron {
	private ArrayList<Double> inputs;
	private ArrayList<Double> weights;
	private Double output;
	private int numberOfInputs = 0;
	private Double bias = 1.0;
	private static Random rand = new Random();

	public Neuron(int numberOfInputs) {
		this.numberOfInputs = numberOfInputs;
		inputs = new ArrayList<>(numberOfInputs);
		weights = new ArrayList<>(numberOfInputs);
	}
	
	//initializes random weights and biases
	public void init() {
		for (int i = 0; i <= numberOfInputs; i++) {
			double newWeight = rand.nextDouble();
			this.weights.add(newWeight);
		}
		this.weights.add(bias);
	}

	//sets input(s)
	public void setInput(ArrayList<Double> list) {
		inputs = list;
	}

	//calculates the sigmoid value
	public void calc() {
		double temp = 0.0;
		if (numberOfInputs > 0) {
			if (inputs != null && weights != null) {
				for (int i = 0; i <= numberOfInputs; i++) {
					if (i == numberOfInputs) {
						// this is where the bias would go if you need it
					} else {
						temp += inputs.get(i) * weights.get(i);
					}
				}
			}
		}
		output = sigmoid(temp);
	}

	// returns sigmoid value
	private double sigmoid(double val) {
		return 1.0 / (1.0 + Math.exp(-val));
	}

	//gets the output
	public double getOutput() {
		return output;
	}
}
