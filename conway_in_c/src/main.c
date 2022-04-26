#include <stdio.h>
#include <stdbool.h>
#include <SDL.h>

#define MAX(X, Y) (((X) > (Y)) ? (X) : (Y))
#define MIN(X, Y) (((X) < (Y)) ? (X) : (Y))

#define SCREEN_WIDTH	800
#define SCREEN_HEIGHT	600

int main() {
	printf("Initializing SDL...\n");
	if (SDL_Init(SDL_INIT_VIDEO) < 0) {
		printf("SDL could not be initialized!\n");
		return -1;
	}

	printf("Initializing window...\n");
	SDL_Window *window = SDL_CreateWindow("Conway's Game of Life",
		SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED, SCREEN_WIDTH,
		SCREEN_HEIGHT, SDL_WINDOW_SHOWN);
	if (!window) {
		printf("Window could not be initalized!\n");
	}
	else {
		printf("Creating renderer...\n");
		SDL_Renderer *renderer = SDL_CreateRenderer(window, -1,
			SDL_RENDERER_ACCELERATED);
		if (!renderer) {
			printf("Could not create renderer!\n");
		}
		else {
			printf("Initializing graphics...\n");

			SDL_Rect squareRect;
			squareRect.w = 100;
			squareRect.h = 100;
			squareRect.x = 100;
			squareRect.y = 100;

			bool quit = false;

			while (!quit) {
				SDL_Event e;
				SDL_WaitEvent(&e);

				if (e.type == SDL_QUIT) {
					quit = true;
				}

				SDL_SetRenderDrawColor(renderer, 0xFF, 0xFF, 0xFF, 0xFF);
				SDL_RenderClear(renderer);
				SDL_SetRenderDrawColor(renderer, 0xFF, 0x00, 0x00, 0xFF);
				SDL_RenderFillRect(renderer, &squareRect);
				SDL_RenderPresent(renderer);
			}

			SDL_DestroyRenderer(renderer);
		}

		SDL_DestroyWindow(window);
	}

	SDL_Quit();

	return 0;
}
