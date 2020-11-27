classmates = ['Michael', 'Bob', 'Tracy']
print(classmates)
print("len(classmates) = ", len(classmates))

print("classmates[0] = ", classmates[0])
print("classmates[1] = ", classmates[1])
print("classmates[2] = ", classmates[2])
print("classmates[-1] = ", classmates[-1])

classmates.append('Adam')
print("After classmates.append('Adam'), classmates = ", classmates)


classmates.insert(1, 'Jack')
print("After classmates.insert(1, 'Jack'), classmates = ", classmates)

pitem = classmates.pop()
print("classmates.pop() = ", pitem)
print("After classmates.pop(), classmates = ", classmates)

