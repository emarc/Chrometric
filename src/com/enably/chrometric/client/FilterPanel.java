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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.UIObject;

public class FilterPanel extends HorizontalPanel {

	public FilterPanel() {
		setStyleName("filters");
		build();
	}
	
	private void build() {
		PushButton f = new PushButton(new Image("icons/chrometric_16.png"));
		f.setTitle("Normal");
		f.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Filters.applyFilter(null);
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.setMode(b.getTitle());
			}

		});
		f.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.showTooltip("Normal","Exceedingly common. Duh.", b);
			}

		});
		f.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				ChrometricApp.hideTooltip();
			}
		});
		add(f);

		f = new PushButton(new Image("icons/BLUR.png"));
		f.setTitle("Blur");
		f.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Filters.applyBlur(3);
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.setMode(b.getTitle());
			}

		});
		f.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.showTooltip("Blur","A.k.a forgotten glasses.", b);
			}

		});
		f.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				ChrometricApp.hideTooltip();
			}
		});
		add(f);

		f = new PushButton(new Image("icons/ACHROMATOPSIA.png"));
		f.setTitle("Reduced contrast");
		f.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Filters.CONTRAST.apply();
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.setMode(b.getTitle());
			}

		});
		f.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				ChrometricApp.showTooltip(Filters.CONTRAST, (UIObject)event.getSource());
			}

		});
		f.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				ChrometricApp.hideTooltip();
			}
		});
		add(f);

		f = new PushButton(new Image("icons/ACHROMATOMALY.png"));
		f.setTitle("ACHROMATOMALY");
		f.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Filters.ACHROMATOMALY.apply();
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.setMode(b.getTitle());
			}

		});
		f.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				ChrometricApp.showTooltip(Filters.ACHROMATOMALY, (UIObject)event.getSource());
			}

		});
		f.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				ChrometricApp.hideTooltip();
			}
		});
		add(f);

		f = new PushButton(new Image("icons/ACHROMATOPSIA.png"));
		f.setTitle("ACHROMATOPSIA");
		f.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Filters.ACHROMATOPSIA.apply();
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.setMode(b.getTitle());
			}

		});
		f.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				ChrometricApp.showTooltip(Filters.ACHROMATOPSIA, (UIObject)event.getSource());
			}

		});
		f.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				ChrometricApp.hideTooltip();
			}
		});
		add(f);

		f = new PushButton(new Image("icons/DEUTERANOMALY.png"));
		f.setTitle("DEUTERANOMALY");
		f.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Filters.DEUTERANOMALY.apply();
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.setMode(b.getTitle());
			}

		});
		f.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				ChrometricApp.showTooltip(Filters.DEUTERANOMALY, (UIObject)event.getSource());

			}

		});
		f.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				ChrometricApp.hideTooltip();
			}
		});
		add(f);

		f = new PushButton(new Image("icons/DEUTERANOPIA.png"));
		f.setTitle("DEUTERANOPIA");
		f.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Filters.DEUTERANOPIA.apply();
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.setMode(b.getTitle());
			}

		});
		f.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				ChrometricApp.showTooltip(Filters.DEUTERANOPIA, (UIObject)event.getSource());

			}

		});
		f.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				ChrometricApp.hideTooltip();
			}
		});
		add(f);

		f = new PushButton(new Image("icons/PROTANOPIA.png"));
		f.setTitle("PROTANOPIA");
		f.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Filters.PROTANOPIA.apply();
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.setMode(b.getTitle());

			}

		});
		f.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				ChrometricApp.showTooltip(Filters.PROTANOPIA, (UIObject)event.getSource());

			}

		});
		f.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				ChrometricApp.hideTooltip();
			}
		});
		add(f);

		f = new PushButton(new Image("icons/PROTANOMALY.png"));
		f.setTitle("PROTANOMALY");
		f.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Filters.PROTANOMALY.apply();
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.setMode(b.getTitle());
			}

		});
		f.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				ChrometricApp.showTooltip(Filters.PROTANOMALY, (UIObject)event.getSource());

			}

		});
		f.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				ChrometricApp.hideTooltip();
			}
		});
		add(f);

		f = new PushButton(new Image("icons/TRITANOMALY.png"));
		f.setTitle("TRITANOMALY");
		f.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Filters.TRITANOMALY.apply();
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.setMode(b.getTitle());
			}

		});
		f.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				ChrometricApp.showTooltip(Filters.TRITANOMALY, (UIObject)event.getSource());

			}

		});
		f.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				ChrometricApp.hideTooltip();
			}
		});
		add(f);

		f = new PushButton(new Image("icons/TRITANOPIA.png"));
		f.setTitle("TRITANOPIA");
		f.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Filters.TRITANOPIA.apply();
				PushButton b = (PushButton) event.getSource();
				ChrometricApp.setMode(b.getTitle());
			}

		});
		f.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				ChrometricApp.showTooltip(Filters.TRITANOPIA, (UIObject)event.getSource());

			}

		});
		f.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				ChrometricApp.hideTooltip();
			}
		});
		add(f);

	}
}
