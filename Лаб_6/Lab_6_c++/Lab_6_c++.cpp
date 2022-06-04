#include "Template.h"
#include "Functions.h"


int main() {
    char s[50], key;
    int counter = 0;
    string yn;
    TTree<char> chTree;
    do {
        cout << "Input: "; cin >> s;
        if (*s != '~') chTree.build(chTree.root, NULL, *s);
    } while (*s != '~');

    cout << "\nInput key: "; cin >> key;
    counter = chTree.find(chTree.root, key, counter);

    output(counter);

    cout << "\nYour tree:" << endl;
    chTree.printLevelOrder(chTree.root);

    cout << "\nDid it print normally? [Y/N]: "; cin >> yn;
    if (yn[0] == 'n' || yn[0]=='N'||yn[0]=='-') {
        chTree.horizontalPrint(chTree.root, 0);
    }
}