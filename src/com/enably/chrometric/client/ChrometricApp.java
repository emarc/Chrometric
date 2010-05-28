/*-
	Copyright Marc Englund 2010
	
	This file is available under the GNU General Public License.
-*/

/*-
 This file is part of Chrometric.

 Chrometric is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Chrometric is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Chrometric.  If not, see <http://www.gnu.org/licenses/>.
 -*/
package com.enably.chrometric.client;

import com.enably.chrometric.client.Filters.Filter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ChrometricApp implements EntryPoint {

	private Frame frame;
	// private WebView frame; // TODO will allow better browsing & filtering

	private static PopupPanel tooltip;

	private SuggestBox urlField;
	private MultiWordSuggestOracle suggestions = new MultiWordSuggestOracle(
			" ./:");

	private static String currentMode = "Normal";
	private static Label typeLabel;

	public void onModuleLoad() {
		final DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.PX);
		RootPanel.get().setHeight("100%");
		RootPanel.get().add(mainPanel);
		mainPanel.setWidth("100%");
		mainPanel.setHeight("100%");

		final VerticalPanel controlPanel = new VerticalPanel();
		controlPanel.setWidth("100%");
		mainPanel.addNorth(controlPanel, 75);

		final DockLayoutPanel modePanel = new DockLayoutPanel(Unit.PX);
		modePanel.setWidth("100%");
		modePanel.setHeight("40px");
		controlPanel.add(modePanel);

		final FilterPanel filterPanel = new FilterPanel();
		modePanel.addEast(filterPanel, 325);

		final DockLayoutPanel topPanel = new DockLayoutPanel(Unit.PX);
		topPanel.setHeight("30px");
		topPanel.setWidth("100%");
		controlPanel.add(topPanel);

		frame = new Frame("http://enably.com/chrometric/startup/");
		frame.setWidth("100%");
		frame.setHeight("100%");
		mainPanel.add(frame);

		final PushButton openButton = new PushButton(new Image(
				"icons/open.png"));
		openButton.addStyleName("open");
		topPanel.addWest(openButton, 30);
		openButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				openFile();
			}

		});
		openButton.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				PushButton b = (PushButton) event.getSource();
				showTooltip("Open file", "Open a local HTML file", b);
			}

		});
		openButton.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				hideTooltip();
			}
		});

		suggestions.add("http://enably.com/chrometric");
		suggestions.add("http://en.wikipedia.org/wiki/Color_blindness");
		urlField = new SuggestBox(suggestions);
		urlField.setAnimationEnabled(true);
		urlField.setAutoSelectEnabled(false);
		urlField.addSelectionHandler(new SelectionHandler<Suggestion>() {
			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				String s = event.getSelectedItem().getReplacementString();

				frame.setUrl(s);
			}
		});
		urlField.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (!urlField.isSuggestionListShowing()
						&& event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					String s = urlField.getText();
					setUrl(s);
				}
			}
		});

		topPanel.add(urlField);

		final PushButton screenshotButton = new PushButton(new Image(
				"icons/screenshot.png"));
		screenshotButton.addStyleName("screenshot");
		topPanel.addEast(screenshotButton, 30);
		screenshotButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hideTooltip();
				screenshotButton.setFocus(false);
				DeferredCommand.addCommand(new Command() {

					@Override
					public void execute() {
						screenshot();

					}

				});
			}

		});
		screenshotButton.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				PushButton b = (PushButton) event.getSource();
				showTooltip("Screenshot",
						"Save a JPG screenshot of the current view.", b);
			}

		});
		screenshotButton.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				hideTooltip();
			}
		});

		typeLabel = new Label(currentMode);
		typeLabel.setWidth("300px");
		modePanel.add(typeLabel);

		PushButton logo = new PushButton(new Image("icons/logo.png"));
		logo.setStyleName("logo");
		logo.setTitle("Chrometric BETA");
		logo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				frame.setUrl("http://enably.com/chrometric/startup");
			}

		});
		logo.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				PushButton b = (PushButton) event.getSource();
				typeLabel.setText(b.getTitle());
			}

		});
		logo.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				typeLabel.setText(currentMode);
			}
		});
		modePanel.addWest(logo, 40);

	}

	public static void setMode(String name) {
		currentMode = name;
		typeLabel.setText(currentMode);
	}

	public static void showTooltip(String caption, String content,
			UIObject target) {
		if (tooltip == null) {
			tooltip = new PopupPanel();
			tooltip.setWidth("300px");
			tooltip.addStyleName("tooltip");
		}
		tooltip.setWidget(new HTML("<h2>" + caption + "</h2>" + content));
		tooltip.showRelativeTo(target);
	}

	public static void showTooltip(Filter filter, UIObject target) {
		showTooltip(filter.getName(), filter.getDesc(), target);
	}

	public static void hideTooltip() {
		if (tooltip != null) {
			tooltip.hide();
		}
	}

	public void setUrl(String s) {
		if (!s.startsWith("http://") && !s.startsWith("https://")
				&& !s.startsWith("file://")) {
			s = "http://" + s;
		}
		suggestions.add(s);
		urlField.setText(s);
		frame.setUrl(s);
	}

	private native void openFile()/*-{
		var filter = new $wnd.air.FileFilter("HTML", "*.html;*.htm"); 
		var file = $wnd.air.File.documentsDirectory; //new $wnd.air.File();
		var self = this;
		try {
		   file.addEventListener($wnd.air.Event.SELECT, function(event) {
		   	var s = event.target.url;
		   	self.@com.enably.chrometric.client.ChrometricApp::setUrl(Ljava/lang/String;)(s);				
		   });
		   file.browseForOpen("Open file");	    
		}
		catch (error)
		{
		   alert("Sorry, that did not work...");
		}
	}-*/;

	private native void screenshot()/*-{
		var dir = $wnd.air.File.documentsDirectory;
		var self = this;
		var scale = 1;
		try {
		   dir.addEventListener($wnd.air.Event.SELECT, function(event) {
		   	var file = event.target;
		   	if (file.nativePath.indexOf(".jpg") == -1) {
		   		file.nativePath += ".jpg";
		   	}
		   	var bitmapData = new $wnd.air.BitmapData(scale * $wnd.innerWidth, scale * $wnd.innerHeight);
		   	var scaleMatrix = new $wnd.air.Matrix();
		   	scaleMatrix.scale(scale, scale);
		   	bitmapData.draw($wnd.nativeWindow.stage, scaleMatrix);
		   	var jpegEncoder = new $wnd.runtime.com.adobe.images.JPGEncoder(85);
		   	var data = jpegEncoder.encode(bitmapData);
		   	bitmapData.dispose();	
		   	var stream = new $wnd.air.FileStream();
		   	stream.open(file, $wnd.air.FileMode.WRITE);
		   	stream.writeBytes(data, 0, data.length);
		   	stream.close();
		   	alert("Saved " + file.nativePath);
		   });
		   dir.browseForSave("Save JPG");	    
		}
		catch (error)
		{
		   alert("Sorry, that did not work...");
		}
	}-*/;

}