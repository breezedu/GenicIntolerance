#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 12
#SBATCH --mem-per-cpu=8G
#SBATCH --nodes=4
#SBATCH --job-name=0327_1kG40kIteration

R CMD BATCH ./0327_PoissonRegression_1000G40k.R