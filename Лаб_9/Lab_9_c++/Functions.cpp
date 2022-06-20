#include "Functions.h"


vector<Item> input(string name, int& weight, int& count) {
    int space;
    string line;
    vector<Item> list;

    ifstream inFile(name);
    getline(inFile, line, ' ');
    weight = stoi(line);
    getline(inFile, line);
    count = stoi(line);

    list.resize(count);
    for (int i = 0; i < count; i++) {
        getline(inFile, line);
        space = line.find(' ');
        list[i].setCost(stoi(line.substr(0, space)));
        list[i].setWeight(stoi(line.substr(space, line.length() - space)));
    }

    inFile.close();
    return list;
}

int** capacity(vector<Item>& list, int count, int weight) {
    int w, cost;
    int** arr = new int* [count];
    for (int i = 0; i < count; i++) {
        arr[i] = new int[weight];
        w = list[i].getWeight();
        cost = list[i].getCost();
        for (int j = 0; j < weight; j++) {
            if (i == 0) {
                arr[i][j] = 0;
            }
            else if (j >= w) {
                arr[i][j] = max(arr[i - 1][j], arr[i - 1][j - w] + cost);
            }
            else {
                arr[i][j] = arr[i - 1][j];
            }
        }
    }
    return arr;
}

void writer(string name, int** arr, int count, int weight) {
    ofstream outFile(name);
    outFile << arr[count - 1][weight - 1];
    outFile.close();
}