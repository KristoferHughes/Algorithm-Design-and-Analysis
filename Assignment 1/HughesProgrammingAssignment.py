#Kristofer Hughes
#Design/Analysis of Algorithms
#Programming Assignment
#Oct 27, 2019

import math
from operator import itemgetter


def begin(file):
    
    
    with open(file, 'r') as f:
        data = f.readlines()
        
        coordinates = []        
        
        for line in data:
            temp = line.split()
            temp = [int(val) for val in temp] 
            temp = tuple(temp)
            coordinates.append(temp)
        
        return coordinates
    
def step1(file, answer, pairs):
    
    
    with open(file, 'a+') as f:
        f.write(pairs + ' test file:\n\n')
        
        minDst = step2(answer[0], answer[1])
        f.write('The minimum distance is:\n')
        
        f.write(str(minDst) + ': ' 
                + str(answer[0]) + '<--->' + str(answer[1]) + '\n\n\n')

def step2(pointA, pointB):
    
    return math.sqrt((pointB[0]-pointA[0])**2 + (pointB[1]-pointA[1])**2)
    
def step3(S):
    
    minimumDistance = step2(S[0], S[1])
    closestPair = []
    
    for i in range(0, len(S)-1):
        for j in range(i+1, len(S)):
            tempDstance = step2(S[i], S[j])
            
            if tempDstance <= minimumDistance:
                minimumDistance = tempDstance
                closestPair = [S[i], S[j]]
    
    return closestPair

def step4(S, D):
      
    leftA = []
    rightA = []
    
    for point in S:
        if point[0] <= D:
            leftA.append(point)
        
        if point[0] >= D:
            rightA.append(point)  
    
    return [leftA, rightA]
    
     
def executeProgram(S, X, Y):
    
    if len(S) <= 3:
        return step3(S)
    
    if len(X) % 2 == 0:
        midpointA = X[int((len(X)/2)-1)]
        midpointB = X[int(len(X)/2)]
        
        D = (midpointA[0] + midpointB[0]) / 2
    
    else:
        middlePoint = X[int(len(X)/2)+1]
        
        D = middlePoint[0]
    
    Ssplit = step4(S, D)
    leftA = Ssplit[0]
    rightA = Ssplit[1]
    
    
    
    Xsplit = step4(X, D)
    Xleft = Xsplit[0]
    Xright = Xsplit[1]
    
    Ysplit = step4(Y, D)
    Yleft = Ysplit[0]
    Yright = Ysplit[1]
    
    PlQl = executeProgram(leftA, Xleft, Yleft)
    PrQr = executeProgram(rightA, Xright, Yright)
      
    leftA = step2(PlQl[0], PlQl[1])
    rightA = step2(PrQr[0], PrQr[1])
    
    if leftA < rightA:
        delta = leftA
        
    else:
        delta = rightA
    
    middle = []
    
    for i in Y:
        if (D - delta) <= i[0] and i[0] <= (D + delta):
            middle.append(i)
    
    if len(middle) >= 2:
        minDst = step2(middle[0], middle[1])
        pairofMins = [middle[0], middle[1]]
        
        for i in range(0, len(middle)-1):
            if (i+7) >= len(middle):
                pair = step3(middle[i:(i+8)])
                tempDst = step2(pair[0], pair[1])
                
                if  tempDst < minDst:
                    minDst = tempDst
                    pairofMins = pair
                           
            else:
                pair = step3(middle[i: len(middle)])
                tempDst = step2(pair[0], pair[1])
                
                if  tempDst < minDst:
                    minDst = tempDst
                    pairofMins = pair            
        
        lastmid = pairofMins
        minimumPairs = minimumPairs = [PlQl, PrQr, lastmid]
        
    else: 
        minimumPairs = [PlQl, PrQr]
    
    
    minDst = step2(PlQl[0], PlQl[1])
    pairofMins = PlQl
    
    for pair in minimumPairs[1:len(minimumPairs)]:
        tempDst = step2(pair[0], pair[1])
        
        if tempDst < minDst:
            minDst = tempDst
            pairofMins = pair
        
    
    return pairofMins

S = begin('10points.txt')

X = sorted(S, key=itemgetter(0))
Y = sorted(S, key=itemgetter(1))

answer = executeProgram(S, X, Y)


step1('HughesCSCSolutions.txt.txt', answer, '10 points')


print('10 points file:\n')

minDst = step2(answer[0], answer[1])
print('The minimum distance is:')

print(str(minDst) + ': ' + str(answer[0]) 
      + '<--->' + str(answer[1]) + '\n\n')


S = begin('100points.txt')

X = sorted(S, key=itemgetter(0))
Y = sorted(S, key=itemgetter(1))


answer = executeProgram(S, X, Y)

step1('HughesCSCSolutions.txt', answer, '100 points')


print('100 points file:\n')

minDst = step2(answer[0], answer[1])
print('The minimum distance is:')

print(str(minDst) + ': ' + str(answer[0]) 
      + '<--->' + str(answer[1]) + '\n\n')


S = begin('1000points.txt')

X = sorted(S, key=itemgetter(0))
Y = sorted(S, key=itemgetter(1))


answer = executeProgram(S, X, Y)

step1('HughesCSCSolutions.txt.txt', answer, '1000 points')

print('1000 points file:\n')

minDst = step2(answer[0], answer[1])
print('The minimum distance is:')

print(str(minDst) + ': ' + str(answer[0]) 
      + '<--->' + str(answer[1]) + '\n\n')

  