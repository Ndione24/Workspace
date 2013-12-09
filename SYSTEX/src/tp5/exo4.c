#include <unistd.h>
#include <stdio.h>      /* printf, fopen */
#include <stdlib.h>     /* exit, EXIT_FAILURE */
#include <fcntl.h>

#define STD_ERR 2

void substituer_sortie(const char *fichier) {
    int fd;
    fd = open(fichier, O_WRONLY | O_CREAT | O_TRUNC, 0644); // recup err
    if (fd == -1) {
        perror("pb ouverture du fichier\n");
        exit(EXIT_FAILURE);
    }

    if (close(STD_ERR) == -1) {
        perror("pb close STD_ERR\n");
        exit(1);
    }

    if (dup (fd) == -1) {
        perror("pb dup STD_ERR \n");
        exit(1);
    }

    fprintf(stderr, "Le fichiers des erreurs redirigé vers %s\n",fichier);

    dup2(fd, 2);
    close(fd);
}

int main(int argc, char const *argv[])
{
    if (argc < 2) {
        fprintf(stderr, "nombre de parametres incorrect\n");
        exit(1);
    }

    substituer_sortie(argv[1]);

    return 0;
}
