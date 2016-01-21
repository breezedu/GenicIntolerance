# -*- coding: utf-8 -*-
"""
Created on Tue Dec 29 17:06:11 2015

@author: sz86
"""

import numpy as np

data=np.genfromtxt("C:/Users/sz86/Desktop/Andrew/data/sumtable.txt", delimiter=',',skiprows=1, dtype=None, names=("chr","gene","sumenvarp","sumenvarpfc"  ))
gene=range(1,len(data)+1)
x=data["sumenvarp"]
y=data["sumenvarpfc"]
N=len(data)
M1_table={'J': N,
               'X': x,
               'Y': y,
               'gene':gene
               }

fit1 = pystan.stan(file="pyfitsumcode", data=M1_table,
                  iter=40000, chains=4)
plot(pars=["beta","beta_0","sigma_a"])     #plot the density function of paraments             
result1=fit1.extract(permuted=True) 
a=result1["a"]
beta=result1["beta"]
sigma_a=result1["sigma_a"]
                  
                  
