def is_prime(inputnum):
    for num in range(2, inputnum):
        if inputnum % num == 0:
            return False

    return True

def prime_list(inputnum):
    primes = []
    for num in range(2, inputnum + 1):
        if is_prime(num):
            primes.append(num)
    return primes

number = "x"
while not (number.isdecimal()):
    number = input("Up to which number do you want all prime numbers: ")

number = int(number)
print(prime_list(number))
