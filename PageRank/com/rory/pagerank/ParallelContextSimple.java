package com.rory.pagerank;

public class ParallelContextSimple extends ParallelContext {
    private class ThreadSimple extends Thread {

        public void run() {
        }
    };

    public ParallelContextSimple( int num_threads_ ) {
        super( num_threads_ );
    }

    public void terminate() { }

    // The edgemap method for Q3 should create threads, which each process
    // one graph partition, then wait for them to complete.
    public void edgemap( SparseMatrix matrix, Relax relax ) {
        // use matrix.edgemap( relax, from, to ); in each thread
    }
}
