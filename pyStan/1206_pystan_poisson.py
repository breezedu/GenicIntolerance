import matplotlib
matplotlib.use('Agg')
import pystan
import numpy as np
#from pystan.external.pymc.plots import traceplot
import matplotlib.pyplot as mp

import pandas as pd

#import matplotlib
#import matplotlib.pyplot

stan_code = """
data {
    int<lower=0> J; // number of obs
    int<lower=0> N; // number of gene level
    int<lower=1, upper=J> gene[N]; // number of obs

    vector[N] x[N]; // estimated treatment effects
    int<lower=0> sigma[N]; // s.e. of effect estimates
}

parameters {
    vector[J] a;
    real<lower=0> sigma_a;
    real<lower=0> sigma_epsilon;
    real beta;
    vector[N] epsilon_raw[J];
}
transformed parameters {
    vector[N] lambda[J];
    vector[N] epsilon[J];
    for (i in 1:N)
    	epsilon[i] = sigma_epsilon * epsilon_raw[i];
    for (i in 1:N)
	lambda[i] = beta*x[i] + a[gene[i]] + epsilon[i]; 
}

model {
	
	beta ~ normal(0, 10);
	a ~ normal(0, sigma_a);
	epsilon_raw ~ normal(0, 1);
	y ~ poisson_log(lambda); 
}
"""

######################
# init the parameters
######################
#N<-dim(table)[1]
#J<-dim(table1)[1]
#gene<-as.numeric(table$gene)
#genelevel<-length(unique(gene))
#index<-match(gene, unique(gene)) 
#M1_table<-list(N=N, J=J, y=table$envarpfc,
#x=table$envarp,gene=index)
################################################


## Have to read data from txt document
## These data were outputed from R code

table = pd.read_csv('exon_table.csv')
table1 = pd.read_csv('exon_table1.csv')
index_source = pd.read_csv('index.csv')
M1_table_source = pd.read_csv('M1_table.csv')

## get unique list of gene:
J = len(table)
N = len(table1)
J
N

geneList = table.gene
uniGene = set(geneList)
uniGene


exon_dat = {
	
	'J': len(table),
	'N': len(table1),
	'gene': table.gene,
	
	'genelevel': len(uniGene),
	     	
	'index': index_source,     ###match(gene, uniGene),	### match is not defined here

	'M1_table': M1_table_source,   ###list(N=N, J=J, y = table.envarpfc, x= table.envarp, gene=index),          ###	

	}


fit = pystan.stan(model_code=stan_code, data=exon_dat,
                  iter=1000, chains=4)

print(fit)

eta = fit.extract(permuted=True)['eta']
np.mean(eta, axis=0)

# if matplotlib is installed (optional, not required), a visual summary and
# traceplot are available

print("plot figure")

fit.plot()
mp.savefig("StanPlot.png")


#######################################
##  END
#######################################