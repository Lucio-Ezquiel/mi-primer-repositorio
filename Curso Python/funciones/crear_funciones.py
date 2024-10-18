#Creando una funcion simple
#def saludar():
  #print("Hola como estas")
    
#Creando una funcion que tenga parametros
def saludar(nombre):
    print(f"Hola {nombre} como estas") 
       
saludar("Lucio")

#Creando una funcion que retorne un valor
def crear_contraseña_random(num):
    listado_de_caracteres = "dajsdhasd"
    num_entero = str(num)
    num = int(num_entero[0])
    c1 = num - 2
    c2 = num
    c3 = num + 5
    contraseña = f"{listado_de_caracteres[c1]}{listado_de_caracteres[c2]}{listado_de_caracteres[c3]}"
    #Retornar valores me permite guardar el dato fuera de la funcion en otra variable
    return contraseña

password = crear_contraseña_random(10)
print(password)