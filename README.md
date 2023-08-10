# PageRank
PageRank is Google’s algorithm to rank the search results that match the queried keywords [1]. The algorithm
models the internet as a directed graph where web pages are represented as vertices and links between pages are
edges. The PageRank algorithm calculates the likelihood, or rank, that a page will be visited by people surfing
the web. The rank of a page depends on the rank of the pages that link to it, where pages that are frequently
pointed to tend to gain a higher rank. Also, pages pointed to by highly ranked pages tend to receive a higher rank.
The algorithm is itself quite simple but in practice it captures well the appreciation of the importance of pages by
humans.

I aim to create a Java program that solves this problem concurrently.
