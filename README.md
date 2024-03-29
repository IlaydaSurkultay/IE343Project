# Spring-23-Term-Project

## Question 1: How does the execution time change when cooling rate increased? Report it by running the same algorithm for the given dataset
- We ran the algorithm 10 times with a cooling rate of 0.03.the average execution time was 34.9 milliseconds.cooling rate increased and when we did 0.7, after running it 10 times, the average execution time was 34.3 milliseconds. 
- This shows us that as the cooling rate increases, the execution time decreases

- When the Cooling Rate is 0.03, the execution times were: 41, 42, 15, 44, 75, 15, 25, 27, 29, 36
- When the Cooling Rate is 0.7, the execution times were: 88, 36, 53, 44, 23, 19, 27, 21, 20, 18

## Question 2: How does the execution time and solution quality change when the difference between starting temperature and stopping temperature is increased? Report it by running the same algorithm for the given datasets.

- When we select the highest temperature of 1000, the difference becomes 9999.9999999990234375. When we run it like this 5 times, the best value average is 1288.8 and the average execution time is 39.4 millisecond. When we increase the highest temperature and set it 2000, the difference becomes 1999.999999998819. When we run it like this 5 times, the best value average is 1324.8 and the execution time average is 35.6 millisecond. 
- These results show that when the difference between the initial and final temperature increases, the best value increases and the execution time decreases.

- When the Max Temperature is 1000, the best values and execution times were: (1275,28); (1296,64); (1291,57); (1297,28); (1312,20)
- When the Max Temperature is 2000, the best values and execution times were: (1314,19); (1320,47); (1308,59); (1380,35); (1324,8)

## The explanation about the code:

- The initial solution and other relevant variables are initialized.
- A random number generator is created to generate random numbers.
- The simulated annealing process is initiated.
- The loop runs as many as the specified number of iterations.
- In each iteration, an item is removed from the bag (outOfBag) and an item from outside the bag is added to it (inToBag).
- The value and weight of the solution are calculated.
- If the solution does not exceed the capacity of the bag, the solution is accepted and its new value is calculated.
- If the new solution has a better value than the current solution, it is accepted. 
- Otherwise, an acceptance probability is calculated and compared with a random number to decide acceptance or rejection.
- The best value is updated in each iteration.
- When the capacity is exceeded, the previous iteration is reverted to.
- In the last iteration, the best solution and value are determined.

### Methods :

- calculateValue: Calculates the value of a solution.
- calculateWeight: Calculates the weight of a solution.
- findCurrentAndNextTemp: Using the formula finds the current and next temperature values.

***********************************************************************************************************************************

For this semester, students are expected to implement the given metaheuristic (simulated annealing) for the Knapsack Problem within the scope of IE343 lecture. Problem definition is as follows:
	Simulated annealing is a probabilistic algorithm used to find a good solution to an optimization problem. It is based on the physical process of annealing in metallurgy, where a metal is heated and then slowly cooled to reduce its defects and increase its strength. Similarly, simulated annealing starts with a high-energy state and then gradually cools down, allowing the algorithm to explore a wide range of potential solutions.
