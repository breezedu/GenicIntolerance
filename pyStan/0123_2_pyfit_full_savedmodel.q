#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=breeze.du@gmail.com
#SBATCH -c 16
#SBATCH --mem-per-cpu=5G
#SBATCH --nodes=4
#SBATCH --job-name=PyStanSavedModel

/dscrhome/gd44/jeffapps/bin/python2.7 0121_pystan_AllExon_SavedModel.py