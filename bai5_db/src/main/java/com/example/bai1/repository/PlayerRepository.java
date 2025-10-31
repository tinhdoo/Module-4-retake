package com.example.bai1.repository;

import com.example.bai1.entity.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerRepository implements IPlayerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> findAll() {
        TypedQuery<Player> query = entityManager.createQuery("FROM Player", Player.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public boolean add(Player player) {
        try {
            entityManager.persist(player);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Player findByCode(String code) {
        try {
            TypedQuery<Player> query = entityManager.createQuery(
                    "SELECT p FROM Player p WHERE p.code = :code", Player.class);
            query.setParameter("code", code);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    @Transactional
    @Override
    public void delete(Player player) {
        try {
            entityManager.remove(entityManager.contains(player) ? player : entityManager.merge(player));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
