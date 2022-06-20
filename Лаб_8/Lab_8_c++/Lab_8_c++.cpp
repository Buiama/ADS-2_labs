#include "Functions.h"


int main()
{
    int topCount = 0;
    int** point = input("input.txt", topCount, 2);
    double** graph = graphMake(topCount, point);

    int min = INT_MAX;
    vector<int> combinations;
    alg(graph, topCount, min, combinations);
    writer("output.txt", min, combinations);
}