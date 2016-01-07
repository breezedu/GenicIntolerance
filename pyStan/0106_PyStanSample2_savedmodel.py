## from __future__ import division
import matplotlib
matplotlib.use('Agg')


import os
import sys
import glob


import pystan
import numpy as np
#from pystan.external.pymc.plots import traceplot
import matplotlib.pyplot as plt
import pandas as pd

plt.style.use('ggplot')

np.random.seed(1234)

#import matplotlib
import matplotlib.pyplot as mp


############################################################

print("\n\n the second part, Log data")

# observed data
df = pd.read_csv('HtWt.csv', error_bad_lines=False)
df.head()

log_reg_code = """
data {
    int<lower=0> n;
    int male[n];
    real weight[n];
    real height[n];
}
transformed data {}
parameters {
    real a;
    real b;
    real c;
}
transformed parameters {}
model {
    a ~ normal(0, 10);
    b ~ normal(0, 10);
    c ~ normal(0, 10);
    for(i in 1:n) {
        male[i] ~ bernoulli(inv_logit(a*weight[i] + b*height[i] + c));
  }
}
generated quantities {}
"""

log_reg_dat = {
             'n': len(df),
             'male': df.male,
             'height': df.height,
             'weight': df.weight
            }


#############################################
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

model = pystan.StanModel(model_code = log_reg_code)
save(model, 'log_reg_model')

new_model = load('log_reg_model')



########################################
## fit new model with saved model
#############
fit_log = new_model.sampling(log_reg_dat, iter=100000, chains=4)

## fit_log = pystan.stan(model_code="pystancode.stan", data=log_reg_dat, iter=20000, chains=4)

print(fit_log)

#eta = fit_log.extract(permuted=True)['eta']
#np.mean(eta, axis=0)

# if matplotlib is installed (optional, not required), a visual summary and
# traceplot are available

print("plot log figure")

fit_log.plot()
mp.savefig("0106_StanPlot_log.png")
