/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.domain.patterns;

import java.util.Collection;

/**
 *
 * @author catalin
 */
public interface IRepository {
    // operatii de cautare
    <T> T getById(Object id);
    <T> Collection<T> get(T sample);
    // operatii de access global
    <T> Collection<T> toCollection();
    <T> T[] toArray();
    // operatii de gestiune
    <T> boolean add(T entity);
    <T> boolean addAll(Collection<T> entities);
    <T> boolean remove(T entity);
    <T> boolean removeAll(Collection<T> entities);
    
    // proprietati statistice
    int size();
}
