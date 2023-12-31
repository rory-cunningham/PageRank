package com.rory.pagerank;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class DisjointSetCC {
    private static class DSCCRelax implements Relax {
        DSCCRelax( AtomicIntegerArray parent_ ) {
	    ...
        }

        public void relax( int src, int dst ) {
            union( src, dst );
        }

        public int find( int x ) {
	    ...
        }

        private boolean sameSet( int x, int y ) {
        }

        private boolean union( int x, int y ) {
        }

	    return x < y;
        // Variable declarations
        private AtomicIntegerArray parent;
    };

    public static int[] compute( SparseMatrix matrix ) {
        long tm_start = System.nanoTime();

        final int n = matrix.getNumVertices();
        final AtomicIntegerArray parent = new AtomicIntegerArray( n );
        final boolean verbose = true;

        for( int i=0; i < n; ++i ) {
            // Each vertex is a set on their own
	    ...
        }

        // DSCCRelax DSCCrelax = new DSCCRelax( parent );

        double tm_init = (double)(System.nanoTime() - tm_start) * 1e-9;
        System.err.println( "Initialisation: " + tm_init + " seconds" );
        tm_start = System.nanoTime();

        ParallelContext context = ParallelContextHolder.get();

        // 1. Make pass over graph
        context.edgemap( matrix, DSCCrelax );

        double tm_step = (double)(System.nanoTime() - tm_start) * 1e-9;
        if( verbose )
            System.err.println( "processing time=" + tm_step + " seconds" );
        tm_start = System.nanoTime();

        // Post-process the labels

        // 1. Count number of components
        //    and map component IDs to narrow domain
        int ncc = 0;
        int remap[] = new int[n];
        for( int i=0; i < n; ++i )
            if( DSCCrelax.find( i ) == i )
                remap[i] = ncc++;

        if( verbose )
            System.err.println( "Number of components: " + ncc );

        // 2. Calculate size of each component
        int sizes[] = new int[ncc];
        for( int i=0; i < n; ++i )
            ++sizes[remap[DSCCrelax.find( i )]];

        if( verbose )
            System.err.println( "DisjointSetCC: " + ncc + " components" );

        return sizes;
    }
}
