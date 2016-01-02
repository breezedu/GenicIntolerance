#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 16
#SBATCH --mem-per-cpu=8G
#SBATCH --ntasks=1
#SBATCH --job-name=AllGenePoReg1kIteration

R CMD BATCH ./0101_PoissonRegression_5000G1000ite.R