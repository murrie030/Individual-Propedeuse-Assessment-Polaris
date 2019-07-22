package nl.hu.ipass.serviceproviders;

/*
 * De service provider maakt het mogelijk om te bepalen op one single point
 * in je applicatie of een service moet worden geïnstantieerd voor elke webservice-oproep 
 * of applicatie-scoped moet zijn. In dit geval application-scoped.
 */

public class ServiceProvider {
	private static ArtikelService artikelService = new ArtikelService();
	private static BedrijfService bedrijfService = new BedrijfService();

	public static ArtikelService getArtikelService() {
		return artikelService;
	}

	public static BedrijfService getBedrijfService() {
		return bedrijfService;
	}
	
}
