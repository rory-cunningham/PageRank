package com.rory.pagerank;

public interface SparseMatrix {
    public abstract int getNumVertices();

    public abstract int getNumEdges();

    public abstract void calculateOutDegree( int outdeg[]);

    public abstract void edgemap( Relax relax);

    public abstract void rangedEdgemap(Relax relax, int from, int to);
}
