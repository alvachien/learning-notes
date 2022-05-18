newlines = []
dimension = []
col = 0
row = 0

with open("key.txt", "r") as file:
    for line in file:
        line = line.strip()
        dimension.append(int(line))

col = dimension[0]
row = dimension[1]
currentline = []
currow = 0


with open("secret.txt", "r") as file:
    for line in file:
        if currow < row:
           line = line.strip()
           currentline.append(line)
           if len(currentline) == col:
               newlines.append(currentline)
               currentline = []
               currow += 1

with open("public.txt", "w") as file:
    for aline in newlines:
        file.write(''.join(aline) + "\n")

with open("public.txt", "r") as file:
    for line in file:
        line = line.strip()
        print(line)
           
