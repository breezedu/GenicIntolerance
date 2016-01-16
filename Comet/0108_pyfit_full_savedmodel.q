#!/bin/sh

#SBATCH -A TG-DMS160001
#SBATCH --mail-type=ALL
#SBATCH --mail-user=breeze.du@gmail.com
#SBATCH -p compute
#SBATCH -c 24
#SBATCH --mem-per-cpu=5G
#SBATCH --ntasks=20
#SBATCH --job-name=PyStanSavedModel
#SBATCH -t 48:00:00

module load python
python2.7 0108_pystan_AllExon_SavedModel.py