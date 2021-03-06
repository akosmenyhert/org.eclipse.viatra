/*******************************************************************************
 * Copyright (c) 2004-2013, Abel Hegedus, Zoltan Ujhelyi and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Abel Hegedus, Zoltan Ujhelyi - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.emf.runtime.rules;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.incquery.runtime.evm.api.RuleSpecification;
import org.eclipse.incquery.runtime.evm.api.event.EventFilter;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * Helper collection for grouping transformation rules 
 */
public class TransformationRuleGroup<Rule extends ITransformationRule<?, ?>> extends HashSet<Rule> {
	
	private final class RuleTransformerFunction implements
			Function<Rule, RuleSpecification<?>> {
		@Override
		public RuleSpecification<?> apply(Rule rule) {
			return rule.getRuleSpecification();
		}
	}
	
	private static final long serialVersionUID = 7057984500208333710L;
	
	public TransformationRuleGroup() {
		super();
	}
	
	public TransformationRuleGroup(Rule... rules) {
		super(rules.length);
		for (Rule rule : rules) {
			add(rule);
		}
	}
	
	public Set<RuleSpecification<?>> getRuleSpecifications() {
		return Sets.newHashSet(Collections2.transform(this, new RuleTransformerFunction()));
	}
	
	public Multimap<RuleSpecification<?>, EventFilter<?>> getFilteredRuleMap() {
		Multimap<RuleSpecification<?>, EventFilter<?>> map = HashMultimap.<RuleSpecification<?>, EventFilter<?>>create();
		for (Rule element : this) {
            RuleSpecification<?> spec = element.getRuleSpecification();
            EventFilter<?> filter = element.getFilter() != null ? element.getFilter() : spec.createEmptyFilter();
			map.put(spec, filter);
		}
		return map;
	}
}
