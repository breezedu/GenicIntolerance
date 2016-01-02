#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 16
#SBATCH --mem-per-cpu=5G
#SBATCH --ntasks=8
#SBATCH --job-name=0102AllGenePoReg40kIteration

R CMD BATCH ./0102_PoissonRegression_AllG40Kite.R