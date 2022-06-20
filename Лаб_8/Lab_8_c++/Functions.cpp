#include "Functions.h"


int** input(string name, int& vert, int col) {
    ifstream inFile(name);
    string line;
    getline(inFile, line);
    vert = stoi(line);

    int** arr = new int* [vert];
    int x, y, space;
    for (int i = 0; i < vert; i++) {
        arr[i] = new int[col];
        getline(inFile, line);
        space = line.find(' ', 0);
        x = stoi(line.substr(0, space));
        y = stoi(line.substr(space, line.length() - space));
        arr[i][0] = x;
        arr[i][1] = y;
    }

    inFile.close();
    return arr;
}

double** graphMake(int vert, int** coordinates) {
    int  x1, y1;
    const int x2 = 0, y2 = 1;
    double** graph = new double* [vert];
    for (int i = 0; i < vert; i++) {
        graph[i] = new double[vert];
        x1 = coordinates[i][x2];
        y1 = coordinates[i][y2];
        for (int j = 0; j < vert; j++) {
            if (i == j) {
                graph[i][j] = 0;
            }
            else {
                graph[i][j] = distance(x1, y1, coordinates[j][x2], coordinates[j][y2]);
            }
        }
    }
    return graph;
}

double distance(int x1, int y1, int x2, int y2) {
    double distance = sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
    return distance;
}

void alg(double** graph, int vert, int& min, vector<int>& combinations) {
    vector<int> c;
    c.push_back(0);
    search(vert, c, graph, min, combinations);
    combinations.push_back(0);
}

void search(int vert, vector<int> c, double** graph, int& min, vector<int>& combinations) {
    if (c.size() == vert) {
        int sum = find(c, graph);
        if (sum < min) {
            min = sum;
            combinations = c;
        }
        return;
    }
    vector<int> newC = c;
    for (int i = 1; i < vert; i++) {
        if (duplicates(i, newC)) {
            newC.push_back(i);
            search(vert, newC, graph, min, combinations);
            newC.pop_back();
        }

    }
}

double find(vector<int> c, double** graph) {
    c.push_back(0);
    double sum = 0;
    for (int i = 0; i < c.size() - 1; i++) {
        sum += graph[c[i]][c[i + 1]];
    }
    return sum;
}

bool duplicates(int ind, vector<int> c) {
    for (int i = 0; i < c.size(); i++) {
        if (c[i] == ind) {
            return false;
        }
    }
    return true;
}

void writer(string name, int min, vector<int>& c) {
    ofstream outFile(name);
    outFile << min << "\n";
    for (int i = c.size() - 1; i >= 0; i--) {
        outFile << c[i] << " ";
    }
    outFile.close();
}
