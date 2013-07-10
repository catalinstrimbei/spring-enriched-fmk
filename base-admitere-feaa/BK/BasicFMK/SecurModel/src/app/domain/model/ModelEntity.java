/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author catalin
 */
@Entity
public class ModelEntity implements Serializable{
    @Id private Integer id;
}
