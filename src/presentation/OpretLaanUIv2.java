package presentation;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.List;

import entity.Biler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.GetBiler;
import logic.GetKV;
import logic.LaanCheckTlf;
import logic.TFieldLogik;
import logic.TFieldLogik.TFieldResult;
import logic.opretLaan;

public class OpretLaanUIv2 {

	DecimalFormat numberFormatter = new DecimalFormat("0");
	private TextField tlfTField, bilnavnTField, bilprisTField, udbetalingTField, laengdeTField, samletprisTField,
			renteTField, mdlYdelseTField;
	private Stage opretLaanStage;
	private Scene scene3;
	private Pane pane3;
	private String bilnavnGetText, bilprisGetText, udbetalingGetText, laanleangdeGetText, valgtBilNavn, tlfGetText,
			renteString, cprnr, samletPrisString, mdlydelseString;
	private Image ferrari;
	private ImageView ferraripic;
	private Button vaelgBilBtn, indsaetBil, opretLaanBtn, tlfSoegBtn, opretKundeBtn;
	private Label opretStatusLbl, lblbilMangler, lbltlfUgyldig;
	private double rente, mdlYdelse, samletPris;
	private boolean isClicked = false;
	private GetBiler billogic = new GetBiler();
	private List<Biler> getbiler = billogic.getAllBilerInfo();
	private ObservableList<String> getbiler1 = FXCollections.observableArrayList();
	private ListView<String> bilList1 = new ListView<>(getbiler1);

