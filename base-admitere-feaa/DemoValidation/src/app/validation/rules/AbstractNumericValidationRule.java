/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.validation.rules;

import app.validation.controller.ValidationRule;

/**
 *
 * @author catalin
 */
public abstract class AbstractNumericValidationRule extends ValidationRule{
    protected Number getNumberValueOf(String stringValue, Class numberType) {
        if (numberType.equals(Short.class)) {
            return Short.valueOf(stringValue);
        }
        if (numberType.equals(Integer.class)) {
            return Integer.valueOf(stringValue);
        }
        if (numberType.equals(Long.class)) {
            return Long.valueOf(stringValue);
        }
        if (numberType.equals(Float.class)) {
            return Float.valueOf(stringValue);
        }
        else {
            return Double.valueOf(stringValue);
        }
    }

    protected int compare(Number firstValue, Number secondValue) {
        Class numberType = firstValue.getClass();
        if (numberType.equals(Short.class)) {
            short shortFirstValue = firstValue.shortValue();
            short shortSecondValue = secondValue.shortValue();
            if (shortFirstValue == shortSecondValue) {
                return 0;
            }
            if (shortFirstValue > shortSecondValue) {
                return 1;
            }
            else {
                return -1;
            }
        }
        if (numberType.equals(Integer.class)) {
            int intFirstValue = firstValue.intValue();
            int intSecondValue = secondValue.intValue();
            if (intFirstValue == intSecondValue) {
                return 0;
            }
            if (intFirstValue > intSecondValue) {
                return 1;
            }
            else {
                return -1;
            }
        }
        if (numberType.equals(Long.class)) {
            long longFirstValue = firstValue.longValue();
            long longSecondValue = secondValue.longValue();
            if (longFirstValue == longSecondValue) {
                return 0;
            }
            if (longFirstValue > longSecondValue) {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            double doubleFirstValue = firstValue.doubleValue();
            double doubleSecondValue = secondValue.doubleValue();
            if (doubleFirstValue == doubleSecondValue) {
                return 0;
            }
            if (doubleFirstValue > doubleSecondValue) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }
}
