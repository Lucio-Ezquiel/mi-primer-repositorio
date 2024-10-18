animales = ["gato","perro","elefante","mono"]
numeros = [2,10,9,8]

#Recorriendo la lista animales
for animal in animales:
    print(animal)

#Recorriendo la lista numeros
for numero in numeros:
    resultado = numero * 10
    print(resultado)
    
#Si defino range con el primer parametro indico donde arranca y con el segundo donde termina
#Si solo le paso un parametro va a ir de 1 hasta ese parametro
for num in range(5,10):
    print(num)

#Forma no optima de recorrer una lista por su indice 
for num in range(len(numeros)):
    print(numeros[num])

#Si queremos recorrer una tupla con su indice
for num in enumerate(numeros):
    print(num)
    
#Usando el for/else
for numero in numeros:
    print(numero)
else:
    print("El bucle terminó")
    
#Todo lo anterior funciona exactamente para tuplas