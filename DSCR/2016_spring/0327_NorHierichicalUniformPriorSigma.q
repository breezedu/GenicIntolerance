#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 12
#SBATCH --mem-per-cpu=8G
#SBATCH --nodes=4
#SBATCH --job-name=HierLasso40k

R CMD BATCH ./0327_hier_normal_LASSO40k.R