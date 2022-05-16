
#
# Sudoucore.py
# Row: 0 - 8
# Column: 0 - 8
# Section: 0 - 8
# Cell index: 0 - 80
# 

# Initial. number check dictionary
# For each row, column and section
def init_num_check_dict():
    return {
        1: 0,
        2: 0,
        3: 0,
        4: 0,
        5: 0,
        6: 0,
        7: 0,
        8: 0,
        9: 0,
    }


# Workout section index by row and column
# Returns: Section ID and the position in elements
def workout_section_index(rowidx, colidx):
    if rowidx >=0 and rowidx <= 2:
        if colidx >= 0 and colidx <= 2:
            curpos = 3 * ( rowidx % 3 ) + colidx
            return 0, curpos
        elif colidx >= 3 and colidx <= 5:
            curpos = 3 * ( rowidx % 3 ) + (colidx - 3)
            return 1, curpos
        elif colidx >= 6 and colidx <= 8:
            curpos = 3 * ( rowidx % 3 ) + (colidx - 6)
            return 2, curpos
        else:
            return -1, -1
    elif rowidx >= 3 and rowidx <= 5:
        if colidx >= 0 and colidx <= 2:
            curpos = 3 * ( ( rowidx - 3 ) % 3 ) + colidx
            return 3, curpos
        elif colidx >= 3 and colidx <= 5:
            curpos = 3 * ( ( rowidx - 3 ) % 3 ) + ( colidx - 3 )
            return 4, curpos
        elif colidx >= 6 and colidx <= 8:
            curpos = 3 * ( ( rowidx - 3 ) % 3 ) + ( colidx - 6 )
            return 5, curpos
        else:
            return -1, -1
    elif rowidx >= 6 and rowidx <= 8:
        if colidx >= 0 and colidx <= 2:
            curpos = 3 * ( ( rowidx - 6 ) % 3 ) + colidx
            return 6, curpos
        elif colidx >= 3 and colidx <= 5:
            curpos = 3 * ( ( rowidx - 6 ) % 3 ) + ( colidx - 3)
            return 7, curpos
        elif colidx >= 6 and colidx <= 8:
            curpos = 3 * ( ( rowidx - 6 ) % 3 ) + ( colidx - 6)
            return 8, curpos
        else:
            return -1, -1
    else:
        return -1, -1


# Get all cell indexes by section ID
def get_section_cellindexes(sectionid):
    if sectionid == 0:
        return [0, 1, 2, 9, 10, 11, 18, 19, 20]
    if sectionid == 1:
        return [3, 4, 5, 12, 13, 14, 21, 22, 23]
    if sectionid == 2:
        return [6, 7, 8, 15, 16, 17, 24, 25, 26]
    if sectionid == 3:
        return [27, 28, 29, 36, 37, 38, 45, 46, 47]
    if sectionid == 4:
        return [30, 31, 32, 39, 40, 41, 48, 49, 50]
    if sectionid == 5:
        return [33, 34, 35, 42, 43, 44, 51, 52, 53]
    if sectionid == 6:
        return [54, 55, 56, 63, 64, 65, 72, 73, 74]
    if sectionid == 7:
        return [57, 58, 59, 66, 67, 68, 75, 76, 77]
    if sectionid == 8:
        return [60, 61, 62, 69, 70, 71, 78, 79, 80]


# Get cell index from row and column
def get_cell_index(rowidx, colidx):
    return rowidx * 9 + colidx


# Workout row and column from cell index
def from_cell_index(cellidx):
    return (int(cellidx / 9), int(cellidx % 9))


