package com.googlecode.gwt.charts.client.apiloader;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

import com.googlecode.gwt.charts.client.util.ArrayHelper;

/**
 * An object to pass as the third parameter to load() call.
 */
public class ApiLoaderOptions extends JavaScriptObject {
	/**
	 * Default constructor.
	 * 
	 * @return a new object instance
	 */
	public static ApiLoaderOptions create() {
		return createObject().cast();
	}

	protected ApiLoaderOptions() {
	}

	/**
	 * The base domain from which to load the API.
	 * 
	 * @param baseDomain todo
	 */
	public final native void setBaseDomain(String baseDomain) /*-{
		this.base_domain = baseDomain;
	}-*/;

	/**
	 * The function to call once the script has loaded. If using the Auto-loading feature, this must specify a function
	 * name, not a function reference.
	 * 
	 * @param onLoad todo
	 */
	public final native void setCallback(Runnable onLoad) /*-{
		this.callback = function() {
			@com.googlecode.gwt.charts.client.apiloader.ExceptionHelper::runProtected(Ljava/lang/Runnable;)(onLoad);
		}
	}-*/;

	/**
	 * The language in which to localize the API's UI controls. This is specified as a ISO639 language code.
	 * 
	 * @param language todo
	 */
	public final native void setLanguage(String language) /*-{
		this.language = language;
	}-*/;

	/**
	 * A boolean that tells the API whether to load any style sheets typically associated with its controls. If you
	 * don't intend to use the default CSS, you can reduce the load time by setting this to true.
	 * 
	 * @param value the default setting is false.
	 */
	public final native void setNoCss(boolean value) /*-{
		this.nocss = value;
	}-*/;

	/**
	 * Specific parameters supported by a particular API (and usually very specific to the API). An alternative to
	 * passing in a parameter via a &lt;script&gt; tag.
	 * 
	 * @param otherParams todo
	 */
	public final native void setOtherParams(String otherParams) /*-{
		this.other_params = otherParams;
	}-*/;

	/**
	 * An array of strings specifying related packages to be read in along with the core API. For example, you could
	 * load "piechart" and "table" along with the Visualization API.
	 * 
	 * @param packages todo
	 */
	public final void setPackages(String... packages) {
		setPackages(ArrayHelper.createArray(packages));
	}

	private final native void setPackages(JsArrayString packages) /*-{
		this.packages = packages;
	}-*/;
}
