import java.util.*; 

public class Main1 
{
	static ArrayList<ArrayList<Double>> allIns = new ArrayList<>(15);
	static int[][] inputs = {
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
		ArrayList<Double> weightsHiddenOut = new ArrayList<>(); 
		ArrayList<Double> weightsInHidden = new ArrayList<>(); 
		ArrayList<Double> newWeightsHiddenOut = new ArrayList<>(); 
		ArrayList<Double> newWeightsInHidden = new ArrayList<>(); 
		ArrayList<Double> hiddenLayerResults = new ArrayList<>(); 
		ArrayList<Double> outputLayerResults = new ArrayList<>(); 
		final double[] outs = {0.00, 0.07, 0.14, 0.21, 0.28, 0.35, 0.42, 0.49, 
				0.56, 0.63, 0.70, 0.77, 0.84, 0.91, 1.00};
		
		populateIns(); 
		random = rand.nextInt(14); 
		
		ArrayList<Double> in = new ArrayList<>(); 
		//in.add((double) 255); 
		//in.add((double) 0);
		//in.add((double) 0); 
		in = allIns.get(random); 
		System.out.println(random);
		System.out.println(allIns.get(random));
		System.out.println(outs[random]);
		
		ArrayList<Neuron1> neurons = new ArrayList<>(); 
		for(int i = 0; i < 3; i++)
		{
			neurons.add(new Neuron1(in.size())); 
			neurons.get(i).setInput(in); 
			neurons.get(i).init();
			neurons.get(i).calc();
			weightsInHidden.addAll(neurons.get(i).getWeights());
			hiddenLayerResults.add(neurons.get(i).getOutput()); 
		}
		
		ArrayList in1 = new ArrayList<>(); 
		for(int i = 0; i < neurons.size(); i ++)
		{
			in1.add(neurons.get(i).getOutput()); 
		}
		
		Neuron1 out = new Neuron1(in1.size()); 
		out.setInput(in1);
		out.init(); 
		out.calc(); 
		weightsHiddenOut.addAll(out.getWeights()); 
		System.out.println("Guess: " + (out.getOutput())); 
		
		double outputError = outs[random] - out.getOutput(); 
		double deltaOutputSum = out.dSum() * outputError; 
		
		out.changeWeightsOut(deltaOutputSum, hiddenLayerResults);
		for(int i = 0; i < neurons.size(); i++)
		{
			neurons.get(i).changeWeightsHidden(deltaOutputSum, weightsHiddenOut.get(i)); 
		}
		
		
		do {
			weightsInHidden.clear(); 
			hiddenLayerResults.clear(); 
			//random = rand.nextInt(14); 
			in = allIns.get(random); 
			
			for(int i = 0; i < neurons.size(); i++)
			{
				neurons.get(i).setInput(in);
				neurons.get(i).calc(); 
				weightsInHidden.addAll(neurons.get(i).getWeights());
				hiddenLayerResults.add(neurons.get(i).getOutput()); 
			}
			
			in1.clear(); 
			for(int i = 0; i < neurons.size(); i ++)
			{
				in1.add(neurons.get(i).getOutput());
			}
			
			out.setInput(in1);
			out.calc(); 
			weightsHiddenOut.addAll(out.getWeights()); 
			System.out.println("Guess: " + (out.getOutput()));
			
			outputError = outs[random] - out.getOutput(); 
			deltaOutputSum = out.dSum() * outputError; 
			
			out.changeWeightsOut(deltaOutputSum,  hiddenLayerResults);
			for(int i = 0; i < neurons.size(); i++)
			{
				neurons.get(i).changeWeightsHidden(deltaOutputSum, weightsHiddenOut.get(i)); 
			}
			
			count++; 
		} while(count < 100); 
		
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
