/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.validation.controller;

import app.validation.patterns.IValidationRule;
import app.validation.support.ValidationChangeSupportException;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyDescriptor;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author catalin
 * For:
 * 1. Validation Rules' Repository
 * 2. Validation Rule activation and manage validation results
 * org.jdesktop.beansbinding.Validator
 */

public class ValidationController implements VetoableChangeListener, Serializable {
    private transient Logger logger = Logger.getLogger(ValidationController.class.getName());
    public static final String VALIDATION_RULES_ATTR = "validation.rules";

    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        logger.info("Process validation rules for " + evt.getPropertyName() + "/" + evt.getClass().getName());
        processRulesFor(evt);
    }

    public void setRules(Map<String, Object> rulesMap) throws Exception {
        Class entityClass;
        String fieldName;
        String ruleDef;
        String className;
        for (Map.Entry<String, Object> ruleMap : rulesMap.entrySet()) {
            try {
                ruleDef = ruleMap.getKey();
                className = ruleDef.substring(0, ruleDef.indexOf("@"));
                fieldName = ruleDef.substring(ruleDef.indexOf("@") + 1);
                entityClass = Class.forName(className);
                if (ruleMap.getValue() instanceof List) {
                    registerRuleFor(entityClass, fieldName, (List) ruleMap.getValue());
                }
                if (ruleMap.getValue() instanceof IValidationRule) {
                    registerRuleFor(entityClass, fieldName,
                            Arrays.asList(new IValidationRule[]{
                                (IValidationRule)ruleMap.getValue()
                            }));
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ValidationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ValidationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(ValidationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    private String prepareMessageFromExceptionList(List<PropertyVetoException> multiExceptionList){
        String message = "";
        StringBuffer messageBuffer = new StringBuffer();

        for(PropertyVetoException ex: multiExceptionList){
            messageBuffer.append(ex.getMessage() + "\n");
        }

        message = messageBuffer.toString();
        return message;
    }

    private void prepareVetoFeedbackException(PropertyChangeEvent evt, List<PropertyVetoException> vetoExceptions)
    throws PropertyVetoException {
        throw new ValidationChangeSupportException(evt, vetoExceptions,
                prepareMessageFromExceptionList(vetoExceptions));
    }

    private void processRulesFor(PropertyChangeEvent evt) throws PropertyVetoException{
        ValidationEvent validationEvent = new ValidationEvent(evt);
        try {
            PropertyDescriptor descriptor = getDescriptor(validationEvent.getSource().getClass(),
                    (evt.getPropertyName()));

            // Get rules
            List<IValidationRule> rules = (List<IValidationRule>) descriptor.getValue(VALIDATION_RULES_ATTR);
            if (rules == null){
                String rulesTarget = validationEvent.getSource().getClass().getName() + "@" + evt.getPropertyName();
                rules = ruleRepository.get(rulesTarget);
            }
            List<PropertyVetoException> vetoExceptions = new ArrayList<PropertyVetoException>();
            for (IValidationRule rule : rules) {
                System.out.println("Rule of type " +
                        rule.getClass().getName());
                // apply rule
                try {
                    rule.validate(validationEvent);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(this.getClass().getName()).info(
                            "Catched property veto exception: " + ex.getMessage());
                    //throw ex;
                    vetoExceptions.add(ex);
                }
            }

            if (!vetoExceptions.isEmpty())
                prepareVetoFeedbackException(evt, vetoExceptions);
            
        } catch (Exception ex) {
            if (ex instanceof PropertyVetoException)
                    throw (PropertyVetoException)ex;
            else{
                Logger.getLogger(this.getClass().getName()).info("Alta eroare [nu de validare]: ");
            }
        }
    }

    // make rule repository
    private transient Map<String, List<IValidationRule>> ruleRepository = new HashMap<String, List<IValidationRule>>();
    private void registerRuleFor(Class entityClass, String fieldName, List<IValidationRule> rules)
            throws Exception {
        PropertyDescriptor descriptor = getDescriptor(entityClass, fieldName);
        logger.info("Register rule for: " + entityClass.getName() + "@" + fieldName);
        descriptor.setValue(VALIDATION_RULES_ATTR, rules);
        ruleRepository.put(entityClass.getName() + "@" + fieldName, rules);
    }

    static PropertyDescriptor getDescriptor(Class clazz, String name)
            throws Exception {
        BeanInfo info = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        for (int i = 0; i < pds.length; i++) {
            if (pds[i].getName().equals(name)) {
                return pds[i];
            }
        }
        throw new IllegalArgumentException("Property " + name + " not found.");
    }
}
