/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author angel
 */
@Entity
@Table(name = "materiasserializacion")
public class MateriasSerializacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmateriasserializacion")
    private Integer id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "idmateria", nullable = false)
    private Materia materia;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "idmateriaseriada", nullable = false)
    private Materia materiaSeriada;

    
    


    public MateriasSerializacion() {
    }

    public MateriasSerializacion(Integer id, Materia materia, Materia materiaSeriada) {
        this.id = id;
        this.materia = materia;
        this.materiaSeriada = materiaSeriada;
    }
    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Materia getMateriaSeriada() {
        return materiaSeriada;
    }

    public void setMateriaSeriada(Materia materiaSeriada) {
        this.materiaSeriada = materiaSeriada;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriasSerializacion)) {
            return false;
        }
        MateriasSerializacion other = (MateriasSerializacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "objetosNegocio.MateriasSerializacion[ id=" + id + " ]";
    }
    
}
