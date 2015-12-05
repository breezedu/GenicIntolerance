import matplotlib
matplotlib.use('Agg')
import pystan
import numpy as np
#from pystan.external.pymc.plots import traceplot
import matplotlib.pyplot as mp

#import matplotlib
#import matplotlib.pyplot

stan_code = """
data {
    int<lower=0> J; // number of obs
    int<lower=0> N; // number of gene level
    int<lower=1, upper=J> gene[N]; // number of obs

    row_vector[N] x[J]; // estimated treatment effects
    int<lower=0> sigma[N]; // s.e. of effect estimates
}

parameters {
    row_vector[J] a;
    real<lower=0> sigma_a;
    real<lower=0> sigma_epsilon;
    real beta;
    row_vector[N] epsilon_raw[J];
}
transformed parameters {
    row_vector[N] lambda[J];
    row_vector[N] epsilon[J];
    for (i in 1:N)
    	epsilon[i] <- sigma_epsilon * epsilon_raw[i];
    for (i in 1:N)
	lambda[i] <- beta*x[i] + a[gene[i]] + epsilon[i]; 
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
N<-dim(table)[1]
J<-dim(table1)[1]
gene<-as.numeric(table$gene)
genelevel<-length(unique(gene))
index<-match(gene, unique(gene)) 
M1_table<-list(N=N, J=J, y=table$envarpfc,
x=table$envarp,gene=index)
################################################


## Have to read data from txt document:
exon_dat = {'J': 8,
               'y': [28,  8, -3,  7, -1,  1, 18, 12],
               'sigma': [15, 10, 16, 11,  9, 11, 10, 18]}

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

#mp.figure(figsize=(8,6))
#mp.plot(fit)
#mp.savefig('fit.png')
#mp.close()

#mp.plot(fit)
#mp.show()

#traceplot(fit)

