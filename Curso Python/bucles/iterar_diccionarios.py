diccionario = {
    "nombre" : "dato1",
    "edad" : 30,
    "altura" : 30
}

#Recorriendo diccionario para obtener las claves
for key in diccionario:
    print(key)
    
#Recorriendo un diccionario con items para obtener la clave y el valor
for datos in diccionario.items():
    key = datos[0]
    value = datos[1]
    print(F"{key} {value}")
