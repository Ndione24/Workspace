#include <iostream>
#include <stdlib.h>
#include <unistd.h>

using namespace std;

int main (int argc, char *argv[]) {
    if (argc != 3) {
        cerr << "Nombre d'arguments incorrect" << endl;
        return 1;
    }
    int x=atoi(argv[1]);
    int iter_fils=atoi(argv[2]);
    pid_t pidFils = fork();
    if (0 == pidFils) { // on est dans le fils
        for (int i=iter_fils; i>0; --i){
            cout << "\t\t\t\t\t" << "FILS valeur de x :" << x << endl;
        }
    } else { // on est dans le père
        for ( ; x>0; --x){
            cout << "PERE valeur de x :" << x << endl;
        }
    }
    return 0;
}
