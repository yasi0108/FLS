package logic;

import db.OpretKundeDB;

public class opretKunde {

	OpretKundeDB oprKundeDB = new OpretKundeDB();

	public void CreateKunde(String fornavnGetText, String efternavnGetText, int	tlf, int postnr,
			String byGetText, String vejGetText, int husnr, String cprGetText, String mailGetText,
			Enum<?> kreditVurdering) {
		oprKundeDB.createKunde(fornavnGetText, efternavnGetText, tlf, postnr, byGetText, vejGetText,
				husnr, cprGetText, mailGetText, kreditVurdering);
	}

}