#include <unistd.h> // pipe()
#include <fcntl.h> // dup2()
#include <sys/types.h> // wait()
#include <sys/wait.h> // wait()

#define STDIN   0
#define STDOUT  1

int main(int argc, char const *argv[])
{

    int fp[2];
    pipe(fp);

    if (0 == fork()) { // On est dans le père

        // Le père est l'écrivain
        if ( -1 == close(STDIN) ) {
            perror("pb close STD_IN\n");
            exit(1);
        }
        write(fp[STDOUT], argv[1], strlen(argv[1]));
        close(fp[STDOUT]);

        exit(1);

    } else { // On est dans le fils

        if (-1 == close(STDOUT) ) { // Le fils est le lecteur
            perror("pb close STD_OUT\n");
            exit(1);
        }
        lu = read(fp[STDIN], buff, MAX);
        if (lu < 0) {
            perror();
            exit(EXIT_FAILURE);
        }
        printf("fils : ");
        close(fp[STDIN]);

        exit(1);

    }

    close(fp[0]); close(fp[1]);
    return 0;
}
