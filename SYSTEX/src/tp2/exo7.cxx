#include <iostream>
#include <stdlib.h>
#include <sys/times.h>
#include <unistd.h>

#define CLK_TCK sysconf (_SC_CLK_TCK)

using namespace std;

int main(int argc, char const *argv[])
{
    if (argc != 2) {
    	cout << "main() " << argv[0] << " Usage : " << " <nb_iter>" << endl;
    	return 1;
	}

	int nb_iter = atoi(argv[1]);

	struct tms info;
	float u,s;

	int j;
	for (int i = 0; i < nb_iter; ++i)
	{
		j = j+1;
	}

	times(&info);

	u = info.tms_utime;
	s = info.tms_stime;

	cout << "Temps CPU consommé\n"
	 << "nombre de clics "			<< times(&info) << " ticks\n"
	 << "nombre de clics par sec "	<< times(&info)/CLK_TCK << " tick/sec\n"
	 << "mode utilisateur " 		<< u/CLK_TCK << " s\n"
	 << "mode système " 			<< s/CLK_TCK << " s" << endl;

	return 0;
}
