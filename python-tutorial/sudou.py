alldata = []
elemdict = {}

def init_numdict():
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

def get_row_element(idx):
    return alldata[idx]

def get_column_element(idx):
    return [alldata[0][idx], alldata[1][idx], alldata[2][idx],
            alldata[3][idx], alldata[4][idx], alldata[5][idx],
            alldata[6][idx], alldata[7][idx], alldata[8][idx]]

def get_section_index(rowidx, colidx):
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


def get_section_element(idx):
    if idx == 0:
        return [alldata[0][0], alldata[0][1], alldata[0][2],
            alldata[1][0], alldata[1][1], alldata[1][2],
            alldata[2][0], alldata[2][1], alldata[2][2]]
    if idx == 1:
        return [alldata[0][3], alldata[0][4], alldata[0][5],
            alldata[1][3], alldata[1][4], alldata[1][5],
            alldata[2][3], alldata[2][4], alldata[2][5]]
    if idx == 2:
        return [alldata[0][6], alldata[0][7], alldata[0][8],
            alldata[1][6], alldata[1][7], alldata[1][8],
            alldata[2][6], alldata[2][7], alldata[2][8]]
    if idx == 3:
        return [alldata[3][0], alldata[3][1], alldata[3][2],
            alldata[4][0], alldata[4][1], alldata[4][2],
            alldata[5][0], alldata[5][1], alldata[5][2]]
    if idx == 4:
        return [alldata[3][3], alldata[3][4], alldata[3][5],
            alldata[4][3], alldata[4][4], alldata[4][5],
            alldata[5][3], alldata[5][4], alldata[5][5]]
    if idx == 5:
        return [alldata[3][6], alldata[3][7], alldata[3][8],
            alldata[4][6], alldata[4][7], alldata[4][8],
            alldata[5][6], alldata[5][7], alldata[5][8]]
    if idx == 6:
        return [alldata[6][0], alldata[6][1], alldata[6][2],
            alldata[7][0], alldata[7][1], alldata[7][2],
            alldata[8][0], alldata[8][1], alldata[8][2]]
    if idx == 7:
        return [alldata[6][3], alldata[6][4], alldata[6][5],
            alldata[7][3], alldata[7][4], alldata[7][5],
            alldata[8][3], alldata[8][4], alldata[8][5]]
    if idx == 8:
        return [alldata[6][6], alldata[6][7], alldata[6][8],
            alldata[7][6], alldata[7][7], alldata[7][8],
            alldata[8][6], alldata[8][7], alldata[8][8]]


def get_section_cellindexes(idx):
    if idx == 0:
        return [0, 1, 2, 9, 10, 11, 18, 19, 20]
    if idx == 1:
        return [3, 4, 5, 12, 13, 14, 21, 22, 23]
    if idx == 2:
        return [6, 7, 8, 15, 16, 17, 24, 25, 26]
    if idx == 3:
        return [27, 28, 29, 36, 37, 38, 45, 46, 47]
    if idx == 4:
        return [30, 31, 32, 39, 40, 41, 48, 49, 50]
    if idx == 5:
        return [33, 34, 35, 42, 43, 44, 51, 52, 53]
    if idx == 6:
        return [54, 55, 56, 63, 64, 65, 72, 73, 74]
    if idx == 7:
        return [57, 58, 59, 66, 67, 68, 75, 76, 77]
    if idx == 8:
        return [60, 61, 62, 69, 70, 71, 78, 79, 80]


# Check data core
def check_data_core(cursor, elements):
    numdic = init_numdict()
    zerovalue = 0
    largervalue = 0
    allerr = []
    for element in elements:
        if element == 0:
            zerovalue += 1
        else:
            numdic[element] += 1

        for key, val in numdic.items():
            if val == 0:
                allerr.append(str(cursor) + " part missing figure: " + str(key))
            elif val > 1:
                allerr.append(str(cursor) + " part have figure: " + str(key) + " with " + str(val))

    if zerovalue > 0:
        allerr.append(str(cursor) + " part found zero value: " + str(zerovalue))

    return allerr


def get_cell_index(rowidx, colidx):
    return rowidx * 9 + colidx

def from_cell_index(cellidx):
    return (int(cellidx / 9), int(cellidx % 9))


# Check data
def check_data():
    # General check
    errs = []
    cursor = 1

    # Check row
    for rowidx in range(9):
        row = get_row_element( rowidx )
        curerrs = check_data_core(cursor, row)
        errs.append(curerrs)
        cursor += 1


    # Check column
    cursor = 1
    for rowidx in range(9):
        curcol = get_column_element( rowidx )
        curerrs = check_data_core(cursor, row)
        errs.append(curerrs)
        cursor += 1

    # Check section
    cursor = 1
    for rowidx in range(9):
        curcol = get_section_element( rowidx )
        curerrs = check_data_core(cursor, row)
        errs.append(curerrs)
        cursor += 1

    for err in errs:
        print(err)


