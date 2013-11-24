//#include <stdio.h>
//#include <signal.h>
//
//#define NOMBRE_DONNEES 5
//#define DELAI_INITIAL 2
//
//struct lecture {
//	int donnee;
//	int tentatives;
//	int temps_ecoule;
//} tableau(NOMBRE_DONNEES);
//
//sigjmp_buf env;
//
//int delai_courant;
//int indice_donnee_courante;
//
//void interception_signal_sigalarm(int signum) {
//	tableau[indice_donnee_courante].tentatives++;
//	tableau[indice_donnee_courante].temps_ecoule += delai_courant;
//	printf("\n");
//	alarm(++delai_courant);
//		siglongjmp(env,1);
//}
//
//int main() {
//	signal(SIGALRM, interception_signal_sigalarm);
//
//	for (indice_donnee_courante = 0; indice_donnee_courante < NOMBRE_DONNEES; ++indice_donnee_courante) {
//		tableau[indice_donnee_courante].tentatives = 1;
//		tableau[indice_donnee_courante].temps_ecoule = 0;
//
//		delai_courant = DELAI_INITIAL;
//
//		jmp=sigsetjmp(env, 1);
//
//		printf("Introduire la donnee d'indice %d : \n", indice_donnee_courante);
//
//		alarm(delai_courant);
//		printf("\n");
//		scanf("%d", &tableau[indice_donnee_courante].donnee);
//		tableau[indice_donnee_courante].temps_ecoule += (delai_courant = alarm(0)); // on arrête le minuteur et on regarde le temps restant
//	}
//
//	printf("\nRand\tValeur\tTentatives \n");
//	for (int i = 0; i < NOMBRE_DONNEES; ++i) {
//
//	}
//}
