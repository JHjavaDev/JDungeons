package com.jhjava.jdungeons.engine.render;

public class Light {
	public int[] lightMap;
	public int color, radius, diameter;

	public Light(int color, int radius) {
		this.color = color;
		this.radius = radius;
		this.diameter = radius * 2;

		lightMap = new int[diameter * diameter];

		for(int x = 0; x < diameter; x++) {
			for(int y = 0; y < diameter; y++) {
				float distance = (float)Math.sqrt((x - radius) * (x - radius) + (y - radius) * (y - radius));

				if(distance < radius) {
					lightMap[x + y * diameter] = Pixel.getColorPower(color, 1 - distance / radius);
				} else {
					lightMap[x + y * diameter] = 0xff000000;
				}
			}
		}
	}

	public int getLightValue(int x, int y) {
		if(x < 0 || x >= diameter || y < 0 || y >= diameter) {
			return 0xff000000;
		}
		return lightMap[x + y * diameter];
	}
}
