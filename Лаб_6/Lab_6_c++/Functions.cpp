#include "Functions.h"


void space(int count) { // вывод заданного количества пробелов
    for (int i = 0; i < count; i++) {
        cout << ' ';
    }
}
void output(int count) {
    if (count == 0) cout << "\nNot found(" << endl;
    else {
        cout << "\nNumber of occuences = " << count; cout << endl;
    }
}