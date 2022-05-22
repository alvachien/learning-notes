
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
    previousdata = []
    process_stack = []
    # Element data in work solution
    elemdict = {}

    def __init__(self):
        pass

    def clear(self):
        self.currentdata = []
        self.previousdata = []
        self.process_stack = []
        self.elemdict.clear()

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
                allerr.append("行、列、宫的所有数字组合中，缺少数字: " + str(key))
            elif val > 1:
                allerr.append("数字重复出现： " + str(key) + " 出现的次数为：  " + str(val))

        if zerovalue > 0:
            allerr.append("行、列、宫出现了空值（即0）: " + str(zerovalue))

        return allerr


    # Check data
    def check_data(self):
        # General check
        errs = []

        # Check row
        for idx in range(9):
            curerrs = self.check_data_core( self.get_row_element( idx ) )
            if len( curerrs ) > 0:
                errs.append("第： " + str(idx) + " 行出错")
                errs.append(curerrs)

        # Check column
        for idx in range(9):
            curerrs = self.check_data_core( self.get_column_element( idx ) )
            if len( curerrs ) > 0:
                errs.append("第 " + str(idx) + " 列出错")
                errs.append(curerrs)

        # Check section
        for idx in range(9):
            curerrs = self.check_data_core( self.get_section_element( idx ) )
            if len( curerrs ) > 0:
                errs.append("第 " + str(idx) + " 宫出错")
                errs.append(curerrs)

        for err in errs:
            print(err)
        if len(errs) == 0:
            print("没有发现错误")


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

        return list(numdic.keys())


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
            for colidx in range(9):
                cellidx = get_cell_index(rowidx, colidx)
                if self.currentdata[rowidx][colidx] == 0:
                    possbledata = self.get_possible_value(rowidx, colidx)
                    if len(possbledata) == 1:
                        self.currentdata[rowidx][colidx] = possbledata[0]
                        self.elemdict[cellidx] = (self.currentdata[rowidx][colidx], [])    
                    else:
                        self.elemdict[cellidx] = (0, possbledata)
                else:
                    self.elemdict[cellidx] = (self.currentdata[rowidx][colidx], [])

        # Level 2. restrict values per section
        for sectid in range(9):
            sectnumdict = init_num_check_dict()
            cellindexs = get_section_cellindexes(sectid)
            
            for cellidx in cellindexs:
                if self.elemdict[cellidx][0] != 0:
                    if self.elemdict[cellidx][0] in sectnumdict:
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
        #print("After ====================")
        errocurr = False
        for key, val in self.elemdict.items():
            if val[0] == 0:                
                #print([rowx, colx], val[1], len(val[1]))
                valen = len(val[1])
                if valen == 1:
                    rowx, colx = from_cell_index(key)
                    self.currentdata[rowx][colx] = val[1][0]
                elif valen == 0:
                    errocurr = True

        return errocurr

    ## Workout solution
    def workout_solution(self):
        emptyelements = self.get_empty_cell_count()
        while emptyelements > 0:
            print("剩余未填数字的数量为: ", emptyelements)
            prvelments = emptyelements
            erroccur = self.workout_solution_core()
            if erroccur == True:
                if len(self.process_stack) > 0:
                    print("\033[0;30;45m当期布局下无正确解，回滚到上一次\033[0m")
                    self.currentdata.clear()
                    self.currentdata = self.process_stack.pop()
                    emptyelements = self.get_empty_cell_count()
                    continue
                else:
                    print("\033[0;30;41m无法解决\033[0m")
                    break
            else:
                emptyelements = self.get_empty_cell_count()
                if emptyelements == prvelments:
                    # Parse the detect lock.
                    print("\033[0;30;46m当期布局没有直接可确定的数值，僵局。\033[0m")

                    if self.detect_deadlock_entries() == True:
                        self.display()

                        emptyelements = self.get_empty_cell_count()
                        continue
                    else:
                        break
                else:
                    self.display()

    ## Detect dead lock case 1
    def deadlock_case1(self):
        dictduplicates = {}

        # Case  1. find out two cells have two pairs. Chose one and go!
        # elemdict: [8, 8] dict_keys([2, 4])
        for key, val in self.elemdict.items():
            if val[0] == 0 and len(val[1]) == 2:
                tuplval = tuple( val[1] )
                if tuplval in dictduplicates.keys():
                    dictduplicates[tuplval].append(key)
                else:
                    dictduplicates[tuplval] = [key]

        # dictduplicates: Key: ([2, 4]) Value: [8, 80]
        dictkeydelted = []
        for dictkey, dictval in dictduplicates.items():
            if len(dictval) != 2:
                dictkeydelted.append(dictkey)
            else:
                print(dictkey, dictval)

        for dictkey in dictkeydelted:
            del dictduplicates[dictkey]

        # Find out the potential solution
        potentialSolution = False
        for dictkey, dictval in dictduplicates.items():
            pos1row, pos1col = from_cell_index(dictval[0])
            pos2row, pos2col = from_cell_index(dictval[1])
            sect1 = workout_section_index(pos1row, pos1col)
            sect2 = workout_section_index(pos2row, pos2col)

            if pos1row == pos2row or pos1col == pos2col or sect1 == sect2:
                print("发现两个完全相同的可能节点且相关", dictkey, dictval)
                currdata2 = []
                for line in self.currentdata:
                    currdata2.append(line.copy())

                currdata2[pos1row][pos1col] = dictkey[0]
                currdata2[pos2row][pos2col] = dictkey[1]
                self.process_stack.append(currdata2.copy())

                currdata2.clear()
                for line in self.currentdata:
                    currdata2.append(line.copy())

                currdata2[pos1row][pos1col] = dictkey[1]
                currdata2[pos2row][pos2col] = dictkey[0]
                self.process_stack.append(currdata2.copy())

                potentialSolution = True
 
        if potentialSolution == False:
            for key, val in self.elemdict.items():
                if val[0] == 0 and len(val[1]) == 2:
                    print("发现该位置只可能有两个值：", key, val)
                    
                    posrow, poscol = from_cell_index(key)
                    currdata2 = []
                    for line in self.currentdata:
                        currdata2.append(line.copy())
                    currdata2[posrow][poscol] = val[1][0]
                    self.process_stack.append(currdata2.copy())

                    currdata2 = []
                    for line in self.currentdata:
                        currdata2.append(line.copy())
                    currdata2[posrow][poscol] = val[1][1]
                    self.process_stack.append(currdata2.copy())
                    
                    potentialSolution = True

        if potentialSolution == True:
            self.currentdata.clear()
            self.currentdata = self.process_stack.pop()
        else:
            pass

        return potentialSolution

    ## Detect dead lock case 2
    def deadlock_case2(self):
        allpossvaldict = init_num_check_dict()
        for rowidx in range(9):
            for colidx in range(9):
                if self.currentdata[rowidx][colidx] != 0:
                    allpossvaldict[self.currentdata[rowidx][colidx]] += 1

        mostposkey = 0
        mostposkeyappear = 0
        for posvalkey, posval in allpossvaldict.items():
            if mostposkeyappear < posval:
                mostposkey = posvalkey
                mostposkeyappear = posval

        potentialSolution = False
        while mostposkey != 0:
            print("减少出现次数最多的数字：", mostposkey, "，共出现: ", mostposkeyappear )
            del allpossvaldict[mostposkey]

            # Missing numbers in each row, does it can only occur it two cell?
            for rowidx in range(9):
                rownumdict = {
                    1: [],
                    2: [],
                    3: [],
                    4: [],
                    5: [],
                    6: [],
                    7: [],
                    8: [],
                    9: [],
                }
                for colidx in range(9):
                    cellidx = get_cell_index(rowidx, colidx)
                    if self.elemdict[cellidx][0] == 0:
                        for possval in self.elemdict[cellidx][1]:
                            rownumdict[possval].append([rowidx, colidx])
                    else:
                        pass
                
                for numkey, numval in rownumdict.items():
                    if numkey == mostposkey and len(numval) == 2:
                        print("发现行项目上", numval)
                        potentialSolution = True

                        currdata2 = []
                        for line in self.currentdata:
                            currdata2.append(line.copy())
                        currdata2[numval[0][0]][numval[0][1]] = numkey
                        self.process_stack.append(currdata2)
                        currdata2 = []
                        for line in self.currentdata:
                            currdata2.append(line.copy())
                        currdata2[numval[1][0]][numval[1][1]] = numkey
                        self.process_stack.append(currdata2)

            for colidx in range(9):
                colnumdict = {
                    1: [],
                    2: [],
                    3: [],
                    4: [],
                    5: [],
                    6: [],
                    7: [],
                    8: [],
                    9: [],
                }
                for rowidx in range(9):
                    cellidx = get_cell_index(rowidx, colidx)
                    if self.elemdict[cellidx][0] == 0:
                        for possval in self.elemdict[cellidx][1]:
                            colnumdict[possval].append([rowidx, colidx])
                    else:
                        pass
                
                for numkey, numval in colnumdict.items():
                    if numkey == mostposkey and len(numval) == 2:
                        print("发现列项目上")
                        potentialSolution = True

                        currdata2 = []
                        for line in self.currentdata:
                            currdata2.append(line.copy())
                        currdata2[numval[0][0]][numval[0][1]] = numkey
                        self.process_stack.append(currdata2)
                        currdata2 = []
                        for line in self.currentdata:
                            currdata2.append(line.copy())
                        currdata2[numval[1][0]][numval[1][1]] = numkey
                        self.process_stack.append(currdata2)                    

            for sectidx in range(9):
                sectnumdict = {
                    1: [],
                    2: [],
                    3: [],
                    4: [],
                    5: [],
                    6: [],
                    7: [],
                    8: [],
                    9: [],
                }            
                for cellidx in get_section_cellindexes(sectidx):
                    if self.elemdict[cellidx][0] == 0:
                        for possval in self.elemdict[cellidx][1]:
                            rowx, colx = from_cell_index(cellidx)
                            sectnumdict[possval].append([rowx, colx])
                    else:
                        pass
                
                for numkey, numval in sectnumdict.items():
                    if numkey == mostposkey and len(numval) == 2:
                        print("发现块项目上")
                        potentialSolution = True

                        currdata2 = []
                        for line in self.currentdata:
                            currdata2.append(line.copy())
                        currdata2[numval[0][0]][numval[0][1]] = numkey
                        self.process_stack.append(currdata2.copy())
                        currdata2 = []
                        for line in self.currentdata:
                            currdata2.append(line.copy())
                        currdata2[numval[1][0]][numval[1][1]] = numkey
                        self.process_stack.append(currdata2.copy())

            if potentialSolution == True:
                self.currentdata.clear()
                self.currentdata = self.process_stack.pop()
                break
            else:
                mostposkey = 0
                mostposkeyappear = 0
                for posvalkey, posval in allpossvaldict.items():
                    if mostposkeyappear < posval:
                        mostposkey = posvalkey
                        mostposkeyappear = posval

        return potentialSolution


    ## Detect dead lock entries
    def detect_deadlock_entries(self):
        # Case 1. Two numbers for two possiblities
        if self.deadlock_case1() == True:
            return True
        
        # Case 2. One number
        if self.deadlock_case2() == True:
            return True

        return False


    # Display line
    def display_line(self, rowindex):
        line = []
        line.append("\033[1;36m|\033[0m") 
        for colidx in range(9):
            if self.currentdata[rowindex][colidx] == 0:
                line.append("   ")
            else:
                if len(self.previousdata) > 0 and self.currentdata[rowindex][colidx] != self.previousdata[rowindex][colidx]:
                    line.append(" \033[0;30;41m" + str(self.currentdata[rowindex][colidx]) + "\033[0m ")
                else:
                    line.append(" " + str(self.currentdata[rowindex][colidx]) + " ")
            if colidx == 2 or colidx == 5 or colidx == 8:
                line.append("\033[1;36m|\033[0m")
        print(''.join(line))

    # Display 
    def display(self):        
        print("\033[1;36m/---------+---------+---------\\\033[0m")
        self.display_line(0)
        self.display_line(1)
        self.display_line(2)
        print("\033[1;36m+---------+---------+---------+\033[0m")
        self.display_line(3)
        self.display_line(4)
        self.display_line(5)
        print("\033[1;36m+---------+---------+---------+\033[0m")
        self.display_line(6)
        self.display_line(7)
        self.display_line(8)
        print("\033[1;36m\\---------+---------+---------/\033[0m")

        self.previousdata = []
        for line in self.currentdata:
            self.previousdata.append(line.copy())

