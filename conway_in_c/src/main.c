#include <stdio.h>
#include <SDL.h>

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
			while(1) {}
		}
	}

	SDL_Quit();

	return 0;
}
