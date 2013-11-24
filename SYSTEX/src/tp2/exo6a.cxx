#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

using namespace std;

int main(int argc, char const *argv[])
{
    if (argc != 2) {
        cout << "main() " << argv[0] << " Usage : " << " exit or _exit" << endl;
        return 1;
    }

 	cout << "Buffer remplie";
 	if ( strcmp(argv[1], "exit") == 0 ) {
		exit(0);
    } else if ( strcmp(argv[1], "_exit") == 0 ) {
    	_exit(0);
    } else {
    	cout << "Erreur d'argument" << endl;
    	return -1;
    }

}
