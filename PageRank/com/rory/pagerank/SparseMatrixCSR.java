package com.rory.pagerank;

import java.io.*;
import java.sql.SQLOutput;

public class SparseMatrixCSR implements SparseMatrix {
    //TODO: variable declarations

    int num_vertices;
    int num_edges;

    public SparseMatrixCSR( String file)
    {
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream( file ), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            readFile(bufferedReader);
        } catch (FileNotFoundException e)
        {
            System.err.println("File not found" + e);
            return;
        }
        catch (UnsupportedEncodingException e)
        {
            System.err.println( "Unsupported encoding exception: " + e );
            return;
        }
        catch (Exception e)
        {
            System.err.println( "Exception: " + e );
            return;
        }
    }

    int getNext( BufferedReader rd ) throws Exception {
        String line = rd.readLine();
        if( line == null )
            throw new Exception( "premature end of file" );
        return Integer.parseInt( line );
    }

    void readFile( BufferedReader rd ) throws Exception {
        String line = rd.readLine();
        if( line == null )
            throw new Exception( "premature end of file" );
        if( !line.equalsIgnoreCase( "CSR" ) && !line.equalsIgnoreCase( "CSC-CSR" ) )
            throw new Exception( "file format error -- header" );

        num_vertices = getNext(rd);
        num_edges = getNext(rd);

        // TODO: Allocate memory for the CSR representation
	...

        for( int i=0; i < num_vertices; ++i ) {
            line = rd.readLine();
            if( line == null )
                throw new Exception( "premature end of file" );
            String elm[] = line.split( " " );
            assert Integer.parseInt( elm[0] ) == i : "Error in CSR file";
            for( int j=1; j < elm.length; ++j ) {
                int dst = Integer.parseInt( elm[j] );
                // TODO:
                //    Record an edge from source i to destination dst
		...
            }
        }
    }

    // Return number of vertices in the graph
    public int getNumVertices() { return num_vertices; }

    // Return number of edges in the graph
    public int getNumEdges() { return num_edges; }

    // Auxiliary function for PageRank calculation
    public void calculateOutDegree( int outdeg[] ) {
        // TODO:
        //    Calculate the out-degree for every vertex, i.e., the
        //    number of edges where a vertex appears as a source vertex.
	...
    }

    // Apply relax once to every edge in the graph
    public void edgemap( Relax relax ) {
        // TODO:
        //    Iterate over all edges in the sparse matrix and calculate
        //    the contribution to the new PageRank value of a destination
        //    vertex made by the corresponding source vertex
	...
    }

    public void rangedEdgemap( Relax relax, int from, int to ) {
        // Only implement for parallel/concurrent processing
        // if you find it useful
    }

}
