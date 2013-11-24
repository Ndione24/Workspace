#include <iostream>
#include <stdlib.h>
#include <unistd.h>

using namespace std;

int main (int argc, char *argv[]) {
    
    if (argc != 2) {
        cout << "main() " << argv[0] << " Usage : " << " <NbIter>" << endl;
        return 1;
    }
    
    /* On est dans P1 */
    const int Iter = atoi(argv[1]);    
    
	if (pid_t pidFils = fork()) { // On est dans le père
        for (int i = Iter; i; --i) {
	        cout << "PERE, valeur de X : " << i << endl;
	    }
	    cout << "\nPERE, adresse de i : " << &Iter << endl;
	} else { // On est dans le fils
	    /* On est dans P2 */
        for (int i = Iter; i; --i) {
	        cout << "\t\t\t\t\t\t" << "FILS, valeur de X : " << i << endl;
	    }
	    cout << "\n\t\t\t\t\t\tFILS, adresse de i : " << &Iter << endl;
	}
	
    return 0;
    
}