# Class: Sudou Core
class SudouCore:
    currentdata = []
    process_stack = []
    deadlock_tried = []
    # Element data in work solution
    elemdict = {}

    def __init__(self):
        pass

    def get_row_element(self, rowindex):
        return self.currentdata[rowindex]

    def get_column_element(self, columnindex):
        return [self.currentdata[0][columnindex], self.currentdata[1][columnindex], self.currentdata[2][columnindex],
            self.currentdata[3][columnindex], self.currentdata[4][columnindex], self.currentdata[5][columnindex],
            self.currentdata[6][columnindex], self.currentdata[7][columnindex], self.currentdata[8][columnindex]]

    def get_section_element(self, sectionindex):
        if sectionindex == 0:
            return [self.currentdata[0][0], self.currentdata[0][1], self.currentdata[0][2],
                self.currentdata[1][0], self.currentdata[1][1], self.currentdata[1][2],
                self.currentdata[2][0], self.currentdata[2][1], self.currentdata[2][2]]
        if sectionindex == 1:
            return [self.currentdata[0][3], self.currentdata[0][4], self.currentdata[0][5],
                self.currentdata[1][3], self.currentdata[1][4], self.currentdata[1][5],
                self.currentdata[2][3], self.currentdata[2][4], self.currentdata[2][5]]
        if sectionindex == 2:
            return [self.currentdata[0][6], self.currentdata[0][7], self.currentdata[0][8],
                self.currentdata[1][6], self.currentdata[1][7], self.currentdata[1][8],
                self.currentdata[2][6], self.currentdata[2][7], self.currentdata[2][8]]
        if sectionindex == 3:
            return [self.currentdata[3][0], self.currentdata[3][1], self.currentdata[3][2],
                self.currentdata[4][0], self.currentdata[4][1], self.currentdata[4][2],
                self.currentdata[5][0], self.currentdata[5][1], self.currentdata[5][2]]
        if sectionindex == 4:
            return [self.currentdata[3][3], self.currentdata[3][4], self.currentdata[3][5],
                self.currentdata[4][3], self.currentdata[4][4], self.currentdata[4][5],
                self.currentdata[5][3], self.currentdata[5][4], self.currentdata[5][5]]
        if sectionindex == 5:
            return [self.currentdata[3][6], self.currentdata[3][7], self.currentdata[3][8],
                self.currentdata[4][6], self.currentdata[4][7], self.currentdata[4][8],
                self.currentdata[5][6], self.currentdata[5][7], self.currentdata[5][8]]
        if sectionindex == 6:
            return [self.currentdata[6][0], self.currentdata[6][1], self.currentdata[6][2],
                self.currentdata[7][0], self.currentdata[7][1], self.currentdata[7][2],
                self.currentdata[8][0], self.currentdata[8][1], self.currentdata[8][2]]
        if sectionindex == 7:
            return [self.currentdata[6][3], self.currentdata[6][4], self.currentdata[6][5],
                self.currentdata[7][3], self.currentdata[7][4], self.currentdata[7][5],
                self.currentdata[8][3], self.currentdata[8][4], self.currentdata[8][5]]
        if sectionindex == 8:
            return [self.currentdata[6][6], self.currentdata[6][7], self.currentdata[6][8],
                self.currentdata[7][6], self.currentdata[7][7], self.currentdata[7][8],
                self.currentdata[8][6], self.currentdata[8][7], self.currentdata[8][8]]

    # Check data core
    def check_data_core(self, elements):
        numdic = init_num_check_dict()
        zerovalue = 0
        allerr = []
        for element in elements:
            if element == 0:
                zerovalue += 1
            else:
                numdic[element] += 1

        for key, val in numdic.items():
            if val == 0:
                allerr.append("Missing figure: " + str(key))
            elif val > 1:
                allerr.append("Figure " + str(key) + " appeared  " + str(val))

        if zerovalue > 0:
            allerr.append("Zero value found: " + str(zerovalue))

        return allerr


    # Check data
    def check_data(self):
        # General check
        errs = []

        # Check row
        for idx in range(9):
            curerrs = self.check_data_core( self.get_row_element( idx ) )
            if len( curerrs ) > 0:
                errs.append("Row " + str(idx) + "contains error")
                errs.append(curerrs)

        # Check column
        for idx in range(9):
            curerrs = self.check_data_core( self.get_column_element( idx ) )
            if len( curerrs ) > 0:
                errs.append("Column " + str(idx) + "contains error")
                errs.append(curerrs)

        # Check section
        for idx in range(9):
            curerrs = self.check_data_core( self.get_section_element( idx ) )
            if len( curerrs ) > 0:
                errs.append("Section " + str(idx) + "contains error")
                errs.append(curerrs)

        for err in errs:
            print(err)


    # Get possible value for one specified cell
    def get_possible_value(self, rowidx, colidx):
        numdic = init_num_check_dict()

        # Row
        rowcursor = 0
        for elem in self.get_row_element(rowidx):
            if rowcursor != colidx and elem > 0 and elem in numdic.keys():
                del numdic[elem]

            rowcursor += 1

        # Column
        colcursor = 0
        for elem in self.get_column_element(colidx):
            if colcursor != rowidx and elem > 0 and elem in numdic.keys():
                del numdic[elem]
            
            colcursor += 1

        # Section
        sid, spos = workout_section_index(rowidx, colidx)
        sectcursor = 0
        for elem in self.get_section_element(sid):
            if sectcursor != spos and elem > 0 and elem in numdic.keys():
                del numdic[elem]
            sectcursor += 1

        return numdic.keys()


    # Get empty cell count.
    def get_empty_cell_count(self):
        itemcnt = 0
        for rowidx in range(9):
            for elem in self.get_row_element( rowidx ):
                if elem == 0:
                    itemcnt += 1

        return itemcnt


    # Fight for solution in one step
    def workout_solution_core(self):
        self.elemdict.clear()

        # Level 1. parse all values
        for rowidx in range(9):
            rowelements = self.get_row_element( rowidx )
            for colidx in range(9):
                cellidx = get_cell_index(rowidx, colidx)
                if self.currentdata[rowidx][colidx] == 0:
                    possbledata = self.get_possible_value(rowidx, colidx)
                    self.elemdict[cellidx] = (0, possbledata)
                else:
                    self.elemdict[cellidx] = (self.currentdata[rowidx][colidx], [])

        # Level 2. restrict values per section
        for sectid in range(9):
            sectnumdict = init_num_check_dict()
            cellindexs = get_section_cellindexes(sectid)
            
            for cellidx in cellindexs:
                if self.elemdict[cellidx][0] != 0:
                    del sectnumdict[self.elemdict[cellidx][0]]

            for miskey in sectnumdict.keys():
                ocurtimes = 0
                for cellidx in cellindexs:
                    if self.elemdict[cellidx][0] == 0 and miskey in self.elemdict[cellidx][1]:
                        ocurtimes += 1
                if ocurtimes == 1:
                    for cellidx in cellindexs:
                        if self.elemdict[cellidx][0] == 0 and miskey in self.elemdict[cellidx][1]:
                            self.elemdict[cellidx] = (0, [miskey])
        
        # Level 3 print data
        print("After ====================")
        for key, val in self.elemdict.items():
            if val[0] == 0:
                rowidx, colidx = from_cell_index(key)
                print([rowidx, colidx], val[1])
                if len(val[1]) == 1:
                    self.currentdata[rowidx][colidx] = val[1][0]

        for linedata in self.currentdata:
            print(linedata)


    ## Workout solution
    def workout_solution(self):
        emptyelements = self.get_empty_cell_count()
        while emptyelements > 0:
            print("Empty Elements Left: ", emptyelements)
            prvelments = emptyelements
            self.workout_solution_core()
            emptyelements = self.get_empty_cell_count()
            if emptyelements == prvelments:
                # Parse the detect lock.
                print("僵局")
                self.detect_deadlock_entries()
                break


    ## Detect dead lock entries
    def detect_deadlock_entries(self):
        dictduplicates = {}
        # Check section
        for key, val in self.elemdict.items():
            if val[0] == 0 and len(val[1]) == 2:
                tuplval = tuple( val[1] )
                if tuplval in dictduplicates.keys():
                    dictduplicates[tuplval] += 1
                else:
                    dictduplicates[tuplval] = 0

        deadlock = []
        for dictkey, dictval in dictduplicates.items():
            if dictval == 2 and dictkey not in self.deadlock_tried:
                deadlock.append(dictkey)
                print(dictkey)

        # Find out the potential solution
        deadlockpos = []
        for dl in deadlock:
            for key, val in self.elemdict.items():
                if val[0] == 0 and len(val[1]) == 2:
                    tuplval = tuple( val[1] )
                    if dl == tuplval:
                        deadlockpos.append(key)
                        print(key)
        

        

        

        