	public void start() {
		opretLaanStage = new Stage();
		opretLaanStage.setTitle("Ferrari lånesystem");
		opretLaanStage.getIcons()
				.add(new Image("https://i.pinimg.com/564x/c9/87/c8/c987c8a5c896fca22c5cfbd62edb7359.jpg"));

		Label lgnNameLbl = new Label("logged in som " + "username");

		pane3 = new Pane();
		ferrari = new Image(
				"https://3.bp.blogspot.com/-DRM75enaO7s/VDrpAiCm55I/AAAAAAAABGM/VnsBvuXIygU/s1600/Ferrari%2BCar%2Blogos.jpg%22");
		ferraripic = new ImageView();

		// Button assignments
		opretLaanBtn = new Button("Opret lånetilbud");
		vaelgBilBtn = new Button("Vælg Bil");
		indsaetBil = new Button("Inds�t bil i lån");
		tlfTField = new TextField();
		opretStatusLbl = new Label();
		bilnavnTField = new TextField();
		bilprisTField = new TextField();
		udbetalingTField = new TextField();
		laengdeTField = new TextField();
		samletprisTField = new TextField();
		renteTField = new TextField();
		mdlYdelseTField = new TextField();
		lblbilMangler = new Label("Vælg venligst en bil!");
		lbltlfUgyldig = new Label("Beklager, denne bruger findes ikke i systemet!");
		tlfSoegBtn = new Button("S�g");
		opretKundeBtn = new Button("Opret ny kunde");

		// location
		tlfTField.relocate(320.0, 200.0);
		tlfTField.setPrefHeight(51.0);
		tlfTField.setPrefWidth(272.0);
		tlfSoegBtn.relocate(595, 200);

		mdlYdelseTField.relocate(320, 525);
		mdlYdelseTField.setPrefHeight(35);
		mdlYdelseTField.setPrefWidth(272.0);
		mdlYdelseTField.setVisible(false);
		mdlYdelseTField.setEditable(false);

		samletprisTField.relocate(320.0, 575.0);
		samletprisTField.setPrefHeight(35);
		samletprisTField.setPrefWidth(272.0);
		samletprisTField.setVisible(false);
		samletprisTField.setEditable(false);

		renteTField.relocate(320, 475);
		renteTField.setPrefHeight(35);
		renteTField.setPrefWidth(272.0);
		renteTField.setVisible(false);
		renteTField.setEditable(false);
		// 60179802
		bilnavnTField.relocate(320, 275);
		bilnavnTField.setPrefHeight(35.0);
		bilnavnTField.setPrefWidth(272.0);
		bilprisTField.relocate(320, 325);
		bilprisTField.setPrefHeight(35.0);
		bilprisTField.setPrefWidth(272.0);
		udbetalingTField.relocate(320, 375);
		udbetalingTField.setPrefHeight(35.0);
		udbetalingTField.setPrefWidth(272.0);
		laengdeTField.relocate(320, 425);
		laengdeTField.setPrefHeight(35.0);
		laengdeTField.setPrefWidth(272.0);
		vaelgBilBtn.relocate(595, 277);
		vaelgBilBtn.setPrefHeight(30.0);
		vaelgBilBtn.setPrefWidth(80);
		indsaetBil.setPrefWidth(200);
		indsaetBil.relocate(620, 575);
		indsaetBil.setVisible(false);
		bilList1.setPrefHeight(300);
		bilList1.setPrefWidth(250);
		// bilList1.relocate(0, 275);
		bilList1.relocate(600, 270);
		bilList1.setVisible(false);
		opretLaanBtn.relocate(360, 630);
		lgnNameLbl.relocate(800, 675);
		lblbilMangler.relocate(642, 535);
		lblbilMangler.setVisible(false);
		lblbilMangler.setTextFill(Color.WHITE);
		ferraripic.setFitWidth(350);
		ferraripic.setFitHeight(130);
		ferraripic.setImage(ferrari);
		ferraripic.relocate(285, 25);
		bilnavnTField.setEditable(false);
		bilprisTField.setEditable(true);
		lbltlfUgyldig.relocate(250, 290);
		lbltlfUgyldig.setTextFill(Color.WHITE);
		lbltlfUgyldig.setVisible(false);
		opretKundeBtn.setVisible(false);
		opretKundeBtn.relocate(375, 380);

		// Setting prompt text style to only appear once a character has been inserted
		mdlYdelseTField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
		renteTField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
		tlfTField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
		laengdeTField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
		udbetalingTField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
		bilprisTField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
		bilnavnTField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
		samletprisTField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");

		// Setting prompt text
		mdlYdelseTField.setPromptText("Månedlig ydelse");
		renteTField.setPromptText("Rente på lånet");
		samletprisTField.setPromptText("Samlet pris for lånetilbuddet");
		tlfTField.setPromptText("Indtast telefonnummer");
		laengdeTField.setPromptText("Lånets længde:");
		udbetalingTField.setPromptText("Udbetaling:");
		bilprisTField.setPromptText("Bilpris:");
		bilnavnTField.setPromptText("Bilnavn:");
		bilnavnTField.setVisible(false);
		bilprisTField.setVisible(false);
		udbetalingTField.setVisible(false);
		laengdeTField.setVisible(false);
		vaelgBilBtn.setVisible(false);
		opretLaanBtn.setVisible(false);
		opretStatusLbl.setVisible(false);

		bilprisTField.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = numberFormatter.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));

		udbetalingTField.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = numberFormatter.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));

		laengdeTField.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = numberFormatter.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));

		// Event handlers
		opretLaanBtn.setOnAction(e -> LaanCheck());
		tlfSoegBtn.setOnAction(e -> tlfnrCheck());
		opretKundeBtn.setOnAction(e -> startKundeUI());

		vaelgBilBtn.setOnAction(e -> {
			bilList1.setVisible(true);
			indsaetBil.setVisible(true);
			vaelgBilBtn.setDisable(true);
			if (isClicked == false) {
				for (int i = 0; i < getbiler.size(); i++) {
					String bilnavnList = getbiler.get(i).getBilnavn();
					getbiler1.add(bilnavnList);
					isClicked = true;
				}
			}

		});

		indsaetBil.setOnAction(e -> {

			valgtBilNavn = bilList1.getSelectionModel().getSelectedItem();
			// String �ge = valgtBilNavn();

			if (valgtBilNavn == null) {
				lblbilMangler.setVisible(true);
			} else {
				bilnavnTField.setText(valgtBilNavn);
				bilList1.setVisible(false);
				indsaetBil.setVisible(false);
				vaelgBilBtn.setDisable(false);
				lblbilMangler.setVisible(false);
				// int bilprisList = getbiler.get(i).getBilPris();
			}

		});

		// font size
		opretKundeBtn.setFont(new Font(24));
		lblbilMangler.setFont(new Font(24));
		lbltlfUgyldig.setFont(new Font(24));
		renteTField.setFont(new Font(14));
		samletprisTField.setFont(new Font(14));
		mdlYdelseTField.setFont(new Font(14));
		tlfSoegBtn.setFont(new Font(24));
		tlfTField.setFont(new Font(24));
		opretLaanBtn.setFont(new Font(24));
		laengdeTField.setFont(new Font(14));
		udbetalingTField.setFont(new Font(14));
		bilprisTField.setFont(new Font(14));
		bilnavnTField.setFont(new Font(14));
		vaelgBilBtn.setFont(new Font(12));
		indsaetBil.setFont(new Font(14));
		opretStatusLbl.setFont(new Font(24));

		// Set color to pane
		pane3.setStyle("-fx-background-color: #F40808");

		// Add to pane
		pane3.getChildren().addAll(opretLaanBtn, opretStatusLbl, ferraripic, tlfTField, lgnNameLbl, bilnavnTField,
				bilprisTField, udbetalingTField, laengdeTField, renteTField, mdlYdelseTField, samletprisTField,
				vaelgBilBtn, indsaetBil, bilList1, lblbilMangler, lbltlfUgyldig, tlfSoegBtn, opretKundeBtn);

		// Show scene
		scene3 = new Scene(pane3, 950, 700);
		opretLaanStage.setScene(scene3);
		opretLaanStage.show();
	}

	private void LaanCheck() {
		// add tlf
		bilnavnGetText = bilnavnTField.getText();
		bilprisGetText = bilprisTField.getText();
		udbetalingGetText = udbetalingTField.getText();
		laanleangdeGetText = laengdeTField.getText();

		if (checkTFieldsEmpty() == true) {
		} else {
			getRente();

			renteString = Double.toString(rente);
			samletPrisString = Double.toString(samletPris);
			mdlydelseString = Double.toString(mdlYdelse);

			// Setting format for our textfields

			renteTField.setText("Renten er: " + renteString + "%");
			samletprisTField.setText("Samlede pris: " + samletPrisString + "kr.");
			mdlYdelseTField.setText(
					"M�nedlig ydelse: " + mdlydelseString + ",- pr. m�ned over " + laanleangdeGetText + "mdr.");

			OpretLaan();
		}

	}

	private boolean checkTFieldsEmpty() {
		TFieldLogik tflog = new TFieldLogik();
		TFieldResult tfCheck = tflog.TFieldCheck(tlfGetText, bilnavnGetText, bilprisGetText, udbetalingGetText,
				laanleangdeGetText, rente);
		opretStatusLbl.setTextFill(Color.WHITE);

		switch (tfCheck) {
		case bilnavnIsEmpty:
			opretStatusLbl.setText("Udfyld venligst bilnavn");
			opretStatusLbl.relocate(350, 575);
			return true;
		case bilprisIsEmpty:
			opretStatusLbl.setText("Udfyld venligst bilpris");
			opretStatusLbl.relocate(355, 575);
			return true;
		case udbetalingIsEmpty:
			opretStatusLbl.setText("Udfyld venligst udbetaling");
			opretStatusLbl.relocate(335, 575);
			return true;
		case laanleangdeIsEmpty:
			opretStatusLbl.setText("Udfyld venligst l�nets l�ngde");
			opretStatusLbl.relocate(320, 575);
			return true;
		case allIsEmpty:
			opretStatusLbl.setText("Udfyld venligst alle manglende felter");
			opretStatusLbl.relocate(275, 575);
			return true;
		case Success:
			opretStatusLbl.setText("L�n Oprettet!");
			opretStatusLbl.relocate(400, 575);
			return false;
		}
		return true;
	}

	private void tlfnrCheck() {
		tlfGetText = tlfTField.getText();
		LaanCheckTlf tlfLogic = new LaanCheckTlf();
		if (tlfLogic.CheckTlfDB(tlfGetText) == true) {
			bilnavnTField.setVisible(true);
			bilprisTField.setVisible(true);
			udbetalingTField.setVisible(true);
			laengdeTField.setVisible(true);
			vaelgBilBtn.setVisible(true);
			lbltlfUgyldig.setVisible(false);
			opretKundeBtn.setVisible(false);
			opretLaanBtn.setVisible(true);
			opretStatusLbl.setVisible(true);
			samletprisTField.setVisible(true);
			renteTField.setVisible(true);
			mdlYdelseTField.setVisible(true);

		}

		else if (tlfLogic.CheckTlfDB(tlfGetText) == false) {
			bilnavnTField.setVisible(false);
			bilprisTField.setVisible(false);
			udbetalingTField.setVisible(false);
			laengdeTField.setVisible(false);
			vaelgBilBtn.setVisible(false);
			opretLaanBtn.setVisible(false);
			lbltlfUgyldig.setVisible(true);
			opretKundeBtn.setVisible(true);
			opretStatusLbl.setVisible(false);
			samletprisTField.setVisible(false);
			renteTField.setVisible(false);
			mdlYdelseTField.setVisible(false);

		}

	}

	private void startKundeUI() {
		OpretKundeUI kundeUI = new OpretKundeUI();
		kundeUI.start();
	}

	private void getRente() {
		LaanCheckTlf tlflogic = new LaanCheckTlf();
		GetKV getCPR = new GetKV();
		cprnr = tlflogic.getCPRNR(tlfGetText);
		rente = getCPR.getRente(cprnr);
		mdlYdelse = getCPR.getMdlYdelse(cprnr, bilprisGetText, udbetalingGetText, laanleangdeGetText);
		samletPris = getCPR.getSamletPris(cprnr, bilprisGetText, udbetalingGetText);
		mdlYdelse = Math.round(mdlYdelse * 100.0) / 100.0;
		rente = Math.round(rente * 100.0) / 100.0;
		samletPris = Math.round(samletPris * 100.0) / 100.0;
		System.out.println("Rente format giver os: " + rente);
	}

	private void OpretLaan() {
		opretLaan laanlogic = new opretLaan();
		laanlogic.CreateLaan(tlfGetText, udbetalingGetText, laanleangdeGetText, rente, mdlYdelse, samletPris);
	}

}
