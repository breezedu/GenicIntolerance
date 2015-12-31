#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 12
#SBATCH --mem-per-cpu=8G
#SBATCH --ntasks=4
#SBATCH --job-name=AllGenePoReg100kIteration

R CMD BATCH ./1230_PoissonRegression_AllG4000ite.R