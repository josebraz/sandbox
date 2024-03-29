from tkinter import *
from random import randint

##########################################
# Configurações do jogo
canvas_width = 600
canvas_height = 400
pixel_size = 20
velocity = 1  # ticks por segundo
snake_color = "red"
food_color = "green"

max_x = int(canvas_width / pixel_size)
max_y = int(canvas_height / pixel_size)


##########################################

# Variáveis do jogo
food_x = -1
food_y = -1
food_generated = False
snake_x = max_x / 2
snake_y = max_y / 2


def draw_pixel(x, y, color="yellow"): #definir a função de desenhar o pixel
    w.create_rectangle(
        x * pixel_size,
        y * pixel_size,
        (x + 1) * pixel_size,
        (y + 1) * pixel_size,
        fill=color)


# Gera uma comida em uma coordenada aleatória e
# desenha ela na tela
def generate_food():
    global food_x, food_y, food_generated
    food_x = randint(0, max_x - 1)
    food_y = randint(0, max_y - 1)
    food_generated = True


def draw_food():
    draw_pixel(food_x, food_y, food_color)


# cria a cobra
def draw_snake():
    draw_pixel(snake_x, snake_y, snake_color)


# verifica se a cobrinha está com a cabela em
# cima de uma comida
def snake_eat():
    global food_generated
    if food_x == snake_x and food_y == snake_y:
        food_generated = False


# verifica se a cobra morreu (bateu na parede ou se comeu)
def snake_die():
    pass


# verifica se a coordenada passada é a mesma de
# alguma parte do corpo da cobrinha
def coord_in_body(x, y):
    pass


def left_key(event):
    global snake_x
    print("left_key")
    snake_x -= 1
    pass


def right_key(event):
    global snake_x
    print("right_key")
    snake_x += 1
    pass


def down_key(event):
    global snake_y
    print("down_key")
    snake_y += 1
    pass


def up_key(event):
    global snake_y
    print("up_key")
    snake_y -= 1
    pass


# Função principal do jogo, fica rodando sempre
# e chamando as outras funções para controlar as ações
def game_loop():
    ## inicio loop do jogo
    w.delete("all")
    print("loop")
    if food_generated == False:
        generate_food()
    draw_food()
    draw_snake()
    snake_eat()
    ## fim loop do jogo
    master.after(int(1000 / velocity), game_loop)


# ---------------------------------------

# Cria a janela canvas do jogo
master = Tk()
master.title("Snake Game")

w = Canvas(master,
           width=canvas_width,
           height=canvas_height)
w.pack()

master.bind("<Left>", left_key)
master.bind("<Right>", right_key)
master.bind("<Up>", up_key)
master.bind("<Down>", down_key)

master.after(int(1000 / velocity), game_loop)
master.mainloop()
