def reaction_path(speed):
    """
    speed is km/h
    """
    return speed * 0.3

def brake_distance(speed):
    """
    speed is km/h
    """
    tmp = speed / 10
    return tmp * tmp

def stopping_distance(speed):
    """
    speed is km/h
    """
    return reaction_path(speed) + brake_distance(speed)

speed = int(input("Your speed:"))
print(stopping_distance(speed))
