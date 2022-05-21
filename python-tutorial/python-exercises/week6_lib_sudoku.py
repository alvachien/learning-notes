from sudoku import Sudoku

puzzle = Sudoku(3).difficulty(0.5)
puzzle.show()

rst = input("Do you need the answer? (Yes or No):")
if rst == "Yes":
    solution = puzzle.solve()
    solution.show()

