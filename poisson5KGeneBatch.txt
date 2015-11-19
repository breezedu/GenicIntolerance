#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 20
#SBATCH --mem=128G
#SBATCH --ntasks=5
#SBATCH --job-name=100KGenePoReg 

R CMD BATCH ./1113_PoissonRegression_1000Gene2000ite.R