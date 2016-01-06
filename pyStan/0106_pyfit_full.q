#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 16
#SBATCH --mem=80G
#SBATCH --ntasks=1
#SBATCH --job-name=PyStanFull

/dscrhome/gd44/jeffapps/bin/python2.7 dataset_for_fullpystan.py