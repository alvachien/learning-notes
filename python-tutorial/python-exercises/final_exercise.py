import random

# Word list
def word_list():
    words = []
    # Read file and returns the list of the words
    with open("5_letter_words.txt", "r") as file:
        for line in file:
            line = line.strip()
            words.append(line)
    return words

def random_word(allwords):
    # A list of words as parameter and return a random word
    randidx = random.randint(0, len(allwords) - 1)
    return allwords[randidx]

def is_real_word(guess, allwords):
    return guess in allwords

def check_guess(guess, realword):
    arresult = []
    dictguess = {}
    dictguess2 = {}
    dictreal = {}
    for char in guess:
        if char in dictguess.keys():
            dictguess[char] += 1
        else:
            dictguess[char] = 1
            dictguess2[char] = 0
    for char in realword:
        if char in dictreal.keys():
            dictreal[char] += 1
        else:
            dictreal[char] = 1
    
    for i in range(len(guess)):
        if guess[i] == realword[i]:
            arresult.append('X')
        elif guess[i] in realword:
            arresult.append('O')
        else:
            arresult.append('_')

    # Special logic for 'O'    
    for i in range(len(guess)):
        dictguess2[guess[i]] += 1
        if arresult[i] == 'O' and dictguess[guess[i]] == 2:
            if guess[i] in dictreal and dictreal[guess[i]] == 1:
                if dictguess2[guess[i]] > 1:
                    arresult[i] = '_'

    return ''.join(arresult)

def next_guess(allwords):
    # Takes a world list as parameter
    is_guess_accepted = False
    guess = ""
    while is_guess_accepted != True:
        guess = input("Please enter a guess:")
        guess = guess.lower()
        is_guess_accepted = is_real_word(guess, allwords)
        if is_guess_accepted != True:
            print("That's not a real word!")

    return guess

def play():
    # Uses the functions word_list and random_word to select a random 5 letter word.
    allwords = word_list()
    selectedword = random_word(allwords)
    
    tried_times = 0
    is_succeed = False

    while tried_times < 6 and is_succeed  == False:
        # Asks the user for a guess using the next_guess function.
        guess = next_guess(allwords)

        # Checks each guess using the check_guess function and shows the result to the user.
        check_rst = check_guess(guess, selectedword)
        # Checks if the users guessed the right word with six guesses or less. If yes, the user wins and the function prints You won!. Otherwise the user loses and the function prints You lost! as well as The word was: followed by word the user had to find.    
        if check_rst == 'xxxxx':
            is_succeed = True
        else:
            print(check_rst)
            tried_times += 1
    
    if is_succeed == True:
        print("You won!")
    else:
        print("You lost!\nThe word was:", selectedword)

# Main program:
play()
