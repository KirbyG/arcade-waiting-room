import pygame
from pygame.locals import Rect, K_RETURN, K_LEFT, K_RIGHT, K_DOWN, K_UP
import time
import random
import asyncio

BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
PAPAYAWHIP = (255,150,233)
MAHOBURGUNDY = (219, 19, 19)

segment_size = 15
gap = 2

class Dot():
    def __init__(self, x, y):
        self.x = x
        self.y = y

width = 682
height = 699
size = [width, height]

pygame.init()
screen = pygame.display.set_mode(size)
background = pygame.Surface(screen.get_size())

myfont = pygame.font.SysFont("monospace", 15)

def dropBox(cp, color):
    actualX = gap + cp.x * (gap + segment_size)
    actualY = gap + cp.y * (gap + segment_size)
    pygame.draw.rect(
        screen,
        color,
        Rect((actualX, actualY),(segment_size,segment_size))
    )
    pygame.display.update()

def otherYummy(test):
    if test.x > 39 or test.x < 0 or test.y > 39 or test.y < 0:
        return False
    return True

def yummy(test, dots):
    for i in range(0, len(dots) - 1):
        if (dots[i].x == test.x and dots[i].y == test.y):
            return False
    return True

def bait(dots):
    ret = Dot(random.randint(0, 39), random.randint(0, 39))
    while (not yummy(ret, dots)):
        ret = Dot(random.randint(0, 39), random.randint(0, 39))
    return ret

def lose():
    pygame.event.get()
    keys=pygame.key.get_pressed()
    while not keys[K_RETURN]:
        pygame.event.get()
        keys=pygame.key.get_pressed()
        myfont = pygame.font.SysFont("monospace", 50)
        label = myfont.render('you lose', 1, MAHOBURGUNDY)
        screen.blit(label, (200, 300))
        againFont = pygame.font.SysFont("monospace", 25)
        label = againFont.render('press enter to play again', 1, MAHOBURGUNDY)
        screen.blit(label, (130, 360))
        pygame.display.update()

async def main():

    while True:
        restart = True

        d_x = segment_size + gap
        d_y = 0

        allspriteslist = pygame.sprite.Group()

        dots = []
        dots.append(Dot(15, 15))
        left = False
        right = False
        up = False
        down = False
        vel = Dot(0,0)

        food = bait(dots)
        dropBox(food, PAPAYAWHIP)
        counter = 0
        temp = Dot(0,0)
        while restart:
            ct = time.time()
            while (time.time() - ct < 0.07):
                pygame.event.get()
                keys=pygame.key.get_pressed()
                if keys[K_LEFT] and not vel.x == 1:
                    temp.x = -1
                    temp.y = 0
                if keys[K_RIGHT] and not vel.x == -1:
                    temp.x = 1
                    temp.y = 0
                    right = True
                if keys[K_UP] and not vel.y == 1:
                    temp.x = 0
                    temp.y = -1
                    up = True
                if keys[K_DOWN] and not vel.y == -1:
                    temp.x = 0
                    temp.y = 1
                    down = True
            vel.x = temp.x
            vel.y = temp.y    

            square = Dot(
                dots[len(dots)-1].x + vel.x,
                dots[len(dots)-1].y + vel.y
            )
            if not (vel.x == 0 and vel.y == 0):
                if not yummy(square, dots):
                    dropBox(square, MAHOBURGUNDY)
                    lose()
                    restart = False
                if not otherYummy(square):
                    dropBox(dots[len(dots)-1], MAHOBURGUNDY)
                    lose()
                    restart = False
            dots.append(Dot(
                dots[len(dots)-1].x + vel.x,
                dots[len(dots)-1].y + vel.y)
            )
            dropBox(dots[len(dots)-1], WHITE)
            if counter == 0:
                dropBox(dots[0], BLACK)
                del dots[0]
            else:
                counter -= 1
            if dots[len(dots)-1].x == food.x and dots[len(dots)-1].y == food.y:
                food = bait(dots)
                dropBox(food, PAPAYAWHIP)
                counter = 5
            pygame.draw.rect(screen, WHITE, Rect((0, 682), (682, 17)))
            label = myfont.render('scorePlayer = ' + str(len(dots)), 1, BLACK)
            screen.blit(label, (0, 682))
            await asyncio.sleep(0)
        await asyncio.sleep(0)

asyncio.run(main())