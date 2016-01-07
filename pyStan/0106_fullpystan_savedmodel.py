# -*- coding: utf-8 -*-
# """
# Created on 01-06-2016
#
# @author: sz86
# @author: Jeff
# """

################################
## part one 
## import packages
################################

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


################################
## part three
## data management
################################
print("\n data management")

data=np.genfromtxt("fulldata.txt", delimiter=' ',skip_header=1, dtype=None, names=("chr","gene","envarp","envarpfc","index")
             )
## data = data[0:10000]

data1=np.genfromtxt("sumtable.txt", delimiter=',',skip_header=1, 
			dtype=None, names=("chr","gene","sumenvarp","sumenvarpfc")  
	 	)
## data1 = data1[0:6]


## parameters for stan model
N=len(data)		//number of exons
J=len(data1)		//number of genes
x=data['envarp']	//x	the x-value in RVIS plot
y=data["envarpfc"]	//y	the y-value in RVIS plot
gene=data["index"]	//the index of genes


## from the source data, we got arrays of x and y, they data type is string
## we have to transform x and y from string array to list of integers
x1 = list(map(int, x))
y1 = list(map(int, y))

x = x1
y = y1


################################
## part four
## Mtable
################################
Mtable={	'N':N,'J': J,
		'x': x,
		'y': y,
		'gene':gene
	}
####################################################




#############################################
## part five
## saving compiled models
#############################################
def save(obj, filename):
    """Save compiled models for reuse."""
    import pickle
    with open(filename, 'w') as f:
        pickle.dump(obj, f, protocol=pickle.HIGHEST_PROTOCOL)

def load(filename):
    """Reload compiled models for reuse."""
    import pickle
    return pickle.load(open(filename, 'r'))


############################################

model = pystan.StanModel(model_code = pyfitfull)
save(model, 'pyfitfull_model')

new_model = load('pyfitfull_model')



################################################
## part six
## fit stan model with saved pyfitfull_model
################################################

print("\n fit pystan model")

## fit=pystan.stan(model_code="pystancode.stan", data=Mtable, iter=1000, chains=4)
fit = new_model.sampling(Mtable, iter = 100000, chains=4)

##################################################
#extract the result
result=fit.extract(permuted=True) 
a=result["a"]
beta=result["beta"]
sigma_a=result["sigma_a"]
print(fit)



###############################################
## plot
#############################################
print("plot fit figure")

fit.plot()
mp.savefig("0106_PyStan_Exon_10K_Plot.png")


################################
## End
################################