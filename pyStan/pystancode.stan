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