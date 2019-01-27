# Installation Instructions

## Retrieve the dataset

  1. Initialize the dataset as a submodule
    - `git submodule init`
  2. Update the dataset contents
    - `git submodule update`

After the execution of these commands, you will have access to the sample dataset in the `dataset` directory. The `solution` directory contains acceptable solution for the _APB_ algorithm.

## Build the LAAAARGE dataset

  1. Concatenate all the chunks into a single file
    - `cat sample_1_000_000.txt.bz2.a* > sample_1_000_000.txt.bz2`
  2. Deflate the archive
    - `bzip2 -d sample_1_000_000.txt.bz2`

You now have a dataset of 1 million students (324M), named `sample_1_000_000.txt`
