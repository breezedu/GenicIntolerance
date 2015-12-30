#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 10
#SBATCH --mem-per-cpu=5G
#SBATCH --ntasks=10
#SBATCH --job-name=AllGenePoReg 

R CMD BATCH ./1125_PoissonRegression_AllGene200ite.R