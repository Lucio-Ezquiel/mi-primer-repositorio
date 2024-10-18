#Creando una lista con list
lista = list(("hola","como","estas"))

#Devuelve la cantidad de elementos de la lista
cantidad_elementos_lista = len(lista)

#Agrega elementos a la lista
lista.append("wow")

#Agregando un elemento a la lista con un indice especifico
lista.insert(2, "wow")

#Agregando varios elementos a la lista
lista.extend((False, 2023))

#Eliminar un elemento
#Con -1 se elimina el ultimo
lista.pop(0)

#Remueve un elemento de la lista por su valor
lista.remove("estas")

#Ordena de menor a mayor true, false y numeros (si usamos el parametro reverse=True lo ordena en reversa)
lista.sort()

#Invierte los elementos de una lista
lista.reverse()

print(lista)

#Eliminar todos los elementos
lista.clear()

#Con dir me dice los metodos que puedo aplicar a esa tupla
#print(dir(('hola', 5)))