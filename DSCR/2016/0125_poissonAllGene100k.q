#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 16
#SBATCH --mem-per-cpu=8G
#SBATCH --ntasks=8
#SBATCH --job-name=0125_100kIteration

R CMD BATCH ./0125_PoissonRegression_AllG100k.R