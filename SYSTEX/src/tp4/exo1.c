#include <signal.h>     // kill()
#include <stdio.h>      // printf()
#include <stdlib.h>     // atoi()
//#include <sys/types.h>  // kill()
hello

int main(int argc, char const *argv[])
{
  if (argc < 4) {
    printf("Usage : %s <pid> <sig> <nb_sig>\n", argv[0]);
    exit(EXIT_FAILURE);
  }

  int pid = atoi(argv[1]);
  int sig = atoi(argv[2]);
  int nb_sig = atoi(argv[3]);

//  for (int i = 0; i < nb_sig; ++i) {
//	if (-1 == kill(pid, sig)) {
//		perror("kill command error");
//		exit(EXIT_FAILURE);
//	}
//	printf("Signal %d envoyé à %d\n", sig, pid);
//  }

  return 0;
}
