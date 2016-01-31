#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 16
#SBATCH --mem-per-cpu=8G
#SBATCH --nodes=4
#SBATCH --job-name=0130_40kIteration

R CMD BATCH ./0130_PoissonRegression_AllG40k.R