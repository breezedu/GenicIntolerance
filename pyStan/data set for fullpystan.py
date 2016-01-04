# -*- coding: utf-8 -*-
"""
Created on Tue Dec 29 22:57:45 2015

@author: sz86
"""

import numpy as np
data=np.genfromtxt("C:/Users/sz86/Desktop/Andrew/data/fulldata.txt", delimiter=' ',skiprows=1, dtype=None, names=("chr","gene","envarp","envarpfc","index")
             )
data1=np.genfromtxt("C:/Users/sz86/Desktop/Andrew/data/sumtable.txt", delimiter=',',skiprows=1, dtype=None, names=("chr","gene","sumenvarp","sumenvarpfc"
             )  )
N=len(data)
J=len(data1)
x=data['envarp']
y=data["envarpfc"]
gene=data["index"]
Mtable={'N':N,'J': J,
               'X': x,
               'Y': y,
               'gene':gene
               }
               
fit=pystan.stan(file="pyfitfull", data=Mtable,
                  iter=100000, chains=4)
                  
#extract the result
result=fit.extract(permuted=True) 
a=result["a"]
beta=result["beta"]
sigma_a=result["sigma_a"]