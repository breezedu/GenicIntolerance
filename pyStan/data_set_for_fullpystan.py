# -*- coding: utf-8 -*-
#"""
#Created on Tue Dec 29 22:57:45 2015
#
#@author: sz86
#"""
import matplotlib
matplotlib.use('Agg')
import pystan
import numpy as np
import matplotlib.pyplot as mp


##############
## pystan code
##############

pyfitfull = """
data {
    int<lower=0> J; // number of obs
    int<lower=0> N; // number of gene level
    int<lower=1, upper=J> gene[N]; // number of obs
    int<lower=0> x[N]; // estimated treatment effects
    int<lower=0> y[N]; // s.e. of effect estimates
}
parameters {
    vector[J] a;
    real<lower=0> sigma_a;
    real beta;
   
}
transformed parameters {
    vector[N] lambda;
    for (i in 1:N)
	lambda[i] <- beta*x[i] + a[gene[i]];
}
model {
	
	beta ~ normal(0, 10);
	a ~ normal(0, sigma_a);
	y ~ poisson_log(lambda); 
}
"""

## data management
data=np.genfromtxt("fulldata.txt", delimiter=' ',skip_header=1, dtype=None, names=("chr","gene","envarp","envarpfc","index")
             )
data = data[0:100]

data1=np.genfromtxt("sumtable.txt", delimiter=',',skip_header=1, 
			dtype=None, names=("chr","gene","sumenvarp","sumenvarpfc")  
	 	)
data1 = data1[0:6]

## parameters
N=len(data)
J=max(data["index"])
x=data['envarp']
y=data["envarpfc"]
gene=data["index"]

## transform from array to list

x1 = list(map(int, x))
y1 = list(map(int, y))

x = x1
y = y1


##Mtable
Mtable={'N':N,'J': J,
               'x': x,
               'y': y,
               'gene':gene
               }
               
fit=pystan.stan(model_code=pyfitfull, data=Mtable,
                  iter=10000, chains=4)
                  
#extract the result
result=fit.extract(permuted=True) 
a=result["a"]
beta=result["beta"]
sigma_a=result["sigma_a"]
print(fit)


###############
## plot
#############

fit.plot()
mp.savefig("0106StanPlot.png")
