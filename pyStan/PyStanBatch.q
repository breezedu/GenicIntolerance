#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=jeff.du@duke.edu
#SBATCH -c 4
#SBATCH --mem=20G
#SBATCH --ntasks=1
#SBATCH --job-name=PyStanSample

/dscrhome/gd44/jeffapps/bin/python2.7 PyStanSample.py