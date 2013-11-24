#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <stdio.h>

#define NB_ARGUMENTS 16

int main(int argc, char **argv, char **arge)
{
    if ( argc < 2 ) {
        printf("Usage : programme commande liste_arguments\n"); 
        exit(1);
    }

    char *argv_exec[NB_ARGUMENTS]; 
    int indice;

    for ( indice = 0; indice < argc; indice++)
        argv_exec[indice] = argv[indice + 1];

    if ( execvp(argv_exec[0], argv_exec) == -1 ) {
        printf("Echec exec\n"); 
        exit(2);
    }

}