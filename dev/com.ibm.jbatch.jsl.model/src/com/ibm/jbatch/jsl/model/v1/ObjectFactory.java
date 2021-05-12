/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vIBM 2.2.3-11/28/2011 06:21 AM(foreman)-
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.06.11 at 05:49:00 PM EDT
//

package com.ibm.jbatch.jsl.model.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.ibm.jbatch.jsl.model package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups. Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory implements com.ibm.jbatch.jsl.model.ObjectFactory {

    private final static QName _Job_QNAME = new QName("http://xmlns.jcp.org/xml/ns/javaee", "job");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ibm.jbatch.jsl.model
     *
     */
    public ObjectFactory() {
    }

    /** {@inheritDoc} */
    @Override
    public ExceptionClassFilter createExceptionClassFilter() {
        return new ExceptionClassFilter();
    }

    /** {@inheritDoc} */
    @Override
    public JSLJob createJSLJob() {
        return new JSLJob();
    }

    /** {@inheritDoc} */
    @Override
    public Batchlet createBatchlet() {
        return new Batchlet();
    }

    /** {@inheritDoc} */
    @Override
    public Analyzer createAnalyzer() {
        return new Analyzer();
    }

    /** {@inheritDoc} */
    @Override
    public ItemWriter createItemWriter() {
        return new ItemWriter();
    }

    /** {@inheritDoc} */
    @Override
    public Partition createPartition() {
        return new Partition();
    }

    /** {@inheritDoc} */
    @Override
    public Chunk createChunk() {
        return new Chunk();
    }

    /** {@inheritDoc} */
    @Override
    public Flow createFlow() {
        return new Flow();
    }

    /** {@inheritDoc} */
    @Override
    public Next createNext() {
        return new Next();
    }

    /** {@inheritDoc} */
    @Override
    public JSLProperties createJSLProperties() {
        return new JSLProperties();
    }

    /** {@inheritDoc} */
    @Override
    public PartitionPlan createPartitionPlan() {
        return new PartitionPlan();
    }

    /** {@inheritDoc} */
    @Override
    public Listener createListener() {
        return new Listener();
    }

    /** {@inheritDoc} */
    @Override
    public ItemReader createItemReader() {
        return new ItemReader();
    }

    /** {@inheritDoc} */
    @Override
    public ItemProcessor createItemProcessor() {
        return new ItemProcessor();
    }

    /** {@inheritDoc} */
    @Override
    public Listeners createListeners() {
        return new Listeners();
    }

    /** {@inheritDoc} */
    @Override
    public PartitionReducer createPartitionReducer() {
        return new PartitionReducer();
    }

    /** {@inheritDoc} */
    @Override
    public Stop createStop() {
        return new Stop();
    }

    /** {@inheritDoc} */
    @Override
    public CheckpointAlgorithm createCheckpointAlgorithm() {
        return new CheckpointAlgorithm();
    }

    /** {@inheritDoc} */
    @Override
    public PartitionMapper createPartitionMapper() {
        return new PartitionMapper();
    }

    /** {@inheritDoc} */
    @Override
    public Step createStep() {
        return new Step();
    }

    /** {@inheritDoc} */
    @Override
    public Collector createCollector() {
        return new Collector();
    }

    /** {@inheritDoc} */
    @Override
    public Property createProperty() {
        return new Property();
    }

    /** {@inheritDoc} */
    @Override
    public Split createSplit() {
        return new Split();
    }

    /** {@inheritDoc} */
    @Override
    public End createEnd() {
        return new End();
    }

    /** {@inheritDoc} */
    @Override
    public Decision createDecision() {
        return new Decision();
    }

    /** {@inheritDoc} */
    @Override
    public Fail createFail() {
        return new Fail();
    }

    /** {@inheritDoc} */
    @Override
    public ExceptionClassFilter.Include createExceptionClassFilterInclude() {
        return new ExceptionClassFilter.Include();
    }

    /** {@inheritDoc} */
    @Override
    public ExceptionClassFilter.Exclude createExceptionClassFilterExclude() {
        return new ExceptionClassFilter.Exclude();
    }

    /** {@inheritDoc} */
    @XmlElementDecl(namespace = "http://xmlns.jcp.org/xml/ns/javaee", name = "job")
    public JAXBElement<JSLJob> createJob(JSLJob value) {
        return new JAXBElement<JSLJob>(_Job_QNAME, JSLJob.class, null, value);
    }

}
