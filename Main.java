import java.util.Arrays;
import java.util.Random;

public class Main {

	// Define the maximum temperature and cooling rate for the annealing process
	private static final double MAX_TEMPERATURE = 1000;
	private static final double COOLING_RATE = 0.03;
	private static int[] values = {68, 64, 47, 55, 72, 53, 81, 60, 72, 80, 62, 42, 48, 47, 68, 51, 48, 68, 83, 55, 48, 44, 49, 68, 63, 71, 82, 55, 60, 63, 56, 75, 42, 76, 42, 60, 75, 68, 67, 42, 71, 58, 66, 72, 67, 78, 49, 50, 51};
	private static int[] weights = {21, 11, 11, 10, 14, 12, 12, 14, 17, 13, 11, 13, 17, 14, 16, 10, 18, 10, 16, 17, 19, 12, 12, 16, 16, 13, 17, 12, 16, 13, 21, 11, 11, 10, 14, 12, 12, 14, 17, 13, 11, 13, 17, 14, 16, 10, 18, 10, 16};

	private static int knapsackCapacity = 300;

	// Define the solution state variables
	private static boolean[] currentSolution;
	private static boolean[] bestSolution;
	private static int currentValue; //currentOFV
	private static int bestValue;

	private static double[] currentTemp;
	private static double[] nextTemp;

	private static int iterationSize =10;

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		// Initialize the solution state variables
		currentSolution = new boolean[values.length];
		bestSolution = new boolean[values.length];
		currentValue = 0;
		bestValue = 0;
		boolean[] initialSolution = {false, true, false, true, false, true, true, false, true, true, true, false, false, true, false, false, true, false, false, true, true, true, true, false, true, true, false, true, false, false, false, false, false, true, false, true, false, false, false, true, false, false, true, false, true, false, false, false, false};

		// Initialize the random number generator
		Random random = new Random();

		// Start the simulated annealing process
		int iteration = 0;
		
		int[] currentOFV = new int[iterationSize];
		boolean[][] solution = new boolean[iterationSize][49];
		int[] solutionOFV = new int[iterationSize];
		String[] isBetterSolution = new String[iterationSize];
		double[] metroPolisNum = new double[iterationSize];
		double[] randomNum = new double[iterationSize];
		String[] acceptOrReject = new String[iterationSize];
		int[] newOFV = new int[iterationSize];

		int outOfBag;
		int inToBag;

		int outOfBagIndex;
		int inToBagIndex;

		int countTrue  = 0;
		int countFalse = 0;

		int currentWeight;

		currentSolution = initialSolution;
		
		findCurrentAndNextTemp();
		
