import java.io.*;

import com.rory.pagerank.*;

public class Driver {
    public static void main( String args[] ) {
        if( args.length < 5 ) {
            System.err.println( "Usage: java Driver format inputfile algorithm num-threads outputfile" );
            System.exit(43); // Kattis
            return; // silence compiler errors
        }

        String format = args[0];
        String inputFile = args[1];
        String algorithm = args[2];
        int num_threads = Integer.parseInt( args[3] );
        String outputFile = args[4];

        // Tell us what you're doing
        System.err.println( "Format: " + format );
        System.err.println( "Input file: " + inputFile );
        System.err.println( "Algorithm: " + algorithm );
        System.err.println( "Number of threads: " + num_threads );
        System.err.println( "Output file: " + outputFile );

        long tm_start = System.nanoTime();

        SparseMatrix matrix;

        // Step 1. Read in the file
        if( format.equalsIgnoreCase( "CSR" ) ) {
            matrix = new SparseMatrixCSR( inputFile );
        } else if( format.equalsIgnoreCase( "CSC" )
                || format.equalsIgnoreCase( "PARCSC" ) ) {
            matrix = new SparseMatrixCSC( inputFile );
        } else if( format.equalsIgnoreCase( "COO" ) ) {
            matrix = new SparseMatrixCOO( inputFile );
        } else {
            System.err.println( "Unknown format '" + format + "'" );
            System.exit(43); // Kattis
            return; // silence compiler errors
        }

        double tm_input = (double)(System.nanoTime() - tm_start) * 1e-9;
        System.err.println( "Reading input: " + tm_input + " seconds" );
        tm_start = System.nanoTime();

        // What facilities for parallel execution do we have?
        // Options:
        // - ParallelContextSingleThread: fully implemented
        // - ParallelContextSimple: needs to be completed by yourself
        if( format.equalsIgnoreCase( "PARCSC" ) )
            ParallelContextHolder.set( new ParallelContextSimple(num_threads) );
        else
            ParallelContextHolder.set( new ParallelContextSingleThread() );

        try {
            if( algorithm.equalsIgnoreCase( "PR" ) ) {
                // Step 2. Calculate PageRank values for the graph
                double PR[] = PageRank.compute( matrix );

                double tm_total = (double)(System.nanoTime() - tm_start) * 1e-9;
                System.err.println( "PageRank: total time: " + tm_total + " seconds" );
                tm_start = System.nanoTime();

                // Step 3. Dump PageRank values to file
                writeToFile( outputFile, PR );

                double tm_write = (double)(System.nanoTime() - tm_start) * 1e-9;
                System.err.println( "Writing file: " + tm_write + " seconds" );
            } else if( algorithm.equalsIgnoreCase( "CC" ) ) {
                // Step 2. Calculate connected components of the graph
                int CC[] = ConnectedComponents.compute( matrix );

                double tm_total = (double)(System.nanoTime() - tm_start) * 1e-9;
                System.err.println( "Connected Components: total time: " + tm_total + " seconds" );
                tm_start = System.nanoTime();

                // Step 3. Dump component sizes to file
                writeToFile( outputFile, CC );

                double tm_write = (double)(System.nanoTime() - tm_start) * 1e-9;
                System.err.println( "Writing file: " + tm_write + " seconds" );
            } else if( algorithm.equalsIgnoreCase( "DS" ) ) {
                // Step 2. Calculate connected components of the graph
                int CC[] = DisjointSetCC.compute( matrix );

                double tm_total = (double)(System.nanoTime() - tm_start) * 1e-9;
                System.err.println( "Disjoint Set: total time: " + tm_total + " seconds" );
                tm_start = System.nanoTime();

                // Step 3. Dump component sizes to file
                writeToFile( outputFile, CC );

                double tm_write = (double)(System.nanoTime() - tm_start) * 1e-9;
                System.err.println( "Writing file: " + tm_write + " seconds" );
            } else {
                System.err.println( "Unknown algorithm '" + algorithm + "'" );
                System.exit(43); // Kattis
                return; // silence compiler errors
            }
        } finally {
            ParallelContextHolder.get().terminate();
        }
        System.exit(0); // DOMjudge
    }

    static void writeToFile( String file, double[] v ) {
        try {
            OutputStreamWriter os
                    = new OutputStreamWriter( new FileOutputStream( file ), "UTF-8" );
            BufferedWriter wr = new BufferedWriter( os );
            writeToBuffer( wr, v );
        } catch( FileNotFoundException e ) {
            System.err.println( "File not found: " + e );
            System.exit(43); // Kattis
            return; // silence compiler errors
        } catch( UnsupportedEncodingException e ) {
            System.err.println( "Unsupported encoding exception: " + e );
            System.exit(43); // Kattis
            return; // silence compiler errors
        } catch( Exception e ) {
            System.err.println( "Exception: " + e );
            System.exit(43); // Kattis
            return; // silence compiler errors
        }
    }
    static void writeToBuffer( BufferedWriter buf, double[] v ) {
        PrintWriter out = new PrintWriter( buf );
        for( int i=0; i < v.length; ++i )
            out.println( i + " " + v[i] );
        out.close();
    }
    static void writeToFile( String file, int[] v ) {
        try {
            OutputStreamWriter os
                    = new OutputStreamWriter( new FileOutputStream( file ), "UTF-8" );
            BufferedWriter wr = new BufferedWriter( os );
            writeToBuffer( wr, v );
        } catch( FileNotFoundException e ) {
            System.err.println( "File not found: " + e );
            System.exit(43); // Kattis
            return; // silence compiler errors
        } catch( UnsupportedEncodingException e ) {
            System.err.println( "Unsupported encoding exception: " + e );
            System.exit(43); // Kattis
            return; // silence compiler errors
        } catch( Exception e ) {
            System.err.println( "Exception: " + e );
            System.exit(43); // Kattis
            return; // silence compiler errors
        }
    }
    static void writeToBuffer( BufferedWriter buf, int[] v ) {
        PrintWriter out = new PrintWriter( buf );
        for( int i=0; i < v.length; ++i )
            out.println( i + " " + v[i] );
        out.close();
    }
}
