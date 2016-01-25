0121_pyfitsumcode = """
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