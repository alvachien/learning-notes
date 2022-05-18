alphabet = {
    "a": 0,
    "b": 1,
    "c": 2,
    "d": 3,
    "e": 4,
    "f": 5,
    "g": 6,
    "h": 7,
    "i": 8,
    "j": 9,
    "k": 10,
    "l": 11,
    "m": 12,
    "n": 13,
    "o": 14,
    "p": 15,
    "q": 16,
    "r": 17,
    "s": 18,
    "t": 19,
    "u": 20,
    "v": 21,
    "w": 22,
    "x": 23,
    "y": 24,
    "z": 25,
    }    

def get_character(inputpos):
    for word in alphabet:
        if alphabet[word] == inputpos:
            return word
    return ""
    
def calculate_shifts(inputletter):
    if inputletter in alphabet:
        return alphabet[inputletter]
    return 0

def encrypt_letter(inputchar, inputkey):
    if inputchar.isalpha() == True and inputkey > 0:        
        origpos = calculate_shifts(inputchar)    
        origpos += inputkey
        origpos = origpos % 26
        return get_character(origpos)
    return inputchar

def encrypt_text(inputstring, key):
    encrypted = []
    cursor = 0
    for word in inputstring:        
        encryptedword = word.lower()
        if word.isalpha() == True:
            keyword = key[cursor % len(key)].lower()
            encryptedword = encrypt_letter(encryptedword, calculate_shifts(keyword))

        encrypted.append(encryptedword)
        cursor += 1
        
    return ''.join(encrypted)

inputstr = input("Which text should be encrypted: ")
keystr = input("Which keyword should be used?")
print(encrypt_text(inputstr, keystr))

