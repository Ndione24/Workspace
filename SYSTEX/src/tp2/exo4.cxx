#include <iostream>
#include <stdlib.h>
#include <unistd.h>

using namespace std;

int main (int argc, char *argv[]) {

    /* Processus P */
    pid_t p = getpid();
    cout << "Valeur de P : " << p << endl;
    /* Création du processus f1 */
    pid_t f1 = fork();

    if (f1 > 0) { // On est dans le père
        /* Création du processus f2 */
        pid_t f2 = fork();
        if (f2 == 0) { // On est dans le fils
            cout << "Je suis " << getpid() << " : F2, frêre de " << f1 << " : F1" << endl;
            pid_t f3 = fork();
            if (f3 == 0) {
                cout << "Je suis " << getpid() << " : F3, le petit fils de " << p << " : P " << "et le neveu de " << f1 << " : F1" << endl;
            }
        }
    } else {
        return 1;
    }

    return 0;
}
