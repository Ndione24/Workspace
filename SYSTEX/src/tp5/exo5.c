#include <unistd.h>
#include <stdio.h>      /* printf, fopen */
#include <stdlib.h>     /* exit, EXIT_FAILURE */
#include <sys/types.h>
#include <fcntl.h>
#include <errno.h>

int main(int argc, char const *argv[])
{
    if (argc < 2) {
        fprintf(stderr, "Usage : %s <nom_fichier> <type_verrou> <debut_zone> <longueur_zone>\n", argv[0]);
        exit(1);
    }

    struct flock verrou;
    int fd;

    if (-1 == (fd = open(argv[1], O_RDWR) ) {
        perror("pb ouverture du fichier\n");
        exit(EXIT_FAILURE);
    }

    if (argv[2] == 's') {
        verrou.l_type = F_RDLCK;
    } else if (argv[2] == 'x') {
        verrou.l_type = F_WRLCK;
    } else {
        fprintf(stderr, "%s mode invalide\n", argv[2]);
        exit(EXIT_FAILURE);
    }

    /* verrou exclusif */
    verrou.l_whence = SEEK_SET; /* par rapport au début du fichier */
    verrou.l_start = 0; /* 0 par rapport au début du fichier */

    // verrouillage de tout le fichier
    verrou.l_len = lseek(fd, 0, SEEK_END); /* récupérer la taille du fichier */

    for (ret_fcntl = fcntl(fd, F_SETLK, &verrou);
         ret_fcntl < 0;
         ret_fcntl = fcntl(fd, F_SETLK, &verrou)) {
        if (errno == EACCES || errno == EAGAIN) sleep(5);
    }

    if (ret_fcntl<0) {
        perror("Pose du verrou: ");
        exit(EXIT_FAILURE);
    }

    return 0;
}