		while(iteration<iterationSize) {
			
			outOfBag =  random.nextInt(22)+1;
			inToBag = random.nextInt(28)+1;

			outOfBagIndex = 0;
			inToBagIndex = 0;

			countTrue=0;
			countFalse=0;

			currentWeight=0;

			currentValue = calculateValue(currentSolution);
			currentOFV[iteration] = currentValue;
			
			System.out.print("Out of bag : " + outOfBag + " In to bag : " + inToBag + " : ");
			for(int i = 0; i< currentSolution.length;i++) {
				
				if(currentSolution[i]) {
	
					countTrue++;
					currentWeight+=weights[i];
					
					if(countTrue==outOfBag) {
						outOfBagIndex = i;
						currentWeight -= weights[i];
					}
				}
				else {
					
					countFalse++;
					
					if(countFalse == inToBag) {
						inToBagIndex = i;
						currentWeight += weights[i];
					}
				}
			}
					
			if(currentWeight<=knapsackCapacity) {

				currentSolution[outOfBagIndex] = false;
				currentSolution[inToBagIndex] = true;
				
				solutionOFV[iteration] = calculateValue(currentSolution);
				
				System.out.println(" currentOFV = " + currentOFV[iteration] + ", solutionOFV =  " + solutionOFV[iteration] + ", weight= " + calculateWeight(currentSolution) + " Next Temp = " + nextTemp[iteration]);
				System.out.println("iteration "+ (iteration+1) +" : ");
				for(int i = 0; i< currentSolution.length;i++) {
					
					if(currentSolution[i]) {
						solution[iteration][i] = true;
					}else {
						solution[iteration][i] = false;
					}
					System.out.print(solution[iteration][i] + " ");
				}
				System.out.println();

				if(solutionOFV[iteration] >= currentOFV[iteration]) {
					isBetterSolution[iteration] = "YES";
					metroPolisNum[iteration] = calculateAcceptanceProbability(currentOFV[iteration],solutionOFV[iteration],currentTemp[iteration]);
					acceptOrReject[iteration] = "Accept";
					newOFV[iteration] = solutionOFV[iteration];
					
				}else {
					isBetterSolution[iteration] = "NO";
					metroPolisNum[iteration] = calculateAcceptanceProbability(currentOFV[iteration],solutionOFV[iteration],currentTemp[iteration]);
					randomNum[iteration] = random.nextDouble();
					
					if(metroPolisNum[iteration]>randomNum[iteration]) {
						acceptOrReject[iteration] = "Accept";
						newOFV[iteration]= solutionOFV[iteration];
					}else {
						acceptOrReject[iteration] = "Reject";
						newOFV[iteration]= currentOFV[iteration];
					}
				}
				
				if(newOFV[iteration]> bestValue) {
					bestValue = newOFV[iteration];
				}
			}
		
			if( currentWeight>knapsackCapacity && iteration != 0) {
			//	System.out.println("out of capacity" + currentWeight);
				iteration--;
				

			}
			iteration++;
		}
		
		iteration--;
		System.out.println(iteration);
		
		while(iteration>=0) {
			if((newOFV[iteration]==bestValue) && (acceptOrReject[iteration].equals("Accept"))) {
				for(int j=0;j<bestSolution.length; j++) {
					bestSolution[j]= solution[iteration][j];
				}
				break;
			}
			iteration--;
		}


		//finding current and next temperature
		 findCurrentAndNextTemp();

		// Print the best solution found
		 System.out.println("Best Solution: " + Arrays.toString(bestSolution));
		 System.out.println("Best Value: " + bestValue);
		 
		 long endTime = System.currentTimeMillis();
	     long executionTime = endTime - startTime;

	     System.out.println("Execution time: " + executionTime + " milliseconds");
	}

	// Helper method to calculate the fitness value of a solution
	
	private static int calculateValue(boolean[] solution) {
		int value = 0;
		int weight = 0;
		for (int i = 0; i < solution.length; i++) {
			if (solution[i]) {
				value += values[i];
				weight += weights[i];
			}
		}
		if (weight > knapsackCapacity) {
			return 0;
		} else {
			return value;
		}
	}
	
	
	
	
	private static int calculateWeight(boolean[] solution) {
		int value = 0;
		int weight = 0;
		for (int i = 0; i < solution.length; i++) {
			if (solution[i]) {
				value += values[i];
				weight += weights[i];
			}
		}
		if (weight > knapsackCapacity) {
			return weight;
		} else {
			return weight;
		}
	}
	
	
	
	
	// Helper method to calculate the acceptance probability of a neighbor solution
	private static double calculateAcceptanceProbability(int currentValue, int neighborValue, double temperature) {
        if (neighborValue > currentValue) {
            return 1;
        } else {
            return Math.exp((neighborValue - currentValue) / temperature);
        }
    }

	private static void findCurrentAndNextTemp() {
		//finding current and next temperature
		currentTemp = new double[iterationSize];
		nextTemp = new double[iterationSize];

		currentTemp[0]= MAX_TEMPERATURE;
		nextTemp[0]= currentTemp[0]*COOLING_RATE;
		for(int i=1;i<nextTemp.length;i++) {
			nextTemp[i] = nextTemp[i-1]*COOLING_RATE;

		}
		for(int i=1;i<currentTemp.length;i++) {
			currentTemp[i]=nextTemp[i-1];
		}

	}
}
