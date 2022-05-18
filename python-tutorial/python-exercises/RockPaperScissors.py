player1 = []
player2 = []

with open("player1.txt", "r") as file:
    for line in file:
        line = line.strip()
        player1.append(line)

with open("player2.txt", "r") as file:
    for line in file:
        line = line.strip()
        player2.append(line)

player1win = 0
player2win = 0
draw = 0

for index in range(0, 100):
    if player1[index] == player2[index]:
        draw += 1
    else:
        if player1[index] == 'P':
            if player2[index] == 'S':
                player2win += 1
            elif player2[index] == 'R':
                player1win += 1
        elif player1[index] == 'S':
            if player2[index] == 'P':
                player1win += 1
            elif player2[index] == 'R':
                player2win += 1
        elif player1[index] == 'R':
            if player2[index] == 'P':
                player2win += 1
            elif player2[index] == 'S':
                player1win += 1

with open("result.txt", "w") as file:
    file.write(f"Player1 wins: {player1win}\n")
    file.write(f"Player2 wins: {player2win}\n")
    file.write(f"Draws: {draw}\n")

with open("result.txt", "r") as file:
    for line in file:
        line = line.strip()
        print(line)


