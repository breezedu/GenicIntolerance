#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 16
#SBATCH --mem-per-cpu=8G
#SBATCH --ntasks=4
#SBATCH --job-name=0104AllGenePoReg40kIteration

R CMD BATCH ./0104_PoissonRegression_AllG40Kite.R