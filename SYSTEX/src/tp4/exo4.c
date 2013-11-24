#include <stdio.h>
#include <signal.h>
#include <sys/types.h>
#include <stdlib.h>


int nb_sigusr1;
int nb_sigusr2;

void gest_sigusr1 (int sig) {
	++nb_sigusr1;
	printf("reçu %d signal %d (SIGUSR1)\n", nb_sigusr1, sig);
}

void gest_sigusr2 (int sig) {
	++nb_sigusr2;
	printf("reçu %d signal %d (SIGUSR2)\n", nb_sigusr2, sig);
}

int main(int argc, char const *argv[])
{
	// Initialisation de la sigaction
	struct sigaction action;

	// Installation du gestionnaire pour SIGUSER1
	action.sa_handler = gest_sigusr1;
	sigemptyset (&action.sa_mask); // Par précaution on vide le mask
	if (sigaction (SIGUSR1, &action, NULL)<0) {perror("pb signal !"); exit(EXIT_FAILURE);}

	// Installation du gestionnaire pour SIGUSER2
	action.sa_handler = gest_sigusr2;
	sigemptyset(&action.sa_mask);
	if (sigaction (SIGUSR2, &action, NULL)<0) {perror("pb signal !"); exit(EXIT_FAILURE);}

	for (;;){}
	return 0;
}