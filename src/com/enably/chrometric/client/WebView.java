package com.enably.chrometric.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/*
 * TODO currently not in use.
 * This will replace the iframe someday when I have time to workaround Adobes
 * stupid limitations. This could be trivial, you know. 
 */
public class WebView extends Widget {

	Element outer;
	Element inner;

	public WebView(String url) {
		outer = DOM.createDiv();
		setElement(outer);
		outer.getStyle().setOverflow(Overflow.AUTO);

		inner = DOM.createDiv();
		outer.appendChild(inner);

		// TODO overflow: auto, inner content, change when htmlLoader changes
		init(outer);
		setUrl(url);
	}

	public void setInnerSize(Integer w, Integer h) {
		inner.getStyle().setWidth(w, Unit.PX);
		inner.getStyle().setHeight(h, Unit.PX);

		int wh = outer.getClientHeight() - outer.getAbsoluteTop();
		int ww = outer.getClientWidth();

		setHTMLLoaderHeight(wh);
		setHTMLLoaderWidth(ww);
	}

	@Override
	public void setVisible(boolean visible) {
		setNativeVisible(visible);
		super.setVisible(visible);
	}

	private native void setNativeVisible(boolean visible) /*-{
		$wnd.loader.visible = visible;
	}-*/;

	public native void setUrl(String url) /*-{
		$wnd.loader.load(new $wnd.air.URLRequest(url));
	}-*/;

	private native void init(Element el) /*-{
		var self = this;
		var l = new $wnd.air.HTMLLoader();
		$wnd.loader = l;
		var defaultHost = new $wnd.runtime.flash.html.HTMLHost(true);
		l.htmlHost = defaultHost;
		$wnd.nativeWindow.stage.addChildAt(l,0);
		l.addEventListener("locationChange", function(evt){
			alert($wnd.loader.location);
		});

		l.y = 80;
		l.width = $wnd.nativeWindow.stage.stageWidth;
		l.height = $wnd.nativeWindow.stage.stageHeight - 80;
		$wnd.nativeWindow.stage.addEventListener("resize", function(evt){
			$wnd.loader.width = $wnd.nativeWindow.stage.stageWidth;
			$wnd.loader.height = $wnd.nativeWindow.stage.stageHeight - 80;
			
			var w = $wnd.loader.contentWidth;
			var h = $wnd.loader.contentHeight;			
			self.@com.enably.chrometric.client.WebView::setInnerSize(Ljava/lang/Integer;Ljava/lang/Integer;)(w)(h);
		});
		l.addEventListener("htmlBoundsChange", function(evt){
			alert($wnd.loader.location);
			var w = $wnd.loader.contentWidth;
			var h = $wnd.loader.contentHeight;			
			self.@com.enably.chrometric.client.WebView::setInnerSize(Ljava/lang/Integer;Ljava/lang/Integer;)(w)(h);
		});
	}-*/;

	private native void setHTMLLoaderHeight(int height) /*-{
		$wnd.loader.height = height;
	}-*/;

	private native void setHTMLLoaderWidth(int width) /*-{
		$wnd.loader.width = width;
	}-*/;

}
