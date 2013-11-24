package up5.mi.viethi.tp3.prodhtml;

public class TestBalise {

	public static void main(String[] args) {

		Balise princ = new Balise("html");
		Balise head = new Balise("head");
		Balise body = new Balise("body");

		princ.add(head);
		princ.add(body);

		body.add(new Balise("b", "Bonjour le monde"));

		body.addOption("style", "background-color:red");

		body.add(Balise.getBaliseApplet("up5.mi.dupond.test.Test",
				"allPary.jar,allDupond.jar"));

		head.add(new Balise("title", "essai"));

		System.out.println(princ);
		System.out.println("Taille :" + princ.toString().length());
	}

}

/* AFFICHAGE PRODUIT :
b		<html>
b/sb		<head>
sb				<title>essai</title>
b/sb		</head>
b/sb		<body style="background-color:red">
sb				<b>Bonjour le monde</b>
sb				<applet code="up5.mi.dupond.test.Test" archive="allPary.jar,allDupond.jar"></applet>
b/sb		</body>
b			</html>
	Taille :203
*/
