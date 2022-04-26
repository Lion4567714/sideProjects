#ifndef GRID_H
#define GRID_H

#include <stdio.h>
#include <stdbool.h>

#include <SDL.h>

#define GRID_MAX_X_CELLS		(20)
#define GRID_MAX_Y_CELLS		(20)

struct Cell {
	SDL_Rect rect;
	SDL_Color rect_color;

	SDL_Rect border;
	SDL_Color border_color;
} Cell;

struct Grid {
	SDL_Rect rect;
	SDL_Color background_color;

	unsigned int border;
	SDL_Color border_color;

	int x_cells;
	int y_cells;

	unsigned int cells_border;
	SDL_Color cells_border_color;

	cell cells [GRID_MAX_X_CELLS][GRID_MAX_Y_CELLS];
} Grid;

int adjust_grid_size(Grid *grid);
void align_grid_center(Grid *grid, int screen_width, int screen_height);

bool initialize_grid(Grid *grid);
void initialize_grid_cell(Grid *grid, Cell *cell, int i, int j,
	SDL_Color color, SDL_Color, border_color);

void render_grid(Grid *grid, SDL_Renderer *renderer);
void render_grid_cell(Cell *cell, SDL_Renderer *renderer);

#endif // GRID_H
