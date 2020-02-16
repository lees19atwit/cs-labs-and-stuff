/**
 * uhh real quick go through everything again cause it multiplies and stuff but everything's NaN
 */
import java.util.ArrayList;
import java.util.Random; 

public class Main2
{
	static ArrayList<ArrayList<Double>> allIns = new ArrayList<>(15);
	static double[][] inputs = {
			{255, 0, 0}, 
			{255, 128, 0}, 
			{255, 255, 0}, 
			{128, 255, 0}, 
			{0, 255, 0}, 
			{0, 255, 128}, 
			{0, 255, 255}, 
			{0, 128, 255}, 
			{0, 0, 255}, 
			{128, 0, 255}, 
			{255, 0, 255}, 
			{255, 0, 128}, 
			{255, 255, 255}, 
			{128, 128, 128}, 
			{0, 0, 0}
	};
	
	public static void main(String[] args) throws InterruptedException
	{
		Random rand = new Random(); 
		int count = 0; 
		int random = 0; 
		int neurons = 3; 
		double learningRate = .1; 
		double[][] oldWeightsIn = new double[3][neurons]; 
		double[][] oldWeightsOut = new double[neurons][1]; 
		
		final double[][] outs = {
				{0.00}, 
				{0.07}, 
				{0.14}, 
				{0.21}, 
				{0.28}, 
				{0.35}, 
				{0.42}, 
				{0.49}, 
				{0.56},
				{0.63},
				{0.70},
				{0.77},
				{0.84},
				{0.91},
				{1.00}
			};
		
		//initializes 3x3 matrix
		//three is because we have three inputs 
		//and three because there are three neurons
		double[][] weightsIn = new double[3][neurons]; 
		
		//random weights in - hidden 
		for(int i = 0; i < weightsIn.length; i ++)
		{
			for(int j = 0; j < weightsIn[0].length; j++)
			{
				weightsIn[i][j] = rand.nextDouble(); 
			}
		}
		
		//store random weights
		oldWeightsIn = weightsIn; 
		
		//make a neuron so we can multiply matrices
		Neuron2 n = new Neuron2(); 
		System.out.println(n.canMultiply(inputs, weightsIn)); 
		
		//multiply the inputs with the random weights and then store them
		double[][] layerResult = n.matrixMultiply(inputs,  weightsIn); 
		double[][] layerResultSig = layerResult; 
		for(int i = 0; i < layerResultSig.length; i ++)
		{
			for(int j = 0; j < layerResultSig[0].length; j++)
			{
				layerResultSig[i][j] = n.sigmoid(layerResultSig[i][j]); 
			}
		}
		
		//randomize weightsOut, four is the amount of hidden neurons we have 
		double[][] weightsOut = new double[neurons][1]; 
		for(int i = 0; i < weightsOut.length; i ++)
		{
			for(int j = 0; j < weightsOut[0].length; j++)
			{
				weightsOut[i][j] = rand.nextDouble(); 
			}
		}
		
		oldWeightsOut = weightsOut; 
		
		//calcualte the outputs, keep the sum before sigmoid, and one after
		double[][] outputsSum = n.matrixMultiply(layerResult, weightsOut);
		double[][] outputsSig = outputsSum; 
		for(int i = 0; i < outputsSig.length; i++)
		{
			for(int j = 0; j < outputsSig[0].length; j++)
			{
				outputsSig[i][j] = n.sigmoid(outputsSig[i][j]); 
			}
		}
		
		//print outputs 
		/*
		for(int i = 0; i < outputsSig.length; i++)
		{
			for(int j = 0; j < outputsSig[0].length; j++)
			{
				System.out.println(outputsSig[i][j]);
			}
		}
		*/
		
		//end of forward propagation
		
		double outputError[][] = n.matrixSubtract(outs, outputsSig); 
		
		//dSig the layerResults
		double[][] dSum = new double[layerResult.length][layerResult[0].length]; 
		for(int i = 0; i < layerResult.length; i++)
		{
			for(int j = 0; j < layerResult[0].length; j++)
			{
				dSum[i][j] = n.dSigmoid(layerResult[i][j]); 
			}
		}
		
		//double[][] deltaSum = new double[dSum.length][outputError[0].length]; 
		double[][] deltaOutputLayer = n.dotProduct(dSum, outputError); 
		double[][] hiddenOutputChanges = n.scalar(n.matrixMultiply(dSum, n.transpose(layerResult)), learningRate);
		double[][] deltaHiddenLayer = n.dotProduct(n.matrixMultiply(n.transpose(layerResult), deltaOutputLayer), dSum);
		double[][] inputHiddenChanges = n.scalar(n.matrixMultiply(deltaHiddenLayer, n.transpose(inputs)), learningRate); 
		
		weightsIn = n.matrixAdd(weightsIn, inputHiddenChanges); 
		weightsOut = n.matrixAdd(weightsOut, hiddenOutputChanges);
		
		/*
		//tests
		double[][] a = { {1, 2, 3}, {4, 5, 6} } ; 
		double[][] b = { {7,8}, {9, 10}, {11, 12} }; 
		double[][] c = n.dotProduct(a, b); 
		c = n.scalar(a, 2); 
		
		for(int i = 0; i < c.length; i++)
		{
			for(int j = 0; j < c[0].length; j++)
			{
				System.out.println(c[i][j]);
			}
		}
		*/
		
		for(int i = 0; i < outputsSig.length; i ++)
		{
			for(int j = 0; j < outputsSig[0].length; j++)
			{
				System.out.println(outputsSig[i][j]);
			}
		}
		
		do {
			oldWeightsIn = weightsIn; 
			
			layerResult = n.matrixMultiply(inputs,  weightsIn); 
			layerResultSig = layerResult; 
			for(int i = 0; i < weightsIn.length; i ++)
			{
				for(int j = 0; j < weightsIn[0].length; j++)
				{
					layerResultSig[i][j] = n.sigmoid(layerResultSig[i][j]); 
				}
			}
			
			oldWeightsOut = weightsOut; 
			
			//calcualte the outputs, keep the sum before sigmoid, and one after
			outputsSum = n.matrixMultiply(layerResult, weightsOut);
			outputsSig = outputsSum; 
			for(int i = 0; i < outputsSig.length; i++)
			{
				for(int j = 0; j < outputsSig[0].length; j++)
				{
					outputsSig[i][j] = n.sigmoid(outputsSig[i][j]); 
				}
			}
			
			//end of forward propagation
			
			outputError = n.matrixSubtract(outs, outputsSig); 
			
			//dSig the layerResults
			for(int i = 0; i < layerResult.length; i++)
			{
				for(int j = 0; j < layerResult[0].length; j++)
				{
					dSum[i][j] = n.dSigmoid(layerResult[i][j]); 
				}
			}
			
			//double[][] deltaSum = new double[dSum.length][outputError[0].length]; 
			deltaOutputLayer = n.dotProduct(dSum, outputError); 
			hiddenOutputChanges = n.scalar(n.matrixMultiply(dSum, n.transpose(layerResult)), learningRate);
			deltaHiddenLayer = n.dotProduct(n.matrixMultiply(n.transpose(layerResult), deltaOutputLayer), dSum);
			inputHiddenChanges = n.scalar(n.matrixMultiply(deltaHiddenLayer, n.transpose(inputs)), learningRate); 
			
			weightsIn = n.matrixAdd(weightsIn, inputHiddenChanges); 
			weightsOut = n.matrixAdd(weightsOut, hiddenOutputChanges);
			
			count++; 
			
		}while(count < 10); 
		
		for(int i = 0; i < outputsSig.length; i ++)
		{
			for(int j = 0; j < outputsSig[0].length; j++)
			{
				System.out.println(outputsSig[i][j]);
			}
		}
		System.out.println();
	}
	
