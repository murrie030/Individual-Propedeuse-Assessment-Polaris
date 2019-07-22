/**
 * Dit is het script voor het laden van elk artikel in de herenschoen.html
 */

// Function for the images
function loadArtikelen(){
    fetch("restservices/artikelen")
        .then(response => response.json())
        .then(function (myJson) {
        for (const artikel of myJson){

        	// Create elements
			var div = document.createElement("div");
			div.setAttribute("class", "card");
			
			/*
			 * De eventListener opent een functie en die functie heeft een artikelobject als input.
			 * Deze functie roept dan de productpagina.html aan met het artikelnummer als variabele.
			 */
			var img = document.createElement("img");
			img.setAttribute("class", "card-img-top");
			img.setAttribute("src", artikel.afbeeldingslink);
			img.setAttribute("style", "width:100%");
			img.addEventListener("click", function(event){
				window.localStorage.setItem("artikelnummer", artikel.artikelnummer);
				location.href = 'productpagina.html';
			})
			
			var h3 = document.createElement("h3");
			h3.setAttribute("class", "card-title");
            h3.appendChild(document.createTextNode(artikel.artikelnaam));
            
            var p = document.createElement("p");
            p.appendChild(document.createTextNode( "€"+ artikel.prijs));
            p.setAttribute("class", "price");
            
            var p1 = document.createElement("p");
            
            var voegToeButton = document.createElement("button");
            voegToeButton.appendChild(document.createTextNode("Voeg toe aan verlanglijst"));
            voegToeButton.addEventListener("click", function naarlijst(){
            	var list = [];
            	list = JSON.parse(window.localStorage.verlanglijst);
            	if (list.includes(artikel.artikelnummer)){
            		alert("Dit product is al eerder toegevoegd aan uw verlanglijst!");
            	} else {
            		list.push(artikel.artikelnummer);
                	window.localStorage.setItem("verlanglijst", JSON.stringify(list));
                	alert("Product is succesvol toegevoegd aan uw verlanglijst!");
            	}
            });

            
            // Voeg alle gecreëerde childelementen toe aan de parent
            div.appendChild(img);
            div.appendChild(h3);
            div.appendChild(p);
            p1.appendChild(voegToeButton);
            div.appendChild(p1);
            
            document.querySelector(".card-deck").appendChild(div);
        }
    })
}

loadArtikelen();