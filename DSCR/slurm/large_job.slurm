#!/bin/bash
#----------------------------------------------------
# Example SLURM job script to run large MPI 
# applications in the large queue on TACC's Stampede 
# system.
#----------------------------------------------------
#SBATCH -J large_MPI     # Job name
#SBATCH -o large_MPI.o%j # Name of stdout output file(%j expands to jobId) 
#SBATCH -e large_MPI.o%j # Name of stderr output file(%j expands to jobId)
#SBATCH -p large         # large queue for jobs > 256 nodes
#SBATCH -N 512           # Total number of nodes requested (16 cores/node)
#SBATCH -n 8192          # Total number of mpi tasks requested
#SBATCH -t 01:30:00      # Run time (hh:mm:ss) - 1.5 hours
#The next line is required if the user has more than one project
# #SBATCH -A A-yourproject  # <-- Allocation name to charge job against

# Launch the MPI application named "my_large.exe"
ibrun ./my_large.exe

