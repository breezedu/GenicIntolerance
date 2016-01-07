#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=breeze.du@gmail.com
#SBATCH -c 16
#SBATCH --mem=2000G
#SBATCH --ntasks=8
#SBATCH --job-name=PyStanSavedModel

/dscrhome/gd44/jeffapps/bin/python2.7 0106_fullpystan_savedmodel.py

