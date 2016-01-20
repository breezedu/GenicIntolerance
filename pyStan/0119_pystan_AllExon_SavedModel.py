##############################################
# 0107_pystan_AllExon_SavedModel.py
# -*- coding: utf-8 -*-
# """
# Updated on 01-16-2016
#
# @author: ShuaiQi
# @author: Jeff
# """

## Aim: Pystan is a python package for bayesian statistics
## Here we use PyStan to simulate a Poisson Regresson Model of all Exons from intolerance genes.
## To see whether there's a model (pattern) of gene intolerance in human genomes.
## data source: exon_level_process_v2.txt
## fulldata.txt represents all exons subsetted from the exon_level_process_v2.txt doc
## sumtable.txt represents all genes from the exon_level_process_v2.txt doc
## these txt documents have to be in the same fold of this python script, otherwise the code would not compile
## 
## Pipeline:
## Part 1, import necessary packages
## 
## Part 2, Create Stan Model: 
## J the number of exons; 
## N the number of genes; 
## gene[] a list of gene-series number;
## x[] a list of x-value in RVIS; 
## y[] a list of y-value in RVIS;
##
## Part 3, Data Management
## get all parameters for Stan Model: J, N, gene, x, y;
## 
## Part 4, Create a data table Mtable for Stan model
## the table was assembled by N, J, x, y, and gene;
## 
## Part 5, Save empty compiled models
## This will save some time and memory
## 
## Part 6, Fit stan model with saved model in Part-5
## Also, pass the data table generated in Part-4
## 
## Final Part: print stan model result, the Beta value and rhat, 
## if the rhat value==1, that means the simulation convergenced well, then the model is reliable, 
## the Beta value is useful for later use.
## Plot the fit result, to see the distribution of all chains.



####################################################
## part one 
## import packages
####################################################

import matplotlib
matplotlib.use('Agg')

import os
import sys
import glob

import pystan
import numpy as np
import matplotlib.pyplot as mp



##############################################
## part two
## pystan code
##############################################

pyfitfull = """
data {
    int<lower=0> J; 			// number of obs
    int<lower=0> N; 			// number of gene level
    int<lower=1, upper=J> gene[N]; 	// number of obs
    int<lower=0> x[N]; 			// estimated treatment effects
    int<lower=0> y[N]; 			// s.e. of effect estimates
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


####################################################
## part three
## data management
####################################################
print("\n data management")

data=np.genfromtxt("fulldata.txt", delimiter=' ',skip_header=1, dtype=None, names=("chr","gene","envarp","envarpfc","index")
             )
## data = data[0:10000]

data1=np.genfromtxt("sumtable.txt", delimiter=',',skip_header=1, 
			dtype=None, names=("chr","gene","sumenvarp","sumenvarpfc")  
	 	)
## data1 = data1[0:6]


## parameters for stan model
N=len(data)		#number of exons
J=len(data1)		#number of genes
x=data['envarp']	#x	the x-value in RVIS plot
y=data["envarpfc"]	#y	the y-value in RVIS plot
gene=data["index"]	#the index of genes


## from the source data, we got arrays of x and y, they data type is string
## we have to transform x and y from string array to list of integers
x1 = list(map(int, x))
y1 = list(map(int, y))

x = x1
y = y1


####################################################
## part four
## Mtable
####################################################

Mtable={	'N':N,'J': J,
		'x': x,
		'y': y,
		'gene':gene
	}
####################################################




####################################################
## part five
## saving compiled models
####################################################
def save(obj, filename):
    """Save compiled models for reuse."""
    import pickle
    with open(filename, 'w') as f:
        pickle.dump(obj, f, protocol=pickle.HIGHEST_PROTOCOL)

def load(filename):
    """Reload compiled models for reuse."""
    import pickle
    return pickle.load(open(filename, 'r'))


####################################################

model = pystan.StanModel(model_code = pyfitfull)
save(model, 'pyfitfull_model')

new_model = load('pyfitfull_model')




####################################################
## part six
## fit stan model with saved pyfitfull_model
####################################################

print("\n fit pystan model")

## fit=pystan.stan(model_code="pystancode.stan", data=Mtable, iter=1000, chains=4)
fit = new_model.sampling(Mtable, warmup= 2000, iter = 2500, chains=4)

####################################################
#extract the result
#result=fit.extract(permuted=True) 
#a=result["a"]
#beta=result["beta"]
#sigma_a=result["sigma_a"]
print(fit)



###################################################
## Final Part (7)
## plot the fit results
###################################################
print("plot fit figure")

fit.plot()
mp.savefig("0119_PyStan_Exon_Plot.png")


####################################################
## End
####################################################