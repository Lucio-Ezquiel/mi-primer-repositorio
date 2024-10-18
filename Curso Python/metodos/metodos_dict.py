diccionario = {
    "nombre" : "Lucio",
    "apellido" : "Arena",
    "edad" : 20
}

#Devuelve las claves
claves = diccionario.keys()

#Devuelve el valor de la key que le doy (si no encuentra nada el programa continua)
claves = diccionario.get("nombre")

#Elimina todos los elementos
diccionario.clear()

#Eliminando un elemento del diccionario
diccionario.pop("nombre", "edad")

#Obteniendo un elemento dict_items iterando
diccionario_iterable = diccionario.items()

print(diccionario_iterable)