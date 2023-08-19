# PageRank
This directory holds code files for the graph processing problem. It implements routines to solve the PageRank problem and the Connected Components problem. The latter is valid only for undirected graphs (a.k.a simple graphs).

The code is complete except for the implementation of the data structure that represents the graphs. Three variants are provided and can be selected at runtime: the Coordinate formate (COO), the Compressed Sparse Rows format (CSR) and the Compressed Sparse Columsn format (CSC).

Graph data sets are available from http://www.eeecs.qub.ac.uk/~H.Vandierendonck/CSC3021/graphs/.

The programs can be called using the driver: DriverA2.java. The driver requires command-line arguments to specify what to do (the problem: PageRank; the graph data structure; number of threads to use).

Command line arguments are easily specified when running the programs on the command line.

IDEs also allow to set the programs command-line arguments, which requires a sequence of GUI actions that is more cumbersome.

The command line arguments are as follows:

When compiling the DriverA3 file % javac DriverA3.java % java Driver Usage: java Driver inputfile-COO inputfile-CSR inputfile-CSC algorithm num-threads outputfile % java Driver /path/to/graph.COO /path/to/graph.CSR /path/to/graph.CSC (pr|cc) 8 /path/to/outputfile.txt
