/**
 * Dit script dient voor het creëren van een verlanglijst en dient de artikelen toe te voegen 
 * en te verwijderen.
 */
// Hier maak je de lijst 
function loaden(){
	var res = JSON.parse(window.localStorage.verlanglijst);
    if (res.length > 0) {
    	document.getElementById('verlanglijstLeeg').style.display = 'none';
    } else {
    	document.getElementById('verlanglijstLeeg').style.display = 'block';
    }
    fetch("restservices/artikelen")
        .then(response => response.json())
        .then(function (myJson) {
        for (const artikel of myJson){
        	if (res.includes(artikel.artikelnummer)){
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

                
                // Bij var artikelnummerString2 maak je van artikelnummer een String
                var verwijderen = document.createElement("button");
                verwijderen.appendChild(document.createTextNode("Verwijderen"));
                verwijderen.addEventListener("click", function asd(){
                	var list = [];
                	list = JSON.parse(window.localStorage.verlanglijst);
                	itemIndex = list.indexOf(artikel.artikelnummer);
                	if (itemIndex > -1) {
                		  list.splice(itemIndex, 1);
                	}
                	window.localStorage.setItem("verlanglijst", JSON.stringify(list));
                	alert("Product is succesvol verwijderd van uw verlanglijst!");
                	document.querySelector(".card-deck").innerHTML = '';
                	loaden();
    			});
                
                // Voeg alle gecreëerde childelementen toe aan de parent
                div.appendChild(img);
                div.appendChild(h3);
                div.appendChild(p);
                div.appendChild(verwijderen);
                
                document.querySelector(".card-deck").appendChild(div);
    		}
    	}
    });
}

loaden();