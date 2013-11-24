#include <stdio.h>
#include <signal.h>
#include <sys/types.h>
#include <stdlib.h>

int nb_sigusr1 = 0; 
int nb_sigusr2 = 0;

void gest_sigusr1 (int sig) {
	++nb_sigusr1;
}

void gest_sigusr2 (int sig) {
	++nb_sigusr2;
}

int main(int argc, char const *argv[])
{
	struct sigaction action;

	// Installation du gestionnaire pour SIGUSER1
	action.sa_handler = gest_sigusr1;
	sigemptyset (&action.sa_mask); // Par précaution on vide le mask
	if (sigaction (SIGUSR1, &action, NULL)<0) {perror("pb sigaction"); exit(EXIT_FAILURE);}

	// Installation du gestionnaire pour SIGUSER2
	action.sa_handler = gest_sigusr2;
	sigemptyset(&action.sa_mask);
	if (sigaction (SIGUSR2, &action, NULL)<0) {perror("pb sigaction"); exit(EXIT_FAILURE);}

	pid_t pidFils; int status;
	if (pidFils = fork()) { // PERE
		printf("P1: Envoi signal a P2\n");
		if (kill(pidFils, SIGUSR2) < 0) {perror("pere pb kill"); exit(EXIT_FAILURE);}
		printf("P1: Attente du signal\n");
		printf("P1: Recu signal de P2\n");
		wait();
	} else { // FILS
		printf("\t\t\tP2: Recu signal de P1\n");
		printf("\t\t\tP2: Envoi signal a P1\n");
		if (kill(getppid (), SIGUSR1) < 0) {perror("pere pb kill"); exit(EXIT_FAILURE);}
		printf("\t\t\tP2: Attente du signal\n");
	}

	printf("P1: TERMINE - %d signaux reçus\n", nb_sigusr1);
	printf("\t\t\tP2: TERMINE - %d signaux reçus\n", nb_sigusr2);

	return 0;
}

// Il faut remplacer PAUSE par SIGSUSPEND
