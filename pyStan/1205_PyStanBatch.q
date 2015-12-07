#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 16
#SBATCH --mem=64G
#SBATCH --ntasks=1
#SBATCH --job-name=PyStanSample

/dscrhome/gd44/jeffapps/bin/python2.7 1206_pystan_poisson.py

#R CMD BATCH ./1205_PoissonRegression_1000Gene2000ite.R