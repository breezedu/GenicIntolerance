#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 16
#SBATCH --mem-per-cpu=8G
#SBATCH --nodes=6
#SBATCH --job-name=0131_80kIteration

R CMD BATCH ./0131_PoissonRegression_AllG100k.R