def get_possible_value(rowidx, colidx):
    defaultvalue = [1, 2, 3, 4, 5, 6, 7, 8, 9]

    # Row
    rowcursor = 0
    for elem in get_row_element(rowidx):
        if rowcursor != colidx and elem > 0 and elem in defaultvalue:
            defaultvalue.remove(elem)
            
        rowcursor += 1

    # Column
    colcursor = 0
    for elem in get_column_element(colidx):
        if colcursor != rowidx and elem > 0 and elem in defaultvalue:
            defaultvalue.remove(elem)
        
        colcursor += 1

    # Section
    sid, spos = get_section_index(rowidx, colidx)
    sectcursor = 0
    for elem in get_section_element(sid):
        if sectcursor != spos and elem > 0 and elem in defaultvalue:
            defaultvalue.remove(elem)
        sectcursor += 1

    return defaultvalue


def empty_item_count():
    itemcnt = 0
    for rowidx in range(9):
        for elem in get_row_element( rowidx ):
            if elem == 0:
                itemcnt += 1

    return itemcnt

def parse_data_core():
    elemdict.clear()

    # Level 1. parse all values
    rowcursor = 0
    for rowidx in range(9):
        rowelements = get_row_element( rowidx )
        colcursor = 0
        for colidx in range(9):
            cellidx = get_cell_index(rowcursor, colcursor)
            if alldata[rowidx][colidx] == 0:
                possbledata = get_possible_value(rowcursor, colcursor)
                elemdict[cellidx] = (0, possbledata)
            else:
                elemdict[cellidx] = (alldata[rowidx][colidx], [])
            colcursor += 1
        rowcursor += 1


    # Level 2. restrict values per section
    for sectid in range(9):
        sectnumdict = init_numdict()
        cellindexs = get_section_cellindexes(sectid)

           
        for cellidx in cellindexs:
            if elemdict[cellidx][0] != 0:
                del sectnumdict[elemdict[cellidx][0]]

        for miskey in sectnumdict.keys():
            ocurtimes = 0
            for cellidx in cellindexs:
                if elemdict[cellidx][0] == 0 and miskey in elemdict[cellidx][1]:
                    ocurtimes += 1
            if ocurtimes == 1:
                for cellidx in cellindexs:
                    if elemdict[cellidx][0] == 0 and miskey in elemdict[cellidx][1]:
                        elemdict[cellidx] = (0, [miskey])
    
    
    # Level 3 print data
    print("After ====================")    
    for key, val in elemdict.items():
        if val[0] == 0:
            rowidx, colidx = from_cell_index(key)
            print([rowidx, colidx], val[1])
            if len(val[1]) == 1:
                alldata[rowidx][colidx] = val[1][0]
        
    print(alldata)


def parse_data():
    emptyelements = empty_item_count()
    while emptyelements > 0:
        print("Empty Elements Left: ", emptyelements)
        parse_data_core()
        emptyelements = empty_item_count()



def process_file():
    inputfile = input("输入文件名：")
    with open(inputfile, "r", encoding = "utf-8") as file:
        for line in file:
            line = line.strip()
            curdata = []
            for dig in line:
                if len(curdata) < 9:
                    if dig.isdigit() == True:
                        dignum = int(dig)
                        if dignum == 0:
                            curdata.append(0)
                        elif dignum >= 1 and dignum <= 9:
                            curdata.append(dignum)
                    else:
                        curdata.append(0)
            if len(curdata) > 0:
                nline = []
                for i in range(9):
                    if len(curdata) > i:
                        nline.append(curdata[i])
                    else:
                        nline.append(0)
                alldata.append(nline)
    

def process_input():
    inputtedline = 1
    while inputtedline <= 9:
        inputhint = "Input element for " + str(inputtedline) + ": "
        curline = input(inputhint)
        if len(curline) == 0:
            curline = input(inputhint)
        else:
            curdata = []
            for dig in curline:
                if len(curdata) < 9:
                    if dig.isdigit() == True:
                        dignum = int(dig)
                        if dignum == 0:
                            curdata.append(0)
                        elif dignum >= 1 and dignum <= 9:
                            curdata.append(dignum)
                    else:
                        curdata.append(0)
            if len(curdata) > 0:
                nline = []
                for i in range(9):
                    if len(curdata) > i:
                        nline.append(curdata[i])
                    else:
                        nline.append(0)
                inputtedline += 1
                alldata.append(nline)
    
# Main program:
inputMethod = 0
while inputMethod != 1 and inputMethod != 2:
    inputMethod = int(input("选择处理模式(1  指定文件, 2  屏幕输入): "))

if inputMethod == 1:
    process_file()
elif inputMethod == 2:
    process_input()


print("Origin structure:")
print(alldata)

print("Step 1: Parsing")
#check_data()
parse_data()


print("Step 2: Figure out the error")
