#pragma once
#include <iostream>
#include <iomanip>
//#include <stdlib.h>
//#include <conio.h>
#include "Functions.h"

using namespace std;

template <class T>
class TTree {
    T info; // текущий элемент
    TTree* left;
    TTree* right;
    int height;
    int width;
    int k; // счетчик пробелов
public:
    TTree* root; // указатель на корень
    TTree() { root = NULL; }
    ~TTree(); // деструктор для удаления динамически выделенной памяти
    void build(TTree<T>* r, TTree<T>* previous, T info);
    int find(TTree<T>* r, T key, int& counter);
    int findHeight(TTree<T>* r);
    void printCurrentLevel(TTree<T>* r, int level, int width, bool& flag, int i);
    void printLevelOrder(TTree<T>* r);
    void horizontalPrint(TTree<T>* r, int l);
};

//для шаблонов методы не нужно выносить в отдельный файл

template<class T>
TTree<T>::~TTree() {
    delete left;
    delete right;
    delete root;
}

template<class T>
void TTree<T>::build(TTree<T>* r, TTree<T>* previous, T info) { // строим дерево
    if (!r) {
        r = new TTree<T>;
        r->left = NULL;
        r->right = NULL;
        r->info = info;
        if (!root) {
            root = r;
        }
        else {
            if (info < previous->info) {
                previous->left = r;
            }
            else {
                previous->right = r;
            }
        }
    }
    else if (info < r->info) {
        build(r->left, r, info);
    }
    else {
        build(r->right, r, info);
    }
}

template<class T>
int TTree<T>::find(TTree<T>* r, T key, int& counter) { // ищем заданный элемент
    if (!r) {
        return counter;
    }
    find(r->right, key, counter); // симметричный обход дерева 
    if (r->info == key) counter++;
    find(r->left, key, counter);
}

template<class T>
void TTree<T>::printLevelOrder(TTree<T>* r) { // рекурсивный обход дерева в ширину
    int h = findHeight(r);
    width = pow(2, height);
    int fullWidth = (2 * width - 1) / 2;
    bool flag;

    for (int i = 0; i <= h; i++) {
        flag = true;
        printCurrentLevel(r, i, fullWidth, flag, i);
        fullWidth /= 2;
        cout << endl;
    }
}

template<class T> // печатаем все элементы на уровне
void TTree<T>::printCurrentLevel(TTree<T>* r, int level, int width, bool& flag, int heigh) {
    if (r == NULL) {
        space(2 * width + 1);
        if (level != 0) flag = false;
        if (!k) k = width;
        return;
    }
    if (level == 0) {
        if (!flag) {
            space(heigh * k - heigh * 3 / 2);
            cout << r->info;
            flag = true;
        }
        else {
            space(width);
            cout << r->info;
            space(width);
        }
    }
    else if (level > 0) {
        printCurrentLevel(r->left, level - 1, width, flag, heigh);
        space(1);
        printCurrentLevel(r->right, level - 1, width, flag, heigh);
    }
}

template<class T>
int TTree<T>::findHeight(TTree<T>* r) { // поиск высоты дерева
    if (!(r->left) && !(r->right)) height = 0;
    else if (!(r->right)) {
        height = findHeight(r->left) + 1;
    }
    else if (!(r->left)) {
        height = findHeight(r->right) + 1;
    }
    else {
        height = max(findHeight(r->left), findHeight(r->right)) + 1;
    }
    return height;
}

template <class T> // на всякий случай
void TTree<T>::horizontalPrint(TTree<T>* r, int l) { // горизонтальный вывод
    int i;
    if (!r)
    {
        return;
    }
    horizontalPrint(r->right, l + 1);
    space(l);
    cout << r->info << endl;
    horizontalPrint(r->left, l + 1);
}