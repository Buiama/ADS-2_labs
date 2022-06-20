#include "Item.h"

int Item::getWeight() const {
    return weight;
}

void Item::setWeight(int w) {
    weight = w;
}

int Item::getCost() const {
    return cost;
}

void Item::setCost(int c) {
    cost = c;
}