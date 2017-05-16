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
package com.googlecode.gwt.charts.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.gwt.charts.client.event.Event;
import com.googlecode.gwt.charts.client.event.EventHandler;
import com.googlecode.gwt.charts.client.event.HandlerRef;
import com.googlecode.gwt.charts.client.options.Options;

import java.util.HashMap;

/**
 * This class is used as a widget wrapper for all chart types.
 * Supports automatically resizing via {@link RequiresResize}, which means that all parents
 * must implement {@link com.google.gwt.user.client.ui.ProvidesResize} for this to work.
 * It also supports attach/detach funcionality.
 * 
 * @param <T> the chart options type
 */
public abstract class ChartWidget<T extends Options> extends Widget implements RequiresResize {
	protected ChartObject chartObject;
	private DataSource data;
	private Options options;
	private HashMap<HandlerRef, EventHandler> eventMap;
	private boolean unloaded;
	private boolean pending;

	/**
	 * Creates a new ChartWidget.
	 */
	public ChartWidget() {
		super();
		Element chartDiv = DOM.createDiv();
		chartObject = createChartObject(chartDiv);
		setElement(chartDiv);
		eventMap = new HashMap<HandlerRef, EventHandler>();
	}

	/**
	 * Clears the chart, and releases all of its allocated resources.
	 */
	public void clearChart() {
		chartObject.clearChart();
	}

	/**
	 * Draws the visualization on the page. Behind the scenes this can be fetching a graphic from a server or creating
	 * the graphic on the page using the linked visualization code. You should call this method every time the data or
	 * options change.
	 * 
	 * @param data a {@link DataTable} or {@link DataView} holding the data to use to draw the chart.
	 * @see <a href="http://developers.google.com/chart/interactive/docs/reference.html#visdraw">draw API reference</a>
	 */
	public void draw(DataSource data) {
		draw(data, null);
	}

	/**
	 * Draws the visualization on the page. Behind the scenes this can be fetching a graphic from a server or creating
	 * the graphic on the page using the linked visualization code. You should call this method every time the data or
	 * options change.
	 * 
	 * @param data a {@link DataTable} or {@link DataView} holding the data to use to draw the chart.
	 * @param options A map of name/value pairs of custom options.
	 * @see <a href="http://developers.google.com/chart/interactive/docs/reference.html#visdraw">draw API reference</a>
	 */
	public void draw(DataSource data, T options) {
		this.data = data;
		this.options = options;
		redraw();
	}

	/**
	 * Fires an event to all listeners.
	 * 
	 * @param event the event object to fire
	 */
	public void fireEvent(Event event) {
		chartObject.trigger(event.getEventName(), event.getProperties());
	}

	@Override
	public void onResize() {
		redraw();
	}

	/**
	 * Redraws the chart with last used data and options.
	 */
	public void redraw() {
		if (pending) {
			return;
		}
		pending = true;
		// Double deferred command because of layout issues
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

			@Override
			public void execute() {
				Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

					@Override
					public void execute() {
						redrawNow();
					}
				});
			}
		});
	}

	/**
	 * Removes all existing handlers from this chart.
	 */
	public void removeAllHandlers() {
		eventMap.clear();
		chartObject.removeAllListeners();
	}

	/**
	 * Removes a single handler matching the given handler reference.
	 * 
	 * @param handlerRef an handler reference
	 */
	public void removeHandler(HandlerRef handlerRef) {
		eventMap.remove(handlerRef);
		chartObject.removeListener(handlerRef);
	}

	/**
	 * Call this method to register to receive events fired by a visualization hosted on your page. Note that this will
	 * not work for visualizations embedded in a gadget.
	 * 
	 * @param <H> the handler type
	 * @param handler the function to call when the event is fired
	 * @return the new handler reference. Can be used for removing by calling {@link #removeHandler(HandlerRef)}.
	 */
	protected final <H extends EventHandler> HandlerRef addHandler(H handler) {
		HandlerRef handlerRef = chartObject.addListener(handler.getEventName(), handler);
		eventMap.put(handlerRef, handler);
		return handlerRef;
	}

	protected abstract ChartObject createChartObject(Element parent);

	@Override
	protected void onLoad() {
		if (!unloaded) {
			return;
		}
		unloaded = false;
		recreate();
		redraw();
	}

	@Override
	protected void onUnload() {
		this.unloaded = true;
	}

	protected void recreate() {
		chartObject = createChartObject(getElement());
		for (EventHandler eventHandler : eventMap.values()) {
			chartObject.addListener(eventHandler.getEventName(), eventHandler);
		}
	}

	protected void redrawNow() {
		if (data != null) {
			chartObject.draw(data, options);
		}
		pending = false;
	}
}
