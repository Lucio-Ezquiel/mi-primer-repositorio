#Creando una lista se pueden modificar los datos
lista = ["Lucio", "Arena", True, 45]

#Creando una tupla no se pueden modificar los datos
tupla = ("Lucio", "Arena", True, 45)

#Esto es válido
lista[3] = "Hola"

#Esto no es válido
#tupla[3]= "Hola"

#Creando un cojunto set (no se puede llamar a los elementos por su indice)
#No almacena datos duplicados (tip para borrar datos duplicados)
conjunto = {"Lucio", "Arena", True, 45}

#print(conjunto[3]) -> no puede acceder al elemento

#Creando un diccionario (dict) (la estructura es key : value y separamos con comas)
diccionario = {
    "nombre" : "Lucio",
    "apellido" : "Arena",
    "altura" : 1.70 
}

print(diccionario["nombre"])


