#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 12
#SBATCH --mem-per-cpu=5G
#SBATCH --ntasks=10
#SBATCH --job-name=AllGenePoReg100kIteration

R CMD BATCH ./1229_PoissonRegression_AllGene100kite.R