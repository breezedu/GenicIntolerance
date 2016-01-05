
import numpy as np
import pystan

##################################################################
## pystan code 
##################################################################

pyfit1code = """
data{   				//get the data we have

int <lower=0> J; 			//number of gene level 
int <lower=1,upper=J> gene[J];
vector[J] x;  				#x
int <lower=0> y[J] ; 			//y
}
parameters{ 				//specify the parameter we want to know 
vector[J] a;  				//random intercept when gene is the level 
real <lower=0> sigma_a;  		//variance of intercept

real beta;    				//common slope;
}
transformed parameters{ 		//specify the model we will use 
vector[J] lambda;
for (i in 1:J) 
     lambda[i] <- beta*x[i]+a[gene[i]];	//specify the group
}
model { 				//give the prior distribution
  beta ~ normal(0,10);
  a ~ normal(0, sigma_a);
  y ~ poisson_log(lambda); 		//y and y_hat should have same type 
}
"""


######################################
## data management
######################################
data=np.genfromtxt("sumtable.txt", delimiter=',',skip_header=1, dtype=None, names=("chr","gene","sumenvarp","sumenvarpfc"  ))
gene=range(1,len(data)+1)
x=data["sumenvarp"]
y=data["sumenvarpfc"]
N=len(data)
M1_table={'J': N,
               'X': x,
               'Y': y,
               'gene':gene
               }


#####################################
## fit data to pystan model
#####################################
fit1 = pystan.stan(model_code=pyfit1code, data=M1_table,
                  iter=40000, chains=4)
                  

result1=fit1.extract(permuted=True) 
a=result1["a"]
beta=result1["beta"]
sigma_a=result1["sigma_a"]
                  
print(beta)
                  
####################################
## End
####################################    