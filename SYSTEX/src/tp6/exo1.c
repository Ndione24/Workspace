# include <sys/types.h>
# include <unistd.h>

int main () {
    const int Nbuff = 1000;
    char buff [Nbuff];
    int pF, dFic [2];

    pipe (dFic);
    if (( pF = fork ()) > 0) { // pere
        close (dFic[0]);
        write (dFic[1],"Salut",5);
    } else if (pF == 0) { // fils
        close (dFic[1]);
        int n = read (dFic[0], buff ,Nbuff-1);
        buff[n] = '\0';
        printf("%s\n", buff);
    }
    return 0;
}
