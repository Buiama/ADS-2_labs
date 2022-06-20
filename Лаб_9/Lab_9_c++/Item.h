#pragma once
#include <iostream>

using namespace std;

class Item {
    int weight;
    int cost;

public:
    int getWeight() const;
    void setWeight(int w);
    int getCost() const;
    void setCost(int c);
};