	public static void populateIns() {
		 //red			(255, 000, 000) - 0.00
		ArrayList<Double> red = new ArrayList<>();
		red.add((double) 255); 
		red.add((double) 0);
		red.add((double) 0); 
		allIns.add(red);
		
		 //Orange		(255, 128, 000) - 0.07
		ArrayList<Double> orange = new ArrayList<>();
		orange.add((double) 255); 
		orange.add((double) 128);
		orange.add((double) 0); 
		allIns.add(orange);
		
		 //* Yellow		(255, 255, 000) - 0.14
		ArrayList<Double> yellow = new ArrayList<>();
		yellow.add((double) 255); 
		yellow.add((double) 255);
		yellow.add((double) 0); 
		allIns.add(yellow);
		
		 // Lime		(128, 255, 000) - 0.21
		ArrayList<Double> lime = new ArrayList<>();
		lime.add((double) 128); 
		lime.add((double) 255);
		lime.add((double) 0); 
		allIns.add(lime);
		
		 // Green		(000, 255, 000) - 0.28
		ArrayList<Double> green = new ArrayList<>();
		green.add((double) 0); 
		green.add((double) 255);
		green.add((double) 0); 
		allIns.add(green);
		
		 // Light Green	(000, 255, 128) - 0.35
		ArrayList<Double> liGreen = new ArrayList<>();
		liGreen.add((double) 0); 
		liGreen.add((double) 255);
		liGreen.add((double) 128); 
		allIns.add(liGreen);
		
		 // Cyan		(000, 255, 255) - 0.42
		ArrayList<Double> cyan = new ArrayList<>();
		cyan.add((double) 0); 
		cyan.add((double) 255);
		cyan.add((double) 255); 
		allIns.add(cyan);
		
		 // Light Blue	(000, 128, 255) - 0.49
		ArrayList<Double> liBlue = new ArrayList<>();
		liBlue.add((double) 0); 
		liBlue.add((double) 128);
		liBlue.add((double) 255); 
		allIns.add(liBlue);
		
		 // Blue		(000, 000, 255) - 0.56
		ArrayList<Double> blue = new ArrayList<>();
		blue.add((double) 0); 
		blue.add((double) 0);
		blue.add((double) 255); 
		allIns.add(red);
		
		 // Purple		(128, 000, 255) - 0.63
		ArrayList<Double> purple = new ArrayList<>();
		purple.add((double) 128); 
		purple.add((double) 0);
		purple.add((double) 255); 
		allIns.add(purple);
		
		 // Megenta		(255, 000, 255) - 0.70
		ArrayList<Double> magenta = new ArrayList<>();
		magenta.add((double) 255); 
		magenta.add((double) 0);
		magenta.add((double) 255); 
		allIns.add(magenta);
		
		 // Pink		(255, 000, 128) - 0.77
		ArrayList<Double> pink = new ArrayList<>();
		pink.add((double) 255); 
		pink.add((double) 0);
		pink.add((double) 128); 
		allIns.add(pink);
		
		 // White		(255, 255, 255) - 0.84
		ArrayList<Double> white = new ArrayList<>();
		white.add((double) 255); 
		white.add((double) 255);
		white.add((double) 255); 
		allIns.add(white);
		
		 // Grey		(128, 128, 128) - 0.91
		ArrayList<Double> grey = new ArrayList<>();
		grey.add((double) 128); 
		grey.add((double) 128);
		grey.add((double) 128); 
		allIns.add(grey);
		
		 // Black		(000, 000, 000) - 1.00
		ArrayList<Double> black = new ArrayList<>();
		black.add((double) 0); 
		black.add((double) 0);
		black.add((double) 0); 
		allIns.add(black);
		
	}
}
