from tkinter import *
from random import randint

##########################################
# Configurações do jogo
canvas_width = 600
canvas_height = 400
pixel_size = 20
velocity = 1 # ticks por segundo
snake_color = "red"
food_color = "green"

max_x = canvas_width / pixel_size
max_y = canvas_height / pixel_size
##########################################

def drawPixel(x, y, color = "yellow"):
    w.create_rectangle(
        x * pixel_size, 
        y * pixel_size, 
        (x+1) * pixel_size, 
        (y+1) * pixel_size, 
        fill=color)

# Gera uma comida em uma coordenada aleatória e
# desenha ela na tela
def generate_food():
    pass

# verifica se a cobrinha está com a cabela em
# cima de uma comida
def snake_eat():
    pass

# verifica se a cobra morreu (bateu na parede ou se comeu)
def snake_die():
    pass

# verifica se a coordenada passada é a mesma de
# alguma parte do corpo da cobrinha
def coord_in_body(x, y):
    pass

def left_key(event):
    print("left_key")
    pass

def right_key(event):
    print("right_key")
    pass
    
def down_key(event):
    print("down_key")
    pass
    
def up_key(event):
    print("up_key")
    pass

# Função principal do jogo, fica rodando sempre
# e chamando as outras funções para controlar as ações
def game_loop():
    ## inicio loop do jogo
    print("loop")
    ## fim loop do jogo
    master.after(int(1000/velocity), game_loop);

# ---------------------------------------

# Cria a janela canvas do jogo
master = Tk()
master.title("Snake Game")

w = Canvas(master, 
           width=canvas_width,
           height=canvas_height)
w.pack()


master.bind("<Left>", left_key);
master.bind("<Right>", right_key);
master.bind("<Up>", up_key);
master.bind("<Down>", down_key);

master.after(int(1000/velocity), game_loop);
master.mainloop()
