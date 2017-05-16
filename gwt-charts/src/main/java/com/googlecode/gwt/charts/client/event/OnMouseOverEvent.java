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
package com.googlecode.gwt.charts.client.event;

import com.googlecode.gwt.charts.client.Properties;

/**
 * Fired when the user mouses over a visual entity.
 */
public class OnMouseOverEvent extends Event {
	/**
	 * The event name.
	 */
	public static String NAME = "onmouseover";

	/**
	 * Creates a new event.
	 * 
	 * @param properties todo
	 */
	public OnMouseOverEvent(Properties properties) {
		super(NAME, properties);
	}

	/**
	 * Gets target data column index.
	 * 
	 * @return data column index
	 */
	public Integer getColumn() {
		if (properties.containsKey("column")) {
			return (int) properties.getNumber("column");
		}
		return null;
	}

	/**
	 * Gets target row column index.
	 * 
	 * @return row column index
	 */
	public Integer getRow() {
		if (properties.containsKey("row")) {
			return (int) properties.getNumber("row");
		}
		return null;
	}
}
