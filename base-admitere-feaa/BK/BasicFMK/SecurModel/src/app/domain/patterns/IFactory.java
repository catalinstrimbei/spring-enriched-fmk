/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.patterns;

/**
 *
 * @author catalin
 *
 * Create a complete objects: the internal state of new object is
 * initialized so that object invariant [integrity rules] is checked
 *
 */
public interface IFactory {
    <T> T getObject(Object[] args);
    <T> T getCopyOf(T object);
}
