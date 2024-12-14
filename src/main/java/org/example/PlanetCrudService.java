package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlanetCrudService {

    public Planet savePlanet(Planet planet) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(planet);
            transaction.commit();
            return planet;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Failed to save planet", e);
        } finally {
            session.close();
        }
    }

    public void deletePlanet(String planetId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, planetId);
            if (planet != null) {
                session.delete(planet);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Failed to delete planet", e);
        } finally {
            session.close();
        }
    }

    public Planet findPlanetById(String id) {
        Session session = HibernateUtil.getSession();
        try {
            return session.get(Planet.class, id);
        } finally {
            session.close();
        }
    }
}
