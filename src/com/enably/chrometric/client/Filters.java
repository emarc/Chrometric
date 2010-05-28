/*-
	Copyright Marc Englund 2010
	
	This file is available under the GNU General Public License.
	
	The matrixes pertaining to color blindness are are copyright 
	Matthew Wickline and the Human-Computer Interaction Resource Network
	and are available for non-commercial use. For commercial use, please
    contact the Human-Computer Interaction Resource Network http://hcirn.com/ 
	For details, see http://colorlab.wickline.org/colorblind/colorlab/docs/legal.html
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


public class Filters {

	public static final Filter PROTANOPIA = new Filter("Protanopia","Present in <b>1% of males</b>. A form of dichromatism in which red appears dark.", new double[]{
	//
			56.667, 43.333, 0, 0, 0, // r
			55.833, 44.167, 0, 0, 0, // g
			0, 24.167, 75.833, 0, 0, // b
			0, 0, 0, 100, 0 // a
	});

	public static final Filter PROTANOMALY = new Filter("Protanomaly","Present in <b>1% of males, 0.01% of females</b>. Poor red-green hue discrimination.", new double[]{
	//
			81.667, 18.333, 0, 0, 0, // r
			33.333, 66.667, 0, 0, 0, // g
			0, 12.5, 87.5, 0, 0, // b
			0, 0, 0, 100, 0 // a
	});
	
	public static final Filter DEUTERANOPIA = new Filter("Deuteranopia","Present in <b>1% of males</b>. Moderately affects red-green hue discrimination.", new double[]{
	//
			62.5, 37.5, 0, 0, 0, // r
			70, 30, 0, 0, 0, // g
			0, 30, 70, 0, 0, // b
			0, 0, 0, 100, 0 // a
	});
	
	public static final Filter DEUTERANOMALY = new Filter("Deuteranomaly","Present in <b>6% of males, 0.4% of females</b>. By far the most common type, mildly affecting red-green hue discrimination.", new double[]{
	//
			// 0.8,0.2,0,0,0, 
			// 0.258,0.742,0,0,0, 
			// 0,0.142,0.858,0,0, 
			// 0,0,0,1,0, 
			// 0,0,0,0,1
			
			80, 20, 0, 0, 0, // r
			25.833, 74.167, 0, 0, 0, // g
			0, 14.167, 85.833, 0, 0, // b
			0, 0, 0, 100, 0 // a
	});
	
	public static final Filter TRITANOPIA = new Filter("Tritanopia","<b>Very rare</b> (less than 1% of males and females). Only two cone pigments present and a total absence of blue retinal receptors.", new double[]{
	//
			95, 5, 0, 0, 0, // r
			0, 43.333, 56.667, 0, 0, // g
			0, 47.5, 52.5, 0, 0, // b
			0, 0, 0, 100, 0 // a
	});
	
	public static final Filter TRITANOMALY = new Filter("Tritanomaly","<b>Rare</b> (0.01% for both male and female). Affects blue-yellow hue discrimination. Unlike most other forms, it is not sex-linked.", new double[]{
	//
			96.667, 3.333, 0, 0, 0, // r
			0, 73.333, 26.667, 0, 0, // g
			0, 18.333, 81.667, 0, 0, // b
			0, 0, 0, 100, 0 // a
	});
	public static final Filter ACHROMATOPSIA = new Filter("Achromatopsia","<b>Exceedingly rare</b>. Inability to distinguish any colors.", new double[]{
	//
			29.9, 58.7, 11.4, 0, 0, // r
			29.9, 58.7, 11.4, 0, 0, // g
			29.9, 58.7, 11.4, 0, 0, // b
			0, 0, 0, 100, 0 // a
	});

	public static final Filter ACHROMATOMALY = new Filter("Achromatomaly","<b>Exceedingly rare</b>. Lacking most color vision.", new double[]{
	//
			61.8, 32, 6.2, 0, 0, // r
			16.3, 77.5, 6.2, 0, 0, // g
			16.3, 32.0, 51.6, 0, 0, // b
			0, 0, 0, 100, 0 // a
	});
	
	public static final Filter CONTRAST = new Filter("Reduced contrast","A.k.a my laptop screen in sunlight.", new double[]{
		30,0,0,0,4445,0,30,0,0,4445,0,0,30,0,4445,0,0,0,100,0
	});	

	public static native void applyFilter(double[] filter)/*-{
		if (filter == null) {
			$wnd.htmlLoader.filters = null;
		} else {
			var f = [];
			for (var i=0;i<filter.length;i++) {
				f.push(filter[i]/100);
			}
			$wnd.htmlLoader.filters = [new $wnd.runtime.flash.filters.ColorMatrixFilter(f)];
		}
		//$wnd.htmlLoader.filters = [new $wnd.runtime.flash.filters.BlurFilter()];
	}-*/;

	public static native void applyBlur(int amount)/*-{
		if (amount < 1) {
			$wnd.htmlLoader.filters = null;
		} else {
			$wnd.htmlLoader.filters = [new $wnd.runtime.flash.filters.BlurFilter(amount,amount)];
		}
	}-*/;
	
	public static class Filter {
		private String name;
		private String desc;
		private double[] matrix;
		
		public Filter(String name, String desc, double[] matrix) {
			this.name = name;
			this.desc = desc;
			this.matrix = matrix;
		}

		public String getName() {
			return name;
		}

		public String getDesc() {
			return desc;
		}
		
		public void apply() {
			applyFilter(matrix);
		}
	}

}