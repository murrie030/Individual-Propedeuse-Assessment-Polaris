/**
 * Dit script zorgt ervoor dat een speciek artikel op de productpagina terecht komt
 * met de bijbehorende gegevens die in de database zijn opgenomen.
 */

function artikelInformatieTonen() {
	
	/* Hier komt de fetch met het artikelnummer die in de localStorage is opgenomen
	 * Eerst naar de productpagina.html gaan en dan de fetch uitvoeren
	 */ 
	fetch("restservices/artikelen/" + window.localStorage.artikelnummer)
	.then(response => response.json())
    .then(function (myJson) {
    	
       /* 
        * Selecteer hiet elk element met getElementById en voeg de bijbehorende databasegegevens
        * van elk artikel toe aan het element
        */
    	document.getElementById("beeld1").src = myJson.afbeeldingslink;
    	document.getElementById("beeld2").src = myJson.afbeeldingslink2;
    	document.getElementById("beeld3").src = myJson.afbeeldingslink3;
    	document.getElementById("beeld4").src = myJson.afbeeldingslink4;
    	
    	document.getElementById("artikelnaam").innerHTML = myJson.artikelnaam;
    	document.getElementById("artikelnummer").innerHTML = "<b>Artikelnummer: </b>" + myJson.artikelnummer;
    	document.getElementById("prijs").innerHTML = "<b>Prijs: </b>€" + myJson.prijs;
    	document.getElementById("merk").innerHTML = "<b>Merk: </b>" + myJson.merk;
    	document.getElementById("kleur").innerHTML = "<b>Kleur: </b>" + myJson.kleur;
    	document.getElementById("voorraad").innerHTML = "<b>Aantal in de winkel: </b>" +  myJson.aantal;
    	document.getElementById("beschrijving").innerHTML = "<b>Productbeschrijving: </b>" +  myJson.beschrijving;
    });
}


/*
 * Voeg een artikel toe aan je verlanglijst. Mocht er al een artikel zijn toegevoegd met hetzelde 
 * artikelnummer, geeft het systeem een alert dat het product al eerder is toegevoegd.
 */
document.querySelector("#voeg_btn").addEventListener("click", function naarlijst(){
	var list = [];
	list = JSON.parse(window.localStorage.verlanglijst);
	if (list.includes(parseInt(window.localStorage.artikelnummer))){
		alert("Dit product is al eerder toegevoegd aan uw verlanglijst!");
	} else {
		list.push(parseInt(window.localStorage.artikelnummer));
		window.localStorage.setItem("verlanglijst", JSON.stringify(list));
		alert("Product is succesvol toegevoegd aan uw verlanglijst!");
	}
});

artikelInformatieTonen();

