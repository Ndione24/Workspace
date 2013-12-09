#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>

int main(int argc, char const *argv[])
{
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <pathname_source> <pathname_dest> <taille_buffer>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    int fd_source, fd_dest;

    if ( (fd_source = open(argv[1], O_RDONLY) == -1) ) {
        perror("Error");
        exit(EXIT_FAILURE);
    }

    if ( (fd_dest = open(argv[2], O_WRONLY|O_CREAT) == -1) ) {
        perror("Error");
        exit(EXIT_FAILURE);
    }

    const int size_buf = 1024;
    char buffer[size_buf];
    int r,w;

    printf("Début écriture...\n");
    for (r = read(fd_source,buffer,size_buf); r > 0; r = read(fd_source,buffer,size_buf)) {
        w = write(fd_dest, buffer, r);
        if (w < 0)  {
            perror("write : ");
            exit(EXIT_FAILURE);
        }
    }
    printf("Fin écriture...\n");


    exit(EXIT_SUCCESS);
}
