package com.jhjava.jdungeons.game;

import com.jhjava.jdungeons.engine.render.Pixel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {
	public Loader() {

	}

	public PlayState loadMap(String mapFile) {
		PlayState result = new PlayState();
		result.setName(mapFile);

		BufferedImage image = null;

		int w = 0;
		int h = 0;
		int[] pixels = null;

		try {
			image = ImageIO.read(Loader.class.getResourceAsStream("/com/jhjava/jdungeons/res/levels/" + mapFile + ".png"));
			w = image.getWidth();
			h = image.getHeight();
			pixels = image.getRGB(0, 0, w, h, null, 0, w);
			image.flush();
		} catch (Exception e) {
			System.err.println("Error: Could not load image " + mapFile);
			e.printStackTrace();
		}

		ArrayList<Wall> walls = new ArrayList<>();
		ArrayList<Enemy> enemies = new ArrayList<>();
		ArrayList<Chest> chests = new ArrayList<>();
		Player player = null;
		ArrayList<Floor> floors = new ArrayList<>();

		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				if (Pixel.getRedInt(pixels[x + y * w]) == 255) {
					floors.add(new Floor(x * 32, y * 32, "floor"));
				} else if (Pixel.getRedInt(pixels[x + y * w]) == 127) {
					floors.add(new Floor(x * 32, y * 32, "top"));
				} else if (Pixel.getBlueInt(pixels[x + y * w]) == 255) {
					walls.add(calculateWall(pixels, x, y, w));
				} else if (Pixel.getGreenInt(pixels[x + y * w]) == 255) {
					player = new Player(x * 32, y * 32);
					floors.add(new Floor(x * 32, y * 32, "floor"));
				} else if (Pixel.getGreenInt(pixels[x + y * w]) == 127) {
					enemies.add(new Enemy(x * 32, y * 32));
					floors.add(new Floor(x * 32, y * 32, "floor"));
				} else if (Pixel.getGreenInt(pixels[x + y * w]) == 100) {
					chests.add(new Chest(x * 32, y * 32));
					floors.add(new Floor(x * 32, y * 32, "floor"));
				}
			}
		}

		for(int i = 0; i < floors.size(); i++) {
			result.getManager().addObject(floors.get(i));
		}
		floors.clear();

		result.getManager().addObject(player);

		for(int i = 0; i < chests.size(); i++) {
			result.getManager().addObject(chests.get(i));
		}
		chests.clear();

		for(int i = 0; i < walls.size(); i++) {
			result.getManager().addObject(walls.get(i));
		}
		walls.clear();

		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).setTarget(player);
			result.getManager().addObject(enemies.get(i));
		}
		enemies.clear();

		image.flush();

		return result;
	}

	private Wall calculateWall(int[] pixels, int x, int y, int w) {
		int topLeft = Pixel.getRedInt(pixels[(x - 1) + (y - 1) * w]);
		int top = Pixel.getRedInt(pixels[(x) + (y - 1) * w]);
		int topRight = Pixel.getRedInt(pixels[(x + 1) + (y - 1) * w]);
		int right = Pixel.getRedInt(pixels[(x + 1) + (y) * w]);
		int bottomRight = Pixel.getRedInt(pixels[(x + 1) + (y + 1) * w]);
		int bottom = Pixel.getRedInt(pixels[(x) + (y + 1) * w]);
		int bottomLeft = Pixel.getRedInt(pixels[(x - 1) + (y + 1) * w]);
		int left = Pixel.getRedInt(pixels[(x - 1) + (y) * w]);

		if((top == 255) && !(right == 255) && !(bottom == 255) && !(left == 255)) {
			return new Wall(x * 32, y * 32, "wall-south");
		} else if(!(top == 255) && (right == 255) && !(bottom == 255) && !(left == 255)) {
			return new Wall(x * 32, y * 32, "wall-west");
		} else if(!(top == 255) && !(right == 255) && (bottom == 255) && !(left == 255)) {
			return new Wall(x * 32, y * 32, "wall-north");
		} else if(!(top == 255) && !(right == 255) && !(bottom == 255) && (left == 255)) {
			return new Wall(x * 32, y * 32, "wall-east");
		} else if(!(top == 255) && !(right == 255) && !(bottom == 255) && !(left == 255)) {
			if(topLeft == 255) {
				return new Wall(x * 32, y * 32, "wall-southeastout");
			} else if(topRight == 255) {
				return new Wall(x * 32, y * 32, "wall-southwestout");
			} else if(bottomRight == 255) {
				return new Wall(x * 32, y * 32, "wall-northwestout");
			} else if(bottomLeft == 255) {
				return new Wall(x * 32, y * 32, "wall-northeastout");
			}
		} else if((top == 255) && (right == 255) && !(bottom == 255) && !(left == 255)) {
			return new Wall(x * 32, y * 32, "wall-southwestin");
		} else if(!(top == 255) && (right == 255) && (bottom == 255) && !(left == 255)) {
			return new Wall(x * 32, y * 32, "wall-northwestin");
		} else if(!(top == 255) && !(right == 255) && (bottom == 255) && (left == 255)) {
			return new Wall(x * 32, y * 32, "wall-northeastin");
		} else if((top == 255) && !(right == 255) && !(bottom == 255) && (left == 255)) {
			return new Wall(x * 32, y * 32, "wall-southeastin");
		}
		return new Wall(x * 32, y * 32, "floor");
	}
}