The basic idea is to start with an initial solution and then iteratively improve it by making small changes and accepting the changes if they improve the solution, even if they make it worse in the short term. The acceptance of a worse solution is controlled by a parameter called the temperature, which is gradually reduced over time. Initially, the temperature is high, and the algorithm is free to explore a large space of potential solutions, including those that are worse than the current solution. As the temperature decreases, the algorithm becomes more focused on improving the current solution and less likely to accept worse solutions. 

 
For a minimization problem, as the number of feasible solutions increase, simulated annealing cost function behaviour can be seen above. To implement simulated annealing, these steps should be followed:
Define the problem: Clearly define the objective function to be optimized and the constraints that must be satisfied.
Initialize the solution: Start with an initial solution that satisfies the constraints.
Set the initial temperature: Choose a starting temperature. This should be high enough to allow for exploration of the solution space, but not so high that the algorithm accepts poor solutions too often.
Set the cooling schedule: Choose a cooling schedule that decreases the temperature over time. There are several cooling schedules to choose from, but a common one is to decrease the temperature by a fixed fraction at each iteration.
Define the neighbourhood function: Define a neighbourhood function that generates neighbouring solutions. The neighbourhood function should be able to generate solutions that are close to the current solution, but not too similar.
Choose the stopping criterion: Choose a stopping criterion that defines when the algorithm should terminate. This could be a fixed number of iterations, a maximum amount of time, or a threshold for the objective function.
Implement the algorithm: Implement the algorithm by iterating over the defined temperature schedule and the neighbourhood function. At each iteration, generate a new solution using the neighbourhood function and calculate its objective function value. If the new solution is better, accept it. If it is worse, accept it with a certain probability that depends on the temperature and the degree of the worsening.
Output the best solution: After the algorithm has terminated, output the best solution found during the iterations.
As for the parameters, here are the main ones you'll need to define:
Initial temperature: Choose a temperature that is high enough to allow for exploration, but not so high that the algorithm accepts poor solutions too often.
Cooling rate: Choose a cooling rate that determines how quickly the temperature decreases. This will depend on the problem and the initial temperature.
Neighbourhood function: Choose a neighbourhood function that generates neighbouring solutions. The size of the neighbourhood and the type of moves made will depend on the problem.
Stopping criterion: Choose a stopping criterion that defines when the algorithm should terminate. This could be a fixed number of iterations, a maximum amount of time, or a threshold for the objective function.
Here, we need to define Metropolis criterion as it is a critical part of the simulated annealing:
The Metropolis criterion is a key component of the simulated annealing algorithm. It is used to decide whether to accept or reject a new solution during the algorithm's iterative process.The Metropolis criterion is based on the principle of detailed balance from statistical mechanics, which states that for a system in thermal equilibrium, the probability of transitioning from one state to another is equal to the probability of transitioning back from the second state to the first state. In the context of simulated annealing, this means that the probability of accepting a worse solution is proportional to the probability of moving to that solution and then back to the original solution.
The Metropolis criterion compares the objective function values of the current solution and the proposed new solution. If the new solution has a better objective function value, it is always accepted. If the new solution has a worse objective function value, it is accepted with a probability determined by the current temperature and the difference in objective function values. The probability of accepting the worse solution decreases as the temperature decreases.
The formula for the Metropolis criterion is:
P = e-∆ET

where P is the acceptance probability, ΔE is the difference in objective function values between the current solution and the proposed new solution, T is the current temperature, and e is the exponential function. Please implement your algorithm and create your own neighbourhood function.
Additionally, you are expected to complete a Taguchi analysis for the tuning of the 3 hyperparameters of simulated annealing, starting temperature, cooling rate, and stopping temperature. You are not expected to code Taguchi analysis and the relevant code will be given by a GitHub repository.  
Finally, compare optimality gap with the optimal solutions given.
Code this algorithm by using the packages given. Follow the steps:
1-Clone the given repository by using Git
2-Code the algorithm by given classes and methods.
3-Push the project code to a private repository, share it with the course TA’s
Your repository must include several files, which are:
1-ReadMe.md file that explains the part that is written by you and has the answers for questions asked
2-Exactly same files that is sent to you, if you need to add something for those files, you are expected to explain why you needed to add them and how did you implement.
Question that you need to answer is:
	How does the execution time change when problem size is increased? Report it by running the same algorithm for the given datasets.
	How does the execution time and solution quality change when the difference between starting temperature and stopping temperature is increased? Report it by running the same algorithm for the given datasets.

How to create Git account:
-Get a github account from github.com
-Check if you already have git by typing terminal:
	git --version
	-If you don’t have Git, go to https://git-scm.com/download and download git for your operating system

	-After downloading Git, you need to configurate your account to your local computer by using
	git config --global user.name “Your name here”
	git config --global user.email “Your email here”
	-If you want to check whether if you have username and email configured, type
	git config –global --list

To initialize the git repository, go to the terminal and type
	git init “Folder name”

By doing these things, you are successful to create your first repository. After creating your first repository, you can clone https://github.com/berkkarasu/Term-Project
 

If you are using VS Code, follow this YouTube video:
https://www.youtube.com/watch?v=sz2EM-gkEs0

If you are using Eclipse, follow this YouTube video:
https://www.youtube.com/watch?v=uOpug3ie_h0

If you are using PyCharm, follow this YouTube video:
https://www.youtube.com/watch?v=egpbzk5u0xA

After cloning the repository, don’t forget to push it to the Git, so that we can see your term project.
