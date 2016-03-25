#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 12
#SBATCH --mem-per-cpu=8G
#SBATCH --nodes=2
#SBATCH --job-name=0325_1kG100kIteration

R CMD BATCH ./0325_PoissonRegression_1000G100k.R