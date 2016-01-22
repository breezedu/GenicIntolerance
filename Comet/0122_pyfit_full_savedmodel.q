#!/bin/sh

#SBATCH --mail-type=ALL
#SBATCH --mail-user=breeze.du@gmail.com
#SBATCH -A TG-BIO160003
#SBATCH -p compute
#SBATCH -c 16
#SBATCH --mem-per-cpu=5G
#SBATCH --ntasks=4
#SBATCH --job-name=PyStanSavedModel
#SBATCH -t 48:00:00

module load python
python 0122_pystan_AllExon_SavedModel.py