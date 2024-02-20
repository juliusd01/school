"""
Nombre: Julius Döbelt
Matricula: A01760777
Carrera: Computación y Economía
Fecha: 21/10/2022
"""

import pandas as pd
import sidetable as stb
import matplotlib.pyplot as plt
import matplotlib.dates as mdates
from IPython.display import display
from tabulate import tabulate
import PySimpleGUI as sg
import tkinter as tk
from tkinter import simpledialog
import csv



#cambiar la función del print - ahora se haga el print en la ventana GUI|no en el Shell
print = sg.Print

# Funcion que calcula las frecuencias absolutas y frecuencias relativas de una lista de datos.
def frecuencias(nombre, lista_datos, columna):
    print("*" * 70)
    print(nombre.upper( ).center(50, "*"))
    print( "*" * 70 + "\n" * 2)
    
    # 1. Convertir la lista a un DataFrame para poder calcular las frecuencias usando Pandas o sidetable
    df =  pd.DataFrame(lista_datos)
    
    print( df.stb.counts() )
    
    print(" Frecuencias absolutas calculadoas con f_.value_counts( ) ")
    # 2. Calcular y desplegar las frecuencias absolutas con Pandas
    print( df.value_counts() )

    print(" Frecuencias Relativas con f_.value_counts(normalize = True ) ")

    # 3. Calcular y desplegar las frecuencias relativas con Pandas
    print( df.value_counts (normalize = True ))
    
    # 4. Calcular las frecuencias absolutas y frecuencia Relativas con sidetable
    print("**** Tabla de frecuencias Absolutas, Relativas y Acumulados con f_.stb.freq[0] ****")
    df = df.stb.freq([columna])
    
    print( df )
    
    # 5. Sacar las columnas de las frecuencias absolutas, relativas y acumuladas desde el dataframe
    valores = list(df[columna])
    f_absolutas = df["count"]
    f_relativas = df["percent"]
    f_abs_acum = df["cumulative_count"]
    f_rel_acum = df["cumulative_percent"]
  
      
    # 6. Desplegar las frecuencias absolutas y relativas añadiendo el formato de %
    print("**** Tabla de frecuencias Absolutas, Relativas y Acumulados ****")
    print(f'{"valor":<20}{"frec absoluta":<10}{"frec relativa":^15}{"frec acum":^10}{"frec rel acum":>10}')
    for valor, count, percent, cum_count, cum_perc in zip(valores, f_absolutas, f_relativas, f_abs_acum, f_rel_acum):
        print(f"{valor:<25}{count:<10}{round(percent,2):10.5}%{cum_count:>10}{round(cum_perc,2):>10.5}%")
              
def estadisticas( ):
    file = pd.read_excel("Data.xlsx")

    # Converter la base de datos en un DataFrame
    df = pd.DataFrame(file)
    print (df, "\n")
    
    print("Cuáles sectores hay en la base de datos?\n")
    tdf = df.transpose()
    tdf = tdf.drop("Periodo")
    tdf = tdf.drop("Total")
    index = tdf.index
    print(list(index), "\n")
    
    print("Qué es la contribución promedio de los sectores a los exportaciones de Nuevo León?\n\n")
    print(df.mean(numeric_only = True), "\n")
    
    print("Qué es el sector que contribuye más/menor a los exportaciones de Nuevo León?\n\n")
    print("El sector más importante es el de  Equipo de transporte con 987.83 millones de Dólares por promedio.","\nEl sector con poco contribución es el de Muebles con solo 5.58 millones de Dólares por promedio.")
  
    
    
def estadisticasFormulario():
    
    file = pd.read_csv("Formulario.csv", encoding='latin-1')
    #Desplegar el DataFrame
    print(file)

    frecuencias("Frecuencia por estado civil", file["Casado"], "Casado")
    frecuencias("Frecuencias por Días de Trabajo", file["DiasDeTrabajo"], "DiasDeTrabajo")
    frecuencias("Frecuencias por Salario", file["SalarioMensual"], "SalarioMensual")
    frecuencias("Frecuencia por Sector", file["Sector"], "Sector")
    frecuencias("Frecuencia por número de dependientes", file["Dependientes"], "Dependientes")
    frecuencias("Frecuencias por Horas de Trabajo", file["HorasDeTrabajo"], "HorasDeTrabajo")

