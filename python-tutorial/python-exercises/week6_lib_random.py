import random, statistics

# determining the values of the parameters 
mean = 100
stddiv = 10

def gaussian_distribution():
    randnum = []
    for i in range(1000):
        randnum.append(random.gauss(mean, stddiv))
    
    return randnum

numlist = gaussian_distribution()
print("Mean: ", statistics.mean(numlist))
print("Standard Deviation: ", statistics.stdev(numlist))
