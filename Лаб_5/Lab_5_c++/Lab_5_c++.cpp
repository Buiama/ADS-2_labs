#include <iostream>
#include <string>

using namespace std;

struct Node {
    string data;
    Node* next;
};

class myStack {
    Node* top;
public:
    myStack() :top(NULL) {}
    ~myStack();
    void push(string);
    string pop();
    void reverse(string, string&);
};

myStack::~myStack() {
    while (top) {
        Node* p = top;
        top = top->next;
        delete p;
    }
}

void myStack::push(string word) {
    Node* temp = new Node;
    temp->data = word;
    temp->next = top;
    top = temp;
}

string myStack::pop() {
    string word;
    Node* temp = top;
    if (!temp) {
        cout << "Stack is empty" << endl;
        return 0;
    }
    word = temp->data;
    top = temp->next;
    delete temp;
    return word;
}

void myStack::reverse(string s, string& res) {
    string word;
    for (int i = 0; i < s.length(); i++) {
        if (isspace(s[i])) {
            push(word);
            word = "";
        }
        else {
            word += s[i];
        }
    }
    while (top) {
        res += pop() + ' ';
    }
    res += '\n';
}

void clear(string&);

int main()
{
    string res = "";
    myStack st;
    int ascii = 27; // Ctrl + [
    string line = " ";
    cout << "Press Enter to go to the next line\nPress Ctrl + [ to end writing\n\nEnter your text:\n";
    while (int(line[0]) != ascii) {
        getline(cin, line);
        if (line == "") line = " ";
        clear(line);
        st.reverse(line, res);
    }
    cout << "Your reversed text:" << endl;
    res.erase(res.length() - 3, 3); // убираем \n (-4, 4 - если вообще без абзацов)
    cout << res;
}

void clear(string& s) {
    for (int i = 1; i < s.length(); i++) {
        if (isspace(s[i - 1]) && isspace(s[i])) {
            s.erase(i, 1);
            i--;
        }
    }
    if (s.length() > 1 && isspace(s[0])) { // видаляє зайвий пробіл на початку речення
        s.erase(0, 1);
    }
    if (!isspace(s[s.length() - 1])) {
        s += ' ';
    }
}