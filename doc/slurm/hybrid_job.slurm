#!/bin/bash
#----------------------------------------------------
# Example SLURM job script to run hybrid applications 
# (MPI/OpenMP or MPI/pthreads) on TACC's Stampede 
# system.
#----------------------------------------------------
#SBATCH -J hybrid_job     # Job name
#SBATCH -o hybrid_job.o%j # Name of stdout output file(%j expands to jobId) 
#SBATCH -e hybrid_job.o%j # Name of stderr output file(%j expands to jobId)
#SBATCH -p development    # Queue name
#SBATCH -N 2              # Total number of nodes requested (16 cores/node)
#SBATCH -n 4              # Total number of mpi tasks requested
#SBATCH -t 01:30:00       # Run time (hh:mm:ss) - 1.5 hours
# The next line is required if the user has more than one project
# #SBATCH -A A-yourproject  # <-- Allocation name to charge job against

# This example will run 4 MPI tasks on 2 nodes with each task 
# using 8 threads

# Set the number of threads per task(Default=1)
export OMP_NUM_THREADS=8

# Launch the MPI application using tacc_affinity to ensure proper 
# thread placement
ibrun tacc_affinity ./my_hybrid.exe

