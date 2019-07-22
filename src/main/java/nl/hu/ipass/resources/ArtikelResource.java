package nl.hu.ipass.resources;

import javax.json.*;
import javax.ws.rs.*;

import nl.hu.ipass.domeinmodel.Artikel;
import nl.hu.ipass.serviceproviders.ArtikelService;
import nl.hu.ipass.serviceproviders.ServiceProvider;


/*
 *  Dit is de URI die je aanroept wanneer je de JSON terug wilt 
 *  van alle artikelen.
 */
@Path("/artikelen")
public class ArtikelResource {
	
	
	/*
	 * De GET-methode is de Read van Crud.
	 * Met de GET-methode kunnen we data verkrijgen/ophalen.
	 */
	@GET
	@Produces("application/json")
	public String getArtikelen() {
		ArtikelService service = ServiceProvider.getArtikelService();
		JsonArrayBuilder jab = Json.createArrayBuilder();			// Hier wordt de JsonArrayBuilder aangemaakt met de attributen.
		
		for (Artikel a : service.getAlleArtikelen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("artikelnummer", 	 a.getArtikelnummer());
			job.add("artikelnaam", 	 	 a.getArtikelnaam());
			job.add("merk", 		 	 a.getMerk());
			job.add("prijs", 		  	 a.getPrijs());
			job.add("maat", 	     	 a.getMaat());
			job.add("kleur", 		 	 a.getKleur());
			job.add("beschrijving",  	 a.getBeschrijving());
			job.add("aantal", 		 	 a.getAantal());
			job.add("afbeeldingslink",   a.getAfbeeldingslink());
			job.add("afbeeldingslink2",  a.getAfbeeldingslink2());
			job.add("afbeeldingslink3",  a.getAfbeeldingslink3());
			job.add("afbeeldingslink4",  a.getAfbeeldingslink4());
			
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	
	/*
	 * Met deze URI kun je informatie over één specifiek artikel vinden 
	 * met het artikelnummer in JSON-formaat.
	 */
	@GET
	@Path("{artikelnummer}")
	@Produces("application/json")
	public String getArtikelByNumber(@PathParam("artikelnummer") int artikelnummer) {
		ArtikelService service = ServiceProvider.getArtikelService();
		Artikel a = service.getArtikelByNumber(artikelnummer);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("artikelnummer", 	 a.getArtikelnummer());
		job.add("artikelnaam", 	 	 a.getArtikelnaam());
		job.add("merk", 		 	 a.getMerk());
		job.add("prijs", 		  	 a.getPrijs());
		job.add("maat", 	     	 a.getMaat());
		job.add("kleur", 		 	 a.getKleur());
		job.add("beschrijving",  	 a.getBeschrijving());
		job.add("aantal", 		 	 a.getAantal());
		job.add("afbeeldingslink",   a.getAfbeeldingslink());
		job.add("afbeeldingslink2",  a.getAfbeeldingslink2());
		job.add("afbeeldingslink3",  a.getAfbeeldingslink3());
		job.add("afbeeldingslink4",  a.getAfbeeldingslink4());
		
		return job.build().toString();
	}
}