def plot():
    # La base de datos
    file = pd.read_excel("Data.xlsx")

    # Converter la base de datos en un DataFrame
    df = pd.DataFrame(file)
    # Desplegar DataFrame
    display(df)

    x = df.loc[:, "Periodo"]
    y = df.loc[:, "Total"]

    # Exportaciones en los últimos anos
    plt.plot(x, y)
    plt.gca().xaxis.set_major_locator(mdates.DayLocator(interval=12))
    plt.title("Exportaciones de Nuevo León")
    plt.xlabel("Tiempo")
    plt.ylabel("Total (millones de dólares)")
    plt.figure(1)
    plt.show()


#-------------------------------------FRAME_PASSWORD-----------------------------------------------------------------------------------
def ventana_password():
    imagen = sg.Image(filename = "hacker_PNG4.png", key = "IMG_1")
    
    boton1 = sg.Button("Validar Contrasena", key = "BTN_VALIDAR", font=("Chalkboard", 20))
    texto1 = sg.Text("Situación Problema de nuestro curso", font=("Chalkboard", 20))
    texto2 = sg.Text("Taclea la contrasena", font=("Chalkboard", 20))
    password = sg.Input(key = "INPUT_PASSWORD", password_char = "$", font=("Chalkboard", 20))
    
    sg.theme("Black")
    layout = [	[texto1],
                [imagen],
                [texto2, password],
                [boton1]
              ]
    
    # Crear el frame
    frame_password = sg.Frame("Validar Ingreso al Sistema", layout, key = "FRAME_PASSWORD", visible = True, font=("Chalkboard", 20))

    # Retornar el Frame
    return frame_password




# Calcular la magnitud de los sectores
def porcentajeDeMes():
    file = pd.read_excel("Data.xlsx")

    # Converter la base de datos en un DataFrame
    df = pd.DataFrame(file)
    newDF = df.transpose()
    newDF.columns = newDF.iloc[0]
    newDF = newDF[1:]
    #Borrar la fila Total, porque no la necesito para mis calculaciones
    newDF = newDF.drop("Total")
    firstColumn = newDF.iloc[:, 0]
    
    ROOT = tk.Tk()
    ROOT.withdraw()
    numero = simpledialog.askinteger(title="Ingresar Mes", prompt="Ingresar un número de 1 hasta 111 para ver los exportaciones de un mes (El número representa el mes)  \n1 es Enero de 2013		61-72 es 2018\n2 es Febrero de 2013		73-84 es 2019\n13-24 es 2014			85-96 es 2020\n25-36 es 2015			97-108 es 2021\n37-48 es 2016			109-111 es 2022\n49-60 es 2017")
    columna = newDF.iloc[:, numero]
    print(columna)
    index = newDF.index
    mylabels = list(index)
    plt.pie(columna, labels = mylabels)
    plt.title("Exportaciones por sector")
    plt.show()

def formulario():
    tema = sg.Text("Formulario Economía")
    
    text_ingreso = sg.Text("Cuántos días de la semana trabajas?", font = ("chalkboard", 16))
    numero = sg.Spin(tuple(range(0,8)), key = "INPUT_DEPENDIENTES", font = ("chalkboard", 16))
    print("\n\n")
    
    texto_edo = sg.Text("En que sector trabajas", font = ("chalkboard", 16))
   #Tupla para las opciones del Combo
    opciones_edos = ('Alimentos, bebidas y tabaco', 'Textil y calzado', 'Productos químicos', 'Plástico y hule ',
                     'Minerales no metálicos', 'Metálicas básicas ', 'Productos metálicos ', 'Papel y editorial',
                     'Maquinaria y equipo ', 'Equipo electrónico', 'Equipo de generación eléctrica', 'Electrodomésticos',
                     'Equipo de transporte ', 'Muebles', 'Otros')
    estados = sg.Combo(opciones_edos, key = 'INPUT_ESTADO', font = ('chalkboard', 16),  text_color = "#369B98")
    print("\n\n")
    
    texto3 = sg.Text("Qué es tu salario mensual?", font = ("chalkboard", 16))
    opciones3 = ("Menos de $5000", "Entre $5000 y $10000", "Entre $10000 y $20000", "Más que $20000")
    respuesta3 = sg.Combo(opciones3, key = "INPUT_3", font = ("chalkboard", 16))
    print("\n\n")
    
    texto4 = sg.Text("Cuántos horas trabajas en la semana?", font = ("chalkboard", 16))
    respuesta4 = sg.Spin(tuple(range(0,41)), key = "INPUT_4", font = ("chalkboard", 16))
    print("\n\n")
    
    texto6 = sg.Text("Cuántos dependientes tienes?", font = ("chalkboard", 16))
    respuesta6 = sg.Spin(tuple(range(0,10)), key = "INPUT_6", font = ("chalkboard", 16))
    
    texto5 = sg.Text("Estás casado?", font = ("chalkboard", 16))
    respuesta5 = sg.Checkbox("???", default = False, key = "INPUT_5", font = ("chalkboard", 16))
    
    
    
    
    boton_grabar = sg.Button("Grabar la informacion", key = "BTN_GRABAR", font = ("chalkboard", 16))
    boton_regresar = sg.Button("Regresar", key="BTN_REGRESAR", font = ("chalkboard", 16))
    
    
    
    layout = [ [tema],
               [texto_edo, estados],
               [text_ingreso, numero],
               [texto3, respuesta3],
               [texto4, respuesta4],
               [texto6, respuesta6],
               [texto5, respuesta5],
               [boton_grabar, boton_regresar]]
    
    #crear el frame
    frame_formulario = sg.Frame("Formulario", layout, key = "FRAME_FORMULARIO", visible = False)
    
    #retoner el frame
    return frame_formulario


