def is_even(number):
    if number % 2 == 0:
        return True
    return False

for i in range(100):
    if is_even(i):
        print(i, "is even", end="\n")
    else:
        print(i, "is not even", end="\n")
