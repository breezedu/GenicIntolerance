#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 12
#SBATCH --mem-per-cpu=8G
#SBATCH --nodes=4
#SBATCH --job-name=0326_1kG100kIteration

R CMD BATCH ./0326_PoissonRegression_1000G100k.R