#include <stdio.h>
#include <signal.h>
#include <sys/types.h>

int nb_sigusr1 = 0;
int nb_sigusr2 = 0;

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
	signal(SIGUSR1, gest_sigusr1);
	signal(SIGUSR2, gest_sigusr2);
	for (;;){}
	return 0;
}