/**
 * Copyright 2009-2020 PrimeTek.
 * <p>
 * Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ci.siracide.opus.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class GuestPreferences implements Serializable {

	private String menuMode = "layout-menu-horizontal";

	private String theme = "blue-light";

	private String layout = "layout-blue";

	private String wrapperMode = "";

	private List<ComponentTheme> componentThemes = new ArrayList<ComponentTheme>();

	private List<LayoutThemeColored> layoutThemesColored = new ArrayList<LayoutThemeColored>();

	private List<LayoutThemeImages> layoutThemesImages = new ArrayList<LayoutThemeImages>();

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getMenuMode() {
		return this.menuMode;
	}

	public void setMenuMode(String menuMode) {
		this.menuMode = menuMode;
	}

	public String getWrapperMode() {
		return this.wrapperMode;
	}

	public void setWrapperMode(String wrapperMode) {
		this.wrapperMode = wrapperMode;
	}

	public List<ComponentTheme> getComponentThemes() {
		return componentThemes;
	}

	public List<LayoutThemeColored> getLayoutThemesColored() {
		return layoutThemesColored;
	}

	public List<LayoutThemeImages> getLayoutThemesImages() {
		return layoutThemesImages;
	}

	public void changeLayoutThemeColored(LayoutThemeColored layoutTheme) {
		this.layout = layoutTheme.getFile();
		this.theme = layoutTheme.getComponentTheme();
	}

	public void changeLayoutThemeImages(LayoutThemeImages layoutTheme) {
		this.layout = layoutTheme.getFile();
		this.theme = layoutTheme.getComponentTheme();
	}

	@PostConstruct
	public void init() {
		componentThemes.add(new ComponentTheme("Amber Accent", "amber-accent", "amber-accent.svg"));
		componentThemes.add(new ComponentTheme("Amber Light", "amber-light", "amber-light.svg"));
		componentThemes.add(new ComponentTheme("Amber Dark", "amber-dark", "amber-dark.svg"));
		componentThemes.add(new ComponentTheme("Blue Accent", "blue-accent", "blue-accent.svg"));
		componentThemes.add(new ComponentTheme("Blue Light", "blue-light", "blue-light.svg"));
		componentThemes.add(new ComponentTheme("Blue Dark", "blue-dark", "blue-dark.svg"));
		componentThemes.add(new ComponentTheme("Blue Gray Accent", "bluegray-accent", "bluegray-accent.svg"));
		componentThemes.add(new ComponentTheme("Blue Gray Light", "bluegray-light", "bluegray-light.svg"));
		componentThemes.add(new ComponentTheme("Blue Gray Dark", "bluegray-dark", "bluegray-dark.svg"));
		componentThemes.add(new ComponentTheme("Brown Accent", "brown-accent", "brown-accent.svg"));
		componentThemes.add(new ComponentTheme("Brown Light", "brown-light", "brown-light.svg"));
		componentThemes.add(new ComponentTheme("Brown Dark", "brown-dark", "brown-dark.svg"));
		componentThemes.add(new ComponentTheme("Cyan Accent", "cyan-accent", "cyan-accent.svg"));
		componentThemes.add(new ComponentTheme("Cyan Light", "cyan-light", "cyan-light.svg"));
		componentThemes.add(new ComponentTheme("Cyan Dark", "cyan-dark", "cyan-dark.svg"));
		componentThemes.add(new ComponentTheme("Deep Orange Accent", "deeporange-accent", "deeporange-accent.svg"));
		componentThemes.add(new ComponentTheme("Deep Orange Light", "deeporange-light", "deeporange-light.svg"));
		componentThemes.add(new ComponentTheme("Deep Orange Dark", "deeporange-dark", "deeporange-dark.svg"));
		componentThemes.add(new ComponentTheme("Deep Purple Accent", "deeppurple-accent", "deeppurple-accent.svg"));
		componentThemes.add(new ComponentTheme("Deep Purple Light", "deeppurple-light", "deeppurple-light.svg"));
		componentThemes.add(new ComponentTheme("Deep Purple Dark", "deeppurple-dark", "deeppurple-dark.svg"));
		componentThemes.add(new ComponentTheme("Green Accent", "green-accent", "green-accent.svg"));
		componentThemes.add(new ComponentTheme("Green Light", "green-light", "green-light.svg"));
		componentThemes.add(new ComponentTheme("Green Dark", "green-dark", "green-dark.svg"));
		componentThemes.add(new ComponentTheme("Indigo Accent", "indigo-accent", "indigo-accent.svg"));
		componentThemes.add(new ComponentTheme("Indigo Light", "indigo-light", "indigo-light.svg"));
		componentThemes.add(new ComponentTheme("Indigo Dark", "indigo-dark", "indigo-dark.svg"));
		componentThemes.add(new ComponentTheme("Light Blue Accent", "lightblue-accent", "lightblue-accent.svg"));
		componentThemes.add(new ComponentTheme("Light Blue Light", "lightblue-light", "lightblue-light.svg"));
		componentThemes.add(new ComponentTheme("Light Blue Dark", "lightblue-dark", "lightblue-dark.svg"));
		componentThemes.add(new ComponentTheme("Light Green Accent", "lightgreen-accent", "lightgreen-accent.svg"));
		componentThemes.add(new ComponentTheme("Light Green Light", "lightgreen-light", "lightgreen-light.svg"));
		componentThemes.add(new ComponentTheme("Light Green Dark", "lightgreen-dark", "lightgreen-dark.svg"));
		componentThemes.add(new ComponentTheme("Lime Accent", "lime-accent", "lime-accent.svg"));
		componentThemes.add(new ComponentTheme("Lime Light", "lime-light", "lime-light.svg"));
		componentThemes.add(new ComponentTheme("Lime Dark", "lime-dark", "lime-dark.svg"));
		componentThemes.add(new ComponentTheme("Orange Accent", "orange-accent", "orange-accent.svg"));
		componentThemes.add(new ComponentTheme("Orange Light", "orange-light", "orange-light.svg"));
		componentThemes.add(new ComponentTheme("Orange Dark", "orange-dark", "orange-dark.svg"));
		componentThemes.add(new ComponentTheme("Pink Accent", "pink-accent", "pink-accent.svg"));
		componentThemes.add(new ComponentTheme("Pink Light", "pink-light", "pink-light.svg"));
		componentThemes.add(new ComponentTheme("Pink Dark", "pink-dark", "pink-dark.svg"));
		componentThemes.add(new ComponentTheme("Purple Accent", "purple-accent", "purple-accent.svg"));
		componentThemes.add(new ComponentTheme("Purple Light", "purple-light", "purple-light.svg"));
		componentThemes.add(new ComponentTheme("Purple Dark", "purple-dark", "purple-dark.svg"));
		componentThemes.add(new ComponentTheme("Teal Accent", "teal-accent", "teal-accent.svg"));
		componentThemes.add(new ComponentTheme("Teal Light", "teal-light", "teal-light.svg"));
		componentThemes.add(new ComponentTheme("Teal Dark", "teal-dark", "teal-dark.svg"));
		componentThemes.add(new ComponentTheme("Yellow Accent", "yellow-accent", "yellow-accent.svg"));
		componentThemes.add(new ComponentTheme("Yellow Light", "yellow-light", "yellow-light.svg"));
		componentThemes.add(new ComponentTheme("Yellow Dark", "yellow-dark", "yellow-dark.svg"));

		layoutThemesImages.add(new LayoutThemeImages("Aqua", "layout-aqua", "aqua.png", "cyan-light"));
		layoutThemesImages.add(new LayoutThemeImages("Arecaceae", "layout-arecaceae", "arecaceae.png", "green-light"));
		layoutThemesImages.add(new LayoutThemeImages("Canvas", "layout-canvas", "canvas.png", "indigo-light"));
		layoutThemesImages.add(new LayoutThemeImages("Cross", "layout-cross", "cross.png", "brown-light"));
		layoutThemesImages.add(new LayoutThemeImages("Dream", "layout-dream", "dream.png", "teal-light"));
		layoutThemesImages.add(new LayoutThemeImages("Emerald", "layout-emerald", "emerald.png", "bluegray-light"));
		layoutThemesImages.add(new LayoutThemeImages("Focus", "layout-focus", "focus.png", "bluegray-light"));
		layoutThemesImages.add(new LayoutThemeImages("Forest", "layout-forest", "forest.png", "teal-light"));
		layoutThemesImages.add(new LayoutThemeImages("Fractal", "layout-fractal", "fractal.png", "teal-light"));
		layoutThemesImages.add(new LayoutThemeImages("Gem", "layout-gem", "gem.png", "amber-light"));
		layoutThemesImages.add(new LayoutThemeImages("Grass", "layout-grass", "grass.png", "lightgreen-light"));
		layoutThemesImages.add(new LayoutThemeImages("Jungfraujoch", "layout-jungfraujoch", "jungfraujoch.png", "lightblue-light"));
		layoutThemesImages.add(new LayoutThemeImages("MacKenzie", "layout-mackenzie", "mackenzie.png", "bluegray-light"));
		layoutThemesImages.add(new LayoutThemeImages("Madrid", "layout-madrid", "madrid.png", "bluegray-light"));
		layoutThemesImages.add(new LayoutThemeImages("Marble", "layout-marble", "marble.png", "purple-light"));
		layoutThemesImages.add(new LayoutThemeImages("Metro", "layout-metro", "metro.png", "bluegray-light"));
		layoutThemesImages.add(new LayoutThemeImages("Milan", "layout-milan", "milan.png", "bluegray-light"));
		layoutThemesImages.add(new LayoutThemeImages("Mist", "layout-mist", "mist.png", "cyan-light"));
		layoutThemesImages.add(new LayoutThemeImages("Osterreich", "layout-osterreich", "osterreich.png", "cyan-light"));
		layoutThemesImages.add(new LayoutThemeImages("Palm", "layout-palm", "palm.png", "bluegray-light"));
		layoutThemesImages.add(new LayoutThemeImages("Polygon", "layout-polygon", "polygon.png", "lightblue-light"));
		layoutThemesImages.add(new LayoutThemeImages("Sand", "layout-sand", "sand.png", "brown-light"));
		layoutThemesImages.add(new LayoutThemeImages("Stone", "layout-stone", "stone.png", "lightgreen-light"));
		layoutThemesImages.add(new LayoutThemeImages("Wood", "layout-wood", "wood.png", "green-light"));

		layoutThemesColored.add(new LayoutThemeColored("Amber", "layout-amber", "#FFB300", "#FF6F00", "amber-light"));
		layoutThemesColored.add(new LayoutThemeColored("Amethyst", "layout-amethyst", "#8E24AA", "#5E35B1", "purple-light"));
		layoutThemesColored.add(new LayoutThemeColored("Blue", "layout-blue", "#1E88E5", "#0D47A1", "blue-light"));
		layoutThemesColored.add(new LayoutThemeColored("Blue Grey", "layout-bluegray", "#546E7A", "#263238", "bluegray-light"));
		layoutThemesColored.add(new LayoutThemeColored("Brown", "layout-brown", "#6D4C41", "#3E2723", "brown-light"));
		layoutThemesColored.add(new LayoutThemeColored("Cyan", "layout-cyan", "#00ACC1", "#006064", "cyan-light"));
		layoutThemesColored.add(new LayoutThemeColored("Deep Orange", "layout-deeporange", "#F4511E", "#BF360C", "deeporange-light"));
		layoutThemesColored.add(new LayoutThemeColored("Deep Purple", "layout-deeppurple", "#5E35B1", "#311B92", "deeppurple-light"));
		layoutThemesColored.add(new LayoutThemeColored("Fate", "layout-fate", "#3949AB", "#D81B60", "blue-light"));
		layoutThemesColored.add(new LayoutThemeColored("Green", "layout-green", "#43A047", "#1B5E20", "green-light"));
		layoutThemesColored.add(new LayoutThemeColored("Indigo", "layout-indigo", "#3949AB", "#1A237E", "indigo-light"));
		layoutThemesColored.add(new LayoutThemeColored("Light Blue", "layout-lightblue", "#039BE5", "#01579B", "lightblue-light"));
		layoutThemesColored.add(new LayoutThemeColored("Light Green", "layout-lightgreen", "#7CB342", "#33691E", "lightgreen-light"));
		layoutThemesColored.add(new LayoutThemeColored("Lime", "layout-lime", "#C0CA33", "#827717", "lime-light"));
		layoutThemesColored.add(new LayoutThemeColored("Midnight", "layout-midnight", "#2c3640", "#15202b", "blue-light"));
		layoutThemesColored.add(new LayoutThemeColored("Orange", "layout-orange", "#FB8C00", "#E65100", "orange-light"));
		layoutThemesColored.add(new LayoutThemeColored("Pink", "layout-pink", "#D81B60", "#880E4F", "pink-light"));
		layoutThemesColored.add(new LayoutThemeColored("Purple", "layout-purple", "#8E24AA", "#4A148C", "purple-light"));
		layoutThemesColored.add(new LayoutThemeColored("Rhyme", "layout-rhyme", "#1E88E5", "#8E24AA", "blue-light"));
		layoutThemesColored.add(new LayoutThemeColored("Smoke", "layout-smoke", "#6c757d", "#343a40", "lightgreen-light"));
		layoutThemesColored.add(new LayoutThemeColored("Soul", "layout-soul", "#1E88E5", "#311B92", "blue-light"));
		layoutThemesColored.add(new LayoutThemeColored("Steel", "layout-steel", "#43464B", "#212325", "lightgreen-light"));
		layoutThemesColored.add(new LayoutThemeColored("Teal", "layout-teal", "#00897B", "#004D40", "teal-light"));
		layoutThemesColored.add(new LayoutThemeColored("Yellow", "layout-yellow", "#FDD835", "#F57F17", "yellow-light"));
	}

	public class ComponentTheme {
		String name;
		String file;
		String image;

		public ComponentTheme(String name, String file, String image) {
			this.name = name;
			this.file = file;
			this.image = image;
		}

		public String getName() {
			return this.name;
		}

		public String getFile() {
			return this.file;
		}

		public String getImage() {
			return this.image;
		}
	}

	public class LayoutThemeImages {
		String name;
		String file;
		String image;
		String componentTheme;

		public LayoutThemeImages(String name, String file, String image, String componentTheme) {
			this.name = name;
			this.file = file;
			this.image = image;
			this.componentTheme = componentTheme;
		}

		public String getName() {
			return this.name;
		}

		public String getFile() {
			return this.file;
		}

		public String getImage() {
			return this.image;
		}

		public String getComponentTheme() {
			return this.componentTheme;
		}
	}

	public class LayoutThemeColored {
		String name;
		String file;
		String color1;
		String color2;
		String componentTheme;

		public LayoutThemeColored(String name, String file, String color1, String color2, String componentTheme) {
			this.name = name;
			this.file = file;
			this.color1 = color1;
			this.color2 = color2;
			this.componentTheme = componentTheme;
		}

		public String getName() {
			return this.name;
		}

		public String getFile() {
			return this.file;
		}

		public String getColor1() {
			return this.color1;
		}

		public String getColor2() {
			return this.color2;
		}

		public String getComponentTheme() {
			return this.componentTheme;
		}
	}
}
