#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 24
#SBATCH --mem-per-cpu=4G
#SBATCH --ntasks=6
#SBATCH --job-name=100KGenePoReg 

R CMD BATCH ./1113_PoissonRegression_100KGene4000ite.R