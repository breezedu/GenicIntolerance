#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 24
#SBATCH --mem-per-cpu=5G
#SBATCH --ntasks=4
#SBATCH --job-name=AllGenePoReg 

R CMD BATCH ./1124_PoissonRegression_AllGene200ite.R