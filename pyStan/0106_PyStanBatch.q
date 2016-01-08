#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 4
#SBATCH --mem=20G
#SBATCH --ntasks=1
#SBATCH --job-name=PyStanSample

/dscrhome/gd44/jeffapps/bin/python2.7 PyStanSample.py

#R CMD BATCH ./1113_PoissonRegression_1000Gene2000ite.R