#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 12
#SBATCH --mem-per-cpu=5G
#SBATCH --ntasks=1
#SBATCH --job-name=AllGenePoReg400Iteration

R CMD BATCH ./1231_PoissonRegression_AllGene100ite.R