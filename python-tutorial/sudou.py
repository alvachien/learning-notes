import sudoucore

origindata = []

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
                origindata.append(nline)
    

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
                origindata.append(nline)
    
# Main program:
inputMethod = 0
while inputMethod != 1 and inputMethod != 2:
    inputMethod = int(input("选择处理模式(1  指定文件, 2  屏幕输入): "))

if inputMethod == 1:
    process_file()
elif inputMethod == 2:
    process_input()


print("原始输入:")
for originline in origindata:
    print(originline)

print("1. 试图解析")
score = sudoucore.SudouCore()
score.currentdata = origindata

score.workout_solution()

print("2: 校验结果")

