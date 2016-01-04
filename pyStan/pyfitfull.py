# -*- coding: utf-8 -*-
"""
Created on Wed Dec 30 21:37:27 2015

@author: sz86
"""

pyfitfull = """
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
    real beta;
   
}
transformed parameters {
    row_vector[N] lambda[J];
    for (i in 1:N)
	lambda[i] <- beta*x[i] + a[gene[i]]
}
model {
	
	beta ~ normal(0, 10);
	a ~ normal(0, sigma_a);
	y ~ poisson_log(lambda); 
}
"""