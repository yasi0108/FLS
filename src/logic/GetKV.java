package logic;

import com.ferrari.finances.dk.bank.InterestRate;
import com.ferrari.finances.dk.rki.CreditRator;
import com.ferrari.finances.dk.rki.Rating;

public class GetKV {
	public enum kreditRating {
		A, B, C, D, error
	};

	LaanCheckTlf checkTlfNr = new LaanCheckTlf();
	// private double udl�nsrente;
	private double rente, bilpris, kundeindbetaling, samletpris, mdlYdelse, laanlaengde;

	private Rating kv;
	String A, B, C, D;

	public kreditRating getKreditvaerdighed(String cpr) {
		kv = CreditRator.i().rate(cpr);
		CreditRator.i().rate(cpr);

		if (kv == Rating.A) {
			System.out.println("Tjekker kreditrating " + kv);
			return kreditRating.A;
		} else if (kv == Rating.B) {
			System.out.println("Tjekker kreditrating " + kv);
			return kreditRating.B;
		} else if (kv == Rating.C) {
			System.out.println("Tjekker kreditrating " + kv);
			return kreditRating.C;

		} else if (kv == Rating.D) {
			System.out.println("Brugeren er rating D");
			return kreditRating.D;
		}

		else {
			System.out.println("Fejl i koden");
			return kreditRating.error;
		}
	}

	public double getRente(String cprnr) {
		rente = InterestRate.i().todaysRate();
		System.out.println("Base rente er: " + rente);
		kv = CreditRator.i().rate(cprnr);
		// String kv = new String();
		CreditRator.i().rate(cprnr);
		if (kv == Rating.A) {
			rente += 1;
			System.out.println("Tjekker kreditrating " + kv + " og f�r rente: " + rente);

			return rente;
		} else if (kv == Rating.B) {

			rente += 2;
			System.out.println("Tjekker kreditrating " + kv + " og f�r rente: " + rente);
			return rente;
			// rate+=rateA;
		} else if (kv == Rating.C) {
			rente += 3;
			System.out.println("Tjekker kreditrating " + kv + " og f�r rente: " + rente);
			return rente;

		} else if (kv == Rating.D) {

			System.out.println("Brugeren er rating D");
			return 0;
		}

		else {
			System.out.println("Fejl i koden");
			return -1;
		}
	}

	public double getMdlYdelse(String cprnr, String bilprisGetText, String udbetalingGetText,
			String laanleangdeGetText) {
		rente = InterestRate.i().todaysRate();
		rente /= 100;
		kv = CreditRator.i().rate(cprnr);
		bilpris = Double.parseDouble(bilprisGetText);
		laanlaengde = Double.parseDouble(laanleangdeGetText);
		kundeindbetaling = Double.parseDouble(udbetalingGetText);
		// String kv = new String();
		CreditRator.i().rate(cprnr);
		if (kv == Rating.A) {
			rente += 1.01;

			samletpris = ((bilpris - kundeindbetaling) * rente);
			mdlYdelse = (samletpris / laanlaengde);
			System.out.println("Tjekker kreditrating " + kv + " og f�r m�nedlig ydelse p�: " + mdlYdelse + " kr.");

			return mdlYdelse;
		} else if (kv == Rating.B) {
			rente += 1.02;
			samletpris = ((bilpris - kundeindbetaling) * rente);
			mdlYdelse = (samletpris / laanlaengde);
			System.out.println("Tjekker kreditrating " + kv + " og f�r m�nedlig ydelse p�: " + mdlYdelse + " kr.");

			return mdlYdelse;
			// rate+=rateA;
		} else if (kv == Rating.C) {
			rente += 1.03;
			samletpris = ((bilpris - kundeindbetaling) * rente);
			mdlYdelse = (samletpris / laanlaengde);
			System.out.println("Tjekker kreditrating " + kv + " og f�r m�nedlig ydelse p�: " + mdlYdelse + " kr.");

			return mdlYdelse;

		} else if (kv == Rating.D) {

			System.out.println("Brugeren er rating D");
			return 0;
		}

		else {
			System.out.println("Fejl i koden");
			return -1;
		}
	}

	public double getSamletPris(String cprnr, String bilprisGetText, String udbetalingGetText) {
		rente = InterestRate.i().todaysRate();
		rente /= 100;
		System.out.println("Renten divideret med 100: " + rente);
		kv = CreditRator.i().rate(cprnr);
		bilpris = Double.parseDouble(bilprisGetText);
		kundeindbetaling = Double.parseDouble(udbetalingGetText);
		// String kv = new String();
		CreditRator.i().rate(cprnr);
		if (kv == Rating.A) {
			rente += 1.01;
			System.out.println("Rente +0.01 giver: " + rente);

			samletpris = ((bilpris - kundeindbetaling) * rente);
			System.out.println("Tjekker kreditrating " + kv + " og f�r samletpris: " + samletpris);

			return samletpris;
		} else if (kv == Rating.B) {
			rente += 1.02;
			System.out.println("Rente +0.02 giver: " + rente);
			samletpris = ((bilpris - kundeindbetaling) * rente);
			System.out.println("Tjekker kreditrating " + kv + " og f�r samletpris: " + samletpris);
			return samletpris;
			// rate+=rateA;
		} else if (kv == Rating.C) {
			rente += 1.03;
			System.out.println("Rente +0.03 giver: " + rente);

			samletpris = ((bilpris - kundeindbetaling) * rente);
			System.out.println("Tjekker kreditrating " + kv + " og f�r samletpris: " + samletpris);
			return samletpris;

		} else if (kv == Rating.D) {

			System.out.println("Brugeren er rating D");
			return 0;
		}

		else {
			System.out.println("Fejl i koden");
			return -1;
		}
	}
}
