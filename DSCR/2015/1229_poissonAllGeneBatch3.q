#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 12
#SBATCH --mem-per-cpu=5G
#SBATCH --ntasks=4
#SBATCH --job-name=AllGenePoReg1229

R CMD BATCH ./1125_PoissonRegression_AllGene200ite.R