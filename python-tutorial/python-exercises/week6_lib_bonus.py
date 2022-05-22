import math
import random

pointcnt = 0
for i in range(10000):
    pointx = random.random()
    pointy = random.random()

    if math.pow(pointx, 2) + math.pow(pointy, 2) <= 1:
        pointcnt += 1
    else:
        pass
caledpi = float(pointcnt / 10000 * 4)
print("Calculated value of π: ", caledpi)
print("Value of π from math library:", math.pi)
print("Difference:", caledpi - math.pi)

