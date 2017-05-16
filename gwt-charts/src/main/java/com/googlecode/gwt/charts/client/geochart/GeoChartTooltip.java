/*
 * Copyright 2012 Rui Afonso
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.googlecode.gwt.charts.client.geochart;

import com.google.gwt.core.client.JavaScriptObject;

import com.googlecode.gwt.charts.client.options.TextStyle;
import com.googlecode.gwt.charts.client.options.TooltipTrigger;

/**
 * An object that specifies the tooltip style and behaviour.
 */
public class GeoChartTooltip extends JavaScriptObject {
	/**
	 * Default constructor.
	 * 
	 * @return a new object instance
	 */
	public static GeoChartTooltip create() {
		return createObject().cast();
	}

	protected GeoChartTooltip() {
	}

	/**
	 * An object that specifies the tooltip text style.
	 * 
	 * @param textStyle todo
	 */
	public final native void setTextStyle(TextStyle textStyle) /*-{
		this.textStyle = textStyle;
	}-*/;

	/**
	 * Sets the user interaction that causes the tooltip to be displayed.
	 * 
	 * @param trigger default is {@link TooltipTrigger#FOCUS}
	 */
	public final void setTrigger(TooltipTrigger trigger) {
		setTrigger(trigger.getName());
	}

	private final native void setTrigger(String trigger) /*-{
		this.trigger = trigger;
	}-*/;
}
