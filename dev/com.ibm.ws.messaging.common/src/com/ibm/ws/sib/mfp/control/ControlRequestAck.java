/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.sib.mfp.control;

/**
 * ControlRequestAck extends the general ControlMessage interface and provides
 * get/set methods for the fields specific to a Control Request Ack Message.
 *
 */
public interface ControlRequestAck extends ControlMessage {

  /* **************************************************************************/
  /* Get Methods                                                              */
  /* **************************************************************************/

  /**
   * Get the DME version for this request
   *
   * @return A long containing the DME version
   */
  public long getDMEVersion();

  /**
   * Get the Tick value for this request.
   *
   * @return A long[] containing the Tick values
   */
  public long[] getTick();

  /* **************************************************************************/
  /* Set Methods                                                              */
  /* **************************************************************************/

  /**
   * Set the DME version for this request
   *
   * @param value A long containing the DME version
   */
  public void setDMEVersion(long value);

  /**
   * Set the Tick values for this request.
   *
   * @param values A long[] containing the Tick values
   */
  public void setTick(long[] values);

}
