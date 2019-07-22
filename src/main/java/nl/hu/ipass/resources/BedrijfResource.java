package nl.hu.ipass.resources;

import java.util.ArrayList;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import nl.hu.ipass.domeinmodel.Bedrijf;
import nl.hu.ipass.serviceproviders.BedrijfService;
import nl.hu.ipass.serviceproviders.ServiceProvider;

/*
 * Dit is de URI die je aanroept wanneer je de JSON terug wilt
 * van het bedrijf.
 */
@Path("/bedrijf")
public class BedrijfResource {
	private BedrijfService service = ServiceProvider.getBedrijfService();

	/*
	 * De GET-methode is de Read van Crud. Met de GET-methode kunnen we data
	 * verkrijgen/ophalen.
	 */
	@GET
	@Path("{bedrijfsnaam}")
	@Produces("application/json")
	public String getBedrijf(@PathParam("bedrijfsnaam") String bedrijfsnaam) {
		BedrijfService service = ServiceProvider.getBedrijfService();
		JsonArrayBuilder jab = Json.createArrayBuilder(); 		// Hier wordt de JsonArrayBuilder aangemaakt met de
																// attributen.

		Bedrijf b = service.findBedrijfByName(bedrijfsnaam); 	// Dit dient voor het returnen van slechts één bedrijf.

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("bedrijfsnaam", b.getBedrijfnaam());
		job.add("adres", b.getAdres());
		job.add("postcode", b.getPostcode());
		job.add("plaats", b.getPlaats());

		jab.add(job);

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Produces("application/json")
	public String bedrijfsNamen() {
		BedrijfService service = ServiceProvider.getBedrijfService();
		JsonArrayBuilder jab = Json.createArrayBuilder(); 		// Hier wordt de JsonArrayBuilder aangemaakt met de
																// attributen.

		ArrayList<String> bn = service.bedrijfsnamen(); 		// Dit dient voor het returnen van meerdere bedrijfsnamen.
		JsonObjectBuilder job = Json.createObjectBuilder();

		for (String bedrijfsnaam : bn) {
			job.add("bedrijfsnaam", bedrijfsnaam);
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	/*
	 * De POST-methode is de Create van CRUD. De POST verzend of ontvangt informatie
	 * van de service. Wijzigt de resource op een unieke manier.
	 */
	@POST
	@Path("/toevoegen")
	public Response bedrijfToevoegen(@FormParam("bedrijfsnaam") String bedrijfsnaam, @FormParam("adres") String adres,
			@FormParam("postcode") String postcode, @FormParam("plaats") String plaats) {

		if (service.saveBedrijf(bedrijfsnaam, adres, postcode, plaats)) {
			return Response.ok().build();
		}
		return Response.status(404).build();
	}

	/*
	 * De PUT-methode is de Update van CRUD. De PUT-methode zorgt voor de update
	 * (en/of de insert) (invoegen/bewerken) van de resource.
	 */
	@PUT
	@Path("/wijzigen")
	@Produces("application/json")
	public Response updateBedrijf(@FormParam("bedrijfsnaam") String bedrijfsnaam,
								  @FormParam("adres") String adres,
								  @FormParam("postcode") String postcode, 
								  @FormParam("plaats") String plaats) {

		if (service.updateBedrijf(bedrijfsnaam, adres, postcode, plaats)) {
			return Response.ok().build();
		}
		return Response.status(404).build();
	}

	/*
	 * De DELETE-methode is de Delete van CRUD. De DELETE-methode verwijdert de
	 * resource, als deze bestaat. DELETE-requests hoeven alleen een HTTP-status
	 * code te returnen.
	 */
	@DELETE
	@Path("{bedrijfsnaam}")
	@Produces("application/json")
	public Response deleteBedrijf(@PathParam("bedrijfsnaam") String bedrijfsnaam) {

		if (service.deleteBedrijf(bedrijfsnaam)) {
			return Response.ok().build();
		}
		return Response.status(404).build();
	}
}
