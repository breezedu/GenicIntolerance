#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 16
#SBATCH --mem-per-cpu=4G
#SBATCH --ntasks=8
#SBATCH --job-name=AllGenePoReg 

R CMD BATCH ./1114_PoissonRegression_AllGene4000ite.R