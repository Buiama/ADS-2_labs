#include "Functions.h"


void space(int count) { // ����� ��������� ���������� ��������
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