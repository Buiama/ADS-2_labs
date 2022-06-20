#pragma once
#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include "item.h"

using namespace std;

vector<Item> input(string, int&, int&);
int** capacity(vector<Item>&, int, int);
void writer(string, int**, int, int);