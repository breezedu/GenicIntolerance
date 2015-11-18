#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH --mem=16G
#SBATCH -c 8
#SBATCH --job-name=poisson100GeneRegression

srun Rscript --vanilla BATCH ./1113_PoissonRegression_100Gene2000ite.R