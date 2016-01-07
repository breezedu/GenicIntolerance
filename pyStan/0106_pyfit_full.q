#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 16
#SBATCH --mem=80G
#SBATCH --ntasks=1
<<<<<<< HEAD
#SBATCH --job-name=PyStanFull

/dscrhome/gd44/jeffapps/bin/python2.7 dataset_for_fullpystan.py
=======
#SBATCH --job-name=PyStanSum

/dscrhome/gd44/jeffapps/bin/python2.7 dataset_for_simpystan.py

#R CMD BATCH ./1205_PoissonRegression_1000Gene2000ite.R
>>>>>>> 48cc792e1e8140ab7ceaab69601a9d9fad0822eb
