/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import objetosNegocio.Calificacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import objetosNegocio.Alumno;

/**
 *
 * @author angel
 */
public class AlumnoJpaController implements Serializable {

    public AlumnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) {
        if (alumno.getCalificaciones() == null) {
            alumno.setCalificaciones(new ArrayList<Calificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Calificacion> attachedCalificaciones = new ArrayList<Calificacion>();
            for (Calificacion calificacionesCalificacionToAttach : alumno.getCalificaciones()) {
                calificacionesCalificacionToAttach = em.getReference(calificacionesCalificacionToAttach.getClass(), calificacionesCalificacionToAttach.getId());
                attachedCalificaciones.add(calificacionesCalificacionToAttach);
            }
            alumno.setCalificaciones(attachedCalificaciones);
            em.persist(alumno);
            for (Calificacion calificacionesCalificacion : alumno.getCalificaciones()) {
                Alumno oldAlumnoOfCalificacionesCalificacion = calificacionesCalificacion.getAlumno();
                calificacionesCalificacion.setAlumno(alumno);
                calificacionesCalificacion = em.merge(calificacionesCalificacion);
                if (oldAlumnoOfCalificacionesCalificacion != null) {
                    oldAlumnoOfCalificacionesCalificacion.getCalificaciones().remove(calificacionesCalificacion);
                    oldAlumnoOfCalificacionesCalificacion = em.merge(oldAlumnoOfCalificacionesCalificacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getId());
            List<Calificacion> calificacionesOld = persistentAlumno.getCalificaciones();
            List<Calificacion> calificacionesNew = alumno.getCalificaciones();
            List<String> illegalOrphanMessages = null;
            for (Calificacion calificacionesOldCalificacion : calificacionesOld) {
                if (!calificacionesNew.contains(calificacionesOldCalificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calificacion " + calificacionesOldCalificacion + " since its alumno field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Calificacion> attachedCalificacionesNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionesNewCalificacionToAttach : calificacionesNew) {
                calificacionesNewCalificacionToAttach = em.getReference(calificacionesNewCalificacionToAttach.getClass(), calificacionesNewCalificacionToAttach.getId());
                attachedCalificacionesNew.add(calificacionesNewCalificacionToAttach);
            }
            calificacionesNew = attachedCalificacionesNew;
            alumno.setCalificaciones(calificacionesNew);
            alumno = em.merge(alumno);
            for (Calificacion calificacionesNewCalificacion : calificacionesNew) {
                if (!calificacionesOld.contains(calificacionesNewCalificacion)) {
                    Alumno oldAlumnoOfCalificacionesNewCalificacion = calificacionesNewCalificacion.getAlumno();
                    calificacionesNewCalificacion.setAlumno(alumno);
                    calificacionesNewCalificacion = em.merge(calificacionesNewCalificacion);
                    if (oldAlumnoOfCalificacionesNewCalificacion != null && !oldAlumnoOfCalificacionesNewCalificacion.equals(alumno)) {
                        oldAlumnoOfCalificacionesNewCalificacion.getCalificaciones().remove(calificacionesNewCalificacion);
                        oldAlumnoOfCalificacionesNewCalificacion = em.merge(oldAlumnoOfCalificacionesNewCalificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = alumno.getId();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Calificacion> calificacionesOrphanCheck = alumno.getCalificaciones();
            for (Calificacion calificacionesOrphanCheckCalificacion : calificacionesOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alumno (" + alumno + ") cannot be destroyed since the Calificacion " + calificacionesOrphanCheckCalificacion + " in its calificaciones field has a non-nullable alumno field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumno.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Alumno findAlumno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumno> rt = cq.from(Alumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
