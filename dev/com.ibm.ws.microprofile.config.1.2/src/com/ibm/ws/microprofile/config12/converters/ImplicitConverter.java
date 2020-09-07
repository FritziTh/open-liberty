/*******************************************************************************
 * Copyright (c) 2018, 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.microprofile.config12.converters;

import java.util.function.Function;

import com.ibm.websphere.ras.Tr;
import com.ibm.websphere.ras.TraceComponent;
import com.ibm.websphere.ras.annotation.Trivial;
import com.ibm.ws.microprofile.config.converters.BuiltInConverter;

/**
 *
 */
public class ImplicitConverter extends BuiltInConverter {

    private static final TraceComponent tc = Tr.register(ImplicitConverter.class);
    private final Function<String, ?> implicitFunction;

    /**
     *
     * @param converterType The class to convert using
     */
    @Trivial
    public ImplicitConverter(Class<?> converterType) {
        super(converterType);
        this.implicitFunction = getImplicitFunction(converterType);
    }

    @Trivial
    protected <X> Function<String, X> getImplicitFunction(Class<X> converterType) {
        Function<String, X> implicitFunction = ConstructorFunction.getConstructorFunction(converterType);

        if (implicitFunction == null) {
            implicitFunction = MethodFunction.getValueOfFunction(converterType);

            if (implicitFunction == null) {
                implicitFunction = MethodFunction.getParseFunction(converterType);

                if (implicitFunction == null) {
                    throw new IllegalArgumentException(Tr.formatMessage(tc, "implicit.string.constructor.method.not.found.CWMCG0017E", converterType));
                } else {
                    if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
                        Tr.debug(tc, "Automatic converter for " + converterType + " using \"parse\"");
                    }
                }
            } else {
                if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
                    Tr.debug(tc, "Automatic converter for " + converterType + " using \"valueOf\"");
                }
            }
        } else {
            if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
                Tr.debug(tc, "Automatic converter for " + converterType + " using \"constructor\"");
            }
        }
        return implicitFunction;
    }

    /** {@inheritDoc} */
    @Override
    public Object convert(String value) {
        return this.implicitFunction.apply(value);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Implicit Converter for type " + getType();
    }
}
