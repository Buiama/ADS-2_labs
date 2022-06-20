#include "Functions.h"
#include "Item.h"

int main() {
    int maxWeight = 0, count = 0;

    vector<Item> itemList = input("input.txt", maxWeight, count);
    int** backpack = capacity(itemList, count, maxWeight);

    writer("output.txt", backpack, count, maxWeight);
    cout << "Done!";
}