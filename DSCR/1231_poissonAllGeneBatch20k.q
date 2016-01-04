#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 16
#SBATCH --mem-per-cpu=8G
#SBATCH --ntasks=4
#SBATCH --job-name=AllGenePoReg20kIteration

R 
CMD BATCH ./1231_PoissonRegression_AllG20000ite.R

#10,000 iterations got rhat values 1.00-1.03, which seems ok.