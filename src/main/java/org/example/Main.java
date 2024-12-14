package org.example;

public class Main {
    public static void main(String[] args) {
        PlanetCrudService service = new PlanetCrudService();

        Planet planet = new Planet("EARTH1", "Earth");
        service.savePlanet(planet);
        System.out.println("Saved planet with ID: " + planet.getId());

        Planet foundPlanet = service.findPlanetById("EARTH1");
        System.out.println("Found planet: " + foundPlanet.getName());

        service.deletePlanet("EARTH1");
        Planet deletedPlanet = service.findPlanetById("EARTH1");
        System.out.println("Deleted planet found: " + (deletedPlanet == null ? "null" : deletedPlanet.getName()));
    }
}
