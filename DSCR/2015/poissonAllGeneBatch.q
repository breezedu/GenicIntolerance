#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 24
#SBATCH --mem-per-cpu=4G
#SBATCH --ntasks=5
#SBATCH --job-name=AllGenePoReg 

R CMD BATCH ./1121_PoissonRegression_AllGene2000ite.R
