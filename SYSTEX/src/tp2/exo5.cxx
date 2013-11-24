#include <iostream>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
       
using namespace std;

int main (int argc, char *argv[]) {
    if (argc != 2) {
        cout << "main() " << argv[0] << " Usage : " << " <CstNbreFils>" << endl;
        return 1;
    }
    
    const unsigned CstNbreFils = atoi(argv[1]);
    pid_t TabPid[CstNbreFils];
    
    for (unsigned i = CstNbreFils; i; --i)
    {
        if( !(TabPid[i] = fork()) ) { // On est dans le père
            cout << "Création du fils " << getpid() << endl;
            sleep(i);
            exit(i);
        }
    }
    
    int status;
    for (unsigned i = CstNbreFils; i; --i)
    {
        wait(&status);
        cout << "Le fils de pid " << TabPid[i] << " s'est terminé ";
        if (WIFEXITED(status)) {
			cout << "par un exit de valeur " << WEXITSTATUS (status);
	    } else if (WIFSIGNALED(status)) {
	        cout << "par un signal de valeur " << WTERMSIG (status);
	    }
	    cout << endl;
    }
	
    return 0;
    
}
