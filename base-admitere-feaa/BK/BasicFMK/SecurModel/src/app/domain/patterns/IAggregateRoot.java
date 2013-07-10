/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.patterns;

import java.io.Serializable;

/**
 *
 * @author catalin
 */
public interface IAggregateRoot extends Serializable{
    Boolean isAggregateValid();
}
