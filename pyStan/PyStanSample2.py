from __future__ import division
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
#import matplotlib.pyplot


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

fit_log = pystan.stan(model_code=log_reg_code, data=log_reg_dat, iter=2000, chains=4)

print(fit_log)

#eta = fit_log.extract(permuted=True)['eta']
#np.mean(eta, axis=0)

# if matplotlib is installed (optional, not required), a visual summary and
# traceplot are available

print("plot log figure")

fit_log.plot()
mp.savefig("StanPlot_log.png")
