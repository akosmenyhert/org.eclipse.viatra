/*******************************************************************************
 * Copyright (c) 2004-2015, Peter Lunk, Zoltan Ujhelyi and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Peter Lunk - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.emf.runtime.debug.configuration

import com.google.common.collect.Lists
import java.util.List
import org.eclipse.viatra.emf.runtime.adapter.IAdapterConfiguration
import org.eclipse.viatra.emf.runtime.adapter.ITransformationAdapter
import org.eclipse.viatra.emf.runtime.debug.ManualConflictResolver
import org.eclipse.viatra.emf.runtime.debug.ui.IDebuggerUI
import org.eclipse.viatra.emf.runtime.debug.ui.impl.ConsoleDebuggerUI

/**
 * Configuration class that defines the manual conflict resolver.
 * 
 */
class ManualConflictResolverConfiguration implements IAdapterConfiguration{
	List<ITransformationAdapter> adapters
	
	new(IDebuggerUI usedUI){
		adapters = Lists.newArrayList
		adapters.add(new ManualConflictResolver(usedUI))
	}
	
	new(){
		val usedUI = new ConsoleDebuggerUI
		adapters = Lists.newArrayList
		adapters.add(new ManualConflictResolver(usedUI))
	}
	
	override getAdapters() {
		return adapters
	}
}