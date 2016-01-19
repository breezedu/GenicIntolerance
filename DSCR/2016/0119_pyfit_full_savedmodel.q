#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=breeze.du@gmail.com
#SBATCH -A TG-DMS160001
#SBATCH -p compute
#SBATCH -c 24
#SBATCH --mem-per-cpu=5G
#SBATCH --ntasks=8
#SBATCH --job-name=PyStanSavedModel
#SBATCH -t 48:00:00

module load python
python 0119_pystan_AllExon_SavedModel.py