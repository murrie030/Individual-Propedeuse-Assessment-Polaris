/**
 * Dit script zorgt voor de CRUD-operaties van het bedrijf.
 */

/*
 * Hier worden alle bedrijfsnamen gezet in de select-box.
 */ 
function bedrijfsnamenInvullen() {    
    document.getElementById("bedrijfsnaam-select").options.length = 1;
    
	fetch("restservices/bedrijf")
	.then(response => response.json())
	.then(function (myJson){
		for (let bedrijfsnaam of myJson){
			var select = document.getElementById("bedrijfsnaam-select");
			var option = document.createElement("option");
			option.text = bedrijfsnaam.bedrijfsnaam;
			option.value = bedrijfsnaam.bedrijfsnaam;
			select.add(option);
		}
	});
	
	document.getElementById("bedrijfsnaam").value = "";
	document.getElementById("adres").value = "";
	document.getElementById("postcode").value = "";
	document.getElementById("plaats").value = "";
}


/*
 * Als je op een bedrijfsnaam klikt in de select-box krijg je de informatie van dat bedrijf.
 */
document.querySelector("#bedrijfsnaam-select").addEventListener("change", function bedrijf_ophalen() {
	let bedrijfsnaam = document.getElementById("bedrijfsnaam-select").value;
	fetch("restservices/bedrijf/" + bedrijfsnaam)
	.then(response => response.json())
	.then(function (myJson){
		for (let bedrijf of myJson){
			document.getElementById("bedrijfsnaam").value = bedrijf.bedrijfsnaam;
			document.getElementById("adres").value = bedrijf.adres;
			document.getElementById("postcode").value = bedrijf.postcode;
			document.getElementById("plaats").value = bedrijf.plaats;
		}
	});
});


/*
 * POST-methode van de clientside.
 * Hier wordt de HTML-form gebruikt om data naar de server te versturen.
 * De FormData zet alle form-input in een object.
 * De URLSearchParams zorgt voor de juiste codering.
 */
document.querySelector("#post").addEventListener("click", function bedrijfToevoegen() {
	let formData = new FormData(document.querySelector("#bedrijfsform"));
	let encData = new URLSearchParams(formData.entries());
	fetch("restservices/bedrijf/toevoegen", {method: 'POST', body: encData})
	.then(function (response) {
      if (response.ok) {							// response-status = 200 OK
        alert("Het bedrijf is toegevoegd!");
      } else {
    	alert("Kan het bedrijf niet toevoegen!");
      }
    })
});


/*
 * PUT-methode van de clientside.
 * Hier wordt de HTML-form gebruikt om data naar de server te versturen.
 * De FormData zet alle form-input in een object.
 * De URLSearchParams zorgt voor de juiste codering.
 */
document.querySelector("#put").addEventListener("click", function bedrijfWijzign() {
	let formData = new FormData(document.querySelector("#bedrijfsform"));
	let encData = new URLSearchParams(formData.entries());
	fetch("restservices/bedrijf/wijzigen", {method: 'PUT', body: encData})
	.then(function (response) {
      if (response.ok) {							// response-status = 200 OK
        alert("Het bedrijf is gewijzigd!");
      } else {
    	alert("Kan het bedrijf niet wijzigen!");
      }
    })
});


/*
 * DELETE-methode van de clientside.
 * De methode geeft een response terug wanneer het bedrijf is verwijderd (response.ok),
 * wanneer het bedrijf niet gevonden is (response.status == 404) en 
 * wanneer het bedrijf niet verwijderd kan worden.
 */
document.querySelector("#delete").addEventListener("click", function () {
  var bedrijfsnaam = document.querySelector("#bedrijfsnaam-select").value;

  fetch("restservices/bedrijf/"+bedrijfsnaam, { method: 'DELETE' })
    .then(function (response) {
      if (response.ok) {							// response-status = 200 OK
        alert("Het bedrijf is verwijderd!");
      } else if (response.status == 404) {
        alert("Bedrijf not found!");
      } else {
    	alert("Kan het bedrijf niet verwijderen!");
      }
    })
});

bedrijfsnamenInvullen();
