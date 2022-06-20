#pragma once
#include <iostream>
#include <string>
#include <fstream>
#include <math.h>
#include <vector>

using namespace std;

int** input(string, int&, int);
double distance(int, int, int, int);
double** graphMake(int, int**);
double find(vector<int>, double**);
bool duplicates(int, vector<int>);
void search(int, vector<int>, double**, int&, vector<int>&);
void alg(double**, int, int&, vector<int>&);
void writer(string, int, vector<int>&);