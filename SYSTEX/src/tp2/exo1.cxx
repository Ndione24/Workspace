#include <iostream>
#include <stdlib.h>
#include <unistd.h>

using namespace std;

int main (int argc, char *argv[]) {
    if (argc != 3) {
        cerr << "Nombre d'arguments incorrect" << endl;
        return 1;
    }
    int iter_pere=atoi(argv[1]);
    int iter_fils=atoi(argv[2]);
    pid_t pidFils = fork();
    if (0 == pidFils) { // on est dans le fils
        for (int i=0; i<iter_fils; ++i){
            cout << "\t\t\t\t\t" << "FILS, mon pid est " << getpid() << '\n'
                 << "\t\t\t\t\t" << "Le pid de mon pere est " << getppid() << endl;
        }
    } else { // on est dans le père
        for (int i=0; i<iter_pere; ++i){
            cout << "PERE, mon pid est " << getpid() << '\n'
                 << "Le pid de mon fils est " << pidFils << endl;
        }
    }
    return 0;
}