def menu_opciones():
    boton_opciones = sg.Button("Analisis de Base de Datos", key = "BTN_FRECUENCIAS", image_filename = "MCE-411.png", font = ("chalkboard", 20))
    boton_freq_formulario = sg.Button("Visualizaciones", key = "BTN_FREQ_FRECUENCIAS", image_filename = "ene.png", font = ("chalkboard", 20))
    boton_formulario = sg.Button("Formulario", key = "BTN_FORMULARIO", image_filename = 'Download.png', font = ("chalkboard", 20))
    boton_y = sg.Button("Estadisticas Formulario", key = "BTN_Y", image_filename = "malo.png", font = ("chalkboard", 20))


    layout = [[boton_opciones, boton_freq_formulario],
              [boton_formulario, boton_y]
              ]
    
    frame_opciones = sg.Frame("Menu de Opciones", layout, key = "FRAME_OPCIONES", visible=False)
    
    return frame_opciones

#------------------------MAIN()---------------------------------------------------------------------------------------------------------------
def main():
    sg.theme("DarkBrown2")
    #Llamar a la función
    frame_password = ventana_password()
    frame_formulario = formulario()
    frame_opciones = menu_opciones()
    
    layout = [[frame_formulario, frame_opciones, frame_password]]
    

    window = sg.Window("Sr. Julius Döbelt", layout, size = (1200, 1200), resizable = True)

    event, values = window.read()

    while event!= sg.WIN_CLOSED:
        if event == "BTN_REGRESAR":
            window["FRAME_FORMULARIO"].update(visible = False)
            window["FRAME_OPCIONES"].update(visible = True)
            
        elif event == "BTN_FREQ_FRECUENCIAS":
            porcentajeDeMes()
        
        elif event == "BTN_FRECUENCIAS":
            estadisticas()
            plot()
    
        elif event == "BTN_Y":
            estadisticasFormulario()
             
         
        elif event == "BTN_FORMULARIO":
            window["FRAME_FORMULARIO"].update(visible = True)
            window["FRAME_OPCIONES"].update(visible = False)
        
        elif event == "BTN_GRABAR":
            #a - si no existe el archivo se crea, si ya existe anade la info al final del archivo
            with open("Formulario.csv", "a", newline="") as csvfile:
                writer = csv.writer(csvfile)
                sector = values["INPUT_ESTADO"]
                dias = values["INPUT_DEPENDIENTES"]
                salario = values["INPUT_3"]
                horas = values["INPUT_4"]
                dependientes = values["INPUT_6"]
                marido = values["INPUT_5"]
                
                #con los otros también!!!!
                
                #Para qué la quiero sacar?
                linea = [str(sector), str(dias), salario, str(horas), str(dependientes), marido]
                writer.writerow(linea)
                
                
            tk.messagebox.showinfo(title="GRACIAS!!!", message="Gracias para tu respuesta! Ayudas el estado Nuevo León para mejorar sus estadisticas :)")
    
        elif event == "OK":
            sg.popup("Oprime el botón de Validar Contrasena")
        
        elif event == "Cancel":
            sg.popup("Gracias por tu vista")
            break
        
        elif event == "BTN_VALIDAR":
            #sacar contenido del input
            psw = values["INPUT_PASSWORD"]
            
            if psw == "DTB":
                sg.popup("Contrasena correcta", "Puedes ingresar al sistema")
                window["FRAME_PASSWORD"].update(visible = False)
                window["FRAME_OPCIONES"].update(visible = True)
            else:
                sg.popup("Contrasena falsa", "Intentalo de nuevo")
        
        event, values = window.read()
            
        
    window.close

#llamda a la función main()
main()