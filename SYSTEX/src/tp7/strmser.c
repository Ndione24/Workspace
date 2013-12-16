// strmser programme serveur
int main(int argc, char const *argv[]) // serveur
{
    /*
    PREPARATION DU NOMMAGE DE LA SOCKET
    */

    bzero();
    bcopy();
    // interrogation du dns

    /*
    NOMMAGE DE LA SOCKET D'ECOUTE
    */

    bind();

    /*
    ECOUTE
    */

    listen();
    LgInfoSockClient = sizeof(InfoSockClient); // doit être initialisé
    while(ligne[0]!='#') {
        /*
        Prise en compte d'une demande de connexion
         */
        accept(SockEoute,(struct sockaddr *)&InfoSockClient, &LgInfoSockClient);

        /*
        Lecture des donnees du client sur la socket de service et traitement de la requete
         */
        printf("\n\t->Une connexion vient d'arriver de : %s / %d \n",
               inet_ntoa (InfoSockClient.sin_addr), ntohs(InfoSockClient.sin_port));
        InfoMachineServeur= gethostbyaddr(&InfoSockClient, sizeof(InfoSockClient), )

        nr = read(SockService, ligne, MAX_LIGNE); // Lecture de ce que le client a envoyé
        if(nr<0) {
            perror("error read");
        } else {
            //...
            if (ligne[0]!='#') {

            }
        }
    }
    return 0;

}
