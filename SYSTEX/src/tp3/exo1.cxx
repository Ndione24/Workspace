#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

using namespace std;

int main(int argc, char **argv, char **arge) {

	int choix;
	cout << "Type d'exécution (0) execl (1) execv : ";
	cin >> choix;
	switch(choix) {
		case 0 :
			cout << "execl(" << argv[1] << ', ' << argv[1] << ", NULL)" << endl;
		    execl(argv[1], argv[1], NULL);
		    break;
		case 1 :
			cout << "execv(" << argv[1] << ', ' << &argv[1] << ')' << endl;
		    execv(argv[1], &argv[1]);
		    break;
		default :  
			cout << "Mauvais argument" << endl;
	}

	perror("");
	exit(EXIT_FAILURE);

}