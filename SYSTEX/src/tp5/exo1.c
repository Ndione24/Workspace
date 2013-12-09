#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
	const char* file = "filetest";
	int fd;
	if (-1 == ( fd = open(file, O_CREAT|O_EXCL, 0666) )) {
        printf ("Code de l'erreur : %d\n", errno);
        if (errno == EEXIST)
            perror("Message");
        else
            perror("Erreur inconnue\n");
	} else printf("Le fichier %s a été ouvert\n", file);
	close(fd);
	return 0;
}
