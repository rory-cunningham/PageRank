package com.rory.pagerank;

public class ParallelContextHolder {
    private static ParallelContext context = null;

    public static void set( ParallelContext context_ ) {
        context = context_;
    }

    public static ParallelContext get() {
        return context;
    }
}
