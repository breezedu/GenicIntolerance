# -*- coding: utf-8 -*-
"""
Created on Tue Dec 29 17:06:11 2015

@author: sz86
"""

import numpy as np
import pystan

pyfitfull = """
data{   
	int <lower=0> J; 		//number of gene level 
	int <lower=1,upper=J> gene[J];
	vector[J] x;  			#x
	int <lower=0> y[J] ; 		//y
}
parameters{ 
	vector[J] a;  			//random intercept when gene is the level 
	real <lower=0> sigma_a;  	//variance of intercept
	real beta_0;               	//the fixed intercept so that the mean of a can be 0
	real beta;    			//common slope;
}
transformed parameters{ 		
	vector[J] lambda;
	for (i in 1:J) 
     		lambda[i] <- beta_0+beta*x[i]+a[gene[i]];	//specify the group
}
model { 	
  	beta ~ normal(0,10);
  	beta_0 ~ normal(0,5);
  	a ~ normal(0, sigma_a);
  	y ~ poisson_log(lambda); 	//y and y_hat should have same type 
}
"""

data=np.genfromtxt("sumtable.txt", delimiter=',',skip_header=1, dtype=None, names=("chr","gene","sumenvarp","sumenvarpfc"  ))
gene=range(1,len(data)+1)
x=data["sumenvarp"]
y=data["sumenvarpfc"]
N=len(data)

M1_table={	'J': N,
               	'x': x,
               	'y': y,
               	'gene':gene
	}



fit1 = pystan.stan(model_code=pyfitfull, data=M1_table,
                  iter=400, chains=4)

print(fit1)

## Plot
plot(pars=["beta","beta_0","sigma_a"])     #plot the density function of paraments
             
result1=fit1.extract(permuted=True) 
a=result1["a"]
beta=result1["beta"]
sigma_a=result1["sigma_a"]
                  
                  
